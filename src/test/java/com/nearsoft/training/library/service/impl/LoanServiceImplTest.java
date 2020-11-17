package com.nearsoft.training.library.service.impl;

import com.nearsoft.training.library.config.CardReaderConfigurationProperties;
import com.nearsoft.training.library.config.LoanConfigurationProperties;
import com.nearsoft.training.library.exception.LoanNotAllowedException;
import com.nearsoft.training.library.model.Book;
import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.model.User;
import com.nearsoft.training.library.repository.BookRepository;
import com.nearsoft.training.library.repository.BooksByUserRepository;
import com.nearsoft.training.library.repository.UserRepository;
import com.nearsoft.training.library.service.CardReaderService;
import com.nearsoft.training.library.service.LoanService;
import com.nearsoft.training.library.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceImplTest {
    @Test
    public void constructorTest() {
        //Given
        CardReaderService cardReaderService = null;
        UserService userService = null;
        LoanConfigurationProperties loanConfigurationProperties = null;
        BookRepository bookRepository = null;
        LoanServiceImpl loanService = new LoanServiceImpl(cardReaderService, userService, loanConfigurationProperties, bookRepository);


    }

    @Test
    public void lendBooksTest() {
        //Given:
        String[] isbnList = {"123"};
        String curp = "12";
        Book book = new Book();
        Optional<Book> bookOptional = Optional.of(book);
        CardReaderService cardReaderService = Mockito.mock(CardReaderService.class);
        UserService userService = Mockito.mock(UserService.class);
        LoanConfigurationProperties loanConfigurationProperties = new LoanConfigurationProperties();
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        LoanServiceImpl loanService = new LoanServiceImpl(cardReaderService, userService, loanConfigurationProperties, bookRepository);
        User user = new User();
        user.setCurp(curp);
        loanConfigurationProperties.setMaxBooksPerUser(5);
        Set<BooksByUser> booksByUser = new HashSet<>();

        Mockito.when(cardReaderService.readUser()).thenReturn(user);
        Mockito.when(userService.getBorrowedBooks(curp)).thenReturn(booksByUser);
        Mockito.when(bookRepository.findById(isbnList[0])).thenReturn(bookOptional);

        //When:
        loanService.lendBooks(isbnList);

        //Then:
        Mockito.verify(userService).registerLoan(user, isbnList);
    }

    @Test
    public void lendBooksTestwhenborrowedBooksOnTimethrowLoanNotAllowedException() throws LoanNotAllowedException {
        //Given:
        User user = new User();
        String[] isbnList = {"123"};
        CardReaderService cardReaderService = Mockito.mock(CardReaderService.class);
        UserService userService = Mockito.mock(UserService.class);
        LoanConfigurationProperties loanConfigurationProperties = new LoanConfigurationProperties();
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        LoanServiceImpl loanService = new LoanServiceImpl(cardReaderService, userService, loanConfigurationProperties, bookRepository);
        user.setCurp("123");
        loanConfigurationProperties.setMaxBooksPerUser(5);
        userService.getBorrowedBooks(isbnList[0]);
        Set<BooksByUser> borrowedBooks = userService.getBorrowedBooks(user.getCurp());
        borrowedBooks.add(new BooksByUser());
        Set<String> borrowedBooksOnTime = new HashSet<>();
        Assertions.assertThrows(Exception.class, () -> {
            throw new LoanNotAllowedException("User must return old loans");
        });
        //When:

        loanService.validateLoan(user, isbnList);
        //borrowedBooksOnTime.size();
        //Mockito.verify(userService).registerLoan(user,isbnList);
        //Mockito.verify(bookRepository);
    }

    @Test
    public void returnBooksTest() {
        //Given:
        String[] isbnList = {"123"};
        String[] otroIsbnList = {"1234"};
        String curp = "12";
        LoanConfigurationProperties loanConfigurationProperties = new LoanConfigurationProperties();
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        BooksByUserRepository booksByUserRepository = Mockito.mock(BooksByUserRepository.class);
        UserService userService = Mockito.mock(UserService.class);
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        CardReaderService cardReaderService = Mockito.mock(CardReaderService.class);
        UserService otherUserService = new UserServiceImpl(userRepository, booksByUserRepository);

        LoanServiceImpl loanServiceImpl = new LoanServiceImpl(cardReaderService,userService,loanConfigurationProperties,bookRepository);
        User user = new User();

        Mockito.when(cardReaderService.readUser()).thenReturn(user);
        //user = cardReaderService.readUser();
        Set<BooksByUser> booksByUsers = userService.registerReturn(user,otroIsbnList);
        Mockito.when(userService.registerReturn(user, isbnList)).thenReturn(booksByUsers);
        //When:
        loanServiceImpl.returnBooks(isbnList);

        //Then:

    }
    @Test
    public void algo(){
        //Given:
        String[] isbnList = {"123"};
        CardReaderService cardReaderService = Mockito.mock(CardReaderService.class);
        UserService userService = Mockito.mock(UserService.class);
        LoanConfigurationProperties loanConfigurationProperties = Mockito.mock(LoanConfigurationProperties.class);
        BookRepository bookRepository = Mockito.mock(BookRepository.class);
        LoanServiceImpl loanService = new LoanServiceImpl(cardReaderService,userService,loanConfigurationProperties,bookRepository);
        String curp = "123";
        User user = new User();
        user.setCurp(curp);

        Set<BooksByUser> booksByUser = new HashSet<>();

        Mockito.when(cardReaderService.readUser()).thenReturn(user);
        Mockito.when(userService.getBorrowedBooks(user.getCurp())).thenReturn(booksByUser);
        //When:
        loanService.lendBooks(isbnList);

        //Then:
    }

}