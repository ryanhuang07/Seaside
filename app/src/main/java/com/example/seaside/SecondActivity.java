package com.example.seaside;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

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

        EventInfo deanSex = new EventInfo("DEan","dean","Male", "haa@gunnypoop");
        EventInfo deanSex2 = new EventInfo("poop","dean","Male", "hahahehe");
        EventInfo deanSex3 = new EventInfo("shit","dean","Male", "shitfar");

        ArrayList<EventInfo> eventList = new ArrayList<>();
        eventList.add(deanSex);
        eventList.add(deanSex2);
        eventList.add(deanSex3);

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
