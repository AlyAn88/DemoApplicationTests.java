package com.example.demo.salariati;

import java.util.Set;
import java.util.TreeSet;

public class Electra {
    private static Set<Person> angajati = new TreeSet<>();

    static void addAngajat(Person person) {
        angajati.add(person);
    }

    public static Set<Person> getAngajati() {
        return angajati;
    }
}
