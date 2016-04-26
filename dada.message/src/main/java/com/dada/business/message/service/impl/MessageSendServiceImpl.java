package com.dada.business.message.service.impl;

import static com.dada.business.message.util.MessageCode.MESSAGE_TEMPLATE_NOTFOUND;
import static com.dada.business.message.util.MessageCode.MESSAGE_CHANNEL_NOTFOUND;
import static com.dada.business.message.api.model.MessageModel.STATUS_PROCESSING;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.dada.business.common.api.model.ApiException;
import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.api.model.MessageSendParam;
import com.dada.business.message.api.model.MessageStatModel;
import com.dada.business.message.api.model.MessageTemplateModel;
import com.dada.business.message.api.service.MessageService;
import com.dada.business.message.api.service.MessageStatService;
import com.dada.business.message.api.service.MessageTemplateService;
import com.dada.business.message.api.service.SyncMessageSendService;
import com.dada.business.message.channel.AbstractMessageChannel;
import com.dada.business.message.spi.MessageChannelRouter;
import com.dada.business.message.spi.MessageContentMerger;
import com.dada.business.message.spi.MessageParamChecker;

/**
 * 
 * Title: MessageSendServiceImpl Description: 消息发送服务接口实现
 * 
 * @author ssc
 * @date 2016年4月20日 上午11:46:22
 */
@Service("messageSendServiceImpl")
public class MessageSendServiceImpl implements SyncMessageSendService {

	@Resource
	private MessageParamChecker messageParamChecker;

	@Resource
	private MessageTemplateService messageTemplateService;

	@Resource
	private MessageContentMerger messageContentMerger;

	@Resource
	private MessageChannelRouter messageChannelRouter;

	@Resource
	private MessageService messageService;

	@Resource
	private MessageStatService messageStatService;

	public void setMessageParamChecker(MessageParamChecker messageParamChecker) {
		this.messageParamChecker = messageParamChecker;
	}

	public void setMessageTemplateService(
			MessageTemplateService messageTemplateService) {
		this.messageTemplateService = messageTemplateService;
	}

	public void setMessageContentMerger(
			MessageContentMerger messageContentMerger) {
		this.messageContentMerger = messageContentMerger;
	}

	public void setMessageChannelRouter(
			MessageChannelRouter messageChannelRouter) {
		this.messageChannelRouter = messageChannelRouter;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setMessageStatService(MessageStatService messageStatService) {
		this.messageStatService = messageStatService;
	}

	@Override
	public void sendMessage(MessageSendParam messageSendParam) {
		// step 1: messageSendParam quickspot
		messageParamChecker.quickspotMessageParam(messageSendParam);

		// step 2: template merge
		MessageTemplateModel messageTemplateModel = messageTemplateService
				.getMessageTemplateByName(messageSendParam.getTemplateName());
		if (messageTemplateModel == null) {
			throw new ApiException(MESSAGE_TEMPLATE_NOTFOUND.getApiCode(),
					MESSAGE_TEMPLATE_NOTFOUND.getApiMessage(messageSendParam
							.getTemplateName()));
		}
		String content = messageContentMerger.mergeMessageContent(
				messageTemplateModel.getContent(),
				messageSendParam.getAttributes());
		String url = (messageTemplateModel.getUrl() != null) ? messageContentMerger
				.mergeMessageContent(messageTemplateModel.getUrl(),
						messageSendParam.getAttributes()) : null;

		// step 3: route select
		AbstractMessageChannel abstractMessageChannel = messageChannelRouter
				.routeMessageChannel(messageSendParam);
		if (abstractMessageChannel == null) {
			throw new ApiException(MESSAGE_CHANNEL_NOTFOUND.getApiCode(),
					MESSAGE_CHANNEL_NOTFOUND.getApiMessage(messageSendParam
							.getChannel()));
		}

		// step 4: build messageModel
		MessageModel messageModel = buildMessageModel(messageSendParam);
		buildMessageModel(messageModel, messageTemplateModel, url, content);

		// step 5: add messageModel to db
		messageService.addMessage(messageModel);

		// step 6: provider send messageModel
		abstractMessageChannel.sendMessageModel(messageModel);

		// step 7: update messageModel to db
		messageService.updateMessage(messageModel);

		// step 8: insert messageStatModel to db
		MessageStatModel messageStatModel = buildMessageStatModel(messageModel);
		if (messageStatModel != null) {
			messageStatService.addMessageStat(messageStatModel);
		}
	}

	private MessageModel buildMessageModel(MessageSendParam messageSenderParam) {
		MessageModel messageModel = new MessageModel();
		messageModel.setMode(messageSenderParam.getMode());
		messageModel.setChannel(messageSenderParam.getChannel());
		messageModel.setType(messageSenderParam.getType());
		messageModel.setOrigin(messageSenderParam.getOrigin());
		messageModel.setDestination(messageSenderParam.getDestination());
		messageModel.setTrack(RandomStringUtils.randomAlphanumeric(8));
		messageModel.setValidDate(messageSenderParam.getValidDate());
		messageModel.setInvalidDate(messageSenderParam.getInvalidDate());
		messageModel.setApplication(messageSenderParam.getApplication());
		messageModel.setExtras(messageSenderParam.getExtras());
		messageModel.setStatus(STATUS_PROCESSING);
		return messageModel;
	}

	private void buildMessageModel(MessageModel messageModel,
			MessageTemplateModel messageTemplateModel, String url,
			String content) {
		messageModel.setTemplateId(messageTemplateModel.getId());
		messageModel.setTemplateName(messageTemplateModel.getName());
		messageModel.setTitle(messageTemplateModel.getTitle());
		messageModel.setContent(content);
		messageModel.setUrl(url);
	}

	private MessageStatModel buildMessageStatModel(MessageModel messageModel) {
		return new MessageStatModel();
	}

	@Override
	public void sendMessages(List<MessageSendParam> messageSendParams) {
		// TODO Auto-generated method stub
	}

}
