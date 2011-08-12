package com.dnlegge.si.syncasync;

import org.junit.Before;
import org.junit.Test;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SyncAsynchServiceTest {

SyncAsynchService beingTested;
        MessageChannel mockChannel;

    @Before
    public void setup() throws Exception {

        beingTested = new SyncAsynchService();
        mockChannel = mock(MessageChannel.class);
        beingTested.setSftpChannel(mockChannel);

    }

    @Test
    public void testUploadFileException() throws Exception {

        when(mockChannel.send(any(Message.class))).thenThrow(new RuntimeException());

        final String filename = "filename";

        final boolean result = beingTested.uploadFile(filename);

        assertFalse(result);

    }

    @Test
    public void testUploadFileSuccess() throws Exception {

        when(mockChannel.send(any(Message.class))).thenReturn(false);

        final String filename = "filename";

        final boolean result = beingTested.uploadFile(filename);

        assertTrue(result);

    }
}
