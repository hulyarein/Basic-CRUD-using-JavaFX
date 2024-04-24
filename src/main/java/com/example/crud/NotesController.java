package com.example.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class NotesController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnSaveChanges;

    @FXML
    private Text lblDate;

    @FXML
    private TextField txtTitle;

    @FXML
    private TextArea txtContent;

    static Note selectedNote;

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
    public void initialize(){
        txtTitle.setText(NotesController.selectedNote.getTitle());
        txtContent.setText(NotesController.selectedNote.getNote());
        lblDate.setText(NotesController.selectedNote.getWrittenDate());
    }
    @FXML
    void SaveChanges(ActionEvent event) {

        try(Connection c = MySQLConnection.getConnection();
            PreparedStatement s = c.prepareStatement("UPDATE tblnote SET noteTitle = ? ," +
                    "notetext = ?, writtenDate = ? WHERE noteID = ?")){

            String title = txtTitle.getText();
            String content = txtContent.getText();
            String date = LocalDate.now().toString();

            s.setString(1, title);
            s.setString(2, content);
            s.setString(3, date);
            s.setInt(4, selectedNote.getId());

            s.executeUpdate();

            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Changes saved successfully.");
            a.show();
        } catch (SQLException e){
            e.printStackTrace();
        }




    }



}
