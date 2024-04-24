package com.example.crud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("GirlJournal");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        try(Connection c = MySQLConnection.getConnection();
            Statement s = c.createStatement()) {
            var table = "CREATE TABLE IF NOT EXISTS tblUser (" +
                    "userID INT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(256) UNIQUE," +
                    "password VARCHAR(256) NOT NULL)";
            s.execute(table);

            var notes = "CREATE TABLE IF NOT EXISTS tblNote (" +
                    "noteID INTEGER PRIMARY KEY AUTO_INCREMENT," +
                     "notetext TEXT NOT NULL," + "username VARCHAR(256) NOT NULL," +
                    " writtenDate DATE NOT NULL," +
                     "FOREIGN KEY(username) REFERENCES tblUser(name))";
            s.execute(notes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        launch();
    }
}