package hanan.hanan_ali.onebooked;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class signup extends AppCompatActivity
{
    EditText email_field_signup,password_field_signup;
    Button signup_btn;
    String email,password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email_field_signup=findViewById(R.id.email_field_signup);
        password_field_signup=findViewById(R.id.password_field_signup);
        signup_btn=findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                email=email_field_signup.getText().toString();
                password=password_field_signup.getText().toString();
                if (email.length()==0|| password.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"دخل الايميل والباسورد يا بابا ولا ادخلهولك انا؟!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    signup(email,password);
                }

            }
        });


        auth=FirebaseAuth.getInstance();
    }
    public void signup(String email,String password)
    {
        auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful())
                        {
                            FirebaseUser user=auth.getCurrentUser();
                            if(user!=null)
                            {
                                user.sendEmailVerification();
                                Intent intent=new Intent(getApplicationContext(),EmailVervActivity.class);
                                startActivity(intent);

                            }
                        }else
                        {
                            Toast.makeText(getApplicationContext(),"this emil is signed in befor",Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }



}
