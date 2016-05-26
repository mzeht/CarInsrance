package com.wpmac.carinsrance.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daimajia.slider.library.SliderLayout;
import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.base.BaseFragment;
import com.wpmac.carinsrance.debug.L;

/**
 * create an instance of this fragment.
 */
public class ShoppingFragment extends BaseFragment {

    private static ShoppingFragment shoppingFragment;
    private SliderLayout mSliderLayout;
    private RecyclerView mRecyclerView;
    private View View;
    // TODO: Rename and change types and number of parameters
    public static ShoppingFragment getInstance() {
        if(shoppingFragment == null){
            shoppingFragment = new ShoppingFragment();
        }
        return shoppingFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(View==null){
            View=inflater.inflate(R.layout.fragment_shopping,container,false);
            L.i("ShoppingFragment  onCreateView");
        }
        findViewById();
        setEventListener();
        initData();
        return View;
    }

    @Override
    protected void findViewById() {
        mSliderLayout= (SliderLayout) View.findViewById(R.id.slider);
        mRecyclerView= (RecyclerView) View.findViewById(R.id.recyclerview);


    }

    @Override
    protected void setEventListener() {

    }

    @Override
    protected void initData() {

    }


    // TODO: Rename method, update argument and hook method into UI event


}
