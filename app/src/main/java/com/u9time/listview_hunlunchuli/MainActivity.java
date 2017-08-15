package com.u9time.listview_hunlunchuli;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.lv);

        ArrayList<String> arrayList = new ArrayList();
        ArrayList<Student> studentsList = new ArrayList();
        for (int i = 0; i < 20; i++) {
            arrayList.add(String.valueOf(i));
            studentsList.add(new Student());
        }

        MyListViewAdapter myListViewAdapter = new MyListViewAdapter(this, arrayList, studentsList);
        listView.setAdapter(myListViewAdapter);

        myListViewAdapter.setmOnBtnOnclickListener(new MyListViewAdapter.OnBtnOnclickListener() {
            @Override
            public void onBtnOnclickListener(View view, int position) {
                Toast.makeText(MainActivity.this, "我是item中的子控件,在activity中被调用了,失败adapter调用的", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 因为已经有子控件拿到了焦点事件,此时需要在item的跟布局中加入android:descendantFocusability="blocksDescendants"
         * 整个条目的点击事件才能起效.
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "我是item中控件,是被listview调用的", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
