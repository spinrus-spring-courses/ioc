package io.itlabs.springtraining.configuration;

import io.itlabs.springtraining.domain.person.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanDefinitions {

    @Bean
    public Person boromir() {
        return new Human("Boromir");
    }

    @Bean
    public Person gimli() {
        return new Dwarf
                .DwarfBuilder()
                .withName("Gimli")
                .build();
    }

    @Bean
    public Person meriadoc() {
        final var hobbit = new Hobbit();
        hobbit.setName("Meriadoc");

        return hobbit;
    }

    @Bean
    public Person pippin() {
        final var hobbit = new Hobbit();
        hobbit.setName("Pippin");

        return hobbit;
    }

    @Bean
    public Person saruman(MageFactory mageFactory) {
        return mageFactory.withName("Saruman");
    }

}
