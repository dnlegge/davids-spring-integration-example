package com.dnlegge.si.example.helloworld;

import org.springframework.integration.Message;

public class HelloService {

    public String sayHello(Message message) {
        return "Hello Priority " + message.getHeaders().get("priority");
    }

}
