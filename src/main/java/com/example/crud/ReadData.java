package com.example.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReadData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement()){

            String query = "SELECT * FROM users";
            ResultSet res = statement.executeQuery(query);

            while (res.next()){
                int id = res.getInt("id");
                String name = res.getString("name");
                String email = res.getString("email");

                System.out.println("ID: " + id);
                System.out.println("Name: " + name);
                System.out.println("Email: " + email);

            }

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
