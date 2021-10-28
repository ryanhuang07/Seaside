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

        ListView mListView = (ListView) findViewById(R.id.listView);
        ArrayList<com.example.seaside.EventInfo> eventList = new ArrayList<>();

        EventInfo event0 = new EventInfo("Event 1", "This is an event.", "1 Address Ln", "3:20 on 6/9");
        EventInfo event1 = new EventInfo("Event 2", "This is an event.", "1 Address Ln", "4:20 on 6/9");
        EventInfo event2 = new EventInfo("Event 3", "This is an event.", "1 Address Ln", "5:20 on 6/9");
        EventInfo event3 = new EventInfo("Event 4", "This is an event.", "1 Address Ln", "6:20 on 6/9");
        EventInfo event4 = new EventInfo("Event 5", "This is an event.", "1 Address Ln", "7:20 on 6/9");

        eventList.add(event4);
        eventList.add(event3);
        eventList.add(event2);
        eventList.add(event1);
        eventList.add(event0);

        EventListAdapter adapter = new EventListAdapter(this, R.layout.adapter_view_layout, eventList);
        mListView.setAdapter(adapter);

        bAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SecondActivity.this, AddEvent.class));
            }
        });

    }
}
