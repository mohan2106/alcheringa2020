package com.app.alcheringa2020.profile;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.alcheringa2020.MainActivity;
import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.external.ThemeUtils;
import com.app.alcheringa2020.feed.FeedFragment;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jiaur Rahman on 09-Jan-20.
 */
public class ProfileFragment extends BaseFragment {
    private static String TAG = ProfileFragment.class.getSimpleName();
    static ViewGroup fragment_container;
    static ProfileFragment fragment;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    LinearLayout team_members_lyt;
    CircleImageView profile_image;
    RelativeLayout themeRlt;
    Switch themeSwitch;
    ThemeUtils themeUtils;
    boolean themeIsLight=false;

    public ProfileFragment() {
        //blank Constructor
    }

    public static ProfileFragment newInstance(Context context1) {
        fragment = new ProfileFragment();
        context = context1;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragment_container = container;
        context = getActivity().getApplicationContext();

        view = inflater.inflate(R.layout.fragment_profile, container, false);
        team_members_lyt = view.findViewById(R.id.team_members_lyt);
        profile_image = view.findViewById(R.id.profile_image);
        themeRlt = view.findViewById(R.id.themeRlt);
        themeSwitch = view.findViewById(R.id.themeSwitch);
        profile_image.bringToFront();

        initData();
        initListner();
        return view;
    }

    private void initData() {
        try{
            themeIsLight = prefManager.getTheme();
            if (!themeIsLight){
                themeSwitch.setChecked(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else{
                themeSwitch.setChecked(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            Log.d(TAG,"all theme data: "+themeIsLight);


            //new ThemeUtils(themeIsLight,context);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void initListner() {
        team_members_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TeamFragment());
            }
        });
        themeRlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (themeSwitch.isChecked()){
                    themeSwitch.setChecked(true);
                    prefManager.setTheme(false);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }else {
                    themeSwitch.setChecked(false);
                    prefManager.setTheme(true);
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                replaceFragment(new ProfileFragment());
            }

        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        MainActivity.nav_title.setText("Team Members");
        MainActivity.noti_rlt.setVisibility(View.VISIBLE);
        MainActivity.add_image.setVisibility(View.VISIBLE);
    }

}
