package com.security.travelguide.views.main;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.security.travelguide.R;
import com.security.travelguide.views.beaches.Beaches;
import com.security.travelguide.views.dashboard.Dashboard;
import com.security.travelguide.views.gardens.Gardens;
import com.security.travelguide.views.hillstations.HillStations;
import com.security.travelguide.views.monuments.Monuments;
import com.security.travelguide.views.religious.Religious;
import com.security.travelguide.views.waterfalls.WaterFalls;

import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class MainActivity extends AppCompatActivity implements Dashboard.OnFragmentInteractionListener, Beaches.OnFragmentInteractionListener,
        Gardens.OnFragmentInteractionListener, HillStations.OnFragmentInteractionListener, Monuments.OnFragmentInteractionListener,
        Religious.OnFragmentInteractionListener, WaterFalls.OnFragmentInteractionListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_main);

            fragmentManager = getSupportFragmentManager();

            BottomNavigation bottomNavigationView = findViewById(R.id.bottom_navigation);

            bottomNavigationView.setMenuItemSelectionListener(new BottomNavigation.OnMenuItemSelectionListener() {
                @Override
                public void onMenuItemSelect(int id, int position, boolean b) {
                    switch (position) {
                        case 0:
                            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(), Integer.toString(getFragmentCount())).commit();
                            break;
                        case 1:
                            Toast.makeText(MainActivity.this, "Places Pending", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(MainActivity.this, "Profile Pending", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(MainActivity.this, "About Us Pending", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }

                @Override
                public void onMenuItemReselect(int id, int position, boolean b) {
                    switch (position) {
                        case 0:
                            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(), Integer.toString(getFragmentCount())).commit();
                            break;
                        case 1:
                            Toast.makeText(MainActivity.this, "Places Pending", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(MainActivity.this, "Profile Pending", Toast.LENGTH_SHORT).show();
                            break;
                        case 3:
                            Toast.makeText(MainActivity.this, "About Us Pending", Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            });

            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard()).commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(Fragment fragment) {
        try {
            fragmentManager.beginTransaction().replace(R.id.frame_layout_main, fragment, Integer.toString(getFragmentCount())).commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getFragmentCount() {
        return getSupportFragmentManager().getBackStackEntryCount();
    }

    @Override
    public void onBackPressed() {
        try {
            Log.d(TAG, "onBackPressed: ");
            fragmentManager = getSupportFragmentManager();
            Fragment fragment = fragmentManager.findFragmentById(R.id.frame_layout_main);
            if (fragment instanceof Beaches) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof HillStations) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Monuments) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Religious) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Gardens) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof WaterFalls) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else if (fragment instanceof Dashboard) {
                super.onBackPressed();
            } else if (getFragmentCount() == 0) {
                fragmentManager.popBackStack();
                fragmentManager.beginTransaction().replace(R.id.frame_layout_main, new Dashboard(),
                        Integer.toString(getFragmentCount())).commit();
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}