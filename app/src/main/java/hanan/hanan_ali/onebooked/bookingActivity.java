package hanan.hanan_ali.onebooked;

import android.app.DatePickerDialog;
import android.app.Dialog;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

public class bookingActivity extends AppCompatActivity
{
    EditText name_fild;
    CheckBox hair,Beard,Pigment,Musk;
    Button set_time,set_date,book_btn;
     static TextView selected_time,selected_date;
     FirebaseDatabase firebaseDatabase;
     DatabaseReference databaseReference;
     String name,hair2="",beard2="",pigment2="",musk2="",data_txt,time_txt;
     double price=0;
     String price2;





    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        name_fild=findViewById(R.id.name_fild);
        hair=findViewById(R.id.shave_hair_chk);
        Beard=findViewById(R.id.shave_beard);
        Pigment=findViewById(R.id.hair_pigment);
        Musk=findViewById(R.id.face_musk);
        set_time=findViewById(R.id.set_time_btn);
        set_date=findViewById(R.id.set_date_btn);

        book_btn=findViewById(R.id.book_btn);
        selected_time=findViewById(R.id.set_time_btn);
        selected_date=findViewById(R.id.set_date_btn);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        set_time.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                android.support.v4.app.DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");


            }
        });
        set_date.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");

            }
        });



        book_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                name=name_fild.getText().toString();
                if (hair.isChecked())
                {
                    price=price+25;
                    hair2="shave hair";
                }
                if (Beard.isChecked())
                {
                    price=price+15;
                    beard2="shave Beard";
                }
                if (Pigment.isChecked())
                {
                    price=price+35;
                    pigment2="hair pigment";

                }

                if (Musk.isChecked())
                {

                    price = price + 20;
                    musk2 = "face musk";
                }
                data_txt=selected_date.getText().toString();
                time_txt=selected_time.getText().toString();
                if (name.length()==0
                        ||hair.length()==0
                        &&Beard.length()==0
                        &&Pigment.length()==0
                        &&Musk.length()==0
                        ||time_txt.length()==0
                        ||data_txt.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"please enter a valid data",Toast.LENGTH_SHORT).show();

                }else
                    {
                        price2=Double.toString(price);
                        writeBookinDataBase(name,hair2,beard2,pigment2,musk2,time_txt,data_txt,price2);
                        Intent intent=new Intent(getApplicationContext(),StartActivity.class);
                        startActivity(intent);
                    }





            }
});

    }
    public void writeBookinDataBase(String name,String hair,String Pigment,String beard,String time,String date,String musk,String price)
    {
        BookList bookList=new BookList(name, hair, Pigment, beard, time, date, musk, price);
        String key=databaseReference.child("allbooks").push().getKey();
        databaseReference.child("allbooks").child(key).setValue(bookList);

    }
    public static class TimePickerFragment extends android.support.v4.app.DialogFragment
            implements TimePickerDialog.OnTimeSetListener
    {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState)
        {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute)
        {
            int hour = hourOfDay;
            int minutes = minute;
            String timeSet = "";
            if (hour > 12) {
                hour -= 12;
                timeSet = "PM";
            } else if (hour == 0) {
                hour += 12;
                timeSet = "AM";
            } else if (hour == 12){
                timeSet = "PM";
            }else{
                timeSet = "AM";
            }

            String min = "";
            if (minutes < 10)
                min = "0" + minutes ;
            else
                min = String.valueOf(minutes);

            // Append in a StringBuilder
            String aTime = new StringBuilder().append(hour).append(':')
                    .append(min ).append(" ").append(timeSet).toString();
            selected_time.setText(aTime);


            // Do something with the time chosen by the user
            selected_time.setText(hourOfDay +" : " + minute);
        }
    }
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day)
        {
            int month2=month+1;
            selected_date.setText(day +"/" + month2 +"/" +year);

            // Do something with the date chosen by the user
        }
    }
}
