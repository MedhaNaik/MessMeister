package com.example.android.login;

/**
 * Created by medha on 17/11/15.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by medha on 17/11/15.
 */
public class ExtendPeriod {
    Context context;
    AlertDialog.Builder alert;
    LayoutInflater factory;

    public ExtendPeriod(final Context context, final int type) {

        this.context = context;
        factory = LayoutInflater.from(context);
        alert = new AlertDialog.Builder(context);
        final View textEntryView = factory.inflate(R.layout.edittext1, null);
        final AutoCompleteTextView days = (AutoCompleteTextView) textEntryView.findViewById(R.id.days);
        alert.setView(textEntryView);
        alert.setTitle("Enter No Of Days To Extend");
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        String n = days.getText().toString();
                        int no = Integer.parseInt(n);
                        if (type == 0) {
                            int grpid = new GroupDatabase(context).getgrpId(RecycleAdapter.grpname);
                            ArrayList<Integer> midlist = new MessMemberGroupDatabase(context).getmidlist(grpid);
                            new MemberDatabase(context).extendperiod(midlist, no);
                        }

                        if (type == 1) {
                            int mid = new MemberDatabase(context).getMemberIdbyName(RecycleAdapter.memberName);
                            new MemberDatabase(context).extendperiodbyMid(mid, no);
                        }
                        if (type == 2) {
                            int mid = new MemberDatabase(context).getMemberIdbyName(MemberDescription.name);
                            new MemberDatabase(context).extendperiodbyMid(mid, no);
                            String value = new MemberDatabase(context).getStartmonth(mid);
                            int day = 0;
                            try {
                                Date Sdate = Date.valueOf(value);
                                Calendar cal = Calendar.getInstance();
                                cal.setTime(Sdate);
                                day = cal.get(Calendar.DAY_OF_MONTH);
                            } catch (Exception e) {
                            }
                            MemberDescription.startmval.setText(Integer.toString(day));
                        }
                    }
                }
        );

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener()

                {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.cancel();
                    }
                }

        );


    }

    public void show() {

        alert.show();
    }


}
