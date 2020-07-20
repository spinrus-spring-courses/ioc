package io.itlabs.springtraining.domain.groups;

import io.itlabs.springtraining.domain.PersonGroup;
import io.itlabs.springtraining.domain.person.Hobbit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Shire implements PersonGroup<Hobbit> {

    private Hobbit frodo;

    private Hobbit sam;

    private Hobbit meriadoc;

    private Hobbit pippin;

    @Override
    public List<Hobbit> persons() {
        return Arrays.asList(frodo, sam, meriadoc, pippin);
    }

    @Autowired
    public void setFrodo(Hobbit frodo) {
        this.frodo = frodo;
    }

    @Autowired
    public void setSam(Hobbit sam) {
        this.sam = sam;
    }

    @Autowired
    public void setMeriadoc(Hobbit meriadoc) {
        this.meriadoc = meriadoc;
    }

    @Autowired
    public void setPippin(Hobbit pippin) {
        this.pippin = pippin;
    }
}
