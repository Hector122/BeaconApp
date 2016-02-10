package com.app.beacon.activists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.app.beacon.asynctask.HelperAsync;
import com.app.beacon.R;
import com.app.beacon.asynctask.SimulateAsyncTask;
import com.app.beacon.utilitys.Validations;

/**
 * Created by hector castillo on 15/1/16.
 */
public class ForgotPasswordActivity extends AppCompatActivity
        implements View.OnClickListener {
    //Reference to email View
    AutoCompleteTextView mEmailView;

    //Reference to continue button view
    Button mContinueButton;

    //Reference to the email editText in the login
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
        mEmailView.setError(null);
        final String email = mEmailView.getText().toString();

        Boolean cancel = false;

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            cancel = true;

        } else if (!Validations.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            cancel = true;
        }

        if (cancel) {
            mEmailView.requestFocus();
        } else {
            starTaskSimulation();
        }
    }

    private void starTaskSimulation() {
        String title = getString(R.string.dummy_title_email);
        String message = getString(R.string.dummy_message_wait);

        //Initialise the Helper
        HelperAsync helper = new HelperAsync(title, message, ForgotPasswordActivity.this);

        //Begin the simulation task.
        new SimulateAsyncTask(helper).execute();

       // finish();
    }

//    private void showSuccessTask(){
//        ProgressDialog dialog = new ProgressDialog();
//
//    }
}
