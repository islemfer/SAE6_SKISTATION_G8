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
import tn.esprit.spring.controllers.PisteRestController;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.services.IPisteServices;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class PisteRestControllerTest {

    @InjectMocks
    private PisteRestController pisteController;

    @Mock
    private IPisteServices pisteServices;

    private MockMvc mockMvc;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(pisteController).build();
    }

    @Test
    public void addPisteTest() throws Exception {
        Piste piste = new Piste();

        when(pisteServices.addPiste(any(Piste.class))).thenReturn(piste);

        mockMvc.perform(MockMvcRequestBuilders.post("/piste/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {
        Long numPiste = 1L;
        Piste piste = new Piste();

        when(pisteServices.retrievePiste(numPiste)).thenReturn(piste);

        mockMvc.perform(MockMvcRequestBuilders.get("/piste/get/{id-piste}", numPiste)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // You can also use assertThat to verify the response content
        // MockMvcResultMatchers.content().json(expectedResponseJson) and check that it matches the expected JSON response.
    }

    @Test
    public void deleteByIdTest() throws Exception {
        Long numPiste = 1L;

        mockMvc.perform(MockMvcRequestBuilders.delete("/piste/delete/{id-piste}", numPiste)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // Verify that the removePiste method was called with the specified numPiste
        verify(pisteServices, times(1)).removePiste(numPiste);
    }

    @Test
    public void getAllPistesTest() throws Exception {
        List<Piste> pistes = Arrays.asList(new Piste(), new Piste());

        when(pisteServices.retrieveAllPistes()).thenReturn(pistes);

        mockMvc.perform(MockMvcRequestBuilders.get("/piste/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // You can use additional assertions to verify the response content.
    }
}
