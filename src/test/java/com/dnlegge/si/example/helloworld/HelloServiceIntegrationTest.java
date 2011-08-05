package com.dnlegge.si.example.helloworld;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.message.GenericMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HelloServiceIntegrationTest {

    private static Logger logger = Logger.getLogger(HelloServiceIntegrationTest.class);
    @Resource
    MessageChannel inputChannelLow;
    @Resource
    MessageChannel inputChannelHigh;
    @Resource
    PollableChannel outputChannel;

    @Test
    public void testSayHello() throws Exception {

        for (int i = 0;  i < 10 ; ++i) {
            inputChannelLow.send(new GenericMessage<String>("World"));
            inputChannelHigh.send(new GenericMessage<String>("World"));
        }

        final ArrayList<Message> messages = new ArrayList<Message>();
        Message<?> receive = null;
        while (true) {
            receive = outputChannel.receive(10);

            if (receive == null) {
                break;
            }
            final String message = receive.getPayload() + " " + String.valueOf(receive.getHeaders().get("priority"));
            System.out.println(message);
            logger.info(message);
            messages.add(receive);
        }

        final int size = messages.size();
        final String message = "Number of messages recieved: " + size;
        System.out.println(message);
        logger.info(message);
        assertEquals("Some messages lost", 20, size);

    }
}
