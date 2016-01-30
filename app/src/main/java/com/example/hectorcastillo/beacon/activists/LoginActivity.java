package com.example.hectorcastillo.beacon.activists;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.helper.HelperUtil;
import com.example.hectorcastillo.beacon.utilitys.Validations;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 *
 * @author hector castillo
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {
    public static final String EXTRA_EMAIL = "com.example.beacon.EMAIL";
    public static final String STATE_EMAIL = "UserEmailInput";

    //TODO:Request code for LOGIN.

    // Id to identity READ_CONTACTS permission request.
    // private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private Button mEmailSignInButton;
    private TextView mForgotPasswordView;

    // Async Task for login.
    private UserLoginTask mAuthTask = null;

    // A dummy authentication store containing known user names and passwords.
    // TODO: remove after connecting to a real authentication system.

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world", "test@m.com:1234", "11:11"
    };

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        initializeVariables();

        //Restore the state.
        if (savedInstanceState != null) {
            String email = savedInstanceState.getString(STATE_EMAIL);
            mEmailView.setText(email);
        }

    }

    private void initializeVariables() {
        // Set up the login form.
        mForgotPasswordView = (TextView) findViewById(R.id.text_forgot_password);
        mForgotPasswordView.setOnClickListener(this);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_text_view);

        mProgressView = findViewById(R.id.login_progress);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(this);

        mPasswordView = (EditText) findViewById(R.id.password);

        mPasswordView.setOnClickListener(this);

        //Providing back navigation.
        setToolbar();

    }

    /**
     * Set the Android Toolbar
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // Providing back navigation.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        //save the user email text.
        savedInstanceState.putString(STATE_EMAIL, mEmailView.getText().toString());

        // Always Call the superclass so it CAN Save the View Hierarchy State
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.password:
            case R.id.email_sign_in_button:
              // startDashBoardCategoryActivity();
                attemptLogin();
                break;

            case R.id.text_forgot_password:
                startForgotEmailActivity();
                break;

            default:
                break;
        }
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

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

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
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            startDashBoardCategoryActivity();

            //TODO: this comment this
            //mAuthTask = new UserLoginTask(email, password);
            //mAuthTask.execute();
        }
    }

    /**
     *
     */
    private void startForgotEmailActivity() {
        Intent intent = new Intent(this, ForgotPasswordActivity.class);
        intent.putExtra(EXTRA_EMAIL, mEmailView.getText().toString());

        startActivity(intent);
    }

    private void startDashBoardCategoryActivity() {
        Intent intent = new Intent(this, DashBoardActivity.class);
        intent.putExtra(EXTRA_EMAIL, mEmailView.getText().toString());
        setResult(RESULT_OK);

        startActivity(intent);

        finish();
    }


    /**
     * Dummy validations credentials.
     *
     * @param email
     * @param password
     */

    private boolean iniUserLoginTask(String email, String password) {
        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        // showProgress(true);

        for (String credential : DUMMY_CREDENTIALS) {
            String[] pieces = credential.split(":");
            if (pieces[0].equals(email)) {
                // Account exists, return true if the password matches.
                return pieces[1].equals(password);
            }
        }
        return false;
    }


    //
    private void setInvisbleForm(View... views) {
        for (View view : views) {
            view.setVisibility(View.GONE);
        }

        HelperUtil.toggleSoftKeyboard(LoginActivity.this, mPasswordView, false);
    }

    private void setVisibilityForm(View... views) {
        for (View view : views) {
            view.setVisibility(View.VISIBLE);
        }
    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }


    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    private class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        //TODO: remove when tere was a real connection
        private final String mEmail;
        private final String mPassword;
        ProgressDialog mProgressDialog;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // show the progress bar
           // mProgressView.setVisibility(View.VISIBLE);

            mProgressDialog = ProgressDialog.show(LoginActivity.this,
                    getString(R.string.dummy_title_login),
                    getString(R.string.dummy_message_wait),
                    true);

            setInvisbleForm(mEmailSignInButton, mForgotPasswordView,
                    mPasswordView, mEmailView);

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                return false;
            }

            // TODO: register the new account here.
            return iniUserLoginTask(mEmail, mPassword);
        }

        @Override
        protected void onPostExecute(final Boolean success) {
          //  mProgressView.setVisibility(View.GONE);
            mProgressDialog.dismiss();

            startDashBoardCategoryActivity();

//            if (success) {
//                //TODO: login successful
//                finish();
//
//            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
//
//                setVisibilityForm(mEmailSignInButton, mForgotPasswordView,
//                        mPasswordView, mEmailView);
//            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}

