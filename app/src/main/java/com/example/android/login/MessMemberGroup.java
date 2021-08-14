package com.example.android.login;

/**
 * Created by manjush on 10-11-2015.
 */
public class MessMemberGroup {

    private int mid;
    private int gid;

    public MessMemberGroup() {
    }

    public MessMemberGroup(int mid, int gid) {
        this.mid = mid;
        this.gid = gid;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }
}
