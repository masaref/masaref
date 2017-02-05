package com.masaref.bahez.masaref.MasarefActivities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.masaref.bahez.masaref.R;

/**
 * Class Name: GeneralActivity
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 02.05.2017
 * Purpose: GeneralActivity class is created to implement general methods to be used by those
 * activities which use the navigation drawer.
 * Additionally, this class implements action listener for application navigation drawer.
 */

public class GeneralActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    public void generateDrawer(){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, (Toolbar) findViewById(R.id.toolbar), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent openHome = new Intent(this, HomeActivity.class);
            openHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openHome);
        } else if (id == R.id.nav_profile) {
            Intent openProfile = new Intent(this, ProfileActivity.class);
            openProfile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openProfile);
        } else if (id == R.id.nav_cards_and_accounts) {
            Intent openCardsAndAccounts = new Intent(this, CartsAndAccountsActivity.class);
            openCardsAndAccounts.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openCardsAndAccounts);
        } else if (id == R.id.nav_agenda) {
            Intent openAgenda = new Intent(this, AgendaActivity.class);
            openAgenda.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openAgenda);
        } else if (id == R.id.nav_report_by_date) {
            Intent openReportByDate = new Intent(this, ReportByDateActivity.class);
            openReportByDate.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openReportByDate);
        } else if (id == R.id.nav_report_by_category) {
            Intent openReportByCategory = new Intent(this, ReportByCategoryActivity.class);
            openReportByCategory.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openReportByCategory);
        } else if (id == R.id.nav_settings) {
            Intent openSettings = new Intent(this, SettingsActivity.class);
            openSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(openSettings);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
