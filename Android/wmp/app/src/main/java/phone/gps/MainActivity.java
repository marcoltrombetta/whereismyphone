package phone.gps;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import phone.gps.fragments.DevicesFragment;
import phone.gps.fragments.LoginFragment;
import phone.gps.fragments.MapFragment;
import phone.gps.obj.Globals;
import phone.gps.obj.LocationTracker;
import phone.gps.preferences.Authentication;
import phone.gps.runnable.LocationServiceRunnable;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Authentication auth;
    NavigationView navigationView;
    TextView sidebaremail;
    LocationTracker locationTracker;

    LocationServiceRunnable.LocationServiceInterface locationServiceInterface= new LocationServiceRunnable.LocationServiceInterface()
    {
        @Override
        public void onComplete() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(getApplicationContext(), getResources().getText(R.string.location_saved), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onError(Exception ex) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        auth=new Authentication(this);
        locationTracker=new LocationTracker(this);

        startService(new Intent(this, LocationService.class));

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close){

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                loadDrawer();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                loadDrawer();
            }
        };

        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Login/Register Fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Container, LoginFragment.newInstance());
        transaction.commit();
    }

    private void loadDrawer(){
        sidebaremail=(TextView)findViewById(R.id.sidebarEmail);

        if(auth.isLogged()) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer_logged);

            sidebaremail.setText(auth.read().getEmail());

            navigationView.refreshDrawableState();
        } else
        {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer_notlogged);

            sidebaremail.setText("");

            navigationView.refreshDrawableState();
        }
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
        getMenuInflater().inflate(R.menu.main2, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_logout) {
            auth.clear();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Container, LoginFragment.newInstance());
            transaction.commit();
        }else if (id == R.id.nav_devices) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Container, DevicesFragment.newInstance());
            transaction.commit();
        }else if (id == R.id.nav_login) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Container, LoginFragment.newInstance());
            transaction.commit();
        }else if (id == R.id.nav_register) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Container, LoginFragment.newInstance());
            transaction.commit();
        }else if (id == R.id.nav_map) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.Container, MapFragment.newInstance());
            transaction.commit();
        }else if (id == R.id.nav_savelocation) {
            //save location on server
            Location location=locationTracker.getLocation();
            new Thread(new LocationServiceRunnable(locationServiceInterface, this.getApplicationContext(),location)).start();
        }else if(id == R.id.nav_share){
            try {
                Location location=locationTracker.getLocation();

                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "https://www.openstreetmap.org/"+"?mlat="+Double.toString(location.getLatitude())+"&mlon="+Double.toString(location.getLongitude())+"#map=16/"+Double.toString(location.getLatitude())+"/"+Double.toString(location.getLongitude());
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "PowApps - Where is my Phone?");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share at"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
