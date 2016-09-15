package com.alluresoft.gettogether;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private EditText email_id,password;
    private Button sign_in_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_id=(EditText) findViewById(R.id.email_id);
        password=(EditText) findViewById(R.id.password);
        sign_in_btn=(Button) findViewById(R.id.sign_in_btn);

        sign_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"Login Successfull!",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
