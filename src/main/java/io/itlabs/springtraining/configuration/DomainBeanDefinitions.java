package io.itlabs.springtraining.configuration;

import io.itlabs.springtraining.domain.person.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanDefinitions {

    @Bean
    public Human boromir() {
        return new Human("Boromir");
    }

    @Bean
    public Dwarf gimli() {
        return new Dwarf
                .DwarfBuilder()
                .withName("Gimli")
                .build();
    }

    @Bean
    public Hobbit meriadoc() {
        final var hobbit = new Hobbit();
        hobbit.setName("Meriadoc");

        return hobbit;
    }

    @Bean
    public Hobbit pippin() {
        final var hobbit = new Hobbit();
        hobbit.setName("Pippin");

        return hobbit;
    }

    @Bean
    public Mage saruman(MageFactory mageFactory) {
        return mageFactory.withName("Saruman");
    }

}
