package com.example.crud;

import java.util.Date;

public class Note {

    private static int idGenerator;
    private final int id;
    private String note;
    private final String username;
    private final Date writtenDate;

    public Note(int id, String note, String username, Date writtenDate) {
        this.id = id;
        this.note = note;
        this.username = username;
        this.writtenDate = writtenDate;
    }

    public static void setIdGenerator(int idGenerator) {
        Note.idGenerator = idGenerator;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getUsername() {
        return username;
    }

    public Date getWriteDate() {
        return writtenDate;
    }

    public int getId() {
        return id;
    }
}
