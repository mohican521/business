package com.dada.business.message.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dada.business.common.api.util.PageList;
import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.api.model.MessageQuery;
import com.dada.business.message.api.service.MessageService;
import com.dada.business.message.dao.mapper.MessageMapper;

/**
 * 
 * Title: MessageServiceImpl Description: 消息实体服务接口实现
 * 
 * @author ssc
 * @date 2016年4月19日 下午2:42:02
 */
@Service("messageServiceImpl")
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageMapper messageMapper;

	@Override
	public void addMessage(MessageModel messageModel) {
		messageMapper.insertMessage(messageModel);
	}

	@Override
	public PageList<MessageModel> queryMessages(MessageQuery messageQuery) {
		List<MessageModel> data = messageMapper.queryMessages(messageQuery);
		if (null == data) {
			return null;
		}
		PageList<MessageModel> result = new PageList<MessageModel>(
				messageQuery.getItemsPerPage(),
				messageMapper.queryMessagesByDestinationCount(messageQuery),
				messageQuery.getPage());
		result.setData(data);
		return result;
	}

	@Override
	public PageList<MessageModel> queryMessagesByDestination(
			MessageQuery messageQuery) {
		List<MessageModel> data = messageMapper
				.queryMessagesByDestination(messageQuery);
		if (null == data) {
			return null;
		}
		PageList<MessageModel> result = new PageList<MessageModel>(
				messageQuery.getItemsPerPage(),
				messageMapper.queryMessagesByDestinationCount(messageQuery),
				messageQuery.getPage());
		result.setData(data);
		return result;
	}

	@Override
	public MessageModel getMessageByTrack(String track) {
		return messageMapper.getMessageByTrack(track);
	}

	@Override
	public List<MessageModel> getMessagesByChannelAndDate(Integer channel,
			Date start, Date end) {
		return messageMapper.getMessagesByChannelAndDate(channel, start, end);
	}

	@Override
	public void updateMessage(MessageModel messageModel) {
		messageMapper.updateMessage(messageModel);
	}

}
