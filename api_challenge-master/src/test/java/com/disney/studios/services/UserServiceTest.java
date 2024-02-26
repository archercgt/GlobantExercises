package com.disney.studios.services;


import com.disney.studios.entities.Answer;
import com.disney.studios.entities.User;
import com.disney.studios.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @MockBean
    private UserRepository userRepository;
    @Test
    public void testCreateUser() {
        Long userId = 1L;
        String fullName = "John Doe";
        when(userRepository.searchByFullName(fullName)).thenReturn(null);
        Answer answer = userService.createUser(userId, fullName);
        assertEquals("User successfully created", answer.getAnswer());
    }

    @Test
    public void testListUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User(1L, "John Doe"));
        users.add(new User(2L, "Jane Smith"));
        when(userRepository.findAll()).thenReturn(users);
        List<User> userList = userService.listUsers();
        assertEquals(users.size(), userList.size());
        assertEquals(users.get(0).getFullName(), userList.get(0).getFullName());
        assertEquals(users.get(1).getFullName(), userList.get(1).getFullName());
    }

    @Test
    public void testExists() {
        Long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(new User(userId, "John Doe")));
        assertTrue(userService.exists(userId));
        Long nonExistentUserId = 2L;
        when(userRepository.findById(nonExistentUserId)).thenReturn(Optional.empty());
        assertFalse(userService.exists(nonExistentUserId));
    }
}
