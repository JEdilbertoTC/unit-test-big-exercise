package com.nearsoft.training.library.service.impl;


import com.nearsoft.training.library.model.Book;
import com.nearsoft.training.library.model.BooksByUser;
import com.nearsoft.training.library.model.User;
import com.nearsoft.training.library.repository.BooksByUserRepository;
import com.nearsoft.training.library.repository.UserRepository;
import com.nearsoft.training.library.service.UserService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    @Test
    public void registerLoanTest() {
        //Given:
        String[] isbnList = {"12"};
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        BooksByUserRepository booksByUserRepository = Mockito.mock(BooksByUserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, booksByUserRepository);
        User user = new User();
        String curp = "1234";

        Book book = new Book();
        user.setCurp(curp);
        userRepository.save(user);

        //When:
        userService.registerLoan(user, isbnList);
        //Then:
    }

    @Test
    public void getBorrowedBooksTest() {
        //Given:
        String curp = "123";
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Set<BooksByUser> booksByUsers = new HashSet<>();
        BooksByUserRepository otherRepository = Mockito.mock(BooksByUserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, otherRepository);
        Mockito.when(otherRepository.findByCurp(curp)).thenReturn(booksByUsers);

        //When:
        Set<BooksByUser> receivedBooksByUser = userService.getBorrowedBooks(curp);

    }

    @Test
    public void registerReturnTest() {

        //Given:
        String [] isbnList = {"123"};
        String [] otroIsbnList = {"1234"};
        String curp = "ABC";
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        BooksByUserRepository otherRepository = Mockito.mock(BooksByUserRepository.class);
        UserServiceImpl userService = new UserServiceImpl(userRepository, otherRepository);
        BooksByUserRepository booksByUserRepository = Mockito.mock(BooksByUserRepository.class);
        User user = new User();
        user.setCurp(curp);
       // Mockito.when(userRepository.findById(user.getCurp())).then();
        userRepository.save(user);
        //When:
        Set<BooksByUser> receivedBooksByUser = userService.registerReturn(user, otroIsbnList);
    }
}
