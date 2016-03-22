package com.app.beacon.activists;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.app.beacon.R;
import com.app.beacon.asynctask.HelperAsync;
import com.app.beacon.asynctask.SimulateAsyncTask;
import com.app.beacon.helper.PreferenceManager;

/**
 * Created by hector castillo on 12/1/16.
 */
public class MainActivity extends Activity implements OnClickListener {
    public static final int MAIN_CLOSED_REQUEST_CODE = 1;

    //Reference to login and Register button views.
    private Button mButtonLogin;
    private Button mButtonRegister;
    private LinearLayout mMainActionContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeVariables();

        validateIsAutoLoginEnable();
    }


    private void validateIsAutoLoginEnable() {
        PreferenceManager preferenceManager = new PreferenceManager(this);
        boolean isLoggedIn = preferenceManager.isLoggeIn();

        if (isLoggedIn) {
            mMainActionContainer.setVisibility(View.INVISIBLE);
            String email = preferenceManager.getEmail();

            String title = getString(R.string.dummy_title_login);
            String message = getString(R.string.dummy_message_wait);

            HelperAsync helperAsync = new HelperAsync(title, message, this);
            new SimulateAsyncTask(helperAsync).execute(email);

        } else{
            mMainActionContainer.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode == MAIN_CLOSED_REQUEST_CODE)
                && (resultCode == RESULT_OK)) {

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

        mMainActionContainer = (LinearLayout) findViewById(R.id.login_and_register_container);
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
