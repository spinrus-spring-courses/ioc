package io.itlabs.springtraining.domain.person;

public class Dwarf extends Person {

    public static class DwarfBuilder {

        private String name;

        public DwarfBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public Dwarf build() {
            Dwarf dwarf = new Dwarf();
            dwarf.name = name;

            return dwarf;
        }
    }
}
