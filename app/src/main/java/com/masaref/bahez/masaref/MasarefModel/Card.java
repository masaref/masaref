package com.masaref.bahez.masaref.MasarefModel;

/**
 * Created by Sherdill Noori on 2017-02-11.
 */
public class Card {
    private int id;
    private String name;
    private double total_amount;
    private double current_amount;


    public Card() {
    }

    public Card(int id, String name, double amount,double current_amount) {
        this.id = id;
        this.name = name;
        this.total_amount = amount;
        this.current_amount=current_amount;
    }

    public Card(String name, double amount,double current_amount) {
        this.name = name;
        this.total_amount = amount;
        this.current_amount=current_amount;
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

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getCurrent_amount() {
        return current_amount;
    }

    public void setCurrent_amount(double current_amount) {
        this.current_amount = current_amount;
    }
}
