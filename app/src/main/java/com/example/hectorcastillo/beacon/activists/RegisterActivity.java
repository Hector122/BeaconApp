package com.example.hectorcastillo.beacon.activists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.asynctask.HelperAsync;
import com.example.hectorcastillo.beacon.asynctask.SimulateAsyncTask;
import com.example.hectorcastillo.beacon.utilitys.Validations;

/**
 * Created by hector castillo on 12/1/16.
 */
public class RegisterActivity extends AppCompatActivity
        implements View.OnClickListener {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmationVIew;
    private Button mRegisterButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        initializeVariables();
        setToolbar();
    }


    private void initializeVariables() {
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_text_view);

        mPasswordView = (EditText) findViewById(R.id.password_edit_text);

        mPasswordConfirmationVIew = (EditText) findViewById(R.id.password_confirmation_edit_text);

        mRegisterButton = (Button) findViewById(R.id.email_sign_in_button);
        mRegisterButton.setOnClickListener(this);

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // Providing back navigation.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        attemptLogin();

    }

    private void startDashBoardCategoryActivity() {
        //  Intent intent = new Intent(this, CategoryActivity.class);
        // intent.putExtra(LoginActivity.EXTRA_EMAIL, mEmailView.getText().toString());
        //setResult(Activity.RESULT_OK);

        String email = mEmailView.getText().toString();

        String title = getString(R.string.dummy_title_register);
        String message = getString(R.string.dummy_message_wait);

        HelperAsync helperAsync = new HelperAsync(title, message, this);
        new SimulateAsyncTask(helperAsync).execute(email);

        //finish();
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);
        mPasswordConfirmationVIew.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String passwordConfirmation = mPasswordConfirmationVIew.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid email address.
        if (Validations.isNullOrEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;

        } else if (!Validations.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;

            // Check for a valid password, if the user entered one.
        } else if (Validations.isNullOrEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;

        } else if (!Validations.isPasswordLongValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        } else if (Validations.isNullOrEmpty(passwordConfirmation)) {
            mPasswordConfirmationVIew.setError(getString(R.string.error_field_required));
            focusView = mPasswordConfirmationVIew;
            cancel = true;

        } else if (!passwordConfirmation.equals(password)) {
            mPasswordConfirmationVIew.setError(getString(R.string.error_invalid_password_confirmation));
            focusView = mPasswordConfirmationVIew;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            startDashBoardCategoryActivity();

//            String title = getString(R.string.dummy_title_register);
//            String message = getString(R.string.dummy_message_wait);
//
//            //Initialise the Helper
//            HelperAsync helper = new HelperAsync(title, message, RegisterActivity.this);
//
//            //Begin the simulation task.
//            SimulateAsyncTask task = new SimulateAsyncTask(helper);
//            task.execute();
//
//            while (task.getStatus() != AsyncTask.Status.RUNNING) {
//                startDashBoardCategoryActivity();
//                break;
            // }

            //TODO: this comment this
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute();
        }
    }
}
