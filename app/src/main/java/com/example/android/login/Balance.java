package com.example.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Balance extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout d;
    ActionBarDrawerToggle drawerToggle;
    NavigationView navigationView;
    TabLayout tabLayout;
    MenuItem menuItem;
    Menu menu;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        toolbar = (Toolbar) findViewById(R.id.t_bar);
        setSupportActionBar(toolbar);
        d = (DrawerLayout) findViewById(R.id.d_lay);
        drawerToggle = new ActionBarDrawerToggle(this, d, toolbar, R.string.closed, R.string.open);
        d.setDrawerListener(drawerToggle);
        drawerToggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        tabLayout = (TabLayout) findViewById(R.id.t_layout);
        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setTabsFromPagerAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        menu = navigationView.getMenu();
        menuItem = menu.findItem(R.id.item5);
        menuItem.setChecked(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_balance, menu);
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
        if (menuItem.getItemId() == R.id.item1) {
            intent = new Intent(this, homepage.class);
        } else if (menuItem.getItemId() == R.id.item2) {
            intent = new Intent(this, Members.class);
        } else if (menuItem.getItemId() == R.id.item5) {
            d.closeDrawer(GravityCompat.START);
            return true;
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

    public void CSVFile(View view) {
        //toast here
        GenerateCSV generateCSV = new GenerateCSV("MessMisterSpreadsheet.csv", this);
        Toast toast;
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "Excel spreadsheet has been saved in downloads folder.";
        toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    public class PagerAdapter extends FragmentStatePagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }


        @Override
        public Fragment getItem(int position) {
            return BalanceFragment.newInstance(position + 1);

        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0)
                return "MONTH VIEW";
            else
                return "YEAR VIEW";
        }


    }

}
