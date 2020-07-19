package io.itlabs.springtraining;

import io.itlabs.springtraining.application.impl.LoggingGreetingService;
import io.itlabs.springtraining.domain.person.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanDefinitionTests {

    @Qualifier("aragorn")
    @Autowired(required = false)
    public Person aragorn;

    @Qualifier("boromir")
    @Autowired(required = false)
    public Person boromir;

    @Qualifier("legolas")
    @Autowired(required = false)
    public Person legolas;

    @Qualifier("gimli")
    @Autowired(required = false)
    public Person gimli;

    @Qualifier("gandalf")
    @Autowired(required = false)
    public Person gandalf;

    @Qualifier("frodo")
    @Autowired(required = false)
    public Person frodo;

    @Qualifier("sam")
    @Autowired(required = false)
    public Person sam;

    @Qualifier("meriadoc")
    @Autowired(required = false)
    public Person meriadoc;

    @Qualifier("pippin")
    @Autowired(required = false)
    public Person pippin;

    @Qualifier("saruman")
    @Autowired(required = false)
    public Person saruman;

    @Autowired(required = false)
    public LoggingGreetingService greetingService;

    @Test
    void testAragornName() {
        assertThat(aragorn).isNotNull();
        assertThat(aragorn.getName()).isEqualTo("Aragorn");
    }

    @Test
    void testBoromirName() {
        assertThat(boromir).isNotNull();
        assertThat(boromir.getName()).isEqualTo("Boromir");
    }

    @Test
    void testLegolasName() {
        assertThat(legolas).isNotNull();
        assertThat(legolas.getName()).isEqualTo("Legolas");
    }

    @Test
    void testGimliName() {
        assertThat(gimli).isNotNull();
        assertThat(gimli.getName()).isEqualTo("Gimli");
    }

    @Test
    void testGandalfName() {
        assertThat(gandalf).isNotNull();
        assertThat(gandalf.getName()).isEqualTo("Gandalf");
    }

    @Test
    void testFrodoName() {
        assertThat(frodo).isNotNull();
        assertThat(frodo.getName()).isEqualTo("Frodo");
    }

    @Test
    void testSamName() {
        assertThat(sam).isNotNull();
        assertThat(sam.getName()).isEqualTo("Sam");
    }

    @Test
    void testMeriadocName() {
        assertThat(meriadoc).isNotNull();
        assertThat(meriadoc.getName()).isEqualTo("Meriadoc");
    }

    @Test
    void testPippinName() {
        assertThat(pippin).isNotNull();
        assertThat(pippin.getName()).isEqualTo("Pippin");
    }

    @Test
    void testSarumanName() {
        assertThat(saruman).isNotNull();
        assertThat(saruman.getName()).isEqualTo("Saruman");
    }

    @Test
    void testGreeting() {
        assertThat(greetingService).isNotNull();
        assertThat(greetingService.getGreeting()).isNotEmpty();
    }
}
