package com.manojz.send_it;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.manojz.send_it.fragments.frag_main;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawerlayout);
        navigationView = (NavigationView) findViewById(R.id.main_navigationview);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((CollapsingToolbarLayout) findViewById(R.id.collapsingtoolbarlayout_main))
                .setTitle(getResources().getString(R.string.app_name));
        if (Build.VERSION.SDK_INT >= 21) {
            ActivityManager.TaskDescription taskDescription = new
                    ActivityManager.TaskDescription(getResources().getString(R.string.app_name),
                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher),
                    getResources().getColor(R.color.colorPrimary));
            setTaskDescription(taskDescription);
        }
        setNav();
        setCards();
    }

    private void setNav() {if (toolbar != null) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
                syncState();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.navigation_req_icons) {
                            startActivity(new Intent(MainActivity.this, frag_main.class));
                            closeDrawerAfterSmallDelay();
                        } /*else if (menuItem.getItemId() == R.id.navigation_icons) {
                            startActivity(new Intent(MainActivity.this, IconsActivity.class));
                            closeDrawerAfterSmallDelay();
                        } else if (menuItem.getItemId() == R.id.navigation_wall) {
                            startActivity(new Intent(MainActivity.this, WallpaperActivity.class));
                            closeDrawerAfterSmallDelay();
                        } else if (menuItem.getItemId() == R.id.navigation_req_icons) {
                            startActivity(new Intent(MainActivity.this, RequestActivity.class));
                            closeDrawerAfterSmallDelay();
                        } else if (menuItem.getItemId() == R.id.navigation_about) {
                            startActivity(new Intent(MainActivity.this, AboutAppActivity.class));
                            closeDrawerAfterSmallDelay();
                        }*/
                        return true;
                    }
                });
    }

    private void setCards() {
        Button button1, button2, button3;
        button1 = (Button) findViewById(R.id.home_card_one_button);
        button2 = (Button) findViewById(R.id.home_card_two_button);
        button3 = (Button) findViewById(R.id.home_card_three_button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getResources().getString(R.string.card1_link));
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goToMarket);
            }
        });
        if (!getResources().getBoolean(R.bool.card1_visible))
            (findViewById(R.id.main_card2)).setVisibility(View.GONE);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getResources().getString(R.string.card2_link));
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goToMarket);
            }
        });
        if (!getResources().getBoolean(R.bool.card2_visible))
            (findViewById(R.id.main_card2)).setVisibility(View.GONE);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(getResources().getString(R.string.card3_link));
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(goToMarket);
            }
        });
        if (!getResources().getBoolean(R.bool.card3_visible))
            (findViewById(R.id.main_card3)).setVisibility(View.GONE);

    }

    private void closeDrawerAfterSmallDelay() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                drawerLayout.closeDrawer(GravityCompat.START);
                navigationView.getMenu().findItem(R.id.navigation_home).setChecked(true);
            }
        }, 800);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }
}
