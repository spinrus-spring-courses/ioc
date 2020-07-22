package io.itlabs.springtraining.configuration;

import io.itlabs.springtraining.domain.person.*;
import io.itlabs.springtraining.domain.weapon.Weapon;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;
import java.util.List;

import static io.itlabs.springtraining.domain.weapon.Weapon.Type.DAGGER;

@Configuration
public class DomainBeanDefinitions {

    @Bean
    @Scope("prototype")
    public Weapon dagger() {
        return new Weapon(DAGGER);
    }

    @Bean
    @Walker
    public Human boromir() {
        return new Human("Boromir");
    }

    @Bean
    @Walker
    public Dwarf gimli() {
        return new Dwarf
                .DwarfBuilder()
                .withName("Gimli")
                .build();
    }

    @Bean
    @Walker
    public Hobbit meriadoc() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Meriadoc");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

    @Bean
    @Walker
    public Hobbit pippin() {
        final List<Weapon> weapons = new ArrayList<>();
        weapons.add(dagger());

        final var hobbit = new Hobbit();
        hobbit.setName("Pippin");
        hobbit.setWeapons(weapons);

        return hobbit;
    }

    @Bean
    public Mage saruman(MageFactory mageFactory) {
        return mageFactory.withName("Saruman");
    }

}
