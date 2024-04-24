package com.example.crud;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class Note {

    private final IntegerProperty id;
    private final StringProperty note;
    private final StringProperty title;
    private final StringProperty writtenDate;

    public Note(Integer id, String note,String title, String writtenDate) {
        this.id = new SimpleIntegerProperty(id);
        this.note = new SimpleStringProperty(note);
        this.writtenDate = new SimpleStringProperty(writtenDate);
        this.title = new SimpleStringProperty(title);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getNote() {
        return note.get();
    }

    public StringProperty noteProperty() {
        return note;
    }

    public void setNote(String note) {
        this.note.set(note);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getWrittenDate() {
        return writtenDate.get();
    }

    public StringProperty writtenDateProperty() {
        return writtenDate;
    }

    public void setWrittenDate(String writtenDate) {
        this.writtenDate.set(writtenDate);
    }
}
