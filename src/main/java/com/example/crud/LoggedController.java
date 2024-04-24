package com.example.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoggedController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnNew;

    @FXML
    private Button btnViewContent;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colNotesID;

    @FXML
    private TableColumn<?, ?> colTitle;

    @FXML
    private Text lblUsername;

    @FXML
    private TableView<?> tableView;

    @FXML
    void DeleteNote(ActionEvent event) {

    }

    @FXML
    void LogOut(ActionEvent event) {
        HelloController.user = null;
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

    @FXML
    void NewNote(ActionEvent event) {

    }

    @FXML
    void ViewNote(ActionEvent event) {

    }

}
