package com.dada.business.message.spi.impl;

import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.channel.SmsMessageChannel;
import com.dada.business.message.spi.SmsMessageProviderRouter;

/**
 * 
 * Title: KeyWordSmsMessageProviderRouter Description: 短信运营商路由根据关键字路由
 * 
 * @author ssc
 * @date 2016年4月26日 上午11:50:27
 */
public class KeyWordSmsMessageProviderRouter implements
		SmsMessageProviderRouter {

	@Override
	public SmsMessageChannel routeMessageProvider(MessageModel messageModel) {
		// TODO Auto-generated method stub
		return null;
	}

}
