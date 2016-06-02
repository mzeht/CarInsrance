package com.wpmac.carinsrance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.model.SelectSimleList;
import com.wpmac.carinsrance.util.Utils;

import java.util.List;

/**
 * Created by wpmac on 16/6/1.
 */
public class PopupwindowSimpleListAdapter extends BaseAdapter{

    private List<SelectSimleList> l;
    private Context context;
    private int selectItem = -1;

    public PopupwindowSimpleListAdapter(Context context, List<SelectSimleList> l) {
        this.context = context;
        this.l = l;
    }



    @Override
    public int getCount() {
        return l.size();
    }

    @Override
    public Object getItem(int position) {
        return l.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (convertView == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.popupwindow_simple_list_item,
                    null);
            holder.name = (TextView) convertView.findViewById(R.id.name_textview);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        if(Utils.isValidValueString(l.get(position).getName())){
            holder.name.setText(l.get(position).getName());
        }

        if (position == selectItem) {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.white));
        } else {
            convertView.setBackgroundColor(context.getResources().getColor(R.color.color_gray));
//            holder.name.setTextColor(Color.BLACK);
        }
        return convertView;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
    }

    class Holder {
        TextView name;
    }
}
