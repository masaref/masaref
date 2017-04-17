package com.masaref.bahez.masaref.MasarefModel;

/**
 * Created by Sherdill Noori on 2017-02-11.
 */
public class Income {
    private int id;
    private double amount;
    private String description;
    private String date;
    private int categoryId;
    private int cardId;
    private String iconAddress;

    public Income() {
    }

    public Income(int id, double amount, String description, String date, int categoryId, int cardId, String iconAddress) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.categoryId = categoryId;
        this.cardId = cardId;
        this.iconAddress = iconAddress;
    }

    public Income(double amount, String description, String date, int categoryId, int cardId, String iconAddress) {
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.categoryId = categoryId;
        this.cardId = cardId;
        this.iconAddress = iconAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getIconAddress() {
        return iconAddress;
    }

    public void setIconAddress(String iconAddress) {
        this.iconAddress = iconAddress;
    }
}
