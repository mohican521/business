package com.dada.business.message.spi;

import com.dada.business.message.api.model.MessageSendParam;
import com.dada.business.message.channel.AbstractMessageChannel;

/**
 * 
 * Title: MessageChannelRouter Description: 消息通道路由接口
 * 
 * @author ssc
 * @date 2016年4月20日 下午4:56:52
 */
public interface MessageChannelRouter {

	public abstract AbstractMessageChannel routeMessageChannel(MessageSendParam messageSendParam);

}
