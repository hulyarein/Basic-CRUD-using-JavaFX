package com.example.crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData {
    public static void main(String[] args) {
        try (Connection c = MySQLConnection.getConnection();
             PreparedStatement statement = c.prepareStatement(
                     "UPDATE users SET name = ? WHERE id = ?"
             )){

            String name = "Pooch";
            int id = 1;
            statement.setString(1, name);
            statement.setInt(2,id);
            int rows = statement.executeUpdate();
            System.out.println("Rows updated: " + rows);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
