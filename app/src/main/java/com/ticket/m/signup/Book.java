package com.ticket.m.signup;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Book extends BasicActivity {
    private Spinner dropdown,classSelection,journeyDetails;
    private Spinner dropdown1;
    private String sourceStation,destinationStation;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapterForClassSelection;
    ArrayAdapter<CharSequence> adapterForJourneySelection;
    private float location,destination,c,rs,amnt;
    private FirebaseAuth auth;
    private int typeOfClass,typeOfJourney;

    Button bookBtn,fare;
    int price;
    float negC;
    Date time_of_booking,expiryTime;
    TextView fareTV;
    //fare logic for first class ticket
    public void firstClass_fare_cal(float n, float m)
    { float a,b;
        a=n;
        b=m;
        c=b-a;

        if (c<0)
        {
            c= -1*(c);
        }
        if(c>=0&&c<=10)
        {
            rs=50;
        }
        else if(c>10&&c<=15)
        {
            rs=70;
        }
        else if(c>15&&c<=20)
        {
            rs=105;
        }
        else if(c>20&&c<=27)
        {
            rs=135;
        }
        else if(c>27&&c<=31)//
        {
            rs=140;
        }
        else if(c>31&&c<=42)
        {
            rs=145;
        }
        else if(c>42&&c<=44)
        {
            rs=160;
        }
        else
        {
            rs=165;
        }

    }

    //fare logic for second class ticket
    public void secondClass_fare_cal(float n, float m)
    {
        float a,b;
        a=n;
        b=m;
        c=b-a;
        System.out.print("The value of the c "+c);
       Math.abs(c);
       System.out.println("The value of the c  abs method "+c);
       if(c<0)
       {
            c= (-1)*(c);
        }
        if(c>=0&&c<=9)
        {
            rs=5;
        }
        else if(c>=10&&c<=30)
        {
            rs=10;
        }
        else if(c>=31&&c<=55)
        {
            rs=15;
        }
        else if(c>=56&&c<=85)
        {
            rs=20;
        }
        else if(c>=86&&c<=105)
        {
            rs=25;
        }
        else if(c>=106&&c<=115)
        {
            rs=30;
        }
        else
        {
            rs=35;
        }
System.out.println("the fare is "+rs);
    }









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        auth=FirebaseAuth.getInstance();
        bookBtn =findViewById(R.id.bookBtn);
        dropdown=findViewById(R.id.dropdown);
        dropdown1=findViewById(R.id.dropdown1);
        classSelection=findViewById(R.id.classSelection);
        journeyDetails=findViewById(R.id.journeyDetails);
        fare=findViewById(R.id.fare);
        fareTV=findViewById(R.id.fareTV);
        //adding array into the adapter object

        adapter = ArrayAdapter.createFromResource(this,R.array.Central_stations,android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter instantiation for class selection
        adapterForClassSelection=ArrayAdapter.createFromResource(this,R.array.classSelection,android.R.layout.simple_spinner_item);

        adapterForClassSelection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       //journey selection adapter instantiation
        adapterForJourneySelection=ArrayAdapter.createFromResource(this,R.array.journeySelection,android.R.layout.simple_spinner_item);
        adapterForJourneySelection.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Book bk=new Book();


//adding onitemselectedlinstener to the classselection adapter
        dropdown.setAdapter(adapter);
        dropdown1.setAdapter(adapter);
        //adding onitemselectedlinstener to the journeyselection adapter
        journeyDetails.setAdapter(adapterForJourneySelection);
        journeyDetails.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch(position)
                {
                    case 0:
                        typeOfJourney=1;
                        break;
                    case 1:
                        typeOfJourney=2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        classSelection.setAdapter(adapterForClassSelection);
        classSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:
                        typeOfClass=1;
                        break;
                    case 1:
                        typeOfClass=2;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //registering the onitem listener
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                               @Override
                                               public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                   switch (position) {

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
                                                       case 26:
                                                           location = 56;
                                                           break;
                                                       case 27:
                                                           location = 58;
                                                           break;
                                                       case 28:
                                                           location = 64;
                                                           break;
                                                       case 29:
                                                           location = 71;
                                                           break;
                                                       case 30:
                                                           location = 79;
                                                           break;
                                                       case 31:
                                                           location = 85;
                                                           break;
                                                       case 32:
                                                           location = 94;
                                                           break;
                                                       case 33:
                                                           location = 107;
                                                           break;
                                                       case 34:
                                                           location = 120;
                                                           break;
                                                       case 35:
                                                           location = 56;
                                                           break;
                                                       case 36:
                                                           location = 57;
                                                           break;
                                                       case 37:
                                                           location=60;
                                                           break;
                                                       case 38:
                                                           location = 67;
                                                           break;
                                                       case 39:
                                                           location = 78;
                                                           break;
                                                       case 40:
                                                           location = 82;
                                                           break;
                                                       case 41:
                                                           location = 86;
                                                           break;
                                                       case 42:
                                                           location = 93;
                                                           break;
                                                       case 43:
                                                           location = 106;
                                                           break;
                                                       case 44:
                                                           location = 111;
                                                           break;
                                                       case 45:
                                                           location = 112;
                                                           break;
                                                       case 46:
                                                           location = 113;
                                                           break;
                                                       case 47:
                                                           location = 114;
                                                           break;
                                                       case 48:
                                                           location = 115;
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
                    case 26:
                        location = 56;
                        break;
                    case 27:
                        location = 58;
                        break;
                    case 28:
                        location = 64;
                        break;
                    case 29:
                        location = 71;
                        break;
                    case 30:
                        location = 79;
                        break;
                    case 31:
                        location = 85;
                        break;
                    case 32:
                        location = 94;
                        break;
                    case 33:
                        location = 107;
                        break;
                    case 34:
                        location = 120;
                        break;
                    case 35:
                        location = 56;
                        break;
                    case 36:
                        location = 57;
                        break;
                    case 37:
                        location=60;
                        break;
                    case 38:
                        location = 67;
                        break;
                    case 39:
                        location = 78;
                        break;
                    case 40:
                        location = 82;
                        break;
                    case 41:
                        location = 86;
                        break;
                    case 42:
                        location = 93;
                        break;
                    case 43:
                        location = 106;
                        break;
                    case 44:
                        location = 111;
                        break;
                    case 45:
                        location = 112;
                        break;
                    case 46:
                        location = 113;
                        break;
                    case 47:
                        location = 114;
                        break;
                    case 48:
                        location = 115;
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
                String type_of_ticket,type_of_journey="";
                sourceStation=dropdown.getSelectedItem().toString();
                destinationStation=dropdown1.getSelectedItem().toString();
                if(typeOfClass==2)
                {
                    secondClass_fare_cal(location,destination);
                     type_of_ticket="II";
                     if(typeOfJourney==1)
                     {
                         type_of_journey="Single";
                     }
                     if(typeOfJourney==2)
                     {
                         type_of_journey="Return";
                         rs=rs*2;
                     }

                }
                else{
                    firstClass_fare_cal(location,destination);
                    type_of_ticket="I";
                    if(typeOfJourney==1)
                    {
                        type_of_journey="Single";
                    }
                    if(typeOfJourney==2)
                    {
                        type_of_journey="Return";
                        rs=rs*2;
                    }
                }

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
                intent.putExtra("type_of_ticket",type_of_ticket);
                intent.putExtra("type_of_journey",type_of_journey);
                intent.putExtra("buttonValue",2);
                startActivity(intent);//starting the activity QrcodeGeneratorView class
            }
        });
        fare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (typeOfClass==2)
                {
                    secondClass_fare_cal(location,destination);
                    if(typeOfJourney==2)
                    {
                        rs=rs*2;
                    }
                }
                else
                {
                    firstClass_fare_cal(location,destination);
                    if(typeOfJourney==2)
                    {
                        rs=rs*2;
                    }
                }

                AlertDialog.Builder builder=new AlertDialog.Builder(Book.this);
                builder.setTitle("Your fare is Rs. "+rs);
                builder.setCancelable(true);
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });
    }
  }
