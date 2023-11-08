package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tn.esprit.spring.controllers.SkierRestController;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.services.ISkierServices;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SkierRestControllerTest {

    @InjectMocks
    private SkierRestController skierController;

    @Mock
    private ISkierServices skierServices;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(skierController).build();
    }

    @Test
    public void addSkierTest() throws Exception {
        Skier skier = new Skier();

        when(skierServices.addSkier(any(Skier.class))).thenReturn(skier);

        mockMvc.perform(MockMvcRequestBuilders.post("/skier/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {
        Long numSkier = 1L;
        Skier skier = new Skier();

        when(skierServices.retrieveSkier(numSkier)).thenReturn(skier);

        mockMvc.perform(MockMvcRequestBuilders.get("/skier/get/{id-skier}", numSkier)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // You can also use assertThat to verify the response content
        // MockMvcResultMatchers.content().json(expectedResponseJson) and check that it matches the expected JSON response.
    }

    @Test
    public void deleteByIdTest() throws Exception {
        Long numSkier = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/skier/delete/{id-skier}", numSkier)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the removeSkier method was called with the specified numSkier
        verify(skierServices, times(1)).removeSkier(numSkier);
    }


    @Test
    public void getAllSkiersTest() throws Exception {
        List<Skier> skiers = Arrays.asList(new Skier(), new Skier());

        when(skierServices.retrieveAllSkiers()).thenReturn(skiers);

        mockMvc.perform(MockMvcRequestBuilders.get("/skier/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // You can use additional assertions to verify the response content.
    }
}
