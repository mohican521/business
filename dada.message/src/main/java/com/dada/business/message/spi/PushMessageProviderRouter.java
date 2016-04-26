package com.dada.business.message.spi;

import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.channel.PushMessageChannel;

/**
 * 
 * Title: PushMessageProviderRouter Description: 推送运营商路由接口
 * 
 * @author ssc
 * @date 2016年4月25日 下午7:13:03
 */
public interface PushMessageProviderRouter extends MessageProviderRouter {

	public abstract PushMessageChannel routeMessageProvider(MessageModel messageModel);

}
