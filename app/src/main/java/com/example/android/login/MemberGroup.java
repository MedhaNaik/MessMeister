package com.example.android.login;

/**
 * Created by manjush on 08-11-2015.
 */
public class MemberGroup {

    //Declarations
    private int group_id;
    private String group_name;
    //Method Implementation

    //Constructor
    public MemberGroup() {
        //this.group_name="";

    }

    public MemberGroup(String group_name) {
        this.group_name = group_name;
    }

    //getters & setters
    public int getGroupID() {
        return group_id;
    }

    public String getGroupName() {
        return group_name;
    }

    public void setGroupName(String group_name) {
        this.group_name = group_name;
    }


    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

}
