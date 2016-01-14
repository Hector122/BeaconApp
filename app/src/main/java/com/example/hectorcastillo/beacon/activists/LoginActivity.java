package com.example.hectorcastillo.beacon.activists;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.utilitys.HelperUtil;
import com.example.hectorcastillo.beacon.utilitys.Validations;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 *
 * @author hector castillo
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {
    // Id to identity READ_CONTACTS permission request.
    // private static final int REQUEST_READ_CONTACTS = 0;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private Button mEmailSignInButton;
    private TextView mForgotPassswordView;

    // Async Task for login.
    private UserLoginTask mAuthTask = null;

    // A dummy authentication store containing known user names and passwords.
    // TODO: remove after connecting to a real authentication system.

    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world", "test@algo.com:1234"
    };

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Set up the login form.
        mForgotPassswordView = (TextView) findViewById(R.id.text_forgot_password);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_text_view);

        mProgressView = findViewById(R.id.login_progress);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(this);

        mPasswordView = (EditText) findViewById(R.id.password);

        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_ACTION_GO) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.login_button:
                //TODO: validate login credentials and empty fields
                break;

            case R.id.email_sign_in_button:
                attemptLogin();

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
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;

        } else if (!Validations.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;

            // Check for a valid password, if the user entered one.
        } else if (TextUtils.isEmpty(password)) {
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
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute();
        }
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

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // show the progress bar
            mProgressView.setVisibility(View.VISIBLE);

            setInvisbleForm(mEmailSignInButton, mForgotPassswordView,
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
            mProgressView.setVisibility(View.GONE);

            if (success) {
                //TODO: login successful
                finish();

            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();

                setVisibilityForm(mEmailSignInButton, mForgotPassswordView,
                        mPasswordView, mEmailView);
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }
}

