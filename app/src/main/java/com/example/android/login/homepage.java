package com.example.android.login;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    static SQLiteDatabase sqLiteDatabase = null;
    Toolbar toolbar;
    DrawerLayout d;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    RecycleAdapter recycleAdapter;
    RecyclerView late;
    RecyclerView today;
    ValuesAdapter valuesAdapter;
    com.getbase.floatingactionbutton.FloatingActionsMenu floatingActionsMenu;
    LoginDatabaseHelper loginDatabaseHelper;
    MenuItem menuItem;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().getIntExtra("value", 1) == 1)
            setContentView(R.layout.activity_homepage);
        else
            setContentView(R.layout.homepage2);
        toolbar = (Toolbar) findViewById(R.id.t_bar);
        floatingActionsMenu = (com.getbase.floatingactionbutton.FloatingActionsMenu) findViewById(R.id.multiple_actions);
        setSupportActionBar(toolbar);
        d = (DrawerLayout) findViewById(R.id.d_lay);
        drawerToggle = new ActionBarDrawerToggle(this, d, toolbar, R.string.closed, R.string.open);
        d.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        menuItem = menu.findItem(R.id.item1);
        menuItem.setChecked(true);
        loginDatabaseHelper = new LoginDatabaseHelper(this, "LOGIN_DB", null, 1);
        sqLiteDatabase = loginDatabaseHelper.getWritableDatabase();
        late = (RecyclerView) findViewById(R.id.recycle_list);
        today = (RecyclerView) findViewById(R.id.recycle_list2);
        if (getIntent().getIntExtra("value", 1) == 1)
            setAdapters();

        //Sending Broadcast
        Intent intent = new Intent();
        intent.setAction("NOTIFICATION");
        intent.addFlags(intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_add) {
            Intent intent = new Intent(this, CreateMember.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {

        Intent intent = null;
        menuItem.setChecked(true);
        if (menuItem.getItemId() == R.id.item1) {
            d.closeDrawer(GravityCompat.START);
            return true;
        } else if (menuItem.getItemId() == R.id.item2) {
            intent = new Intent(this, Members.class);
        } else if (menuItem.getItemId() == R.id.item5) {
            intent = new Intent(this, Balance.class);
        } else if (menuItem.getItemId() == R.id.item7) {
            intent = new Intent(this, About.class);
        } else if (menuItem.getItemId() == R.id.nothing) {
            d.closeDrawer(GravityCompat.START);
            return true;
        }
        d.closeDrawer(GravityCompat.START);
        startActivity(intent);
        finish();
        return false;

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        if (d.isDrawerOpen(GravityCompat.START))
            d.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void addFee(View view) {

        floatingActionsMenu.toggle();
        MyDialog addFee = new MyDialog(this, 0, today);
        addFee.show();

    }

    public void addIncome(View view) {

        floatingActionsMenu.toggle();
        MyDialog addFee = new MyDialog(this, 1, today);
        addFee.show();


    }

    public void addExpense(View view) {

        floatingActionsMenu.toggle();
        MyDialog addFee = new MyDialog(this, 2, today);
        addFee.show();

    }

    public void setAdapters() {
        late.setLayoutManager(new LinearLayoutManager(this));
        recycleAdapter = new RecycleAdapter(this);
        recycleAdapter.setPosition(1);
        late.setAdapter(recycleAdapter);
        today.setLayoutManager(new LinearLayoutManager(this));
        valuesAdapter = new ValuesAdapter(this);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date = sdf.format(new Date());
        valuesAdapter.setPosition(date);
        valuesAdapter.setBool(false);
        today.setAdapter(valuesAdapter);
    }

}