package com.wpmac.carinsrance.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.BaseFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdersFragment extends BaseFragment {
    private static OrdersFragment ordersFragment;
    private View view;


    public static OrdersFragment getInstance(){
        // Required empty public constructor
        if(ordersFragment==null){
            ordersFragment=new OrdersFragment();
        }
        return ordersFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view=inflater.inflate(R.layout.fragment_orders, container, false);
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
