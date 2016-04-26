package com.dada.business.message.dao.mapper;

import java.util.Date;
import java.util.List;

import com.dada.business.message.api.model.MessageModel;
import com.dada.business.message.api.model.MessageQuery;

/**
 * 
 * Title: MessageMapper Description: 消息实体DAO
 * 
 * @author ssc
 * @date 2016年4月19日 下午3:17:05
 */
public interface MessageMapper {

	public abstract void insertMessage(MessageModel messageModel);

	public abstract List<MessageModel> queryMessages(MessageQuery messageQuery);

	public abstract List<MessageModel> queryMessagesByDestination(
			MessageQuery messageQuery);

	public abstract MessageModel getMessageByTrack(String track);

	public abstract MessageModel getMessageByProviderMsgId(String providerMsgId);

	public abstract List<MessageModel> getMessagesByDestination(
			String destination);

	public abstract int getTotalNum(MessageQuery messageQuery);

	public abstract int queryMessagesByDestinationCount(
			MessageQuery messageQuery);

	public abstract List<MessageModel> getMessagesByChannelAndDate(
			Integer channel, Date start, Date end);

	public abstract void updateMessage(MessageModel messageModel);

}
