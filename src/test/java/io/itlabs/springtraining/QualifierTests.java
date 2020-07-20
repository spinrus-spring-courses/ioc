package io.itlabs.springtraining;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class QualifierTests {

    @Qualifier("fellowshipOfTheRing")
    @Autowired(required = false)
    public PersonGroup<Person> fellowshipOfTheRing;

    @Qualifier("saruman")
    @Autowired(required = false)
    public Person saruman;

    @Test
    void fellowshipOfTheRingTest() {
        assertThat(fellowshipOfTheRing.persons()).hasSize(9);
        assertThat(fellowshipOfTheRing.persons()).doesNotContain(saruman);

    }
}
