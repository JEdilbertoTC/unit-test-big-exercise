package com.nearsoft.training.library.service.impl;

import com.nearsoft.training.library.config.CardReaderConfigurationProperties;
import com.nearsoft.training.library.exception.CardReaderFailureException;
import com.nearsoft.training.library.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.io.*;
import java.net.Socket;

class CardReaderServiceImplTest {
    @Test
    public void readUserTest() throws IOException {
        //Given:
        CardReaderConfigurationProperties cardReaderConfigurationProperties = Mockito.mock(CardReaderConfigurationProperties.class);
        CardReaderServiceImpl cardReaderService = new CardReaderServiceImpl(cardReaderConfigurationProperties);
        Socket socket = new Socket();
        User user = new User();
        OutputStream outputStream = Mockito.mock(OutputStream.class);
        InputStream inputStream = Mockito.mock(InputStream.class);

        //When:
        User otherUser = cardReaderService.readUser();
        User user1 = cardReaderService.getUser(outputStream,inputStream);
        //Then:
    }
    @Test
    public void getUserTest() throws  IOException{
        //Given:
        CardReaderConfigurationProperties cardReaderConfigurationProperties = Mockito.mock(CardReaderConfigurationProperties.class);
        CardReaderServiceImpl cardReaderService = new CardReaderServiceImpl(cardReaderConfigurationProperties);
        Socket socket = Mockito.mock(Socket.class);
        BufferedReader in = Mockito.mock(BufferedReader.class);
        String response = "12 12 134";
        String[] data = response.split(" ");

        User user = new User();
        user.setName(data[0]);
        user.setCurp(data[1]);
        user.setValidityDate(data[2]);

        OutputStream outputStream = Mockito.mock(OutputStream.class);
        InputStream inputStream = Mockito.mock(InputStream.class);

        //When:
        try {
            user = cardReaderService.getUser(outputStream, inputStream);
        } catch (IOException e){

        };
        //Then:
    }
}
