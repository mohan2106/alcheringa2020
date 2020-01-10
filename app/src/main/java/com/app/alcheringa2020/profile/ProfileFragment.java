package com.app.alcheringa2020.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.app.alcheringa2020.R;
import com.app.alcheringa2020.base.BaseFragment;
import com.app.alcheringa2020.feed.FeedFragment;

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
        initListner();
        return view;
    }

    private void initListner() {
        team_members_lyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragment(new TeamFragment());
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
