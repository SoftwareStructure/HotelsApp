package ify.com.hotelsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseUser currentUser;
    private Button loginButton,phoneLoginButton;
    private EditText userEmail,userPassword;
    private TextView needNewAccountLink,forgetPasswrdLink;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        InitializeFields();


        needNewAccountLink.setOnClickListener(new View.OnClickListener()
        {
           @Override
            public void onClick(View v) {

                        sendUserToRegisterActivity();
                 }
         }


        );}


    private void InitializeFields() {

        loginButton=(Button)findViewById(R.id.login_button);
        phoneLoginButton=(Button)findViewById(R.id.phone_login_button);
        userEmail=(EditText)findViewById(R.id.login_email);
        userPassword=(EditText)findViewById(R.id.login_password);
        needNewAccountLink=(TextView)findViewById(R.id.need_new_account_link);
        forgetPasswrdLink=(TextView)findViewById(R.id.forget_password_link);

    }


    @Override
    protected void onStart(){

        super.onStart();

        if(currentUser!=null){
            sendUserToMainActivity();
        }
    }


    private void sendUserToMainActivity() {

        Intent loginIntent=new Intent(LoginActivity.this,MainActivity.class);
        startActivity(loginIntent);
    }

    private void sendUserToRegisterActivity() {

        Intent registerIntent=new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(registerIntent);
    }


}
