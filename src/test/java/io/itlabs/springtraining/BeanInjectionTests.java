package io.itlabs.springtraining;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanInjectionTests {

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

    @Autowired(required = false)
    private PersonGroup shire;

    @Test
    void testShireHobbits() {
        assertThat(shire).isNotNull();
        assertThat(shire.persons()).containsExactlyInAnyOrder(frodo, sam, meriadoc, pippin);
    }
}
