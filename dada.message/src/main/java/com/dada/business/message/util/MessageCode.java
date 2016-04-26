package com.dada.business.message.util;

import com.dada.business.common.api.util.ApiCode;

/**
 * 
 * Title: MessageCode Description: 消息错误码
 * 
 * @author ssc
 * @date 2016年4月19日 下午4:24:55
 */
public enum MessageCode implements ApiCode {

	MESSAGE_TEMPLATE_DUPLICATED("00001", "消息模板已存在[%s]."), MESSAGE_TEMPLATE_NOTFOUND(
			"00002", "消息模板不存在[%s]."), MESSAGE_TEMPLATE_ATTRIBUTES_UPDATED(
			"00003", "消息模板属性发生变动[老的=%s 新的=%s]."), MESSAGE_TEMPLATE_INVALID(
			"00004", "消息模板内容格式不正确."),

	MESSAGE_PARAM_IS_NULL("01001", "消息参数为空[%s]."), MESSAGE_PARAM_IP_NOT_IN_WHITELIST(
			"01002", "消息参数IP不在白名单中[%s]."), MESSAGE_PARAM_DESTINATION_INVALID(
			"01003", "消息参数destination不合法[%s]."),

	MESSAGE_CHANNEL_NOTFOUND("02001", "消息通道不存在[%d]."), ;

	private String code;
	private String message;

	private MessageCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	@Override
	public String getApiCode() {
		return code;
	}

	@Override
	public String getApiMessage(Object... params) throws ApiCodeException {
		return String.format(message, params);
	}

}
