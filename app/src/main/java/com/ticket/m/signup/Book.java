package com.ticket.m.signup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book extends AppCompatActivity {
    private Spinner dropdown;
    private Spinner dropdown1;
    private String sourceStation,destinationStation;
    ArrayAdapter<CharSequence> adapter;
    private float location,destination,c,rs,amnt;


    Button bookBtn;
    int price;
    float negC;
    Date time_of_booking,expiryTime;
    //fare logic
    public void cal(float n, float m)
    {
        float a,b;
        a=n;
        b=m;
        c=b-a;
        System.out.println("value of the c before abs "+c);
        Math.abs(c);
        negC=c;
        System.out.println("value of the c after abs "+c);

        if (c==negC)
        {
            c= (-1)*(c);
            System.out.println("value of the c in if block "+c);
        }
        if(c>=0&&c<=10)
        {
            rs=5;
        }
        else if(c>10&&c<=30)
        {
            rs=10;
        }
        else if(c>30&&c<=55)
        {
            rs=15;
        }
        else if(c>55&&c<=85)
        {
            rs=20;
        }
        else if(c>85&&c<=105)
        {
            rs=25;
        }
        else if(c>105&&c<=115)
        {
            rs=30;
        }
        else
        {
            rs=35;
        }


    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        bookBtn =findViewById(R.id.bookBtn);
        dropdown=findViewById(R.id.dropdown);
        dropdown1=findViewById(R.id.dropdown1);
        //adding array into the adapter object

        adapter = ArrayAdapter.createFromResource(this,R.array.stations,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

//Adding adapter to the dropdown
        dropdown.setAdapter(adapter);
        dropdown1.setAdapter(adapter);
        //registering the onitem listener
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                   switch (position) {
                                                       case 0:
                                                           location = 0;
                                                           break;
                                                       case 1:
                                                           location = 1;
                                                           break;
                                                       case 2:
                                                           location = 2;
                                                           break;
                                                       case 3:
                                                           location = 4;
                                                           break;
                                                       case 4:
                                                           location = 5;
                                                           break;
                                                       case 5:
                                                           location = 6;
                                                           break;
                                                       case 6:
                                                           location = 8;
                                                           break;
                                                       case 7:
                                                           location = 9;
                                                           break;
                                                       case 8:
                                                           location = 11;
                                                           break;
                                                       case 9:
                                                           location = 13;
                                                           break;
                                                       case 10:
                                                           location = 15;
                                                           break;
                                                       case 11:
                                                           location = 18;
                                                           break;
                                                       case 12:
                                                           location = 21;
                                                           break;
                                                       case 13:
                                                           location = 23;
                                                           break;
                                                       case 14:
                                                           location = 25;
                                                           break;
                                                       case 15:
                                                           location = 26;
                                                           break;
                                                       case 16:
                                                           location = 28;
                                                           break;
                                                       case 17:
                                                           location = 32;
                                                           break;
                                                       case 18:
                                                           location = 34;
                                                           break;
                                                       case 19:
                                                           location = 36;
                                                           break;
                                                       case 20:
                                                           location = 40;
                                                           break;
                                                       case 21:
                                                           location = 43;
                                                           break;
                                                       case 22:
                                                           location = 47;
                                                           break;
                                                       case 23:
                                                           location = 48;
                                                           break;
                                                       case 24:
                                                           location = 50;
                                                           break;
                                                       case 25:
                                                           location = 54;
                                                           break;


                                                   }
                                               }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dropdown1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position)
                {
                    case 0:
                        destination=0;
                        break;
                    case 1:
                        destination=1;
                        break;
                    case 2:
                        destination=2;
                        break;
                    case 3:
                        destination=4;
                        break;
                    case 4:
                        destination=4;
                        break;
                    case 5:
                        destination=6;
                        break;
                    case 6:
                        destination=8;
                        break;
                    case 7:
                        destination=9;
                        break;
                    case 8:
                        destination=11;
                        break;
                    case 9:
                        destination=13;
                        break;
                    case 10:
                        destination=15;
                        break;
                    case 11:
                        destination=18;
                        break;
                    case 12:
                        destination=21;
                        break;
                    case 13:
                        destination=23;
                        break;
                    case 14:
                        destination=25;
                        break;
                    case 15:
                        destination=26;
                        break;
                    case 16:
                        destination=28;
                        break;
                    case 17:
                        destination=32;
                        break;
                    case 18:
                        destination=34;
                        break;
                    case 19:
                        destination=36;
                        break;
                    case 20:
                        destination=40;
                        break;
                    case 21:
                        destination=43;
                        break;
                    case 22:
                        destination=47;
                        break;
                    case 23:
                        destination=48;
                        break;
                    case 24:
                        destination=50;
                        break;

                    case 25:
                        destination=54;
                        break;



                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sourceStation=dropdown.getSelectedItem().toString();
                destinationStation=dropdown1.getSelectedItem().toString();
                cal(location,destination);
                Calendar calendar=Calendar.getInstance();
                time_of_booking=calendar.getTime();
                calendar.add(Calendar.DAY_OF_YEAR,1);
                expiryTime=calendar.getTime();
                //converting it to the date-month-year format and converting the values into string format
                DateFormat dateFormat=new SimpleDateFormat("dd-MMM-yyyy");
                String bookingTime=dateFormat.format(time_of_booking);
                String expriy_time=dateFormat.format(expiryTime);
                Intent intent=new Intent(Book.this,QrCodeGenerator.class);

                intent.putExtra("source",sourceStation);//adding the values of sourceid  in the  intent hashmap object
                intent.putExtra("destination",destinationStation);//adding the value of destinationid in intent hashmap object
                intent.putExtra("price",rs);
                intent.putExtra("bookingTime",bookingTime);
                intent.putExtra("expiry_time",expriy_time);
                startActivity(intent);//starting the activity QrcodeGeneratorView class
            }
        });
    }
}
