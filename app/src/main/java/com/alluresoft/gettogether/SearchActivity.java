package com.alluresoft.gettogether;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private TextView query;
    private Button meetup,people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Toolbar toolbar=(Toolbar) findViewById(R.id.search_page_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        meetup=(Button) findViewById(R.id.meet_up);
        people=(Button) findViewById(R.id.people);


    }


    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.meet_up:
                Toast.makeText(this,"you pressed meet up button",Toast.LENGTH_SHORT).show();
                break;

            case R.id.people:
                Toast.makeText(this,"you pressed people button",Toast.LENGTH_SHORT).show();
                break;

            default:
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu_main; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.option1) {
            Toast.makeText(this, "option 1 selected", Toast.LENGTH_SHORT).show();

        }else if(id==R.id.option2){
            Toast.makeText(this, "option 2 selected", Toast.LENGTH_SHORT).show();

        }else if(id==R.id.option3){
            Toast.makeText(this, "option 3 selected", Toast.LENGTH_SHORT).show();

        }

        return super.onOptionsItemSelected(item);
    }
}
