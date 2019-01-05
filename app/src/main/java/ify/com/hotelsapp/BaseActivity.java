package ify.com.hotelsapp;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

import dataHolder.UserDataHolder;


public class BaseActivity extends AppCompatActivity {

        private ProgressDialog mProgressDialog;

        UserDataHolder dataHolder=UserDataHolder.getUserDataHolder();

        public void showProgressDialog() {
            if (mProgressDialog == null) {
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setCancelable(false);
                mProgressDialog.setMessage("Loading...");
            }

            mProgressDialog.show();
        }

        public void hideProgressDialog() {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        }

    }

