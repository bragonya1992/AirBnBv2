package com.example.brayany.airbnb;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
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

import java.util.List;
import java.util.Vector;

import com.example.brayany.airbnb.interfaces.OnClickItem;
import com.example.brayany.airbnb.retrofit.SearchResult;
import com.example.brayany.airbnb.storage.database;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnClickItem {
    LocationManager locationManager;
    public static double longitudeGPS = -1;
    public static double latitudeGPS = -1;
    public static boolean isMyRealData=false;
    FragmentMap fragmentMap;
    ViewPager mViewPager;
    private static final int NUM_PAGES = 2;
    private MyFragmentAdapter mPagerAdapter;
    List<Fragment> fragments = new Vector<Fragment>();
    Fragment_list fragmentList;
    Fragment_list fragmentFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Airbnb");
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        fab.setVisibility(View.GONE);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        fragmentMap = new FragmentMap();
        fragmentList = Fragment_list.newInstance(true);
        fragmentList.setListener(this);
        fragmentFav = Fragment_list.newInstance(false);
        fragmentFav.setListener(this);
        fragments.add(fragmentMap);
        fragments.add(fragmentList);
        fragments.add(fragmentFav);
        setupViewPager();
        locationService();
        initView();
    }

    private  void setupViewPager(){
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new MyFragmentAdapter(getSupportFragmentManager(),fragments);
        mViewPager.setAdapter(mPagerAdapter);
    };

    private void locationService(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},126);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);
        if(!checkLocation()) {
            Toast.makeText(this, "GPS isnt enable!", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2 * 20 * 1000, 10, locationListenerGPS);
    }

    private boolean checkLocation() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }


    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();
            if(!isMyRealData) {
                Toast.makeText(getApplicationContext(),"Data update by GPS!",Toast.LENGTH_SHORT).show();
                fragmentList.initView();
                fragmentMap.moveCameraTo(longitudeGPS,latitudeGPS,"Current ubication");
            }
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    public void initView(){

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
        getMenuInflater().inflate(R.menu.main, menu);
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

        if (id == R.id.nav_fav) {
            // Handle the camera action
            mViewPager.setCurrentItem(2);
        } else if (id == R.id.nav_list) {
            mViewPager.setCurrentItem(1);

        } else if (id == R.id.nav_map) {
            mViewPager.setCurrentItem(0);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(Double latitude, Double longitude, String marker) {
        mViewPager.setCurrentItem(0);
        fragmentMap.moveCameraTo(latitude,longitude,marker);
    }

    @Override
    public void addFav(SearchResult result) {
        database.saveAirBnBObject(result,this);
    }

    @Override
    public void deleteFav(String name) {
        database.delete(name,this);
    }
}
