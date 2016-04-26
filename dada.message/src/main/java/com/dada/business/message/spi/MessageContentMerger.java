package com.dada.business.message.spi;

import java.util.Map;

/**
 * 
 * Title: MessageContentMerger Description: 消息内容合并接口
 * 
 * @author ssc
 * @date 2016年4月20日 上午11:32:27
 */
public interface MessageContentMerger {

	public abstract String mergeMessageContent(String content,
			Map<String, String> attributes);

}
