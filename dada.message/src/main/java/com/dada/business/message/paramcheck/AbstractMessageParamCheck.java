package com.dada.business.message.paramcheck;

import com.dada.business.message.api.model.MessageSendParam;

/**
 * 
 * Title: AbstractMessageParamCheck Description: 消息参数校验抽象类
 * 
 * @author ssc
 * @date 2016年4月25日 下午4:03:23
 */
public abstract class AbstractMessageParamCheck {

	public abstract void check(MessageSendParam messageSendParam);

}
