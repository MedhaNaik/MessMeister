package com.example.android.login;

/**
 * Created by medha on 27/10/15.
 */
public class MessMember {


    private int member_id;
    private String name;
    private String start_date;
    private String startof_month;
    private Boolean is_active;
    private int rate_id;
    private Boolean has_paid;
    private Boolean is_late;
    private int due_amount;
    private String phone;
    private int img_id;
    public MessMember() {
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMember_id() {
        return member_id;
    }

    public void setMember_id(int member_id) {
        this.member_id = member_id;
    }

    public String getStartof_month() {
        return startof_month;
    }

    public void setStartof_month(String startof_month) {
        this.startof_month = startof_month;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public int getRate_id() {
        return rate_id;
    }

    public void setRate_id(int rate_id) {
        this.rate_id = rate_id;
    }

    public Boolean getHas_paid() {
        return has_paid;
    }

    public void setHas_paid(Boolean has_paid) {
        this.has_paid = has_paid;
    }

    public Boolean getIs_late() {
        return is_late;
    }

    public void setIs_late(Boolean is_late) {
        this.is_late = is_late;
    }

    public int getDue_amount() {
        return due_amount;
    }

    public void setDue_amount(int due_amount) {
        this.due_amount = due_amount;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }
}

