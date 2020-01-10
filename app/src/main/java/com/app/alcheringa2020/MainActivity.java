package com.app.alcheringa2020;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.alcheringa2020.base.BaseActivity;
import com.app.alcheringa2020.events.EventsFragment;
import com.app.alcheringa2020.feed.FeedFragment;
import com.app.alcheringa2020.notification.NotificationFragment;
import com.app.alcheringa2020.profile.ProfileFragment;
import com.app.alcheringa2020.schedule.ScheduleFragment;
import com.app.alcheringa2020.search.SearchActivity;
import com.app.alcheringa2020.support.SupportFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    String TAG = MainActivity.class.getSimpleName();
    Fragment fragment;
    String currentFragment;
    BottomNavigationView navigationView;
    ImageView main_logo, noti_image, profile_image, back_image, search_image;
    TextView nav_title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_image = findViewById(R.id.search_image);
        back_image = findViewById(R.id.back_image);
        noti_image = findViewById(R.id.noti_image);
        profile_image = findViewById(R.id.profile_image);
        main_logo = findViewById(R.id.main_logo);
        nav_title = findViewById(R.id.nav_title);
        navigationView = findViewById(R.id.bottomNavigationView);
        navigationView.setOnNavigationItemSelectedListener(this);
        fragment = FeedFragment.newInstance(context);
        currentFragment = "FeedFragment";
        setFragment(fragment);
        initListner();
    }

    private void initListner() {
        try {
            noti_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = NotificationFragment.newInstance(context);
                    currentFragment = "NotificationFragment";
                    setFragment(fragment);
                    nav_title.setText(R.string.notifications);
                    showHide();
                }
            });
            search_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent  = new Intent(context, SearchActivity.class);
                    startActivity(intent);
                }
            });
            profile_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    fragment = ProfileFragment.newInstance(context);
                    currentFragment = "ProfileFragment";
                    setFragment(fragment);
                    nav_title.setText(R.string.profile);
                    showHide();
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Log.d(TAG, "item id: " + menuItem.getItemId());
        switch (menuItem.getItemId()) {
            case R.id.nav_feed:
                fragment = FeedFragment.newInstance(context);
                currentFragment = "FeedFragment";
                main_logo.setVisibility(View.VISIBLE);
                back_image.setVisibility(View.GONE);
                noti_image.setVisibility(View.VISIBLE);
                nav_title.setVisibility(View.GONE);
                profile_image.setVisibility(View.VISIBLE);
                setFragment(fragment);
                break;
            case R.id.nav_events:
                fragment = EventsFragment.newInstance(context);
                currentFragment = "EventsFragment";
                setFragment(fragment);
                nav_title.setText(R.string.events);
                showHide();
                break;
            case R.id.nav_schedule:
                fragment = ScheduleFragment.newInstance(context);
                currentFragment = "ScheduleFragment";
                setFragment(fragment);
                nav_title.setText("My Schedule");
                showHide();
                break;
            case R.id.nav_support:
                fragment = SupportFragment.newInstance(context);
                currentFragment = "SupportFragment";
                setFragment(fragment);
                nav_title.setText(R.string.support);
                showHide();
                break;
        }
        return false;
    }

    private void showHide() {
        profile_image.setVisibility(View.GONE);
        main_logo.setVisibility(View.GONE);
        noti_image.setVisibility(View.GONE);
        back_image.setVisibility(View.VISIBLE);
        nav_title.setVisibility(View.VISIBLE);
        if (currentFragment.equalsIgnoreCase("EventsFragment") || currentFragment.equalsIgnoreCase("NotificationFragment")) {
            search_image.setVisibility(View.VISIBLE);
        } else {
            search_image.setVisibility(View.GONE);
        }
    }


    @Override
    public void onBackPressed() {
        exitApp();
    }

    private void exitApp() {
        if (currentFragment.equalsIgnoreCase("FeedFragment")) {
            try {
                new AlertDialog.Builder(this)
                        .setTitle("Confirm")
                        .setIcon(R.drawable.logo)
                        .setMessage("Do you really want to exit?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                finish();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null).show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            fragment = FeedFragment.newInstance(context);
            currentFragment = "FeedFragment";
            main_logo.setVisibility(View.VISIBLE);
            back_image.setVisibility(View.GONE);
            noti_image.setVisibility(View.VISIBLE);
            nav_title.setVisibility(View.GONE);
            profile_image.setVisibility(View.VISIBLE);
            setFragment(fragment);
        }
    }

    public void backOption(View view) {
        exitApp();
    }
}
