package hanan.hanan_ali.onebooked;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signinActivity extends AppCompatActivity {
    EditText email_field_signin, password_field_signin_;
    Button singin_btn;
    String email, password;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email_field_signin = findViewById(R.id.email_field_signin);

        password_field_signin_ = findViewById(R.id.password_field_signin);


        singin_btn = findViewById(R.id.singin_btn);

        progressBar = findViewById(R.id.progressbar);


        auth = FirebaseAuth.getInstance();


        singin_btn.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                email = email_field_signin.getText().toString();

                password = password_field_signin_.getText().toString();


                if (email.length() == 0 || password.length() == 0)

                {

                    Toast.makeText(getApplicationContext(), "Please Enter A Valid Data", Toast.LENGTH_SHORT).show();

                } else

                {

                    progressBar.setVisibility(View.VISIBLE);

                    SignIn(email, password);

                }

            }

        });

    }


    public void SignIn(String email, String password)

    {

        auth.signInWithEmailAndPassword(email, password)

                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task)

                    {

                        if (task.isSuccessful())

                        {

                            user = auth.getCurrentUser();


                            if (user != null)

                            {

                                if (user.isEmailVerified())

                                {

                                    progressBar.setVisibility(View.INVISIBLE);


                                    Intent intent = new Intent(getApplicationContext(), StartActivity.class);

                                    startActivity(intent);

                                } else

                                {

                                    progressBar.setVisibility(View.INVISIBLE);


                                    Toast.makeText(getApplicationContext(), "Please Verify Your Email", Toast.LENGTH_SHORT).show();

                                }

                            }

                        } else

                        {

                            progressBar.setVisibility(View.INVISIBLE);


                            Toast.makeText(getApplicationContext(), "wrong email or password", Toast.LENGTH_SHORT).show();

                        }

                    }

                });

    }
}




