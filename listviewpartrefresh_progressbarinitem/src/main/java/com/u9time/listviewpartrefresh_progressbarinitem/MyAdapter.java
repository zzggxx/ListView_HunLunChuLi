package com.u9time.listviewpartrefresh_progressbarinitem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2017/8/16.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Model> data;

    public MyAdapter(Context context) {
        this.context = context;
        this.data = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Model model = data.get(position);
        ViewHodle viewHodle = null;

        if (convertView == null) {

            viewHodle = new ViewHodle();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item, null, false);

            viewHodle.pb = (ProgressBar) convertView.findViewById(R.id.pb_show);
            viewHodle.btn = (Button) convertView.findViewById(R.id.btn);

            convertView.setTag(viewHodle);
        } else {

            viewHodle = (ViewHodle) convertView.getTag();

        }

        viewHodle.btn.setText(model.getName());
        viewHodle.pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (updateCallback != null)
                    updateCallback.startProgress(model, position);
            }
        });
        viewHodle.pb.setProgress(0);

        return convertView;
    }

    public static class ViewHodle {
        Button btn;
        ProgressBar pb;
    }

    public void addData(Model model) {
        data.add(model);
        notifyDataSetChanged();
    }

    private UpdateCallback updateCallback;

    public UpdateCallback getUpdateCallback() {
        return updateCallback;
    }

    public interface UpdateCallback {
        void startProgress(Model model, int position);
    }

    public void setUpdateCallback(UpdateCallback updateCallback) {
        this.updateCallback = updateCallback;
    }
}
