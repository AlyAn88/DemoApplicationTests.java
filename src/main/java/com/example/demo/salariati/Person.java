package com.example.demo.salariati;

import java.sql.*;

public class Person implements Comparable<Person> {
    private String nume;
    private String prenume;
    private int salariu;
    private String numeFunctie;
    private String nivel;

    public boolean setAngajat(String nume, String prenume, int salariu, String numeFunctie, String nivel) throws SQLException {
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.numeFunctie = numeFunctie;
        this.nivel = nivel;
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/angajati", "root", "3636");
        PreparedStatement updateemp = con.prepareStatement("insert into angajat values (?,?,?,?,?,?)");
        updateemp.setInt(1, 0);
        updateemp.setString(2, nume);
        updateemp.setString(3, prenume);
        updateemp.setInt(4, salariu);
        updateemp.setString(5, numeFunctie);
        updateemp.setString(6, nivel);
        Statement all = con.createStatement();
        String angajati = "select * from angajat where nume = "+"'" +nume+"'";
        ResultSet rs = all.executeQuery(angajati);
        boolean is = true;
        while (rs.next()) {
            if (rs.getString("nume").equals(nume) && rs.getString("prenume").equals(prenume)) {
                System.out.println(nume + " " + prenume + " este deja inregistrat");
                is = false;
            }
        }
        if (is) {
            updateemp.executeUpdate();
        }
        return is;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getSalariu() {
        return salariu;
    }

    public void setSalariu(int salariu) {
        this.salariu = salariu;
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

    @Override
    public int compareTo(Person o) {
        int i = this.nume.compareTo(o.nume);
        if (i == 0) {
            return this.prenume.compareTo(o.prenume);
        }
        return i;
    }
}
