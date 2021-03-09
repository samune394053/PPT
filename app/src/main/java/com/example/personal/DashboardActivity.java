package com.example.personal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Button generalButton;
    Button healthButton;
    Button workButton;
    Button educationButton;
    Button eventsButton;
    Button foodButton;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar tool;
    FloatingActionButton FloatButton;
    String userStr;
    String emailIDStr;
    String passwordStr;
    private TextView logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.getWindow().setStatusBarColor(this.getResources().getColor(R.color.black));
        generalButton = (Button) findViewById(R.id.buttonGeneral);
        healthButton = (Button) findViewById(R.id.buttonHealth);
        workButton = (Button) findViewById(R.id.buttonWork);
        educationButton = (Button) findViewById(R.id.buttonEducation);
        eventsButton = (Button) findViewById(R.id.buttonEvents);
        foodButton = (Button) findViewById(R.id.buttonFood);
        generalButton.setOnClickListener(this::onClickGeneral);
        healthButton.setOnClickListener(this::onClickHealth);
        workButton.setOnClickListener(this::onClickWork);
        educationButton.setOnClickListener(this::onClickEducation);
        //eventsButton.setOnClickListener(this::onClickEvents);
        //foodButton.setOnClickListener(this::onClickFood);

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

        Bundle continuebundle = getIntent().getExtras();
        if (continuebundle != null) {
            userStr = continuebundle.getString("user");
            emailIDStr = continuebundle.getString("emailID");
            passwordStr = continuebundle.getString("password");
        }
    }
    private void onClickWork(View view) {
        Intent workIntent = new Intent(DashboardActivity.this, WorkActivity.class);
        startActivity(workIntent);
    }
    private void onClickHealth(View view) {
        Intent healthIntent = new Intent(DashboardActivity.this, HealthActivity.class);
        startActivity(healthIntent);
    }

    private void onClickEducation(View view) {
        Intent educationIntent = new Intent(DashboardActivity.this, EducationActivity.class);
        startActivity(educationIntent);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }
    public void onClickGeneral(View v) {
        Intent generalIntent = new Intent(DashboardActivity.this, GeneralActivity.class);
        startActivity(generalIntent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_general:
                Intent generalIntent = new Intent(DashboardActivity.this, GeneralActivity.class);
                startActivity(generalIntent);
                break;
            case R.id.nav_health:
                Intent healthIntent = new Intent(DashboardActivity.this, HealthActivity.class);
                startActivity(healthIntent);
                break;
            case R.id.nav_work:
                Intent workIntent = new Intent(DashboardActivity.this, WorkActivity.class);
                startActivity(workIntent);
                break;
            case R.id.nav_education:
                Intent educationIntent = new Intent(DashboardActivity.this, EducationActivity.class);
                startActivity(educationIntent);
                break;
            /*case R.id.nav_events:
                Intent eventIntent = new Intent(DashboardActivity.this, EventActivity.class);
                startActivity(eventIntent);
                break;
            case R.id.nav_food:
                Intent foodIntent = new Intent(DashboardActivity.this, FoodActivity.class);
                startActivity(foodIntent);
                break;
            case R.id.nav_friends:
                Intent friendsIntent = new Intent(DashboardActivity.this, FriendsActivity.class);
                startActivity(friendsIntent);
                break;*/
            case R.id.nav_profile:
                Intent profileIntent = new Intent(DashboardActivity.this, ProfileActivity.class);
                profileIntent.putExtra("emailID", emailIDStr);
                profileIntent.putExtra("user", userStr);
                profileIntent.putExtra("password", passwordStr);
                startActivity(profileIntent);
                break;
            case R.id.nav_logout:
                FirebaseAuth.getInstance().signOut();
                Intent logoutIntent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(logoutIntent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    public void openActivityInput(View v) {
        Intent input = new Intent(DashboardActivity.this, InputActivity.class);
        startActivity(input);
    }

}