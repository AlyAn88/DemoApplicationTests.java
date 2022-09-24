package com.example.demo.salariati;

public class Functie {
    private String numeFunctie;
    private String nivel;

    Functie(String numeFunctie, String nivel){
        this.numeFunctie=numeFunctie;
        this.nivel=nivel;
    }

    public String getNumeFunctie() {
        return numeFunctie;
    }

    public void setNumeFunctie(String numeFunctie) {
        this.numeFunctie = numeFunctie;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}
