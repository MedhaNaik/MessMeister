package com.example.android.login;

/**
 * Created by avhirup on 10/27/2015.
 */
public class Owner {

    //Declarations
    private String owner_name;
    private Income earns;

    //Methods
    public Owner(String owner_name) {
        this.owner_name = owner_name;
        Income earns = new Income();
    }

    public void collectFees(MessMember member) {
        // TODO implement here
    }

    public void viewMember() {
        // TODO implement here
    }

    public void createGroup() {
        // TODO implement here
    }

    public void changeRate() {
        // TODO implement here
    }

    public void addRate() {
        // TODO implement here
    }

    public void deleteGroup() {
        // TODO implement here
    }

    public void deleterate() {
        // TODO implement here
    }

    public void viewIncome(Integer month) {
        // TODO implement here
    }

    public void giveHoliday() {
        // TODO implement here
    }

    public void viewDueMembers() {
        // TODO implement here
    }

    public void snoozeMember(MessMember member) {
        // TODO implement here
    }

    public void addExpense() {
        // TODO implement here
    }

    public void setSnoozePeriod(Integer period) {
        // TODO implement here
    }

}
