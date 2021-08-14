package com.example.android.login;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.sql.Date;
import java.util.Calendar;

public class MemberDescription extends AppCompatActivity {
    public static String name;
    public static TextView startmval;
    MessMember m;
    int mid;
    MemberDatabase memberDatabase;
    TextView phone;
    ToggleButton togglebtn;
    TextView pvalue;
    TextView amtval;
    TextView jdateval;
    ImageButton phoneButton;
    String mphone;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_description);
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);
        //  getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        memberDatabase = new MemberDatabase(this);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        getSupportActionBar().setTitle(name);


        phone = (TextView) findViewById(R.id.phone);
        pvalue = (TextView) findViewById(R.id.pvalue);
        amtval = (TextView) findViewById(R.id.amtval);
        startmval = (TextView) findViewById(R.id.startmval);
        jdateval = (TextView) findViewById(R.id.jdateval);
        togglebtn = (ToggleButton) findViewById(R.id.togglebtn);

        m = new MessMember();
        m.setName(name);

        mid = memberDatabase.getMemberId(m);

        mphone = memberDatabase.getPhone(mid);
        int dueamt = memberDatabase.getdue_amt(mid);

        String pstatus;
        if (dueamt == 0)
            pstatus = "PAID";
        else
            pstatus = "NOT PAID";

        String stdate = memberDatabase.getMemberstartdate(mid);
        String stmonth = memberDatabase.getStartmonth(mid);
        int day = 0;
        try {
            Date Sdate = Date.valueOf(stmonth);
            Calendar cal = Calendar.getInstance();
            cal.setTime(Sdate);
            day = cal.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
        }

        int day1 = 0, month1 = 0, yr = 0;
        try {
            Date jdate = Date.valueOf(stdate);
            Calendar cal = Calendar.getInstance();
            cal.setTime(jdate);
            day1 = cal.get(Calendar.DAY_OF_MONTH);
            month1 = cal.get(Calendar.MONTH);
            yr = cal.get(Calendar.YEAR);
        } catch (Exception e) {
        }

        month1 = month1 + 1;
        String join = Integer.toString(yr) + "-" + Integer.toString(month1 + 1) + "-" + Integer.toString(day1);

        int isactive = memberDatabase.getMemberIsActive(mid);
        String status;
        if (isactive == 0)
            togglebtn.setChecked(false);
        else
            togglebtn.setChecked(true);

        // togglebtn.
        phone.setText(mphone);
        pvalue.setText(pstatus);
        amtval.setText(Integer.toString(dueamt));
        startmval.setText(Integer.toString(day));
        jdateval.setText(join);

        phoneButton = (ImageButton) findViewById(R.id.phoneButton);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + mphone));
                callIntent.setPackage("com.android.server.telecom");
                startActivity(callIntent);

            }
        });

        ImageButton messageButton = (ImageButton) findViewById(R.id.messageButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent whatsapp = new Intent();
                whatsapp.setAction("WHATSAPPACTION");
                sendBroadcast(whatsapp);

            }
        });
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    public void setActiveStatus(View view) {
        togglebtn = (ToggleButton) findViewById(R.id.togglebtn);

        if (togglebtn.isChecked()) {
            memberDatabase.setActive(mid);
        } else
            memberDatabase.setInActive(mid);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_member_description, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.delete) {
            new MemberDatabase(getApplicationContext()).delete(mid);
            Intent intent = new Intent(this, Members.class);
            startActivity(intent);
            finish();
            return true;
        }
        if (id == R.id.action_add_period) {
            ExtendPeriod extendPeriod = new ExtendPeriod(this, 2);
            extendPeriod.show();

        }

        if (id == R.id.action_add_fees) {
            MyDialog addFee = new MyDialog(this, 0, null);
            addFee.show();
        }

        return super.onOptionsItemSelected(item);
    }

    public void editMember(View view) {
        Intent intent = new Intent(this, EditMember.class);
        intent.putExtra("name", name);
        startActivity(intent);
        finish();
    }


}
