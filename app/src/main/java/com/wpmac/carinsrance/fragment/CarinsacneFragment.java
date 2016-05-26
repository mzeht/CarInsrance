package com.wpmac.carinsrance.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.BaseFragment;

/**
 * Created by wpmac on 16/5/23.
 * 定损信息
 */
public class CarinsacneFragment extends BaseFragment {

    private static CarinsacneFragment carinsacneFragment;
    private View view;


    public static CarinsacneFragment getInstance(){
        // Required empty public constructor
        if(carinsacneFragment==null){
            carinsacneFragment=new CarinsacneFragment();
        }
        return carinsacneFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view=inflater.inflate(R.layout.fragment_carinsacne, container, false);
        }
        return view;
    }

    @Override
    protected void findViewById() {

    }

    @Override
    protected void setEventListener() {

    }

    @Override
    protected void initData() {

    }
}
