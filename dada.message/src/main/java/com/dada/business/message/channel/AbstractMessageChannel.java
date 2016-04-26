package com.dada.business.message.channel;

import com.dada.business.message.api.model.MessageModel;

/**
 * 
 * Title: AbstractMessageChannel Description: 消息通道抽象类
 * 
 * @author ssc
 * @date 2016年4月25日 下午5:39:11
 */
public abstract class AbstractMessageChannel {

	public abstract void sendMessageModel(MessageModel messageModel);

}
