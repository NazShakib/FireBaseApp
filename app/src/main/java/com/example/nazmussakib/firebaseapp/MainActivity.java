package com.example.nazmussakib.firebaseapp;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

//package import for bottom navigation bar
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bottomNavigation.appliedJobs;
import bottomNavigation.favoriteJobList;
import bottomNavigation.fullTimeJob;
import bottomNavigation.homeBottomNav;
import bottomNavigation.partTimeJobs;
import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;

    private BottomNavigationView bottomNavigation;
    private MaterialSearchView searchView;

    //Bottom Navigation button
    private homeBottomNav honme;
    private fullTimeJob fullTimeJob;
    private favoriteJobList favoriteJobList;
    private partTimeJobs partTimeJobs;
    private appliedJobs appliedJobs;

    private List<String> SUGGESTIONS =
            Arrays.asList(new String[]{"WEB DEVELOPER", "APPS DEVELOPERS", "IOS DEVELOPERS",
                    "IT MANAGER", "SENIOR OFFICERS", "LECTURER"});



    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isOnline())
        {
            Toasty.error(MainActivity.this,"No Network Connection",Toasty.LENGTH_SHORT).show();

        }
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawerId);
        navigationView = findViewById(R.id.navigationView);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        searchView = findViewById(R.id.search_view);
        toolbar.setTitle("Job Portal");
        setSupportActionBar(toolbar);
   try {
       honme = new homeBottomNav(MainActivity.this);
       fullTimeJob = new fullTimeJob(MainActivity.this);
       favoriteJobList = new favoriteJobList(MainActivity.this);
       partTimeJobs = new partTimeJobs(MainActivity.this);
       appliedJobs = new appliedJobs(MainActivity.this);
   }
   catch (Exception e)
   {
       Log.e(TAG, "onCreate: "+e.toString());
   }
        try {
            toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_drawer, R.string.close_drawer);
            drawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }
        catch (Exception e)
        {
            Log.e(TAG,e.toString());
        }

        navigationView.setNavigationItemSelectedListener(this);
        bottomNavigationBar();
        loadFragment(honme);
        searchViewToolbar();


    }


    private void searchViewToolbar() {

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                String userInfo = query.toLowerCase();
                List<jobInfo> mysobInfo = new ArrayList<>();

                List<jobInfo> jobList  =honme.getJobList();
                int position =0;
                for (jobInfo jobs: jobList)
                {
                    if(jobs.getTitle().toLowerCase().contains(userInfo)
                            || jobs.getCompanyName().toLowerCase().contains(userInfo)
                            || jobs.getExperienceTime().toLowerCase().contains(userInfo)
                            || jobs.getJobNature().toLowerCase().contains(userInfo))
                    {
                        mysobInfo.add(jobList.get(position));

                    }

                    position++;
                }

                boolean m =true;
                if(mysobInfo.isEmpty())
                {
                    m=false;
                    Toasty.error(MainActivity.this,"Does not Match "+query,Toasty.LENGTH_SHORT).show();
                }

                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(MainActivity.this, mysobInfo);
                recyclerViewAdapter.notifyDataSetChanged();
                honme.getRecyclerView().setAdapter(recyclerViewAdapter);
                if (m)
                {
                    Toasty.success(MainActivity.this,"Search "+query+" Successfully",Toasty.LENGTH_SHORT).show();
                }
                return true;
            }


            @Override
            public boolean onQueryTextChange(String newText) {

                String userInfo = newText.toLowerCase();
               List<jobInfo> mysobInfo = new ArrayList<>();

               List<jobInfo> jobList  =honme.getJobList();

                int position =0;
                for (jobInfo jobs: jobList)
                {
                    if(jobs.getTitle().toLowerCase().contains(userInfo)
                            || jobs.getCompanyName().toLowerCase().contains(userInfo)
                    || jobs.getExperienceTime().toLowerCase().contains(userInfo)
                    || jobs.getJobNature().toLowerCase().contains(userInfo))
                    {
                        mysobInfo.add(jobList.get(position));
                    }
                    position++;
                }
                honme.getRecyclerViewAdapter().updateJobInfo(mysobInfo);
                return true;
            }
        });

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }
        });

    }

    private boolean loadFragment(Fragment fragment)
    {
        if (fragment!=null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_layout,fragment)
                    .commit();
            return true;
        }
        return false;
    }


    private void bottomNavigationBar() {

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                Fragment fragment = null;

                switch (menuItem.getItemId())
                {
                    case R.id.home:
                    {
                        try {
                            fragment = honme;
                            Toasty.info(MainActivity.this,"HOME",Toasty.LENGTH_SHORT).show();

                        }
                        catch (Exception e)
                        {
                            Log.e(TAG, "onNavigationItemSelected: "+e.toString());
                        }
                        break;
                    }
                    case R.id.fav_job:
                    {
                        try {
                            fragment = favoriteJobList;
                            print("fav job Clicked");
                        }
                        catch (Exception e)
                        {
                            Log.e(TAG, "onNavigationItemSelected: "+e.toString());
                        }

                        break;
                    }
                    case R.id.full_time:
                    {
                        fragment = fullTimeJob;
                        print("Full time clicked");
                        break;
                    }
                    case R.id.part_time:
                    {
                        fragment = partTimeJobs;
                        print("Part time clicked");
                        break;
                    }
                    case R.id.appliedJob:
                    {
                         fragment = appliedJobs;
                        print("All Applied job clicked");
                        break;
                    }
                    default:
                    {
                        print("Invalid Clicked");
                        break;
                    }
                }
                return loadFragment(fragment);
            }
        });

    }


    public void print(Object o)
    {
        Toast.makeText(this,o.toString(),Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        searchView.setMenuItem(item);
        return true;
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.userHome :
            {
                print("User Home Clicked");
                break;
            }
            case R.id.userProfile :
            {
                print("User Profile Clicked");
                break;
            }
            case R.id.UserProfileUpdate :
            {
                print("User Profile Update Clicked");
                break;
            }
            case R.id.userCV :
            {
                print("User CV Update Clicked");
                break;
            }
            case R.id.allMail :
            {
                print("All mail Clicked");
                break;
            }
            case R.id.spam :
            {
                print("Spam Clicked");
                break;
            }
            case R.id.trash :
            {
                print("Trash Clicked");
                break;
            }

            default:
            {
                print("Invalid Clicked");
                break;
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {

            // toolbar menu's
            case R.id.settings:
            {
                print("Settings CLicked");
                break;
            }
            case R.id.aboutUs:
            {
                print("About Us clicked");
                break;
            }
            case R.id.logOut:
            {
                print("Log out Clicked");
                break;
            }
            default:
            {
                print("Invalid Clicked");
                break;
            }
        }

        return true;

    }


    public boolean isOnline() {

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            return true;
        }
        else
            return false;
    }






}
