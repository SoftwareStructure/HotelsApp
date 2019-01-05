package ify.com.hotelsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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

public class LoginActivity extends BaseActivity implements View.OnClickListener  {

    private static final String TAG = "SignIn";

    private Button loginButton;
    private Button phoneLoginButton;

    private EditText userEmail;
    private EditText userPassword;

    private TextView needNewAccountLink;
    private TextView forgetPasswrdLink;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

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

        findViewById(R.id.need_new_account_link).setOnClickListener(this);
        findViewById(R.id.login_button).setOnClickListener(this);


        mAuth=FirebaseAuth.getInstance();

    }




    @Override
    protected void onStart(){

        super.onStart();

        if(currentUser!=null){
            startActivity(new Intent(this, MainActivity.class));
        }
    }


    private void sendUserToSignIn() {
        Log.d(TAG, "signIn");
        if (!validateForm()) {
            return;
        }

        showProgressDialog();


        String email=userEmail.getText().toString();
        String password=userPassword.getText().toString();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");

                            updateUI(task.getResult().getUser());

                            startActivity(new Intent(LoginActivity.this,Managers.class));

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());

                            Toast.makeText(LoginActivity.this, "Authentication failed. Try again",
                                    Toast.LENGTH_LONG).show();

                        }

                        hideProgressDialog();
                    }
                });
       
    }


    private void updateUI(FirebaseUser user) {

        dataHolder.setUserId(user.getUid());
        dataHolder.setCurrentUser(new User(user.getDisplayName(),user.getEmail()));
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
       startActivity(new Intent(this, RegisterActivity.class));

     else if(id==R.id.login_button)
         sendUserToSignIn();

       }
}

