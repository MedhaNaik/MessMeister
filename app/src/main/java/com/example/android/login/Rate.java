package com.example.android.login;

/**
 * Created by avhirup on 10/27/2015.
 */
public class Rate {

    //Declarations
    private String category;
    private int amount;
    private int rate_id;


    //Constructors

    public Rate() {
        this.category = "";
        amount = 0;
    }

    public Rate(String category, int amount) {
        this.amount = amount;
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRate_id() {
        return rate_id;
    }

    public void setRate_id(int rate_id) {
        this.rate_id = rate_id;
    }
}
