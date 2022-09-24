package com.example.demo.salariati;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
@RestController
public class Controler {

    @PostMapping("addAngajat")
    String addAngajat (@RequestParam String nume, @RequestParam String prenume, @RequestParam int salar , @RequestParam String functie , @RequestParam String nivel ) throws SQLException {
        Person p =new Person();
        if(p.setAngajat(nume,prenume,salar,functie,nivel)){
            return nume + " " + prenume + " este inregistrat";
        }

        return nume + " " + prenume + " este deja inregistrat";
    }

   /*public static void main(String[] args) throws SQLException {



        Electra.addAngajat(new Person("Jipa", "Alexandru", 4000, "Electronist", "Avansat"));
        Electra.addAngajat(new Person("Jipa", "hhhhhh", 4000, "Operator", "Avansat"));
        Electra.addAngajat(new Person("tfgjy", "Alexandru", 4000,"Operator", "Avansat"));
        Metode.startWork();
    }*/
}
