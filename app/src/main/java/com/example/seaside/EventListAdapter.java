package com.example.seaside;

import android.content.Context;
import android.media.metrics.Event;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends ArrayAdapter<EventInfo>{

    private Context mContext;
    private int mResource;


    public EventListAdapter(Context context, int resource, ArrayList<EventInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String title = getItem(position).getTitle();
        String description = getItem(position).getDescription();
        String location = getItem(position).getLocation();
        String time = getItem(position).getTime();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvDescription = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvLocation = (TextView) convertView.findViewById(R.id.textView3);
        TextView tvTime = (TextView) convertView.findViewById(R.id.textView4);

        tvTitle.setText(title);
        tvDescription.setText(description);
        tvLocation.setText(location);
        tvTime.setText(time);

        return convertView;
    }

}
