package com.dada.business.message.spi;

import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.channel.SmsMessageChannel;

/**
 * 
 * Title: SmsMessageProviderRouter Description: 短信运营商路由接口
 * 
 * @author ssc
 * @date 2016年4月25日 下午7:11:32
 */
public interface SmsMessageProviderRouter extends MessageProviderRouter {

	public abstract SmsMessageChannel routeMessageProvider(MessageModel messageModel);

}
