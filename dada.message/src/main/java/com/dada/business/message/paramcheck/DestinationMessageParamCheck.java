package com.dada.business.message.paramcheck;

import static com.dada.business.message.util.MessageCode.MESSAGE_PARAM_DESTINATION_INVALID;

import java.util.regex.Pattern;

import com.dada.business.common.api.model.ApiException;
import com.dada.business.message.api.model.MessageSendParam;

/**
 * 
 * Title: DestinationMessageParamCheck Description: 消息参数校验destination
 * 
 * @author ssc
 * @date 2016年4月20日 下午2:50:32
 */
public class DestinationMessageParamCheck extends AbstractMessageParamCheck {

	private static final String REGEX_DESTINATION = "^type:(userId|tagId|mobile):[a-zA-Z_0-9]+$";
	private static Pattern PATTERN_DESTINATION = Pattern
			.compile(REGEX_DESTINATION);

	@Override
	public void check(MessageSendParam messageSendParam) {
		String destination = messageSendParam.getDestination();
		if (!PATTERN_DESTINATION.matcher(destination).find())
			throw new ApiException(
					MESSAGE_PARAM_DESTINATION_INVALID.getApiCode(),
					MESSAGE_PARAM_DESTINATION_INVALID
							.getApiMessage(destination));
	}

}
