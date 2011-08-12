package com.dnlegge.si.example;

import org.apache.log4j.Logger;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.PollableChannel;
import org.springframework.integration.message.GenericMessage;

public class HelloWorldMain {

    private static Logger logger = Logger.getLogger(HelloWorldMain.class);

    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("/META-INF/spring/integration/config.xml", HelloWorldMain.class);
		MessageChannel inputChannel = context.getBean("inputChannel", MessageChannel.class);
		PollableChannel outputChannel = context.getBean("outputChannel", PollableChannel.class);
		inputChannel.send(new GenericMessage<String>("World"));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        final Message<?> received = outputChannel.receive(0);
        System.out.println(received.getPayload());
		logger.info("==> HelloWorldMain: " + received.getPayload());
    }

}
