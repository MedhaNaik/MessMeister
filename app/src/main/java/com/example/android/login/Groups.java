package com.example.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class Groups extends AppCompatActivity {

    RecycleAdapter recycleAdapter;
    RecyclerView recyclerView;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        name = intent.getStringExtra("grpname");
        getSupportActionBar().setTitle(name);
        recycleAdapter = new RecycleAdapter(this);
        recycleAdapter.setPosition(5);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recycleAdapter);
        recycleAdapter.setgrpname(name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_group, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.delete) {
            int grpid = new GroupDatabase(getApplicationContext()).getgrpId(name);
            new MessMemberGroupDatabase(getApplicationContext()).deletebyGrp(grpid);
            new GroupDatabase(getApplicationContext()).deletebyName(name);
            Intent intent = new Intent(this, Members.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
