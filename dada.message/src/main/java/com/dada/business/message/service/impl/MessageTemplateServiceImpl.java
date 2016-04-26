package com.dada.business.message.service.impl;

import static com.dada.business.message.util.MessageCode.MESSAGE_TEMPLATE_ATTRIBUTES_UPDATED;
import static com.dada.business.message.util.MessageCode.MESSAGE_TEMPLATE_DUPLICATED;
import static com.dada.business.message.util.MessageCode.MESSAGE_TEMPLATE_NOTFOUND;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.dada.business.common.api.model.ApiException;
import com.dada.business.common.api.util.PageList;
import com.dada.business.message.api.model.MessageTemplateModel;
import com.dada.business.message.api.model.MessageTemplateQuery;
import com.dada.business.message.api.service.MessageTemplateService;
import com.dada.business.message.dao.mapper.MessageTemplateMapper;

/**
 * 
 * Title: MessageTemplateServiceImpl Description: 消息模板实体服务接口实现
 * 
 * @author ssc
 * @date 2016年4月19日 下午3:38:36
 */
@Service("messageTemplateServiceImpl")
public class MessageTemplateServiceImpl implements MessageTemplateService {

	@Resource
	private MessageTemplateMapper messageTemplateMapper;

	@Override
	public void addMessageTemplate(MessageTemplateModel messageTemplateModel) {
		assertNotNull(messageTemplateModel, "template is null.");
		assertNotBlank(messageTemplateModel.getType().toString(),
				"template's type is empty.");
		assertNotBlank(messageTemplateModel.getName(),
				"template's name is empty.");
		assertNotBlank(messageTemplateModel.getTitle(),
				"template's title is empty.");
		assertNotBlank(messageTemplateModel.getContent(),
				"template's content is empty.");

		if (getMessageTemplateByName(messageTemplateModel.getName()) != null) {
			throw new ApiException().build(MESSAGE_TEMPLATE_DUPLICATED
					.getApiCode(), MESSAGE_TEMPLATE_DUPLICATED
					.getApiMessage(messageTemplateModel.getName()));
		}

		messageTemplateModel.setAttributes(getAttributes(
				messageTemplateModel.getContent(),
				messageTemplateModel.getUrl()));
		messageTemplateMapper.insertMessageTemplate(messageTemplateModel);
	}

	@Override
	public void updateMessageTemplate(MessageTemplateModel messageTemplateModel) {
		assertNotNull(messageTemplateModel, "template is null.");
		assertNotNull(messageTemplateModel.getId(), "template's id is null.");
		assertNotBlank(messageTemplateModel.getTitle(),
				"template's title is empty.");
		assertNotBlank(messageTemplateModel.getContent(),
				"template's content is empty.");

		MessageTemplateModel mt = messageTemplateMapper
				.getMessageTemplateById(messageTemplateModel.getId());
		if (mt == null) {
			throw new ApiException().build(MESSAGE_TEMPLATE_NOTFOUND
					.getApiCode(), MESSAGE_TEMPLATE_NOTFOUND
					.getApiMessage(messageTemplateModel.getName()));
		}

		String attributes = mt.getAttributes();
		String newAttributes = getAttributes(messageTemplateModel.getContent(),
				messageTemplateModel.getUrl());
		if (!attributesEquals(attributes, newAttributes)) {
			throw new ApiException().build(MESSAGE_TEMPLATE_ATTRIBUTES_UPDATED
					.getApiCode(), MESSAGE_TEMPLATE_ATTRIBUTES_UPDATED
					.getApiMessage(attributes, newAttributes));
		}

		mt.setTitle(messageTemplateModel.getTitle());
		mt.setContent(messageTemplateModel.getContent());
		mt.setUrl(messageTemplateModel.getUrl());
		messageTemplateMapper.updateMessageTemplate(mt);
	}

	@Override
	public MessageTemplateModel getMessageTemplateByName(String name) {
		return messageTemplateMapper.getMessageTemplateByName(name);
	}

	@Override
	public List<MessageTemplateModel> getMessageTemplateByVagueType(
			String vagueTypeName) {
		return messageTemplateMapper
				.getMessageTemplateByVagueType(vagueTypeName);
	}

	@Override
	public PageList<MessageTemplateModel> queryMessageTemplates(
			MessageTemplateQuery messageTemplateQuery) {
		List<MessageTemplateModel> data = messageTemplateMapper
				.queryMessageTemplates(messageTemplateQuery);
		if (null == data) {
			return null;
		}
		PageList<MessageTemplateModel> result = new PageList<MessageTemplateModel>(
				messageTemplateQuery.getItemsPerPage(),
				messageTemplateMapper.getTotalNum(messageTemplateQuery),
				messageTemplateQuery.getPage());
		result.setData(data);
		return result;
	}

	private void assertNotNull(Object value, String message) {
		if (value == null) {
			throw new IllegalArgumentException(message);
		}
	}

	private void assertNotBlank(String value, String message) {
		if (value == null || value.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	private static enum Stat {
		INIT, PRE, TOKEN,
	}

	protected String getAttributes(String content, String url) {
		StringBuilder sb = new StringBuilder();
		if (content != null) {
			sb.append(content);
		}
		if (url != null) {
			sb.append(url);
		}
		String value = sb.toString();

		if (value.isEmpty() || !value.contains("$")) {
			return "";
		}

		Set<String> attributes = new HashSet<>();

		Stat stat = Stat.INIT;
		int start = 0;
		for (int i = 0; i < value.length(); i++) {
			char c = value.charAt(i);
			switch (stat) {
			case INIT:
				if ('$' == c) {
					stat = Stat.PRE;
				}
				break;
			case PRE:
				if ('$' == c) {
					stat = Stat.PRE;
				} else if ('{' == c) {
					stat = Stat.TOKEN;
					start = i;
				} else if ('!' == c) {
					continue;
				} else {
					stat = Stat.INIT;
				}
				break;
			case TOKEN:
				if ('}' == c) {
					stat = Stat.INIT;
					attributes.add(value.substring(start + 1, i));
				}
				break;
			default:
				break;
			}
		}

		String[] ret = attributes.toArray(new String[0]);
		Arrays.sort(ret);
		return StringUtils.join(ret, ",");
	}

	protected boolean attributesEquals(String attributes, String newAttributes) {
		if (attributes == newAttributes) {
			return true;
		}
		if (attributes == null || newAttributes == null) {
			return false;
		}
		return attributes.equals(newAttributes);
	}

	public void setMessageTemplateMapper(
			MessageTemplateMapper messageTemplateMapper) {
		this.messageTemplateMapper = messageTemplateMapper;
	}

}
