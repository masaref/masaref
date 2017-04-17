package com.masaref.bahez.masaref.MasarefModel;

/**
 * Created by Sherdill Noori on 2017-02-11.
 */
public class Agenda {
    private int id;
    private String name;
    private String  date;
    private int    reminder;

    public Agenda() {
    }

    public Agenda(String name, String date, int reminder) {
        this.name = name;
        this.date = date;
        this.reminder = reminder;
    }

    public Agenda(int id, String name, String date, int reminder) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.reminder = reminder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getReminder() {
        return reminder;
    }

    public void setReminder(int reminder) {
        this.reminder = reminder;
    }
}
