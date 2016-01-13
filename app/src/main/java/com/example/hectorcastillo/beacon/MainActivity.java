package com.example.hectorcastillo.beacon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * Created by hector castillo on 12/1/16.
 */
public class MainActivity extends Activity implements OnClickListener {
    //Reference to login and Register button.
    private Button mButtonLogin;
    private Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
    }

    private void initializeVariables() {
              mButtonLogin = (Button) findViewById(R.id.login_button);
        mButtonLogin.setOnClickListener(this);

        mButtonRegister = (Button) findViewById(R.id.register_button);
        mButtonRegister.setOnClickListener(this);
    }

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
