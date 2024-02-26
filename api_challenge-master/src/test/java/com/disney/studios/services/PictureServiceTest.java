package com.disney.studios.services;

import com.disney.studios.entities.Answer;
import com.disney.studios.entities.Picture;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.disney.studios.repositories.PictureRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PictureServiceTest {
    @Autowired
    private PictureService pictureService;

    @MockBean
    private PictureRepository pictureRepository;

    @MockBean
    private UserService userService;

    @Test
    public void testListByBreed() {
        // Define the mock data
        List<Picture> mockPictures = new ArrayList<>();
        mockPictures.add(new Picture("url1", "breed1"));
        mockPictures.add(new Picture("url2", "breed2"));

        // Mock the behavior of the repository method
        when(pictureRepository.searchByBreed("breed1")).thenReturn(mockPictures);

        // Call the service method
        List<Picture> pictures = pictureService.listByBreed("breed1");

        // Assert the results
        assertNotNull(pictures);
        assertEquals(2, pictures.size());
    }

    @Test
    public void testListAll() {
        // Define the mock data
        List<Picture> mockPictures = new ArrayList<>();
        mockPictures.add(new Picture("url1", "breed1"));
        mockPictures.add(new Picture("url2", "breed2"));

        // Mock the behavior of the repository method
        when(pictureRepository.findAll()).thenReturn(mockPictures);

        // Call the service method
        TreeMap<String, List<Picture>> result = pictureService.listAll();

        // Assert the results
        assertNotNull(result);
        assertEquals(2, result.size());
    }
    @Test
    public void testCreatePicture() {
        // Define the mock data
        Picture existingPicture = new Picture("url1", "breed1");

        // Mock the behavior of the repository method
        when(pictureRepository.searchByUrl("url1")).thenReturn(existingPicture);

        // Call the service method
        pictureService.createPicure("url1", "breed1");

        // Verify the behavior
        verify(pictureRepository, times(0)).save(any(Picture.class));
    }

    @Test
    public void testVote() {
        // Define the mock data
        Picture picture = new Picture("url1", "breed1");
        Long userId = 1L;

        // Mock the behavior of the repository method
        when(pictureRepository.searchByUrl("url1")).thenReturn(picture);
        when(userService.exists(userId)).thenReturn(true);

        // Call the service method
        Answer answer = pictureService.vote("url1", "up", userId);

        // Assert the results
        assertEquals("The vote was successfully saved!!!", answer.getAnswer());
    }

    @Test
    public void testGetInfo() {
        // Define the mock data
        Picture picture = new Picture("url1", "breed1");
        picture.setAdditional_info("Additional information");

        // Mock the behavior of the repository method
        when(pictureRepository.searchByUrl("url1")).thenReturn(picture);

        // Call the service method
        Answer answer = pictureService.getInfo("url1");

        // Assert the results
        assertEquals("Additional information", answer.getAnswer());
        // Add further assertions as needed
    }

    @Test
    public void testAddInfo() {
        // Define ythe mock data
        Picture picture = new Picture("url1", "breed1");

        // Mock the behavior of ythe repository method
        when(pictureRepository.searchByUrl("url1")).thenReturn(picture);

        // Call the service method
        Answer answer = pictureService.addInfo("url1", "Additional information");

        // Assert the results
        assertEquals("Additional information successfully updated", answer.getAnswer());
        assertEquals("Additional information", picture.getAdditional_info());
    }
}
