package com.example.seaside;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.lang.Object;

public class SecondActivity extends AppCompatActivity {

    ArrayList<String> listItem = new ArrayList<>();
    ArrayAdapter adapter;

    ListView events;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Button bAddEvent = (Button)findViewById(R.id.buttonAddEvent);


        /*DatabaseAccess controller = new DatabaseAccess(SecondActivity.this);
        ArrayList<Integer> ids = controller.loadEvents();

        if (ids.get(0) == -1) {
            Toast.makeText(SecondActivity.this, "Failed loading events", Toast.LENGTH_LONG).show();
        } else {
            try {
                for (int i = 0; i < ids.size(); i++) {
                    String[] eventInfo = controller.eventInfo(ids.get(i));

                    if (eventInfo.length != 1) {
                        listItem.add(eventInfo[0]);
                        listItem.add(eventInfo[1]);
                    }
                }

                adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, listItem);
                events = findViewById(R.id.eventList);
                events.setAdapter(adapter);

                events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(SecondActivity.this, "Poopy " + position, Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {
                Toast.makeText(SecondActivity.this, "Failed loading events", Toast.LENGTH_LONG).show();
            }
        }*/

        /*class PopupWindow {
            int volunteerIncrement = 0;
        }

        PopupWindow PopupWindow = new PopupWindow();
        int registeredVolunteers = PopupWindow.volunteerIncrement;*/

        ListView mListView = (ListView) findViewById(R.id.listView);
        ArrayList<com.example.seaside.EventInfo> eventList = new ArrayList<>();

        EventInfo event0 = new EventInfo("Event 1", "This is an event.", "1 Address Ln", "11:30 on 12/8/21", "0 Registered, 4 Needed");
        EventInfo event1 = new EventInfo("Event 2", "This is an event.", "1 Address Ln", "11:00 on 12/1/21", "0 Registered, 15 Needed");
        EventInfo event2 = new EventInfo("Event 3", "This is an event.", "1 Address Ln", "7:00 on 11/22/21", "0 Registered, 25 Needed");
        EventInfo event3 = new EventInfo("Event 4", "This is an event.", "1 Address Ln", "12:00 on 11/15/21", "0 Registered, 12 Needed");
        EventInfo event4 = new EventInfo("Event 5", "This is an event.", "1 Address Ln", "5:00 on 11/1/21", "1 Registered, 10 Needed");

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
                startActivity(new Intent(SecondActivity.this, PopupWindow.class));
            }
        });

        bAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, AddEvent.class));
            }
        });

    }
}
