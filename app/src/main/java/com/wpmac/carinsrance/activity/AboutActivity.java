package com.wpmac.carinsrance.activity;

import android.os.Bundle;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.CarInsuranceActivity;

/**
 * Created by wpmac on 16/6/1.
 */
public class AboutActivity extends CarInsuranceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abuot);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
