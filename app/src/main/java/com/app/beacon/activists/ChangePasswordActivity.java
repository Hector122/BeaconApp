package com.app.beacon.activists;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.beacon.R;

/**
 * Created by hector castillo on 14/3/16.
 */
public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mCurrentPasswordView, mPasswordView, mConfirmationPasswordView;
    Button mSedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initializeVariables();

        setToolbar();
    }

    /**
     * Ini
     */

    private void initializeVariables() {
        mCurrentPasswordView =  (EditText) findViewById(R.id.current_password_view);

        mPasswordView = (EditText) findViewById(R.id.new_password_view);

        mConfirmationPasswordView  = (EditText) findViewById(R.id.new_password_confirmation_view);
        mConfirmationPasswordView.setOnClickListener(this);

        mSedButton = (Button) findViewById(R.id.change_password_button);
        mSedButton.setOnClickListener(this);

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
    public void onClick(View v) {
        //Do nothing :P
        Toast.makeText(this, "Change password", Toast.LENGTH_SHORT).show();
    }
}
