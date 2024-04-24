package com.example.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField registeremail;

    @FXML
    private TextField registerpassword;

    @FXML
    private TextField registerusername;

    @FXML
    void onRegister(ActionEvent event) {
        if(registerusername.getText().isEmpty() || registerpassword.getText().isEmpty()){
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Please fill in all fields!");
            a.show();
            return;
        }

        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement checkUser = c.prepareStatement("SELECT * FROM tblUser WHERE name = ?")
        ) {
            String username = registerusername.getText();
            String password = registerpassword.getText();
            System.out.println(username + " " + password + " " );

            checkUser.setString(1, username);
            ResultSet rs = checkUser.executeQuery();

            if (!rs.next()) {
                PreparedStatement s = c.prepareStatement("INSERT INTO tblUser (name, password) VALUES (?, ?)");
                s.setString(1, username);
                s.setString(2, password);
                s.execute();

                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setContentText("User registered successfully!");
                a.show();
            } else {
                System.out.println("User already exists!");
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("User already exists!");
                a.show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void backtoLogin(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene registerScene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.setResizable(false);
        window.show();
    }
}
