package com.example.oh.week6_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Created by OH on 2017-04-13.
 */

public class ListAdapter extends BaseAdapter {
    Context context;
    ArrayList<FoodData> foodDatas = new ArrayList<>();
    public static boolean[] isChecked;



    public ListAdapter(Context context, ArrayList<FoodData> foodDatas) {
        this.context = context;
        this.foodDatas = foodDatas;
        this.isChecked = new boolean[foodDatas.size()];
    }
    public void setChecked (int position) {
        isChecked[position] = !isChecked[position];
    }

    @Override
    public int getCount() {
        return foodDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return foodDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);

        TextView tName = (TextView) convertView.findViewById(R.id.tName);
        TextView tTel = (TextView) convertView.findViewById(R.id.tTel);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.image);
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
        checkBox.setChecked(((ListView)parent).isItemChecked(position));

        FoodData data = foodDatas.get(position);
        tName.setText(data.getName());
        tTel.setText(data.getTel());


        if (MainActivity.select == true) {
            checkBox.setVisibility(View.VISIBLE);
            checkBox.setClickable(true);
            checkBox.setEnabled(true);
        }
        else {
            checkBox.setVisibility(View.INVISIBLE);
            checkBox.setClickable(false);
            checkBox.setEnabled(false);
        }
        if (data.getType() == 0)
            imageView.setImageResource(R.drawable.chicken);
        else if(data.getType() == 1)
            imageView.setImageResource(R.drawable.hamburger);
        else
            imageView.setImageResource(R.drawable.pizza);
        return convertView;
    }

    public void setSortNameOrType(int num) {
        if (num == 1) {
            Collections.sort(foodDatas, nameAsc);
            this.notifyDataSetChanged();
        }
        else if (num == 2) {
            Collections.sort(foodDatas, typeAsc);
            this.notifyDataSetChanged();
        }
    }

    public void setfoodDatas(ArrayList<FoodData> foodDatas) {
        this.foodDatas = foodDatas;
    }

    private final static Comparator<FoodData> nameAsc = new Comparator<FoodData>() {
        @Override
        public int compare(FoodData o1, FoodData o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    private final static Comparator<FoodData> typeAsc = new Comparator<FoodData>() {
        @Override
        public int compare(FoodData o1, FoodData o2) {
            int num;
            if (o1.getType() > o2.getType())
                num = 1;
            else if (o1.getType() < o2.getType())
                num = -1;
            else
                num = 0;
            return num;
        }
    };
}
