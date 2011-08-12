package com.dnlegge.si.syncasync;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class SyncAsynchService {

   @Autowired
   @Qualifier("channel1") // or "channel2"
    private MessageChannel sftpChannel;

    public boolean uploadFile(String filename) {
		try {
            send(filename);
            return true;
		} catch (Exception e) {
			return false;
		}
    }

    private void send(String filename) {
        sftpChannel.send(buildMessage(filename));
    }

    private Message<String> buildMessage(String filename) {
        return MessageBuilder.withPayload(filename).build();
    }

    void setSftpChannel(MessageChannel sftpChannel) {
        this.sftpChannel = sftpChannel;
    }
}
