package com.u9time.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listview);

        ArrayList<String> arrayList = new ArrayList();
        final ArrayList<Student> studentsList = new ArrayList();
        for (int i = 0; i < 50; i++) {
            arrayList.add(String.valueOf(i));
            studentsList.add(new Student());
        }

        final MyListViewAdapter myListViewAdapter = new MyListViewAdapter(this, arrayList, studentsList);
        mListView.setAdapter(myListViewAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                最简单的方法
                myListViewAdapter.setBackGroundColor(position);

//                最不可取的方法,是错误的
//                for (int i = 0; i < studentsList.size(); i++) {
//                    Student student = studentsList.get(position);
//                    if (i==position){
//                        student.setAdu(true);
//                    }else{
//                        student.setAdu(false);
//                    }
//                }
//                myListViewAdapter.notifyDataSetChanged();
            }
        });
    }
}
