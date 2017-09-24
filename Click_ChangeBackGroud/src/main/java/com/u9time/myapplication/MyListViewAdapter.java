package com.u9time.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2017/9/24.
 */

class MyListViewAdapter extends BaseAdapter {
    private MainActivity mainActivity;
    private ArrayList<String> arrayList;
    private ArrayList<Student> studentsList;
    private final LayoutInflater inflater;
    private int mSelectPostion;

    public MyListViewAdapter(MainActivity mainActivity, ArrayList<String> arrayList, ArrayList<Student> studentsList) {
        this.mainActivity = mainActivity;
        this.arrayList = arrayList;
        this.studentsList = studentsList;
        inflater = LayoutInflater.from(mainActivity);
    }

    public void setBackGroundColor(int position) {
        if (mSelectPostion != position) {
            mSelectPostion = position;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
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
        ViewHolder viewHolder = null;

//        Student student = studentsList.get(position);

        if (convertView == null) {
//            LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null);

            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.rl = (RelativeLayout) convertView.findViewById(R.id.rl);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (mSelectPostion == position) {
            viewHolder.rl.setBackgroundColor(mainActivity.getResources().getColor(R.color.c1));
        } else {
            viewHolder.rl.setBackgroundColor(mainActivity.getResources().getColor(R.color.c2));
        }

//        最不可取的方法
//        if (student.isAdu) {
//            viewHolder.rl.setBackgroundColor(mainActivity.getResources().getColor(R.color.c1));
//        } else {
//            viewHolder.rl.setBackgroundColor(mainActivity.getResources().getColor(R.color.c2));
//        }

        viewHolder.tv.setText(arrayList.get(position));

        return convertView;
    }

    class ViewHolder {
        private TextView tv;
        private RelativeLayout rl;
    }
}
