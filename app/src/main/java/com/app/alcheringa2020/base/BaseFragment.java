package com.app.alcheringa2020.base;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Created by Jiaur Rahman on 27-Dec-19.
 */
public class BaseFragment extends Fragment {
    public static Context context;
    public static View view;
    Fragment fragment1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
