package com.dada.business.message.spi.impl;

import static com.dada.business.message.util.MessageCode.MESSAGE_TEMPLATE_INVALID;

import java.io.StringWriter;
import java.util.Map;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

import com.dada.business.common.api.model.ApiException;
import com.dada.business.message.spi.MessageContentMerger;

/**
 * 
 * Title: VelocityMessageContentMerger Description: Velocity框架实现消息内容合并
 * 
 * @author ssc
 * @date 2016年4月20日 上午11:35:13
 */
public class VelocityMessageContentMerger implements MessageContentMerger {

	private static final String DEFAULT_ENCODING = "UTF-8";

	private VelocityEngine engine = new VelocityEngine();

	public void init() {
		engine.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS,
				"org.apache.velocity.runtime.log.NullLogChute");
		engine.setProperty(RuntimeConstants.INPUT_ENCODING, DEFAULT_ENCODING);
		engine.setProperty(RuntimeConstants.OUTPUT_ENCODING, DEFAULT_ENCODING);
	}

	@Override
	public String mergeMessageContent(String content,
			Map<String, String> attributes) {
		try {
			StringWriter ret = new StringWriter();
			engine.evaluate(new VelocityContext(attributes), ret, "none",
					content);
			return ret.toString();
		} catch (Exception e) {
			throw new ApiException().build(
					MESSAGE_TEMPLATE_INVALID.getApiCode(),
					MESSAGE_TEMPLATE_INVALID.getApiMessage());
		}
	}

}
