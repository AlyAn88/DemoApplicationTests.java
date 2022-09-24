package com.example.demo.salariati;

import java.sql.*;

public class Metode {
    static void startWork () throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/angajati", "root", "3636");
        Statement all =con.createStatement();
        String angajati="select * from angajat";
        ResultSet rs= all.executeQuery(angajati);
        while (rs.next()){
            System.out.println(rs.getString("nume")+" "+ rs.getString("prenume") + " is working");
        }

    }
}
