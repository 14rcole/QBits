package com.example.ryan.qbits;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OccupancyGraphActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String[] spinner = {"Hour", "Day", "Week", "Month", "Year"};
    public String span = spinner[0];
    final String TAG = "OCCUPANCY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_occupancy_graph);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Spinner spinList = (Spinner)findViewById(R.id.occupancySpinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1, spinner);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinList.setAdapter(ad);
        spinList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
                span = spinner[pos];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){

            }
        });

        //Log.d(TAG, "Created data manager");
        //HashMap<String, Integer> values = dm.getOccupancyData("week");
        //Log.d(TAG, "After getOccupancyData. values size is" + values.size());
        //DoublePlot doublePlot = new DoublePlot();
        //Log.d(TAG, "Entering createDoublePlot");
        //doublePlot.createDoublePlot(occupancyChart, values, values, "week");
    }

    public void graph(View view)
    {
        final LineChart occupancyChart = (LineChart)findViewById(R.id.occupancyChart);
        Log.d(TAG, "right after occupancy chart");
        DataManager dm = new DataManager() {
            @Override
            public void onPostExecute(HashMap<String, Integer> values)
            {
                DoublePlot doublePlot = new DoublePlot();
                doublePlot.createDoublePlot(occupancyChart, values, values, "week");
            }
        };
        dm.execute("getOccupancyData", span);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.occupancy_graph, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void chartHour() {
        LineChart lineChart = (LineChart)findViewById(R.id.occupancyChart);
        DataManager dm = new DataManager();

        HashMap<String, Integer> values = dm.getOccupancyData("hour");

        DoublePlot doublePlot = new DoublePlot();
        doublePlot.createDoublePlot(lineChart, values, values, "hour");
    }

    public void chartDay(){
        LineChart lineChart = (LineChart)findViewById(R.id.occupancyChart);
        DataManager dm = new DataManager();

        HashMap<String, Integer> values = dm.getOccupancyData("day");

        DoublePlot doublePlot = new DoublePlot();
        doublePlot.createDoublePlot(lineChart, values, values, "day");
    }

    public void chartWeek(){
        LineChart lineChart = (LineChart)findViewById(R.id.occupancyChart);
        DataManager dm = new DataManager();

        HashMap<String, Integer> values = dm.getOccupancyData("week");

        DoublePlot doublePlot = new DoublePlot();
        doublePlot.createDoublePlot(lineChart, values, values, "week");
    }

    public void chartMonth(){
        LineChart lineChart = (LineChart)findViewById(R.id.occupancyChart);
        DataManager dm = new DataManager();

        HashMap<String, Integer> values = dm.getOccupancyData("month");

        DoublePlot doublePlot = new DoublePlot();
        doublePlot.createDoublePlot(lineChart, values, values, "month");
    }

    public void chartYear() {
        LineChart lineChart = (LineChart) findViewById(R.id.occupancyChart);
        DataManager dm = new DataManager();

        HashMap<String, Integer> values = dm.getOccupancyData("year");

        DoublePlot doublePlot = new DoublePlot();
        doublePlot.createDoublePlot(lineChart, values, values, "year");
    }
}
