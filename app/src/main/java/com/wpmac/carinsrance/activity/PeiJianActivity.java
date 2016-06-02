package com.wpmac.carinsrance.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.adapter.PopupwindowSimpleListAdapter;
import com.wpmac.carinsrance.adapter.ShangPingSearchResultAdapter;
import com.wpmac.carinsrance.base.CarInsuranceActivity;
import com.wpmac.carinsrance.bean.ShangPingBean;
import com.wpmac.carinsrance.comment.AbstractRequestListener;
import com.wpmac.carinsrance.comment.CustomError;
import com.wpmac.carinsrance.manager.ConnectManager;
import com.wpmac.carinsrance.model.SelectSimleList;
import com.wpmac.carinsrance.param.ShangpingParam;
import com.wpmac.carinsrance.util.ActivityForResultUtil;
import com.wpmac.carinsrance.util.LocalList;
import com.wpmac.carinsrance.util.Utils;
import com.wpmac.carinsrance.view.FixListviewHeight;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/6/1.
 */
public class PeiJianActivity extends CarInsuranceActivity {
    @Bind(R.id.buwei_textview)
    TextView buweiTextview;
    @Bind(R.id.buwei_ll)
    RelativeLayout buweiLl;
    @Bind(R.id.pinpai_textview)
    TextView pinpaiTextview;
    @Bind(R.id.pinpai_ll)
    RelativeLayout pinpaiLl;
    @Bind(R.id.jiage_textview)
    TextView jiageTextview;
    @Bind(R.id.jiage_ll)
    RelativeLayout jiageLl;
    @Bind(R.id.more)
    TextView more;
    @Bind(R.id.more_select_tab_ll)
    RelativeLayout moreSelectTabLl;
    @Bind(R.id.search_tab_ll)
    LinearLayout searchTabLl;
    @Bind(R.id.search_company_number_textview)
    TextView searchCompanyNumberTextview;
    @Bind(R.id.search_company_number_ll)
    LinearLayout searchCompanyNumberLl;
    @Bind(R.id.search_result_listview)
    ListView searchResultListview;
    @Bind(R.id.no_search_data_imageview)
    ImageView noSearchDataImageview;
    @Bind(R.id.no_search_data)
    TextView noSearchData;
    @Bind(R.id.test_no_search_histroy_ll)
    LinearLayout testNoSearchHistroyLl;
    private PopupWindow buweiPopupWindow,jiagePopupWindow,pinpaiPopupWindow,morePopupWindow;
    private int screenWidth;
    private int screenHeight;
    private ListView buweiListView,pinpaiListView,jiageListView;
    private PopupwindowSimpleListAdapter buweiAdapter,pinpaiAdapter,jiageAdapter;
    private List<SelectSimleList> buweiList,jiageList,pinpaiList;
    private int imaPosition;
    private String buewiString="",jiageString="",pinpaiString="";
    private ShangPingBean shangPingBean;
    private List<ShangPingBean.ResultsBean> shangpingList;
    private ShangPingSearchResultAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peijian);
        ButterKnife.bind(this);
