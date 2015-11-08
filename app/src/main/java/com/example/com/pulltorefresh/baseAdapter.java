package com.example.com.pulltorefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bruse on 15/11/1.
 */
public class baseAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BaseBena> arrayList;
    private LayoutInflater layoutInflater;

    public baseAdapter(ArrayList<BaseBena> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView=layoutInflater.inflate(R.layout.base_layout,null);
            holder=new ViewHolder();
            holder.name= (TextView) convertView.findViewById(R.id.text_one);
            holder.price= (TextView) convertView.findViewById(R.id.text_two);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        holder.name.setText(arrayList.get(position).getName());
        holder.price.setText(arrayList.get(position).getPrice());
        return convertView;
    }

    class ViewHolder {
        TextView name;
        TextView price;
    }
}
