package com.dada.business.message.dao.mapper;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import com.dada.business.message.api.model.MessageModel;

/**
 * 
 * Title: MessageMapperTest Description: MessageModel测试类
 * 
 * @author ssc
 * @date 2016年4月26日 下午5:32:36
 */
public class MessageMapperTest {

	private static MessageMapper mockMessageMapper;
	private static MessageModel messageModel1;
	private static MessageModel messageModel2;

	@BeforeClass
	public static void setUp() {
		// Create mock object of MessageMapper
		mockMessageMapper = mock(MessageMapper.class);

		// Create few instances of MessageModel class.
		messageModel1 = new MessageModel();
		String track1 = RandomStringUtils.randomAlphanumeric(8);
		messageModel1.setApplication("API");
		messageModel1.setChannel(MessageModel.CHANNEL_SMS);
		messageModel1.setContent("lantianTest1");
		messageModel1.setDestination("type:mobile:18611466872");
		messageModel1.setMode(MessageModel.MODE_UNICAST);
		messageModel1.setOrigin("lantian1");
		messageModel1.setTemplateId(1L);
		messageModel1.setTemplateName("test1");
		messageModel1.setTitle("test1");
		messageModel1.setTrack(track1);
		messageModel1.setType(1);
		messageModel1.setInvalidDate(new Date(1466937071));

		messageModel2 = new MessageModel();
		String track2 = RandomStringUtils.randomAlphanumeric(8);
		messageModel2.setApplication("OPTOOLS");
		messageModel2.setChannel(MessageModel.CHANNEL_SMS);
		messageModel2.setContent("lantianTest2");
		messageModel2.setDestination("type:mobile:18611466872");
		messageModel2.setMode(MessageModel.MODE_UNICAST);
		messageModel2.setOrigin("lantian2");
		messageModel2.setTemplateId(2L);
		messageModel2.setTemplateName("test2");
		messageModel2.setTitle("test2");
		messageModel2.setTrack(track2);
		messageModel2.setType(2);
		messageModel2.setInvalidDate(new Date(1466937071));

		// Stubbing the methods of mocked MessageMapper with mocked data.
		when(mockMessageMapper.getMessageByTrack(track1)).thenReturn(messageModel1);
		when(mockMessageMapper.getMessagesByChannelAndDate(MessageModel.CHANNEL_SMS, new Date(1456482671), new Date(1472207471))).thenReturn(Arrays.asList(messageModel1, messageModel2));
	}

	@Test
	public void getMessagesByChannelAndDate() {
		int channel = MessageModel.CHANNEL_SMS;
		Date start = new Date(1456482671);
		Date end = new Date(1472207471);
		List<MessageModel> allMessageModels = mockMessageMapper.getMessagesByChannelAndDate(channel, start, end);
	    assertEquals(2, allMessageModels.size());
	    MessageModel messageModel = allMessageModels.get(0);
	    assertEquals("API", messageModel.getApplication());
	    assertEquals(MessageModel.CHANNEL_SMS, messageModel.getChannel());
//	    assertEquals("lantianTest1", messageModel.getContent());
	    assertEquals("type:mobile:18611466872", messageModel.getDestination());
	    assertEquals(MessageModel.MODE_UNICAST, messageModel.getMode());
//	    assertEquals("lantian2", messageModel.getOrigin());
//	    assertEquals(new Long(2), messageModel.getTemplateId());
//	    assertEquals("test2", messageModel.getTemplateName());
//	    assertEquals("test2", messageModel.getTitle());
	    assertEquals(new Integer(1), messageModel.getType());
	}

	public void updateMessage(MessageModel messageModel) {
	}

}
