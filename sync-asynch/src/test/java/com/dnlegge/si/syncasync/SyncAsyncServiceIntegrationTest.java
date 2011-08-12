package com.dnlegge.si.syncasync;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.MessagingException;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.core.MessageHandler;
import org.springframework.integration.message.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SyncAsyncServiceIntegrationTest {

    private static Logger logger = Logger.getLogger(SyncAsyncServiceIntegrationTest.class);
    @Resource
    MessageChannel channel1;
    @Resource
    PublishSubscribeChannel channel2;

    @Test
    public void testSayHello() throws Exception {

        MessageHandler messageHandler = new MessageHandler() {
            public void handleMessage(Message<?> message) throws MessagingException {
                logger.info(message.getPayload());
            }
        };

        channel2.subscribe(messageHandler);

        final String filename = "filename";
        channel1.send(new GenericMessage<String>(filename));

    }
}
