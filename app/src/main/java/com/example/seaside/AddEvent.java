package com.example.seaside;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEvent extends AppCompatActivity {

    EditText title, description, location, time, date, volunteers;
    Button submit, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_event);

        submit = (Button) findViewById(R.id.eventSubmit);
        title = (EditText) findViewById(R.id.eventTitle);
        description = (EditText) findViewById(R.id.eventDescription);
        location = (EditText) findViewById(R.id.eventLocation);
        time = (EditText) findViewById(R.id.eventTime);
        date = (EditText) findViewById(R.id.eventDate);
        volunteers = (EditText) findViewById(R.id.eventVolunteers);
        back = (Button)findViewById(R.id.button3);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess controller = new DatabaseAccess(AddEvent.this);

                boolean success = controller.addEvent(title.getText().toString(), description.getText().toString(), location.getText().toString(), time.getText().toString() + "&" + date.getText().toString(), Integer.parseInt(volunteers.getText().toString()));
                String message = success == true ? "Success" : "Failure adding to the database";

                Toast.makeText(AddEvent.this, String.valueOf(message), Toast.LENGTH_SHORT).show();
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddEvent.this, ThirdActivity.class));
            }
        });


      }

    }
