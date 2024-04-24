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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField txtpassword;

    @FXML
    private TextField txtusername;

    public static User user;

    @FXML
    void onLogin(ActionEvent event) {
        try(
                Connection c = MySQLConnection.getConnection();
                Statement s = c.createStatement();
        ) {
            String username =  txtusername.getText();
            String password = txtpassword.getText();
            String query = "SELECT * FROM tblUser WHERE name = '" + username + "' AND password = '" + password + "'";

            ResultSet resultset = s.executeQuery(query);
            if (resultset.next() ) {

                int id = s.getResultSet().getInt("userId");
                String name = s.getResultSet().getString("name");
                user = new User(id, name);



                Parent root = FXMLLoader.load(getClass().getResource("logged.fxml"));
                Scene homepageScene = new Scene(root);

                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(homepageScene);
                window.setResizable(false);
                window.show();

            } else {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Invalid username or password");
                a.show();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }



    }

    @FXML
    void signup(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("register-view.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene registerScene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setResizable(false);
        window.setScene(registerScene);
        window.show();
    }


}
