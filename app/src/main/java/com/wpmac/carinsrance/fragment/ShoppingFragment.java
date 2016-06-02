package com.wpmac.carinsrance.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.activity.PeiJianActivity;
import com.wpmac.carinsrance.adapter.HomeCatgoryAdapter;
import com.wpmac.carinsrance.adapter.decoration.CardViewtemDecortion;
import com.wpmac.carinsrance.base.BaseFragment;
import com.wpmac.carinsrance.bean.HomeCardBean;
import com.wpmac.carinsrance.comment.AbstractRequestListener;
import com.wpmac.carinsrance.comment.CustomError;
import com.wpmac.carinsrance.debug.L;
import com.wpmac.carinsrance.manager.ConnectManager;
import com.wpmac.carinsrance.model.HomebanerList;
import com.wpmac.carinsrance.param.HomeCardParam;
import com.wpmac.carinsrance.service.AVService;
import com.wpmac.carinsrance.util.ActivityForResultUtil;

import java.util.List;

/**
 * create an instance of this fragment.
 */
public class ShoppingFragment extends BaseFragment {

    private static ShoppingFragment shoppingFragment;
//    private PeijianFragment peijianFragment;
    private SliderLayout mSliderLayout;
    private RecyclerView mRecyclerView;
    private TextView peijianTextView;
    private View View;
    private volatile List<HomebanerList> bannerList;
    private HomeCardBean homeCardBean;
    private List<HomeCardBean.ResultsBean>HomecartList;
    private HomeCatgoryAdapter mAdatper;

    private Dialog progressDialog;

    private class GetBannerTask extends AsyncTask<Void, Void, Void> {
        // Override this method to do custom remote calls
        @Override
        protected Void doInBackground(Void... params) {
            bannerList = AVService.findBanners();
            return null;
        }

        @Override
        protected void onPreExecute() {
//            progressDialog =ProgressDialog.show(getActivity(), "", "Loading...", true);
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {

            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void result) {
            // 展现ListView
            initBanner();

        }
    }


    // TODO: Rename and change types and number of parameters
    public static ShoppingFragment getInstance() {
        if (shoppingFragment == null) {
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
        if (View == null) {
            View = inflater.inflate(R.layout.fragment_shopping, container, false);
            L.i("ShoppingFragment  onCreateView");
        }
        findViewById();
        setEventListener();
        initData();
        new GetBannerTask().execute();
        return View;
    }


    @Override
    protected void findViewById() {
        mSliderLayout = (SliderLayout) View.findViewById(R.id.slider);
        mRecyclerView = (RecyclerView) View.findViewById(R.id.recyclerview);
        peijianTextView = (TextView) View.findViewById(R.id.peijian_textview);



    }

    @Override
    protected void setEventListener() {
        peijianTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setClass(getActivity(),PeiJianActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        HomeCardParam param=new HomeCardParam();
        ConnectManager.getInstance().GetHomeCard(param,callback);

    }

    private AbstractRequestListener<HomeCardBean> callback = new AbstractRequestListener<HomeCardBean>() {
        @Override
        public void onComplete(HomeCardBean bean) {
            homeCardBean = bean;
            HomecartList=homeCardBean.getResults();
            tHandler.sendEmptyMessage(ActivityForResultUtil.REQUEST_DATA_SUCCESS);
        }

        @Override
        public void onError(CustomError customError) {

            Message msg = tHandler.obtainMessage();
            msg.obj = customError;
            msg.what = ActivityForResultUtil.REQUEST_DATA_ERROR;
            tHandler.sendMessage(msg);

        }

        @Override
        public void onFault(CustomError fault) {

            Message msg = tHandler.obtainMessage();
            msg.obj = fault;
            msg.what = ActivityForResultUtil.REQUEST_DATA_FAULT;
            tHandler.sendMessage(msg);
        }

    };

    private Handler tHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ActivityForResultUtil.REQUEST_DATA_SUCCESS:
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }
//                    if (Utils.isValidValue(simpleResponseBean.getSuccess())) {
//                        ToastWidget.createDialog(getActivity(), simpleResponseBean.getMessage());
//                    }
                    initRecyclerView();
                    break;

                case ActivityForResultUtil.REQUEST_DATA_ERROR:
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }

                    if (msg.obj instanceof CustomError) {

                        CustomError fault = (CustomError) msg.obj;
//                        ToastWidget.createDialog(getActivity(), fault.getMessage());
                    }

                    break;
                case ActivityForResultUtil.REQUEST_DATA_FAULT:
//                    if (dialog != null && dialog.isShowing()) {
//                        dialog.dismiss();
//                    }

                    if (msg.obj instanceof CustomError) {

                        CustomError fault = (CustomError) msg.obj;
//                        ToastWidget.createDialog(getActivity(), fault.getMessage());
                    }

                    break;
            }

        }
    };


    private void initRecyclerView() {
        mAdatper = new HomeCatgoryAdapter(HomecartList, getActivity());

        mAdatper.setOnCampaignClickListener(new HomeCatgoryAdapter.OnCampaignClickListener() {
            @Override
            public void onClick(View view, HomeCardBean.ResultsBean.CpBean campaign) {


                Intent intent = new Intent(getActivity(), PeiJianActivity.class);
//                intent.putExtra("imgUrl",campaign.getImgUrl());
//                intent.putExtra("imgName",campaign.getTitle());

                startActivity(intent);
//                android.app.ActionBar actionBar = getActivity().getActionBar();
//                if (actionBar != null) {
//                    actionBar.setTitle("采购配件");
//                }
//                if (peijianFragment == null) {
//                    peijianFragment = PeijianFragment.getInstance();
//                }
//                replaceMainFragment(peijianFragment);


            }
        });

        mRecyclerView.setAdapter(mAdatper);

        mRecyclerView.addItemDecoration(new CardViewtemDecortion());

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));


    }

    private void replaceMainFragment(Fragment fragment) {
        FragmentManager manager = getActivity().getSupportFragmentManager();
        if (fragment.isAdded()) {
//            Toast.makeText(getActivity(), "isadded", Toast.LENGTH_SHORT).show();

        } else {
            manager.beginTransaction()
                    .replace(R.id.main_container, fragment)
                    .commit();
        }
    }

    private void initBanner() {
        if (bannerList != null) {

            for (HomebanerList banner : bannerList) {


                TextSliderView textSliderView = new TextSliderView(this.getActivity());
                textSliderView.image(banner.getIMGURL());
                textSliderView.description(banner.getNAME());
                textSliderView.setScaleType(BaseSliderView.ScaleType.Fit);
                mSliderLayout.addSlider(textSliderView);

            }
        }


        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);

        mSliderLayout.setCustomAnimation(new DescriptionAnimation());
        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.RotateUp);
        mSliderLayout.setDuration(3000);

    }




    @Override
    public void onDestroy() {
        super.onDestroy();

        mSliderLayout.stopAutoCycle();
    }

}
