package com.dada.business.message.spi;

import com.dada.business.message.api.model.MessageSendParam;

/**
 * 
 * Title: MessageParamChecker Description: 消息参数校验接口
 * 
 * @author ssc
 * @date 2016年4月19日 下午6:07:29
 */
public interface MessageParamChecker {

	public abstract void quickspotMessageParam(MessageSendParam messageSendParam);

}
