package com.example.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class MakeNoteController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnMakeNote;

    @FXML
    private TextArea txtContent;

    @FXML
    private TextField txtTitle;

    @FXML
    void Back(ActionEvent event) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("logged.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene registerScene = new Scene(root);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(registerScene);
        window.setResizable(false);
        window.show();
    }

    @FXML
    void MakeNote(ActionEvent event) {

        String content = txtContent.getText();
        String title = txtTitle.getText();
        String date = LocalDate.now().toString();

        try (Connection c = MySQLConnection.getConnection();
            PreparedStatement s = c.prepareStatement(
                    "INSERT INTO tblnote (noteTitle, notetext, username, writtenDate) " +
                            "VALUES (?, ?, ?, ?)")){

           s.setString(1,title);
           s.setString(2,content);
           s.setString(3, HelloController.user.getName());
           s.setString(4,date);
            s.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("logged.fxml"));
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
