package com.wpmac.carinsrance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.bean.CustomerBean;
import com.wpmac.carinsrance.util.Utils;

import java.util.List;

/**
 * Created by wpmac on 16/6/1.
 */
public class CustomersAdapter extends BaseAdapter {

    private Context context;
    private List<CustomerBean.ResultsBean> list;
    private LayoutInflater Inflater;
    private int selectItem=-1;

    public CustomersAdapter(Context context,List<CustomerBean.ResultsBean> l){
        this.context=context;
        this.list=l;
        this.Inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_customers,
                    null);
            viewHolder.tv1 = (TextView) convertView.findViewById(R.id.name_textview);
            viewHolder.tv2 = (TextView) convertView.findViewById(R.id.jiage_textview);
            viewHolder.tv3 = (TextView) convertView.findViewById(R.id.pinpai_textview);
            viewHolder.tv4 = (TextView) convertView.findViewById(R.id.type);
            viewHolder.tv5 = (TextView) convertView.findViewById(R.id.type_textview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(Utils.isValidValueString(list.get(position).getName())){
            viewHolder.tv1.setText(String.valueOf("客户姓名"+list.get(position).getName()));
        }else{
            viewHolder.tv1.setText("");
        }
        if(Utils.isValidValueString(list.get(position).getCarnumber())){
            viewHolder.tv2.setText(String.valueOf(list.get(position).getCarnumber()));
        }else{
            viewHolder.tv2.setText("");
        }
        if(Utils.isValidValueString(list.get(position).getPhone())){
            viewHolder.tv3.setText(String.valueOf(list.get(position).getPhone()));
        }else{
            viewHolder.tv3.setText("");
        }
        if(Utils.isValidValueString(list.get(position).getIdcard())){
            viewHolder.tv4.setText(String.valueOf(list.get(position).getIdcard()));
        }else{
            viewHolder.tv4.setText("");
        }
        if(Utils.isValidValueString(list.get(position).getName())){
            viewHolder.tv4.setText(String.valueOf(list.get(position).getName()));
        }else{
            viewHolder.tv4.setText("");
        }
        return convertView;
    }


    private class ViewHolder{
        private TextView tv1,tv2,tv3,tv4,tv5;
    }
}
