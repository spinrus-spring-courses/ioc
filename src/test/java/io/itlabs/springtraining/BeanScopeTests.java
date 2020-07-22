package io.itlabs.springtraining;

import io.itlabs.springtraining.domain.person.Hobbit;
import io.itlabs.springtraining.domain.weapon.Weapon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import static io.itlabs.springtraining.domain.weapon.Weapon.Type.DAGGER;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeanScopeTests {

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

    @Qualifier("dagger")
    @Autowired(required = false)
    public Weapon dagger;

    @Test
    void daggerScopeTest() {
        assertThat(frodo.getWeapons()).hasSize(1);
        assertThat(frodo.getWeapons()).doesNotContain(dagger);
        assertThat(frodo.getWeapons().get(0).getType()).isEqualTo(DAGGER);

        assertThat(sam.getWeapons()).hasSize(1);
        assertThat(sam.getWeapons()).doesNotContain(dagger);
        assertThat(sam.getWeapons().get(0).getType()).isEqualTo(DAGGER);

        assertThat(meriadoc.getWeapons()).hasSize(1);
        assertThat(meriadoc.getWeapons()).doesNotContain(dagger);
        assertThat(meriadoc.getWeapons().get(0).getType()).isEqualTo(DAGGER);

        assertThat(pippin.getWeapons()).hasSize(1);
        assertThat(pippin.getWeapons()).doesNotContain(dagger);
        assertThat(pippin.getWeapons().get(0).getType()).isEqualTo(DAGGER);
    }
}
