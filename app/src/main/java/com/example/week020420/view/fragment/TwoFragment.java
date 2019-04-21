package com.example.week020420.view.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.week020420.R;

/**
 * @Auther: 白俊岭
 * @Date: 2019/4/20 09:33:13
 * @Description:
 */
public class TwoFragment extends Fragment {


    private View inflate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = LayoutInflater.from(getActivity()).inflate(R.layout.twofragment, null);
        return inflate;
    }
}
