package com.example.hectorcastillo.beacon.activists;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.hectorcastillo.beacon.R;
import com.example.hectorcastillo.beacon.utilitys.ActivityConstans;

/**
 * Created by hector castillo on 12/1/16.
 */
public class MainActivity extends Activity implements OnClickListener {
    public static final int MAIN_CLOSED_REQUEST_CODE = 1;

    //Reference to login and Register button views.
    private Button mButtonLogin;
    private Button mButtonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode == MAIN_CLOSED_REQUEST_CODE)
                && ( resultCode == RESULT_OK)){

            finish();
            //TODO: delete
            Log.i("MainActivty", "Close main activity");
        }
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

        startActivityForResult(intent, MAIN_CLOSED_REQUEST_CODE);
    }
}
