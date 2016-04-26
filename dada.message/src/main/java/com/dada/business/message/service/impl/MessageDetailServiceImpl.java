package com.dada.business.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dada.business.common.api.util.PageList;
import com.dada.business.message.api.model.MessageDetailModel;
import com.dada.business.message.api.model.MessageDetailQuery;
import com.dada.business.message.api.service.MessageDetailService;
import com.dada.business.message.dao.mapper.MessageDetailMapper;

/**
 * 
 * Title: MessageDetailServiceImpl Description: 消息详情实体服务接口实现
 * 
 * @author ssc
 * @date 2016年4月19日 下午3:29:38
 */
@Service("messageDetailServiceImpl")
public class MessageDetailServiceImpl implements MessageDetailService {

	@Resource
	private MessageDetailMapper messageDetailMapper;

	@Override
	public void addMessageDetail(MessageDetailModel messageDetailModel) {
		messageDetailMapper.insertMessageDetail(messageDetailModel);
	}

	@Override
	public void updateMessageDetail(MessageDetailModel messageDetailModel) {
		messageDetailMapper.updateMessageDetail(messageDetailModel);
	}

	@Override
	public MessageDetailModel getMessageDetail(Long messageId,
			String destination) {
		return messageDetailMapper.getMessageDetail(messageId, destination);
	}

	@Override
	public PageList<MessageDetailModel> getMessageDetails(Long messageId,
			int page, int itemPerPage) {
		MessageDetailQuery messageDetailQuery = new MessageDetailQuery();
		messageDetailQuery.setPage(page);
		messageDetailQuery.setItemsPerPage(itemPerPage);
		messageDetailQuery.setMessageId(messageId);
		List<MessageDetailModel> data = messageDetailMapper
				.queryMessageDetails(messageDetailQuery);
		if (null == data) {
			return null;
		}
		int totalNum = messageDetailMapper
				.getMessageDetailTotalNum(messageDetailQuery);
		PageList<MessageDetailModel> result = new PageList<MessageDetailModel>(
				messageDetailQuery.getItemsPerPage(), totalNum,
				messageDetailQuery.getPage());
		result.setData(data);
		return result;
	}

}
