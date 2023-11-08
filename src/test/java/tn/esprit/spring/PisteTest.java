package tn.esprit.spring;



import lombok.extern.slf4j.Slf4j;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import tn.esprit.spring.entities.Piste;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(Piste.class)
public class PisteTest {

    @Mock
    private Piste piste;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void pisteSetterTest() {
        Piste piste = new Piste();
        piste.setNumPiste(1L);
        piste.setNamePiste("simple piste");
        Assertions.assertEquals(1L, piste.getNumPiste());
        Assertions.assertEquals("simple piste", piste.getNamePiste());
    }
}
