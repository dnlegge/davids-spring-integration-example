package com.dnlegge.si.example.helloworld;

import org.junit.Test;
import org.springframework.integration.Message;
import org.springframework.integration.MessageHeaders;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class HelloServiceTest {
    @Test
    public void testSayHello() throws Exception {

        Message<?> msg = mock(Message.class);
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("priority", 1) ;
        final MessageHeaders msgHeaders = new MessageHeaders(hashMap);
        when(msg.getHeaders()).thenReturn(msgHeaders);

        final HelloService beingTested = new HelloService();

        final String shouldBeHello = beingTested.sayHello(msg);

        assertNotNull("Returns Null", shouldBeHello);
        assertEquals("Doesn't return Hello", "Hello Priority 1", shouldBeHello);

    }
}
