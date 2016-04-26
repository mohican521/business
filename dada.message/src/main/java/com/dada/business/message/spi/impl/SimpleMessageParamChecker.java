package com.dada.business.message.spi.impl;

import java.util.List;

import com.dada.business.message.api.model.MessageSendParam;
import com.dada.business.message.paramcheck.AbstractMessageParamCheck;
import com.dada.business.message.spi.MessageParamChecker;

/**
 * 
 * Title: SimpleMessageParamChecker Description: 基础实现消息参数校验
 * 
 * @author ssc
 * @date 2016年4月20日 下午1:34:59
 */
public class SimpleMessageParamChecker implements MessageParamChecker {

	private List<AbstractMessageParamCheck> abstractMessageParamChecks;

	public void setAbstractMessageParamChecks(
			List<AbstractMessageParamCheck> abstractMessageParamChecks) {
		this.abstractMessageParamChecks = abstractMessageParamChecks;
	}

	@Override
	public void quickspotMessageParam(MessageSendParam messageSendParam) {
		if (messageSendParam == null)
			throw new IllegalArgumentException("messageSendParam is null.");
		for (AbstractMessageParamCheck abstractMessageParamCheck : abstractMessageParamChecks) {
			abstractMessageParamCheck.check(messageSendParam);
		}
	}

}
