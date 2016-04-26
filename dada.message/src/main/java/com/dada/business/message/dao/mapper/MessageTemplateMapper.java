package com.dada.business.message.dao.mapper;

import java.util.List;

import com.dada.business.message.api.model.MessageTemplateModel;
import com.dada.business.message.api.model.MessageTemplateQuery;

/**
 * 
 * Title: MessageTemplateMapper Description: 消息模板实体DAO
 * 
 * @author ssc
 * @date 2016年4月19日 下午3:39:31
 */
public interface MessageTemplateMapper {

	public abstract void insertMessageTemplate(
			MessageTemplateModel messageTemplateModel);

	public abstract MessageTemplateModel getMessageTemplateById(Long id);

	public abstract MessageTemplateModel getMessageTemplateByName(String name);

	public abstract List<MessageTemplateModel> getMessageTemplateByVagueType(
			String vagueTypeName);

	public abstract void updateMessageTemplate(MessageTemplateModel template);

	public abstract int getTotalNum(MessageTemplateQuery messageTemplateQuery);

	public abstract List<MessageTemplateModel> queryMessageTemplates(
			MessageTemplateQuery messageTemplateQuery);

}
