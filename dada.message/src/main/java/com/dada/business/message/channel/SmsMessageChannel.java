package com.dada.business.message.channel;

import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.spi.SmsMessageProviderRouter;

/**
 * 
 * Title: SmsMessageChannel Description: 消息通道短信抽象类
 * 
 * @author ssc
 * @date 2016年4月20日 下午6:18:00
 */
public class SmsMessageChannel extends AbstractMessageChannel {

	private SmsMessageProviderRouter smsMessageProviderRouter;

	public void setSmsMessageProviderRouter(
			SmsMessageProviderRouter smsMessageProviderRouter) {
		this.smsMessageProviderRouter = smsMessageProviderRouter;
	}

	@Override
	public void sendMessageModel(MessageModel messageModel) {
		SmsMessageChannel smsMessageChannel = smsMessageProviderRouter
				.routeMessageProvider(messageModel);
		smsMessageChannel.sendMessageModel(messageModel);
	}

}
