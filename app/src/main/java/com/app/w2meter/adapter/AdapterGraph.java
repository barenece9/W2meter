package com.app.w2meter.adapter;

/**
 * Created by etrans on 11/6/18.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.app.w2meter.R;

import java.util.ArrayList;
import java.util.HashMap;

public class AdapterGraph extends BaseAdapter {
    Context context;
    LayoutInflater inflater;
    ArrayList<HashMap<String, String>> data;
    HashMap<String, String> resultp = new HashMap<String, String>();
    public AdapterGraph(Context context,
                                ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        data = arraylist;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        View itemView = convertView;
        final MyViewHolder holder;
        if (itemView == null) {
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.inflater_graph_item, null, false);
            holder = new MyViewHolder();
            holder.tv_name=(TextView) itemView.findViewById(R.id.tv_name);

            itemView.setTag(holder);
        } else {
            holder = (MyViewHolder) itemView.getTag();
        }
        resultp = data.get(position);



        holder.tv_name.setText(resultp.get("name"));

        return itemView;
    }
    private static class MyViewHolder {
        public TextView tv_name;
    }

    public void refresh(ArrayList<HashMap<String,String>> list){
        data = list;
        // this.arraylist = new ArrayList<HashMap<String,String>>();
        // this.arraylist.addAll(data);
        notifyDataSetChanged();
    }


}