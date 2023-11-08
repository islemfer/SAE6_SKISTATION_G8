package tn.esprit.spring;

import tn.esprit.spring.entities.Skier;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@Slf4j
@ExtendWith(MockitoExtension.class)
@WebMvcTest(Skier.class)
public class SkierEntityTest {

    private MockMvc mockMvc;

    @Mock
    private Skier skier;



    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(skier).build();
    }

    @Test
    public void skierSetterTest() {
        Skier skier = new Skier();
        skier.setFirstName("nesrine");
        Assertions.assertEquals("nesrine", skier.getFirstName());
    }
}
