package com.dada.business.message.paramcheck;

import static com.dada.business.message.util.MessageCode.MESSAGE_PARAM_IS_NULL;
import static com.dada.business.message.util.MessageCode.MESSAGE_PARAM_IP_NOT_IN_WHITELIST;

import org.apache.commons.lang3.StringUtils;

import com.dada.business.common.api.model.ApiException;
import com.dada.business.message.api.model.MessageSendParam;

/**
 * 
 * Title: IpMessageParamCheck Description: 消息参数校验ip
 * 
 * @author ssc
 * @date 2016年4月20日 下午2:52:01
 */
public class IpMessageParamCheck extends AbstractMessageParamCheck {

	private final static String SYMBOL_SET = ".*";
	private final static char SYMBOL_COMMA = ',';

	private String ipList;

	public void setIpList(String ipList) {
		this.ipList = ipList;
	}

	@Override
	public void check(MessageSendParam messageSendParam) {
		boolean result = false;
		String ip = messageSendParam.getExtra("ipAddress");

		if (StringUtils.isBlank(ip))
			throw new ApiException(MESSAGE_PARAM_IS_NULL.getApiCode(),
					MESSAGE_PARAM_IS_NULL.getApiMessage("ip"));

		if (StringUtils.isBlank(ipList))
			throw new ApiException(
					MESSAGE_PARAM_IP_NOT_IN_WHITELIST.getApiCode(),
					MESSAGE_PARAM_IP_NOT_IN_WHITELIST.getApiMessage(ip));

		String[] whiteIps = ipList.split(String.valueOf(SYMBOL_COMMA));
		for (String whiteIp : whiteIps) {
			if (ip.equals(whiteIp)) {
				result = true;
				break;
			} else if (SYMBOL_SET.contains(whiteIp)) {
				String prefix = whiteIp.substring(0,
						whiteIp.indexOf(SYMBOL_SET));
				if (ip.contains(prefix)) {
					result = true;
					break;
				}
			}
		}

		if (!result)
			throw new ApiException(
					MESSAGE_PARAM_IP_NOT_IN_WHITELIST.getApiCode(),
					MESSAGE_PARAM_IP_NOT_IN_WHITELIST.getApiMessage(ip));
	}

}
