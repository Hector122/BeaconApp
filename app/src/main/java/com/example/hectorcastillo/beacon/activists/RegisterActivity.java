package com.example.hectorcastillo.beacon.activists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;
import android.widget.AutoCompleteTextView;

import com.example.hectorcastillo.beacon.R;

/**
 * Created by hector castillo on 12/1/16.
 */
public class RegisterActivity extends AppCompatActivity {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private EditText mPasswordConfirmationVIew;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        initializeVariables();
        setToolbar();
    }


    private void initializeVariables(){
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email_text_view);

        mPasswordView = (EditText) findViewById(R.id.password_edit_text);

        mPasswordConfirmationVIew = (EditText) findViewById(R.id.password_confirmation_edit_text);
    }

    private void setToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // Providing back navigation.
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
