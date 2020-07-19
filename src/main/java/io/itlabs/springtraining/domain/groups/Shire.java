package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Shire implements PersonGroup {

    private Person frodo;

    private Person sam;

    private Person meriadoc;

    private Person pippin;

    @Override
    public List<Person> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }

    @Autowired
    public void setFrodo(Person frodo) {
        this.frodo = frodo;
    }

    @Autowired
    public void setSam(Person sam) {
        this.sam = sam;
    }

    @Autowired
    public void setMeriadoc(Person meriadoc) {
        this.meriadoc = meriadoc;
    }

    @Autowired
    public void setPippin(Person pippin) {
        this.pippin = pippin;
    }
}
