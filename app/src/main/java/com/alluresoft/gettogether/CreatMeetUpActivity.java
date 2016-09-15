package com.alluresoft.gettogether;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CreatMeetUpActivity extends AppCompatActivity {

    private Button meet_up_popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_meet_up);

        meet_up_popup=(Button) findViewById(R.id.meet_up_popup);
        meet_up_popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreatMeetUpActivity.this,PopUp.class));

            }
        });
    }
}
