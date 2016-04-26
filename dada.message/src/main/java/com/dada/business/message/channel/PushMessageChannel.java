package com.dada.business.message.channel;

import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.spi.PushMessageProviderRouter;

/**
 * 
 * Title: PushMessageChannel Description: 消息通道推送抽象类
 * 
 * @author ssc
 * @date 2016年4月20日 下午6:19:29
 */
public class PushMessageChannel extends AbstractMessageChannel {

	private PushMessageProviderRouter pushMessageProviderRouter;

	public void setPushMessageProviderRouter(
			PushMessageProviderRouter pushMessageProviderRouter) {
		this.pushMessageProviderRouter = pushMessageProviderRouter;
	}

	@Override
	public void sendMessageModel(MessageModel messageModel) {
		PushMessageChannel pushMessageChannel = pushMessageProviderRouter
				.routeMessageProvider(messageModel);
		pushMessageChannel.sendMessageModel(messageModel);
	}

}
