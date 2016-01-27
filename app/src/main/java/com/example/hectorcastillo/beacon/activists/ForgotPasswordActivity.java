package com.example.hectorcastillo.beacon.activists;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.hectorcastillo.beacon.R;

/**
 * Created by hector castillo on 15/1/16.
 */
public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    AutoCompleteTextView mEmailView;
    Button mContinueButton;

    String mEmailAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        initializeVariables();
        setToolbar();
        setEmailText();
    }

    private void initializeVariables() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_text_view);
        mEmailAddress = getIntent().getStringExtra(LoginActivity.EXTRA_EMAIL);

        mContinueButton = (Button) findViewById(R.id.continue_button);
        mContinueButton.setOnClickListener(this);

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // Providing back navigation.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setEmailText() {
        if (null != mEmailAddress) {
            mEmailView.setText(mEmailAddress);
        }
    }


    @Override
    public void onClick(View v) {
            new SendEmailCode().execute();
    }


    private class SendEmailCode extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog mProgressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            mProgressDialog = ProgressDialog.show(ForgotPasswordActivity.this, "Title title", "loading messages", true);
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            try {
                Thread.sleep(4000);
                return true;
            } catch (InterruptedException e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            mProgressDialog.dismiss();

        }
    }
}
