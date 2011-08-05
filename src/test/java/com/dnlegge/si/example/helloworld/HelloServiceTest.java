package com.dnlegge.si.example.helloworld;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class HelloServiceTest {
    @Test
    public void testSayHello() throws Exception {

        final HelloService beingTested = new HelloService();

        final String shouldBeHello = beingTested.sayHello();

        assertNotNull("Returns Null", shouldBeHello);
        assertEquals("Doesn't return Hello", "Hello", shouldBeHello);

    }
}
