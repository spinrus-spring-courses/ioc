package io.itlabs.springtraining;

import io.itlabs.springtraining.domain.groups.Shire;
import io.itlabs.springtraining.domain.person.Hobbit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DependencyInjectionTests {

    @Qualifier("frodo")
    @Autowired(required = false)
    public Hobbit frodo;

    @Qualifier("sam")
    @Autowired(required = false)
    public Hobbit sam;

    @Qualifier("meriadoc")
    @Autowired(required = false)
    public Hobbit meriadoc;

    @Qualifier("pippin")
    @Autowired(required = false)
    public Hobbit pippin;

    @Autowired(required = false)
    private Shire shire;

    @Test
    void testShireHobbits() {
        assertThat(shire).isNotNull();
        assertThat(shire.persons()).containsExactlyInAnyOrder(frodo, sam, meriadoc, pippin);
    }
}
