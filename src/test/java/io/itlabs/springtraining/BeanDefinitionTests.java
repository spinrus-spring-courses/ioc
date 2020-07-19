package io.itlabs.springtraining;

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

    @Qualifier("legolas")
    @Autowired(required = false)
    public Person legolas;

    @Qualifier("gandalf")
    @Autowired(required = false)
    public Person gandalf;

    @Qualifier("frodo")
    @Autowired(required = false)
    public Person frodo;

    @Qualifier("sam")
    @Autowired(required = false)
    public Person sam;

    @Test
    void testAragornName() {
        assertThat(aragorn).isNotNull();
        assertThat(aragorn.getName()).isEqualTo("Aragorn");
    }

    @Test
    void testLegolasName() {
        assertThat(legolas).isNotNull();
        assertThat(legolas.getName()).isEqualTo("Legolas");
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
}
