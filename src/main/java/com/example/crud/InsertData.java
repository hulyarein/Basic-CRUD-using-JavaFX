package com.example.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "INSERT INTO users (name,email) VALUES (?,?)"
             )){

            String name = "Julia Laine Segundo";
            String email = "julialaine.segundo@cit.edu";

            statement.setString(1,name);
            statement.setString(2, email);
            int rows = statement.executeUpdate();
            System.out.println("Rows inserted: " + rows);

        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
