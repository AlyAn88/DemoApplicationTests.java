package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

@RestController
public class Metode {

    public Metode() throws SQLException {
    }

    @GetMapping("/hotelAtributs")
    public String getAtributs() throws IllegalAccessException {
        Hotel h = new Hotel();
        h.setDE(4);
        h.setID(3);
        h.setHotelName("Alex");
        h.setLocatie("Iasi");
        h.setCamere(3);

        Field[] f = h.getClass().getDeclaredFields();
        String s = new String();
        int i = 0;
        for (i = 0; i < f.length; i++) {
            f[i].setAccessible(true);
            if (i == 0) {
                s = s.concat("{\n  \"" + f[i].getName() + "\" : " + f[i].get(h) + ",\n ");
            }
            if (i == f.length - 1) {
                s = s.concat(" \"" + f[i].getName() + "\" : " + f[i].get(h) + "\n }");
            }
            if (i != 0 && i != f.length - 1) {
                s = s.concat(" \"" + f[i].getName() + "\" : " + f[i].get(h) + ",\n ");
            }
        }

        return s;
    }


    @GetMapping("/adminHotel")
    public List<Hotel> getHotel(@RequestHeader String token) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "3636");
        // PreparedStatement getHotel = con.prepareStatement("select hotelName, locatie, camere from hotel where token=?");
        PreparedStatement state = con.prepareStatement("select hotelName, locatie, camere from hotel where token=?");
        state.setString(1, token);
        ResultSet rs = state.executeQuery();
        List<Hotel> hotelList = new ArrayList<>();

        while (rs.next()) {
            Hotel newHotel = new Hotel();
            //int id = rs.getInt("id");
            // newHotel.setID(id);
            String hotelName = rs.getString("hotelName");
            newHotel.setHotelName(hotelName);
            String locatie = rs.getString("locatie");
            newHotel.setLocatie(locatie);
            int camere = rs.getInt("camere");
            newHotel.setCamere(camere);
            hotelList.add(newHotel);

        }
        return hotelList;
    }


    @PostMapping("/addHotel")
    String addHotel(@RequestParam String hotelName, @RequestParam String locatie, @RequestParam int camere, @RequestHeader String token) throws SQLException {
        String rol = getRol(token);
        if (rol.equals("administrator")) {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "3636");
            PreparedStatement updateemp = con.prepareStatement("insert into hotel values (?,?,?,?)");
            int idNumber = getIdNumber("hotel");
            updateemp.setInt(1, idNumber);
            updateemp.setString(2, hotelName);
            updateemp.setString(3, locatie);
            updateemp.setInt(4, camere);
            updateemp.setString(5, token);
            updateemp.executeUpdate();
            return (hotelName + " " + locatie);
        } else {
            System.out.println("Nu este permis");
        }

        return hotelName;
    }


    @GetMapping("/login")
    public String log(@RequestParam String userName, @RequestParam String parola) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "3636");
        Statement stmt = con.createStatement();
        String query = "select*from users";
        ResultSet rs = stmt.executeQuery(query);
        //Token myToken = new Token();
        while (rs.next()) {
            String user = rs.getString("UserName");
            String pass = rs.getString("Parola");

            if (user.equals(userName) && pass.equals(parola)) {
                String token = rs.getString("Token");
                // myToken.setToken(token);
                return token;
            }
        }
        return "Userul sau parola nu exista";
    }

    @GetMapping("/getRol")
    public String rol(@RequestHeader String token) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "3636");
        Statement stmt = con.createStatement();
        String query = "select*from users";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String tokenRequest = rs.getString("Token");
            if (tokenRequest.equals(token)) {
                String rol = rs.getString("Rol");
                return rol;
            }
        }
        return null;
    }

    public String returneaza() {
        return "SUCCES";
    }

    @PostMapping("/signup")
    void insert(@RequestParam String userName, @RequestParam String parola, @RequestParam String rol) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "3636");

        PreparedStatement updateemp = con.prepareStatement("insert into users values (?,?,?,?)");
        updateemp.setString(1, userName);
        updateemp.setString(2, parola);
        updateemp.setString(3, rol);
        String token = UUID.randomUUID().toString();
        updateemp.setString(4, token);
        updateemp.executeUpdate();

    }


    //
//
//	Statement stmt= con.createStatement();
//	String query="select*from users";
//	ResultSet rs = stmt.executeQuery(query);
//
//
//        while (rs.next()){
//		String userName = rs.getString("UserName");
//		int parola = rs.getInt("Parola");
//		String rol = rs.getString("Rol");
//		System.out.println(userName+" "+parola+" "+rol);
//	}
    public int getIdNumber(String userHotel) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "3636");
        PreparedStatement id = con.prepareStatement("selct*from ?");
        id.setString(1, userHotel);
        ResultSet rs = id.executeQuery();
        int number = 1;
        while (rs.next()) {
            number++;
        }
        return number;
    }

    public String getRol(String token) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/users", "root", "3636");
        Statement stmt = con.createStatement();
        String query = "select*from users";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            String tokenRequest = rs.getString("Token");
            if (tokenRequest.equals(token)) {
                String rol = rs.getString("Rol");
                return rol;
            }
        }
        return "Nu este nici un rol";
    }
}


