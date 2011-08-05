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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class HelloServiceIntegrationTest {

    private static Logger logger = Logger.getLogger(HelloServiceIntegrationTest.class);
    @Resource
    MessageChannel inputChannel;
    @Resource
    PollableChannel outputChannel;

    @Test
    public void testSayHello() throws Exception {

        for (int i = 0;  i < 20 ; ++i) {
            final GenericMessage<String> message1 = new GenericMessage<String>("World");
            inputChannel.send(message1);
        }

        final ArrayList<Message> messages = new ArrayList<Message>();
        Message<?> receive = null;
        do {
            receive = outputChannel.receive(5000);

            if (receive == null) {
                break;
            }
            System.out.println(receive.getPayload());
            logger.info(receive.getPayload());
            messages.add(receive);
        } while (true);//(receive != null);

        System.out.println(messages.size());
        logger.info(messages.size());

    }
}
