package com.example.crud;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;
import java.sql.*;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML private Label welcomeText;
    @FXML private TextField username;
    @FXML private TextField password;

    @FXML private TextField registerusername;
    @FXML private TextField registerpassword;
    @FXML private TextField registeremail;

//    @FXML private Label registerStatus;
//
//
//    @FXML
//    private Label idOfAnyControl;



    @FXML
    protected void onLogin(ActionEvent event) {
        try (Connection c = MySQLConnection.getConnection();
             Statement statement = c.createStatement()) {

            String query = "SELECT * FROM users WHERE name = '" + username.getText() + "' AND password = '" + password.getText() + "'";
            ResultSet res = statement.executeQuery(query);

            if (res.next()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("register-view.fxml"));
                Parent parent = loader.load();
                Scene scene = new Scene(parent);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } else {
                welcomeText.setText("Invalid login, please try again.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

//
//
//    private void switchScene(Event event, String fxmlFile) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
//            Parent parent = loader.load();
//            Scene scene = new Scene(parent);
//            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    public void onRegister(ActionEvent event) {
        String username = registerusername.getText();
        String password = registerpassword.getText();
        String email = registeremail.getText();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
           // registerStatus.setText("Please fill in all fields.");
            return;
        }


        try (Connection connection = MySQLConnection.getConnection()) {
            String query = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR(50) NOT NULL," +
                    "password VARCHAR(100) NOT NULL," +
                    "email VARCHAR(100) NOT NULL)";
            try (Statement statement = connection.createStatement()) {
                statement.execute(query);
            }

            String insertUserQuery = "INSERT INTO users (name, password, email) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(insertUserQuery)) {
                statement.setString(1, username);
                statement.setString(2, password);
                statement.setString(3, email);
                int rows = statement.executeUpdate();

//                if (rows > 0) {
//                    registerStatus.setText("Registration successful.");
//                } else {
//                    registerStatus.setText("Registration failed.");
//                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//    public void Signup(MouseEvent mouseEvent) {
//        switchScene (mouseEvent,"register-vew.fxml");
//    }
}
