package com.alluresoft.gettogether;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button create_acc,my_profile,login,search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create_acc=(Button) findViewById(R.id.create_account);
        my_profile = (Button)findViewById(R.id.my_profile);
        login = (Button)findViewById(R.id.login);
        search=(Button) findViewById(R.id.search);

    }

    public void onClick(View v){

        if(v.getId() == R.id.create_account){
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.my_profile){
            Intent intent = new Intent(this, MyProfileActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.login){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.search){
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.meet_window) {
            Intent intent = new Intent(this, CreatMeetUpActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.comment_on_meetup_window) {
            Intent intent = new Intent(this, CustomViewIconTextTabsActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.other_profile) {
            Intent intent = new Intent(this, OtherProfileActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.walk_through_screen) {
            Intent intent = new Intent(this, WalkThroughActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.details_of_meetup) {
            Intent intent = new Intent(this, MeetupDetailsActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.discover_tinder) {
            Intent intent = new Intent(this, DiscoverTinderActivity.class);
            startActivity(intent);
        }else if(v.getId() == R.id.nearby) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }




    }


}


