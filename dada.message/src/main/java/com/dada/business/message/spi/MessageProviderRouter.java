package com.dada.business.message.spi;

import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.channel.AbstractMessageChannel;

/**
 * 
 * Title: MessageProviderRouter Description: 消息运营商路由接口
 * 
 * @author ssc
 * @date 2016年4月20日 下午6:09:23
 */
public interface MessageProviderRouter {

	public abstract AbstractMessageChannel routeMessageProvider(MessageModel messageModel);

}