//        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initSearchView();
        requestData();
    }

    private void initSearchView() {
        initScreenWidth();
        //区域弹出框
        initPopwindowList();

        initSearchItemView();

        setlistener();
    }

    private void initPopwindowList() {
        buweiList= LocalList.getBuweiList();
        pinpaiList=LocalList.getpinpaiList();
        jiageList=LocalList.getjiageList();


    }

    private void setlistener() {
        buweiLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != buweiPopupWindow && buweiPopupWindow.isShowing()) {
                    buweiPopupWindow.dismiss();
                    return;
                } else {
                    //初始化分类弹窗
                    initBuweiPopWindow();
                }
                buweiPopupWindow.showAsDropDown(buweiLl);
            }
        });

        pinpaiLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != pinpaiPopupWindow && pinpaiPopupWindow.isShowing()) {
                    pinpaiPopupWindow.dismiss();
                    return;
                } else {
                    //初始化分类弹窗
                    initPinpaiPopWindow();
                }
                pinpaiPopupWindow.showAsDropDown(pinpaiLl);
            }
        });

        jiageLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != jiagePopupWindow && jiagePopupWindow.isShowing()) {
                    jiagePopupWindow.dismiss();
                    return;
                } else {
                    //初始化分类弹窗
                    initJiagePopWindow();
                }
                jiagePopupWindow.showAsDropDown(jiageLl);
            }
        });



    }

    private void initJiagePopWindow() {
        View left_view = PeiJianActivity.this.getLayoutInflater().inflate
                (R.layout.popupwindow_select_jiage, null, false);
        left_view.setFocusable(true); // 这个很重要
        left_view.setFocusableInTouchMode(true);
        jiageListView = (ListView) left_view.findViewById(R.id.lv1);
        jiageAdapter = new PopupwindowSimpleListAdapter(PeiJianActivity.this, jiageList);
        jiageListView.setAdapter(jiageAdapter);
        FixListviewHeight.setListViewHeightBasedOnChildren(jiageListView);
        // PopupWindow(布局，宽度，高度)
        jiagePopupWindow = new PopupWindow(left_view,ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT, true);
        jiagePopupWindow.setFocusable(true);
        // 重写onKeyListener,按返回键消失
        left_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    jiagePopupWindow.dismiss();
                    //为空的话就会重新构建不会保留
//				popLeft = null;
                    return true;
                }
                return false;
            }
        });
        // 设置动画效果
//	popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击其他地方消失
        left_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (jiagePopupWindow != null && jiagePopupWindow.isShowing()) {
                    jiagePopupWindow.dismiss();
                }
                return false;
            }
        });

        // pop.xml视图里面的控件

        //listview的监听
        jiageListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                jiageAdapter.setSelectItem(position);
                imaPosition = position;
                jiageAdapter.notifyDataSetChanged();
                jiageString = jiageList.get(position).getName();
                //第一个都是1
                setTabHeadText(3, jiageString);
                jiagePopupWindow.dismiss();
                requestData();

            }
        });


    }

    private void initPinpaiPopWindow() {
        View left_view = PeiJianActivity.this.getLayoutInflater().inflate
                (R.layout.popupwindow_select_pinpai, null, false);
        left_view.setFocusable(true); // 这个很重要
        left_view.setFocusableInTouchMode(true);
        pinpaiListView = (ListView) left_view.findViewById(R.id.lv1);
        pinpaiAdapter = new PopupwindowSimpleListAdapter(PeiJianActivity.this, pinpaiList);
        pinpaiListView.setAdapter(pinpaiAdapter);
        FixListviewHeight.setListViewHeightBasedOnChildren(pinpaiListView);
        // PopupWindow(布局，宽度，高度)
        pinpaiPopupWindow = new PopupWindow(left_view,ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT, true);
        pinpaiPopupWindow.setFocusable(true);
        // 重写onKeyListener,按返回键消失
        left_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    pinpaiPopupWindow.dismiss();
                    //为空的话就会重新构建不会保留
//				popLeft = null;
                    return true;
                }
                return false;
            }
        });
        // 设置动画效果
