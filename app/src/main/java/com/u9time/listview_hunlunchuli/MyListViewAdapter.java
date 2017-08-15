package com.u9time.listview_hunlunchuli;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/8/14.
 */

class MyListViewAdapter extends BaseAdapter {
    private MainActivity mainActivity;
    private ArrayList<String> arrayList;
    private List<Student> studentsList;

    public MyListViewAdapter(MainActivity mainActivity, ArrayList<String> arrayList, ArrayList<Student> studentsList) {
        this.mainActivity = mainActivity;
        this.arrayList = arrayList;
        this.studentsList = studentsList;
    }

    @Override
    public int getCount() {
        return studentsList.size();
    }

    @Override
    public Object getItem(int position) {
//        return arrayList.get(position);
        return null != studentsList ? studentsList.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        Student itemStudent = (Student) getItem(position);

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mainActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null);

            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            viewHolder.cb = (CheckBox) convertView.findViewById(R.id.cb);
            viewHolder.btn = (Button) convertView.findViewById(R.id.btn);
            viewHolder.btn_dianzan = (Button) convertView.findViewById(R.id.btn_dianzan);

            /**
             * 以下是为了在activity中使用点击事件,是拿到item中子控件的点击事件,同时可以传很多的值
             */
            viewHolder.btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnBtnOnclickListener != null) {
                        mOnBtnOnclickListener.onBtnOnclickListener(v, position);
                    }
                }
            });

            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Student tag = (Student) finalViewHolder.cb.getTag();
                    tag.setAdu(buttonView.isChecked());
                }
            });

            convertView.setTag(viewHolder);
            /**
             * 此处可设置为我们使用的数据实体对象,不需要自己创建bean对象,无论在第一次或者是复用的使用都需要settag()才行
             * 方便在点击的时候我们及时的拿出来使用.
             */
            viewHolder.cb.setTag(itemStudent);

        } else {

            viewHolder = (ViewHolder) convertView.getTag();
            viewHolder.cb.setTag(itemStudent);

        }

        /**
         *一下做法完全的错误,是行不通的!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         */
        viewHolder.btn_dianzan.setTag(position);
        viewHolder.btn_dianzan.setText("需要填上不同的数据" + position);
        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btn_dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((int) finalViewHolder1.btn_dianzan.getTag()) == position) {
                    finalViewHolder1.btn_dianzan.setText("你牛x");
                }
            }
        });


        viewHolder.tv.setText(arrayList.get(position));
        /**
         * 以下是为了item的背景色状态
         */
        if (position < 3) {
            viewHolder.tv.setBackgroundColor(Color.parseColor("#55ff0000"));
        } else {
            viewHolder.tv.setBackgroundColor(Color.parseColor("#ffffff"));
        }

        viewHolder.cb.setChecked(itemStudent.isAdu);

        return convertView;
    }

    private class ViewHolder {
        TextView tv;
        CheckBox cb;
        Button btn;
        Button btn_dianzan;
    }

    public OnBtnOnclickListener mOnBtnOnclickListener;

    public void setmOnBtnOnclickListener(OnBtnOnclickListener mOnBtnOnclickListener) {
        this.mOnBtnOnclickListener = mOnBtnOnclickListener;
    }

    interface OnBtnOnclickListener {
        void onBtnOnclickListener(View view, int position);
    }
}
