package hanan.hanan_ali.onebooked;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.core.Context;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

public class MainActivity extends AppCompatActivity
{
    Button sign_in_btn,sign_up_btn,dataButton;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        sign_in_btn= findViewById(R.id.signin_btn);
        sign_up_btn=findViewById(R.id.signup_btn);
        sign_in_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),signinActivity.class);
                startActivity(intent);

            }
        });

        sign_up_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(getApplicationContext(),signup.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        user=auth.getCurrentUser();
        if (user!=null)
        {
            Intent intent=new Intent(getApplicationContext(),StartActivity.class);
            startActivity(intent);
        }
    }





}
