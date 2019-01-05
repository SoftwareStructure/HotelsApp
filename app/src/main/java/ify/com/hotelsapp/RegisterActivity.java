package ify.com.hotelsapp;

import android.content.Intent;
import android.support.annotation.NonNull;
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

import models.User;


public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "SignUp";

    private Button createAccountButton;
    private EditText userEmail;
    private EditText userPassword;
    private TextView alreadyHaveAccountLink;

    private FirebaseAuth mAuth;

    private DatabaseReference myRef;

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

        myRef = FirebaseDatabase.getInstance().getReference("users");

    }


    private void createNewAccount() {
        Log.d(TAG, "signUp");
        if (!validateForm()) {
            return;
        }

        showProgressDialog();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUser:onComplete:" + task.isSuccessful());
                        hideProgressDialog();

                        if (task.isSuccessful()) {
                            onAuthSuccess(task.getResult().getUser());

                        } else {
                            Toast.makeText(RegisterActivity.this, "Sign Up Failed",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void onAuthSuccess(FirebaseUser user) {

            String username = usernameFromEmail(user.getEmail());

            // Write new user
           writeNewUser(user.getUid(), username, user.getEmail());

            // Go to Login Activity
          startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

          finish();
        }

    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        myRef.child(userId).setValue(user);
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
            startActivity(new Intent(this,LoginActivity.class));

        else if(id==R.id.register_button) {
            createNewAccount();
        }
    }
}
