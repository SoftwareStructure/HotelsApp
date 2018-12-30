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


import static ify.com.hotelsapp.AddVacation.UserDB;
import static ify.com.hotelsapp.LoginActivity.currentemail;
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "EmailPassword";

    private Button createAccountButton;
    private EditText userEmail,userPassword;
    private TextView alreadyHaveAccountLink;

    private FirebaseAuth mAuth;

   // private ProgressBar


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initializeFields();

    }

    private void initializeFields() {

        userEmail=(EditText)findViewById(R.id.register_email);
        userPassword=(EditText)findViewById(R.id.register_password);

        findViewById(R.id.already_have_an_account_link).setOnClickListener(this);
        findViewById(R.id.register_button).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }


    private void createNewAccount(final String email, String password) {

        Log.d(TAG, "createAccount:" + email);
        if (!validateForm())
            return;

       // show

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign up success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail: success");
                            Toast.makeText(RegisterActivity.this, "Account created successfully.",Toast.LENGTH_LONG).show();
                            UserDB.child(mAuth.getUid()).setValue(email);
                            sendUserToLoginActivity();



                           // sendUserToLoginActivity();
                        } else {
                            // If sign up fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            String message=task.getException().toString();
                            Toast.makeText(RegisterActivity.this, "Error: "+message,Toast.LENGTH_SHORT).show();

                        }

                    //    hideProgressDialog();
                    }
                });

    }


    private void sendUserToLoginActivity() {

        Intent loginIntent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(loginIntent);
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

        if (id==R.id.already_have_an_account_link)
            sendUserToLoginActivity();
        else if(id==R.id.register_button) {

            createNewAccount(userEmail.getText().toString(),userPassword.getText().toString());
        }

    }
}
