package com.nearsoft.training.library.service.impl;

import com.nearsoft.training.library.config.CardReaderConfigurationProperties;
import com.nearsoft.training.library.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class CardReaderServiceImplTest {
    @Test

    public void constructorTest() {
        //GIVEN
        CardReaderServiceImpl cardReaderService;
        CardReaderConfigurationProperties cardReaderConfigurationProperties = Mockito.mock(CardReaderConfigurationProperties.class);
        //When
        cardReaderService = new CardReaderServiceImpl(cardReaderConfigurationProperties);
        //Then
       // boolean True = cardReaderConfigurationProperties == cardReaderService;

        //assertTrue(True);
    }
    @Test
    public void readUser(){
        //Given
        CardReaderConfigurationProperties cardReaderConfigurationProperties = null;
        CardReaderServiceImpl cardReaderService = Mockito.mock(CardReaderServiceImpl.class);
        //OutputStream outputStream = Mockito.mock(OutputStream.class);
        //InputStream inputStream = Mockito.mock(InputStream.class);

        //When
        User user = cardReaderService.readUser();

    }
}