//	popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击其他地方消失
        left_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (pinpaiPopupWindow != null && pinpaiPopupWindow.isShowing()) {
                    pinpaiPopupWindow.dismiss();
                }
                return false;
            }
        });

        // pop.xml视图里面的控件

        //listview的监听
        pinpaiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                pinpaiAdapter.setSelectItem(position);
                imaPosition = position;
                pinpaiAdapter.notifyDataSetChanged();
                pinpaiString = pinpaiList.get(position).getName();
                //第一个都是1
                setTabHeadText(2, pinpaiString);
                pinpaiPopupWindow.dismiss();
                requestData();

            }
        });
    }

    private void initBuweiPopWindow() {
        // 获取自定义布局文件pop.xml的视图
        View left_view = PeiJianActivity.this.getLayoutInflater().inflate
                (R.layout.popupwindow_select_buwei, null, false);
        left_view.setFocusable(true); // 这个很重要
        left_view.setFocusableInTouchMode(true);
        buweiListView = (ListView) left_view.findViewById(R.id.lv1);
        buweiAdapter = new PopupwindowSimpleListAdapter(PeiJianActivity.this, buweiList);
        buweiListView.setAdapter(buweiAdapter);
        FixListviewHeight.setListViewHeightBasedOnChildren(buweiListView);
        // PopupWindow(布局，宽度，高度)
        buweiPopupWindow = new PopupWindow(left_view,ViewGroup.LayoutParams.WRAP_CONTENT , ViewGroup.LayoutParams.WRAP_CONTENT, true);
        buweiPopupWindow.setFocusable(true);
        // 重写onKeyListener,按返回键消失
        left_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    buweiPopupWindow.dismiss();
                    //为空的话就会重新构建不会保留
//				popLeft = null;
                    return true;
                }
                return false;
            }
        });
        // 设置动画效果
//	popupWindow.setAnimationStyle(R.style.AnimationFade);
        //点击其他地方消失
        left_view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (buweiPopupWindow != null && buweiPopupWindow.isShowing()) {
                    buweiPopupWindow.dismiss();
                }
                return false;
            }
        });

        // pop.xml视图里面的控件

        //listview的监听
        buweiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                buweiAdapter.setSelectItem(position);
                imaPosition = position;
                buweiAdapter.notifyDataSetChanged();
                buewiString = buweiList.get(position).getName();
                //第一个都是1
                setTabHeadText(1, buewiString);
                buweiPopupWindow.dismiss();
                requestData();

            }
        });

    }

    private void requestData() {
        ShangpingParam param= new ShangpingParam();
        if(Utils.isValidValueString(buewiString)){

            param.type=buewiString;
        }
        if(Utils.isValidValueString(pinpaiString)){

            param.pinpai=pinpaiString;
        }
        if(Utils.isValidValueString(jiageString)){

            param.pinpai=jiageString;
        }
        ConnectManager.getInstance().GetShangping(param,callback);


    }

    private AbstractRequestListener<ShangPingBean> callback = new AbstractRequestListener<ShangPingBean>() {
        @Override
        public void onComplete(ShangPingBean bean) {
            shangPingBean = bean;
            shangpingList=shangPingBean.getResults();
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
                    initResultView();
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

    private void initResultView() {
        adapter=new ShangPingSearchResultAdapter(getApplicationContext(),shangpingList);
        searchResultListview.setAdapter(adapter);
        searchResultListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent();
                intent.setClass(getApplicationContext(),PeijianDetailActivity.class);
                intent.putExtra("name",shangpingList.get(position).getName());
                intent.putExtra("imageurl",shangpingList.get(position).getImgUrl());
                intent.putExtra("price",shangpingList.get(position).getPrice());
                intent.putExtra("type",shangpingList.get(position).getType());
                intent.putExtra("id",shangpingList.get(position).getId());
                intent.putExtra("pinpai",shangpingList.get(position).getPinpai());
                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();



    }

    private void setTabHeadText(int idx, String text) {
        switch (idx) {
            case 1:
                buweiTextview.setText(text);
//			icon1.setImageResource(image);
                break;
            case 2:
                pinpaiTextview.setText(text);
                break;
            //成立年限
            case 3:
                jiageTextview.setText(text);
                break;
            //更多筛选
            case 4:
//                mMore.setText(text);
                break;
            default:
                break;
        }
    }


    private void initSearchItemView() {

    }

    private void initScreenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        dm = getResources().getDisplayMetrics();
        screenHeight = dm.heightPixels;
        screenWidth = dm.widthPixels;
    }
}
