package com.u9time.listviewpartrefresh_progressbarinitem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

/**
 * 此局部刷新的案例是有问题的.以后就不要看了
 */
public class MainActivity extends AppCompatActivity implements MyAdapter.UpdateCallback {

    private ListView listView;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        myAdapter = new MyAdapter(this);
        myAdapter.setUpdateCallback(this);

        listView.setAdapter(myAdapter);

        // TODO: 2017/8/18 测试哈
        initMyAdapterData();
    }

    /**
     * 这是一种思路方式,也就是说有很多种的添加数据的方式,但是总之而言要注意两点:
     * 1,先添加集合而后在修改数据,对于刷新或者加载更多有意义
     * 2,类对象是adapter.
     */
    private void initMyAdapterData() {
        for (int i = 0; i < 20; i++) {
            Model model = new Model(i, "Click--");
            myAdapter.addData(model);
        }
    }

    @Override
    public void startProgress(final Model model, final int position) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    updateProgressInUiThread(model, i, position);
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    void updateProgressInUiThread(Model model, int progress, int position) {
        updateProgressPartly(progress, position);
    }


    private void updateProgressPartly(int progress, int position) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            View view = listView.getChildAt(position - firstVisiblePosition);
            if (view.getTag() instanceof MyAdapter.ViewHodle) {
                MyAdapter.ViewHodle vh = (MyAdapter.ViewHodle) view.getTag();
                vh.pb.setProgress(progress);
            }
        }
    }
}
