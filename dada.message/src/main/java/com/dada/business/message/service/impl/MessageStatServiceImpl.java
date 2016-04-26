package com.dada.business.message.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dada.business.message.api.model.MessageStatModel;
import com.dada.business.message.api.service.MessageStatService;
import com.dada.business.message.dao.mapper.MessageStatMapper;

/**
 * 
 * Title: MessageStatServiceImpl Description: 消息统计实体服务接口实现
 * 
 * @author ssc
 * @date 2016年4月19日 下午4:38:52
 */
@Service("messageStatServiceImpl")
public class MessageStatServiceImpl implements MessageStatService {

	@Resource
	private MessageStatMapper messageStatMapper;

	@Override
	public void addMessageStat(MessageStatModel messageStatModel) {
		messageStatMapper.insertMessageStat(messageStatModel);
	}

	@Override
	public void updateMessageStat(MessageStatModel messageStatModel) {
		messageStatMapper.updateMessageStat(messageStatModel);
	}

	@Override
	public MessageStatModel getMessageStat(Long messageId) {
		return messageStatMapper.getMessageStat(messageId);
	}

	@Override
	public List<MessageStatModel> getMessageStats(Long[] messageIds) {
		return messageStatMapper.getMessageStats(messageIds);
	}

}
