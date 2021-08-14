package com.example.android.login;

/**
 * Created by medha on 27/10/15.
 */
public class Income {
    //Declarations
    private String date;
    private String income_name;
    private int amount;

    //Constructors
    public Income() {
        income_name = "";
        amount = 0;
    }

    public Income(String income_name, int amount) {
        this.income_name = income_name;
        this.amount = amount;
    }

    //Methods

    public String getIncomeName() {
        return income_name;
    }

    public void setIncomeName(String name) {
        income_name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

}
