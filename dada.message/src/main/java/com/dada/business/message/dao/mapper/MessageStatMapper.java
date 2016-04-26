package com.dada.business.message.dao.mapper;

import java.util.List;

import com.dada.business.message.api.model.MessageStatModel;

/**
 * 
 * Title: MessageStatMapper Description: 消息统计实体DAO
 * 
 * @author ssc
 * @date 2016年4月19日 下午4:39:49
 */
public interface MessageStatMapper {

	MessageStatModel getMessageStat(Long messageId);

	void insertMessageStat(MessageStatModel messageStatModel);

	void updateMessageStat(MessageStatModel messageStatModel);

	List<MessageStatModel> getMessageStats(Long[] messageIds);

}
