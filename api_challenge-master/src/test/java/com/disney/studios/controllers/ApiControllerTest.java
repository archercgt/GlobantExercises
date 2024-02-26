package com.disney.studios.controllers;

import com.disney.studios.entities.Answer;
import com.disney.studios.entities.Picture;
import com.disney.studios.entities.User;
import com.disney.studios.services.PictureService;
import com.disney.studios.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PictureService pictureService;

    @MockBean
    private UserService userService;

    @Test
    void testIndex() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"answer\":\"Welcome to Dogs API challenge\"}"));
    }

    @Test
    void testListByBreed() throws Exception {
        String breed = "Labrador";
        List<Picture> expectedList = new ArrayList<>();
        Mockito.when(pictureService.listByBreed(breed)).thenReturn(expectedList);
        mockMvc.perform(get("/api/list/{breed}", breed))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void testVote() throws Exception {
        Mockito.when(pictureService.vote(anyString(), anyString(), Mockito.anyLong())).thenReturn(new Answer("Vote received"));
        mockMvc.perform(patch("/api/vote")
                        .header("url", "someUrl")
                        .header("vote", "up")
                        .header("userId", "123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.answer").value("Vote received"));
    }

    @Test
    void testVote_getMethodNotAllowed() throws Exception {
        mockMvc.perform(get("/api/vote"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    void testGetInfo() throws Exception {
        Mockito.when(pictureService.getInfo(anyString())).thenReturn(new Answer("Info retrieved"));
        mockMvc.perform(get("/api/info")
                        .header("url", "someUrl"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.answer").value("Info retrieved"));
    }

    @Test
    void testAddInfo() throws Exception {
        Mockito.when(pictureService.addInfo(anyString(), anyString())).thenReturn(new Answer("Info added"));
        mockMvc.perform(patch("/api/info")
                        .header("url", "someUrl")
                        .header("info", "someInfo"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.answer").value("Info added"));
    }

    @Test
    void testCreateUser() throws Exception {
        Mockito.when(userService.createUser(Mockito.anyLong(), anyString())).thenReturn(new Answer("User created"));
        mockMvc.perform(put("/api/users")
                        .header("id", "123")
                        .header("name", "John"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.answer").value("User created"));
    }

    @Test
    void testListUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        Mockito.when(userService.listUsers()).thenReturn(userList);
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
