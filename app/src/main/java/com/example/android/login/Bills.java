package com.example.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class Bills extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout d;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    private int selectionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills);
        toolbar = (Toolbar) findViewById(R.id.t_bar);
        setSupportActionBar(toolbar);
        d = (DrawerLayout) findViewById(R.id.d_lay);
        drawerToggle = new ActionBarDrawerToggle(this, d, toolbar, R.string.closed, R.string.open);
        d.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null)
            selectionId = R.id.item1;
        else
            selectionId = savedInstanceState.getInt("selection");
        //helloooooo tttt
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bills, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;
        menuItem.setChecked(true);
        selectionId = menuItem.getItemId();
        if (menuItem.getItemId() == R.id.item1) {
            intent = new Intent(this, homepage.class);
        } else if (menuItem.getItemId() == R.id.item2) {
            intent = new Intent(this, Members.class);
        } else if (menuItem.getItemId() == R.id.item5) {
            intent = new Intent(this, Balance.class);
        } else if (menuItem.getItemId() == R.id.item7) {
            intent = new Intent(this, About.class);
        }
        d.closeDrawer(GravityCompat.START);
        startActivity(intent);
        finish();
        return false;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("selection", selectionId);
    }

    @Override
    public void onBackPressed() {
        if (d.isDrawerOpen(GravityCompat.START))
            d.closeDrawer(GravityCompat.START);
        else
            super.onBackPressed();
    }

    public void create(View view) {
        Intent intent = new Intent(this, CreateBill.class);
        startActivity(intent);
    }
}
