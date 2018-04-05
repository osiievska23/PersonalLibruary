package com.example.valentina.valentina_libruary;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import butterknife.BindView;

public class MenuBar extends AppCompatActivity {

    @BindView(R.id.drawer)
    DrawerLayout mdDrawer;

    private ActionBarDrawerToggle myToogle;

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.menu_bar);
        myToogle = new ActionBarDrawerToggle(this, mdDrawer, R.string.open, R.string.close);
        mdDrawer.addDrawerListener(myToogle);
        myToogle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(myToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
