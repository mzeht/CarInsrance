package com.wpmac.carinsrance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.bean.ShangPingBean;
import com.wpmac.carinsrance.util.Utils;

import java.util.List;

/**
 * Created by wpmac on 16/6/1.
 */
public class ShangPingSearchResultAdapter extends BaseAdapter {

    private Context context;
    private List<ShangPingBean.ResultsBean> list;
    private LayoutInflater Inflater;
    private int selectItem=-1;

    public ShangPingSearchResultAdapter(Context context,List<ShangPingBean.ResultsBean> l){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.listview_item_shangping_result,
                    null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name_textview);
            viewHolder.price = (TextView) convertView.findViewById(R.id.jiage_textview);
            viewHolder.pinpai = (TextView) convertView.findViewById(R.id.pinpai_textview);
            viewHolder.type2 = (TextView) convertView.findViewById(R.id.type);
            viewHolder.type = (TextView) convertView.findViewById(R.id.type_textview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(Utils.isValidValueString(list.get(position).getPinpai())&&Utils.isValidValueString(list.get(position).getType())){
            viewHolder.name.setText("品牌为："+list.get(position).getPinpai()+"的"+list.get(position).getType());
        }else{
            viewHolder.name.setText("");
        }
        if(Utils.isValidValueString(list.get(position).getPinpai())){
            viewHolder.pinpai.setText(list.get(position).getPinpai());
        }else{
            viewHolder.pinpai.setText("");
        }
        if(Utils.isValidValueString(String.valueOf(list.get(position).getPrice()))){
            viewHolder.price.setText("￥"+String.valueOf(list.get(position).getPrice()));
        }else{
            viewHolder.price.setText("");
        }
        if(Utils.isValidValueString(String.valueOf(list.get(position).getType()))){
            viewHolder.type.setText(String.valueOf(list.get(position).getType()));
            viewHolder.type2.setText(String.valueOf(list.get(position).getType()));
        }else{
            viewHolder.type.setText("");
            viewHolder.type2.setText("");
        }
        return convertView;
    }


    private class ViewHolder{
        private TextView name,price,pinpai,type2,type;
    }
}
