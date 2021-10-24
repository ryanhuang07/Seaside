package com.example.seaside;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Signup extends AppCompatActivity {

    EditText name, email, password, confirm;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        submit = (Button) findViewById(R.id.signupSubmit);
        name = (EditText) findViewById(R.id.signupName);
        email = (EditText) findViewById(R.id.signupEmail);
        password = (EditText) findViewById(R.id.signupPassword);
        confirm = (EditText) findViewById(R.id.signupConfirmPassword);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (password.getText().toString().compareTo(confirm.getText().toString()) != 0) {
                    Toast.makeText(Signup.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                } else {
                    DatabaseAccess controller = new DatabaseAccess(Signup.this);

                    long success = controller.addUser(name.getText().toString(), email.getText().toString(), password.getText().toString(), "students");

                    if (success == -1) {
                        Toast.makeText(Signup.this, "Error, please contact an administrator", Toast.LENGTH_LONG).show();
                    } else if (success == -2) {
                        Toast.makeText(Signup.this, "User with email already exists", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Signup.this, "Success", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
