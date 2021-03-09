package com.example.personal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class HealthActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar tool;
    FloatingActionButton FloatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health);

        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.black));

        drawerLayout = findViewById(R.id.drawer_Layout);
        navigationView = findViewById(R.id.na_view);
        tool = findViewById(R.id.toolbar);
        setSupportActionBar(tool);
        navigationView.bringToFront();
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, tool, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        FloatButton = (FloatingActionButton) findViewById(R.id.floatButton);
        FloatButton.setOnClickListener(this::openActivityInput);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_general:
                Intent generalIntent = new Intent(HealthActivity.this, MainActivity.class);
                startActivity(generalIntent);
                break;
            case R.id.nav_health:
                Intent healthIntent = new Intent(HealthActivity.this, HealthActivity.class);
                startActivity(healthIntent);
                break;
            case R.id.nav_work:
                Intent workIntent = new Intent(HealthActivity.this, WorkActivity.class);
                startActivity(workIntent);
                break;
            case R.id.nav_education:
                Intent educationIntent = new Intent(HealthActivity.this, EducationActivity.class);
                startActivity(educationIntent);
                break;
            /*case R.id.nav_events:
                Intent eventIntent = new Intent(HealthActivity.this, EventActivity.class);
                startActivity(eventIntent);
                break;
            case R.id.nav_food:
                Intent foodIntent = new Intent(HealthActivity.this, FoodActivity.class);
                startActivity(foodIntent);
                break;
            case R.id.nav_friends:
                Intent friendsIntent = new Intent(HealthActivity.this, FriendsActivity.class);
                startActivity(friendsIntent);
                break;*/
            case R.id.nav_profile:
                Intent profileIntent = new Intent(HealthActivity.this, ProfileActivity.class);
                startActivity(profileIntent);
                break;
            case R.id.nav_logout:
                Intent logoutIntent = new Intent(HealthActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void openActivityInput(View v) {
        Intent input = new Intent(HealthActivity.this, InputActivity.class);
        startActivity(input);
    }
}