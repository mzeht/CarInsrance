package com.wpmac.carinsrance.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.BaseFragment;

/**
 * Created by wpmac on 16/5/23.
 * 维修机构
 */
public class MaintenanceFragment extends BaseFragment {
    private static MaintenanceFragment maintenanceFragment;
    private View view;


    public static MaintenanceFragment getInstance(){
        // Required empty public constructor
        if(maintenanceFragment==null){
            maintenanceFragment=new MaintenanceFragment();
        }
        return maintenanceFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (view==null){
            view=inflater.inflate(R.layout.fragment_maintenance, container, false);
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
