package com.example.seaside;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    ArrayList<String> listItem = new ArrayList<>();
    ArrayAdapter adapter;

    ListView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button bAddEvent = (Button)findViewById(R.id.buttonAddEvent);

        ListView mListView = (ListView) findViewById(R.id.listView);
        ArrayList<com.example.seaside.EventInfo> eventList = new ArrayList<>();

        EventInfo event0 = new EventInfo("Event 1", "This is an event.", "1 Address Ln", "11:30 on 12/8/21", "0 Registered, 4 Needed");
        EventInfo event1 = new EventInfo("Event 2", "This is an event.", "1 Address Ln", "11:00 on 12/1/21", "0 Registered, 15 Needed");
        EventInfo event2 = new EventInfo("Event 3", "This is an event.", "1 Address Ln", "7:00 on 11/22/21", "0 Registered, 25 Needed");
        EventInfo event3 = new EventInfo("Event 4", "This is an event.", "1 Address Ln", "12:00 on 11/15/21", "0 Registered, 12 Needed");
        EventInfo event4 = new EventInfo("Event 5", "This is an event.", "1 Address Ln", "5:00 on 11/1/21", "1 Registered, 10 Needed");
        EventInfo event5 = new EventInfo("New Event", "New Event", "3 Example Dr", "8:10 on 10/30/21", "0 Registered, 6 Needed");

        eventList.add(event5);
        eventList.add(event4);
        eventList.add(event3);
        eventList.add(event2);
        eventList.add(event1);
        eventList.add(event0);

        EventListAdapter adapter = new EventListAdapter(this, R.layout.adapter_view_layout, eventList);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(ThirdActivity.this, PopupWindow.class));
            }
        });

        bAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ThirdActivity.this, AddEvent.class));
            }
        });

    }
}

