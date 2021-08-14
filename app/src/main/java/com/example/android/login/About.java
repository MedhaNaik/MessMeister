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

public class About extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout d;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    MenuItem menuItem;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar) findViewById(R.id.t_bar);
        setSupportActionBar(toolbar);
        d = (DrawerLayout) findViewById(R.id.d_lay);
        drawerToggle = new ActionBarDrawerToggle(this, d, toolbar, R.string.closed, R.string.open);
        d.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        menu = navigationView.getMenu();
        menuItem = menu.findItem(R.id.item7);
        menuItem.setChecked(true);
        //

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Intent intent = null;
        menuItem.setChecked(true);
        if (menuItem.getItemId() == R.id.item1) {
            intent = new Intent(this, homepage.class);
        } else if (menuItem.getItemId() == R.id.item2) {
            intent = new Intent(this, Members.class);
        } else if (menuItem.getItemId() == R.id.item5) {
            intent = new Intent(this, Balance.class);
        } else if (menuItem.getItemId() == R.id.item7) {
            d.closeDrawer(GravityCompat.START);
            return true;
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
}
