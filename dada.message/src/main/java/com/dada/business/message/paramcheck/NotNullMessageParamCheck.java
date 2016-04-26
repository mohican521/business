package com.dada.business.message.paramcheck;

import static com.dada.business.message.util.MessageCode.MESSAGE_PARAM_IS_NULL;

import org.apache.commons.lang3.StringUtils;

import com.dada.business.common.api.model.ApiException;
import com.dada.business.message.api.model.MessageSendParam;

/**
 * 
 * Title: NotNullMessageParamCheck Description: 消息参数校验非空
 * 
 * @author ssc
 * @date 2016年4月20日 下午2:58:28
 */
public class NotNullMessageParamCheck extends AbstractMessageParamCheck {

	@Override
	public void check(MessageSendParam messageSendParam) {
		if (StringUtils.isBlank(messageSendParam.getTemplateName()))
			throw new ApiException(MESSAGE_PARAM_IS_NULL.getApiCode(),
					MESSAGE_PARAM_IS_NULL.getApiMessage("templateName"));

		if (StringUtils.isBlank(messageSendParam.getTrack()))
			throw new ApiException(MESSAGE_PARAM_IS_NULL.getApiCode(),
					MESSAGE_PARAM_IS_NULL.getApiMessage("track"));

		if (StringUtils.isBlank(messageSendParam.getOrigin()))
			throw new ApiException(MESSAGE_PARAM_IS_NULL.getApiCode(),
					MESSAGE_PARAM_IS_NULL.getApiMessage("origin"));

		if (StringUtils.isBlank(messageSendParam.getDestination()))
			throw new ApiException(MESSAGE_PARAM_IS_NULL.getApiCode(),
					MESSAGE_PARAM_IS_NULL.getApiMessage("destination"));

		if (StringUtils.isBlank(messageSendParam.getApplication()))
			throw new ApiException(MESSAGE_PARAM_IS_NULL.getApiCode(),
					MESSAGE_PARAM_IS_NULL.getApiMessage("application"));
	}

}
