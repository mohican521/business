package com.dada.business.message.spi.impl;

import java.util.Map;

import com.dada.business.message.api.model.MessageSendParam;
import com.dada.business.message.channel.AbstractMessageChannel;
import com.dada.business.message.spi.MessageChannelRouter;

/**
 * 
 * Title: SimpleMessageChannelRouter Description: 基础实现消息通道路由
 * 
 * @author ssc
 * @date 2016年4月20日 下午5:23:23
 */
public class SimpleMessageChannelRouter implements MessageChannelRouter {

	private Map<Integer, AbstractMessageChannel> channels;

	public void setChannels(Map<Integer, AbstractMessageChannel> channels) {
		this.channels = channels;
	}

	@Override
	public AbstractMessageChannel routeMessageChannel(
			MessageSendParam messageSendParam) {
		if (messageSendParam == null)
			throw new IllegalArgumentException("messageSendParam is null.");
		return channels.get(messageSendParam.getChannel());
	}

}
