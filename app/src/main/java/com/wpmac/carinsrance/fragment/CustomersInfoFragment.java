package com.wpmac.carinsrance.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.adapter.CustomersAdapter;
import com.wpmac.carinsrance.base.BaseFragment;
import com.wpmac.carinsrance.bean.CustomerBean;
import com.wpmac.carinsrance.comment.AbstractRequestListener;
import com.wpmac.carinsrance.comment.CustomError;
import com.wpmac.carinsrance.manager.ConnectManager;
import com.wpmac.carinsrance.param.CustomerParam;
import com.wpmac.carinsrance.util.ActivityForResultUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by wpmac on 16/4/10.
 */
public class CustomersInfoFragment extends BaseFragment {

    private static CustomersInfoFragment customersInfoFragment;
    @Bind(R.id.listview)
    ListView listview;
    @Bind(R.id.add)
    FloatingActionButton add;
    private View View;
    private FloatingActionButton addfab;
    private CustomerBean CustomerBean;
    private List<CustomerBean.ResultsBean> DataList;
    private CustomersAdapter adapter;


    public static CustomersInfoFragment getInstance() {
        if (customersInfoFragment == null) {
            customersInfoFragment = new CustomersInfoFragment();
        }
        return customersInfoFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (View == null) {
            View = inflater.inflate(R.layout.fragment_customersinfo, container, false);
        }
        ButterKnife.bind(this, View);
        initData();
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
        CustomerParam param=new CustomerParam();
        ConnectManager.getInstance().GetCustomer(param,callback);
    }

    private AbstractRequestListener<CustomerBean> callback = new AbstractRequestListener<CustomerBean>() {
        @Override
        public void onComplete(CustomerBean bean) {
            CustomerBean = bean;
            DataList=CustomerBean.getResults();
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
        adapter=new CustomersAdapter(getActivity(),DataList);
        listview.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
