package com.example.android.login;

/**
 * Created by medha on 27/10/15.
 */
public class FeeCollected {

    private static int fee_id;
    private String date;
    private String fee_name;
    private float amount;
    public FeeCollected() {

    }

    public FeeCollected(String fee_name, float amount) {
        this.fee_name = fee_name;
        this.amount = amount;
    }

    public int getFeeId() {
        return fee_id;
    }

    public String getFeeName() {
        return fee_name;
    }

    public void setFeeName(String name) {
        fee_name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

    }
}
