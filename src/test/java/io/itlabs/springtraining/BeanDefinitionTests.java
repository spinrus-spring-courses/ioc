package io.itlabs.springtraining;

import io.itlabs.springtraining.domain.person.Person;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanDefinitionTests {

    @Resource
    public Person aragorn;

    @Resource
    public Person legolas;

    @Resource
    public Person gandalf;

    @Resource
    public Person frodo;

    @Resource
    public Person sam;

    @Test
    void testAragornName() {
        assertThat(aragorn.getName()).isEqualTo("Aragorn");
    }

    @Test
    void testLegolasName() {
        assertThat(legolas.getName()).isEqualTo("Legolas");
    }

    @Test
    void testGandalfName() {
        assertThat(gandalf.getName()).isEqualTo("Gandalf");
    }

    @Test
    void testFrodoName() {
        assertThat(frodo.getName()).isEqualTo("Frodo");
    }

    @Test
    void testSamName() {
        assertThat(sam.getName()).isEqualTo("Sam");
    }
}
