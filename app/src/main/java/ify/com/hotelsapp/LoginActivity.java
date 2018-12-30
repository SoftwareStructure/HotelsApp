package ify.com.hotelsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dataHolder.UserDataHolder;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener  {

    private static final String TAG = "EmailPassword";
    private FirebaseUser currentUser;
    private Button loginButton,phoneLoginButton;
    private EditText userEmail,userPassword;
    private TextView needNewAccountLink, forgetPasswrdLink;

    private FirebaseAuth mAuth;

    static String currentemail;

    private FirebaseUser mUser;
    private DatabaseReference users;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitializeFields();
        }


    private void InitializeFields() {


        phoneLoginButton=(Button)findViewById(R.id.phone_login_button);
        userEmail=(EditText)findViewById(R.id.login_email);
        userPassword=(EditText)findViewById(R.id.login_password);
        forgetPasswrdLink=(TextView)findViewById(R.id.forget_password_link);
        mAuth=FirebaseAuth.getInstance();
        users=FirebaseDatabase.getInstance().getReference("users");


        findViewById(R.id.need_new_account_link).setOnClickListener(this);
        findViewById(R.id.login_button).setOnClickListener(this);
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

    private void sendUserToSignIn(final String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        //showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            sendToManagersActivity();

                            currentemail=email;
                            Toast.makeText(LoginActivity.this,"------>"+currentemail, Toast.LENGTH_LONG).show();


                            writeToUserDatabase(user);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                      
                       // hideProgressDialog();
                    
                    }
                });
       
    }

    private void writeToUserDatabase(FirebaseUser user) {


    }


    private void sendToManagersActivity() {

        Intent managerIntent=new Intent(LoginActivity.this,Managers.class);
        startActivity( managerIntent);

    }

    private void updateUI(FirebaseUser user) {

      //  UserDataHolder.getDataHolder().setCurrentUser(user.getUid());
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private boolean validateForm() {

        boolean valid = true;

        String email = userEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            userEmail.setError("Required.");

            valid = false;
        } else {
            userEmail.setError(null);
        }

        String password = userPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            userPassword.setError("Required.");
            valid = false;
        } else
            userPassword.setError(null);


        return valid;
    }


    @Override
    public void onClick(View v) {

        int id=v.getId();

     if(id==R.id.need_new_account_link)
         sendUserToRegisterActivity();

     else if(id==R.id.login_button)
         sendUserToSignIn(userEmail.getText().toString(),userPassword.getText().toString());

       }

}

