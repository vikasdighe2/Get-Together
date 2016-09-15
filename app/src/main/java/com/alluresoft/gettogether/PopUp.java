package com.alluresoft.gettogether;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by Vikas on 8/30/2016.
 */
public class PopUp extends Activity {
    private ImageView close_btn;
    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView,venue,title,tell_us_event;
    private int year, month, day;
    private Spinner category_spinner;
    private Button btnAddEvent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.popup_window);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width=dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int) (height*.6));

        close_btn=(ImageView) findViewById(R.id.close_btn);
        close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        venue = (TextView) findViewById(R.id.venue);
        title = (TextView) findViewById(R.id.title);
        tell_us_event=(TextView) findViewById(R.id.tell_us_event);
        //for date input
        dateView = (TextView) findViewById(R.id.date_ip);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        //showDate(year, month+1, day);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();

    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), "ca", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
            // arg1 = year
            // arg2 = month
            // arg3 = day
            showDate(arg1, arg2+1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    //for select category spinner
    public void addListenerOnSpinnerItemSelection() {
        category_spinner = (Spinner) findViewById(R.id.category_spinner);
        category_spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos,long id) {
            //((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#616A6B"));
            Toast.makeText(parent.getContext(),"OnItemSelectedListener : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }

    }

    public void addListenerOnButton() {

        btnAddEvent = (Button) findViewById(R.id.register_btn);

        btnAddEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(PopUp.this,"Event created Successfully with " + "\n "+ String.valueOf(category_spinner.getSelectedItem()) +
                                "\n on : "+ dateView,Toast.LENGTH_SHORT).show();
                finish();
            }

        });
    }

}
