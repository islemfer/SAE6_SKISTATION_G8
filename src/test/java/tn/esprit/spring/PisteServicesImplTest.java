package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Piste;
import tn.esprit.spring.repositories.IPisteRepository;
import tn.esprit.spring.services.PisteServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PisteServicesImplTest {

    @InjectMocks
    private PisteServicesImpl pisteServices;

    @Mock
    private IPisteRepository pisteRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveAllPistesTest() {
        // Create a sample list of Pistes
        List<Piste> pistes = new ArrayList<>();
        pistes.add(new Piste());
        pistes.add(new Piste());

        when(pisteRepository.findAll()).thenReturn(pistes);

        List<Piste> result = pisteServices.retrieveAllPistes();

        // Verify that the result matches the sample list
        assertEquals(pistes.size(), result.size());
    }

    @Test
    public void addPisteTest() {
        Piste piste = new Piste();

        when(pisteRepository.save(piste)).thenReturn(piste);

        Piste result = pisteServices.addPiste(piste);

        // Verify that the result matches the input Piste
        assertEquals(piste, result);
    }

    @Test
    public void removePisteTest() {
        Long numPiste = 1L;

        pisteServices.removePiste(numPiste);

        // Verify that the delete method was called with the specified numPiste
        verify(pisteRepository, times(1)).deleteById(numPiste);
    }

    @Test
    public void retrievePisteTest() {
        Long numPiste = 1L;
        Piste piste = new Piste();

        when(pisteRepository.findById(numPiste)).thenReturn(Optional.of(piste));

        Piste result = pisteServices.retrievePiste(numPiste);

        // Verify that the result matches the sample Piste
        assertEquals(piste, result);
    }
}
