package com.wpmac.carinsrance.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.BaseFragment;


/**
 * Created by wpmac on 16/4/10.
 */
public class CustomersInfoFragment extends BaseFragment {

    private static CustomersInfoFragment customersInfoFragment;
    private View View;

    public static CustomersInfoFragment getInstance() {
        if(customersInfoFragment==null){
            customersInfoFragment=new CustomersInfoFragment();
        }
        return customersInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(View==null){
            View=inflater.inflate(R.layout.fragment_customersinfo, container, false);
        }
        return View;
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
