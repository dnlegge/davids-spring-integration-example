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

        inputChannel.send(new GenericMessage<String>("World"));
        final Message<?> message = outputChannel.receive(0);
        System.out.println(message.getPayload());
        logger.info(message.getPayload());

    }
}
