package com.nearsoft.training.library.service.impl;

import com.nearsoft.training.library.config.LoanConfigurationProperties;
import com.nearsoft.training.library.repository.BookRepository;
import com.nearsoft.training.library.service.CardReaderService;
import com.nearsoft.training.library.service.UserService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceImplTest {
    @Test
    public void test(){
        //Given
        CardReaderService cardReaderService = null;
        UserService userService = null;
        LoanConfigurationProperties loanConfigurationProperties = null;
        BookRepository bookRepository = null;
        LoanServiceImpl loanService = new LoanServiceImpl(cardReaderService,userService,loanConfigurationProperties,bookRepository);
        assertEquals(loanService,loanService);
    }

    public void lendBooks(String[] isbnList){
        CardReaderService cardReaderService;
    }
}