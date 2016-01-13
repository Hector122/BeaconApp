package com.example.hectorcastillo.beacon.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.hectorcastillo.beacon.R;

/**
 * Created by hector castillo on 12/1/16.
 */
public class MainActivity extends Activity implements OnClickListener {
    //Reference to login and Register button views.
    private Button mButtonLogin;
    private Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
    }

    /**
     * Initializes class level variables.
     */

    private void initializeVariables() {
        mButtonLogin = (Button) findViewById(R.id.login_button);
        mButtonLogin.setOnClickListener(this);

        mButtonRegister = (Button) findViewById(R.id.register_button);
        mButtonRegister.setOnClickListener(this);
    }

    /**
     * Handler the action to performer wen the user make
     * click int the layout buttons and call to the next activity.
     *
     * @param view
     */

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.login_button:
                intent = new Intent(this, LoginActivity.class);
                break;

            case R.id.register_button:
                intent = new Intent(this, RegisterActivity.class);
                break;

            default:
                break;
        }

        startActivity(intent);
    }
}
