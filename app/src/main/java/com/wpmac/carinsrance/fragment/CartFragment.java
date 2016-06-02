package com.wpmac.carinsrance.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.adapter.ShangPingSearchResultAdapter;
import com.wpmac.carinsrance.base.BaseFragment;
import com.wpmac.carinsrance.bean.ShangPingBean;
import com.wpmac.carinsrance.comment.AbstractRequestListener;
import com.wpmac.carinsrance.comment.CustomError;
import com.wpmac.carinsrance.debug.L;
import com.wpmac.carinsrance.manager.ConnectManager;
import com.wpmac.carinsrance.param.ShangpingParam;
import com.wpmac.carinsrance.util.ActivityForResultUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wpmac on 16/6/1.
 */
public class CartFragment extends BaseFragment {

    @Bind(R.id.cartlistview)
    ListView cartlistview;
    @Bind(R.id.total_price)
    TextView totalPrice;
    private android.view.View View;
    private ShangPingBean shangPingBean;
    private TextView creatOrder;
    private static CartFragment cartFragment;
    private List<ShangPingBean.ResultsBean> shangpingList;
    private ShangPingSearchResultAdapter adapter;

    public static CartFragment getInstance() {
        if (cartFragment == null) {
            cartFragment = new CartFragment();
        }
        return cartFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (View == null) {
            View = inflater.inflate(R.layout.fragment_cart, container, false);
            L.i("CartFragment  onCreateView");
        }
        findViewById();
        setEventListener();
        initData();
        ButterKnife.bind(this, View);
        return View;
    }

    @Override
    protected void findViewById() {
        creatOrder= (TextView) View.findViewById(R.id.create_order_tv);
    }

    @Override
    protected void setEventListener() {
        creatOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"已经生成订单",Toast.LENGTH_LONG).show();
                shangpingList.clear();
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    protected void initData() {
        requestData();
    }


    private void requestData() {
        ShangpingParam param = new ShangpingParam();

        ConnectManager.getInstance().GetShangping(param, callback);


    }

    private AbstractRequestListener<ShangPingBean> callback = new AbstractRequestListener<ShangPingBean>() {
        @Override
        public void onComplete(ShangPingBean bean) {
            shangPingBean = bean;
            shangpingList = shangPingBean.getResults();
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
                    updateview();
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

    private void updateview() {

        adapter=new ShangPingSearchResultAdapter(getActivity(),shangpingList);
        cartlistview.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        int total=0;
        for(int i=0;i<shangpingList.size();i++){
            total+=shangpingList.get(i).getPrice();
        }
        totalPrice.setText("总价："+""+total+"元");


    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
