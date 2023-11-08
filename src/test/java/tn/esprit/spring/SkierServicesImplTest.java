package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Skier;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.services.SkierServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SkierServicesImplTest {

    @InjectMocks
    private SkierServicesImpl skierServices;

    @Mock
    private ISkierRepository skierRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void retrieveAllSkiersTest() {
        // Create a sample list of Skiers
        List<Skier> skiers = new ArrayList<>();
        skiers.add(new Skier());
        skiers.add(new Skier());

        when(skierRepository.findAll()).thenReturn(skiers);

        List<Skier> result = skierServices.retrieveAllSkiers();

        // Verify that the result matches the sample list
        assertEquals(skiers.size(), result.size());
    }

    @Test
    public void addSkierTest() {
        Skier skier = new Skier();

        when(skierRepository.save(skier)).thenReturn(skier);

        Skier result = skierServices.addSkier(skier);

        // Verify that the result matches the input Skier
        assertEquals(skier, result);
    }

    @Test
    public void removeSkierTest() {
        Long numSkier = 1L;

        skierServices.removeSkier(numSkier);

        // Verify that the delete method was called with the specified numSkier
        verify(skierRepository, times(1)).deleteById(numSkier);
    }

    @Test
    public void retrieveSkierTest() {
        Long numSkier = 1L;
        Skier skier = new Skier();

        when(skierRepository.findById(numSkier)).thenReturn(Optional.of(skier));

        Skier result = skierServices.retrieveSkier(numSkier);

        // Verify that the result matches the sample Skier
        assertEquals(skier, result);
    }
}

