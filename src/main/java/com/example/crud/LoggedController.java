package com.example.crud;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private TableColumn<Note, String> colDate;

    @FXML
    private TableColumn<Note, String> colNotesID;

    @FXML
    private TableColumn<Note, String> colTitle;

    @FXML
    private Text lblUsername;

    @FXML
    private TableView<Note> tableView;
    ObservableList<Note>notes = FXCollections.observableArrayList();


    @FXML
    public void initialize(){
        lblUsername.setText("Hello " + HelloController.user.getName());
        try(Connection c = MySQLConnection.getConnection();
        Statement s = c.createStatement()){

            String sql = "SELECT * FROM tblnote WHERE username = '" + HelloController.user.getName() + "'";
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("noteID");
                String title = rs.getString("noteTitle");
                String date = rs.getDate("writtenDate").toString();
                String content = rs.getString("notetext");
                Note note = new Note(id,content,title,date);
                notes.add(note);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        colDate.setCellValueFactory(
                new PropertyValueFactory<>("writtenDate")
        );

        colNotesID.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colTitle.setCellValueFactory(
                new PropertyValueFactory<>("title")
        );

        tableView.setItems(notes);
    }

    @FXML
    void DeleteNote(ActionEvent event) {
        try(Connection c = MySQLConnection.getConnection();
            Statement s = c.createStatement()){

            String sql = "DELETE FROM tblnote WHERE noteID = " + tableView.getSelectionModel().getSelectedItem().getId();
            s.execute(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }

        notes.clear();

        try(Connection c = MySQLConnection.getConnection();
            Statement s = c.createStatement()){

            String sql = "SELECT * FROM tblnote WHERE username = '" + HelloController.user.getName() + "'";
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("noteID");
                String title = rs.getString("noteTitle");
                String date = rs.getDate("writtenDate").toString();
                String content = rs.getString("notetext");
                Note note = new Note(id,content,title,date);
                notes.add(note);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        tableView.setItems(notes);
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("Deleted successfully.");
        a.show();
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
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("make-note.fxml"));
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
    void ViewNote(ActionEvent event) {
        Note note = tableView.getSelectionModel().getSelectedItem();
        NotesController.selectedNote = note;

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("notes-view.fxml"));
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
