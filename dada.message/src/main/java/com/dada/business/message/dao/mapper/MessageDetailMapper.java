package com.dada.business.message.dao.mapper;

import java.util.List;

import com.dada.business.message.api.model.MessageDetailModel;
import com.dada.business.message.api.model.MessageDetailQuery;

/**
 * 
 * Title: MessageDetailMapper Description: 消息详情实体DAO
 * 
 * @author ssc
 * @date 2016年4月19日 下午3:28:13
 */
public interface MessageDetailMapper {

	public abstract MessageDetailModel getMessageDetail(Long messageId,
			String destination);

	public abstract void insertMessageDetail(
			MessageDetailModel messageDetailModel);

	public abstract void updateMessageDetail(
			MessageDetailModel messageDetailModel);

	public abstract List<MessageDetailModel> queryMessageDetails(
			MessageDetailQuery messageDetailQuery);

	public abstract int getMessageDetailTotalNum(
			MessageDetailQuery messageDetailQuery);

	public abstract int getMessageDetailStat(
			MessageDetailQuery messageDetailQuery);

}
