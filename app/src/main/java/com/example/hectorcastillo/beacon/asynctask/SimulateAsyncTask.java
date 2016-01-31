package com.example.hectorcastillo.beacon.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.ThemedSpinnerAdapter;

import com.example.hectorcastillo.beacon.activists.DashBoardActivity;
import com.example.hectorcastillo.beacon.activists.ForgotPasswordActivity;
import com.example.hectorcastillo.beacon.activists.LoginActivity;

/**
 * Created by Hector_2 on 1/30/2016.
 */
public class SimulateAsyncTask extends AsyncTask<Void, Void, Boolean> {
    //Progress Dialog to show the message.
    ProgressDialog mProgressDialog;
    HelperAsync mHelper;

    public SimulateAsyncTask(HelperAsync helper) {
        this.mHelper = helper;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mProgressDialog = ProgressDialog.show(mHelper.getContext(),
                mHelper.getTitle(), mHelper.getMessage(), true);
    }


    @Override
    protected Boolean doInBackground(Void... params) {

        try {
            Thread.sleep(4000);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean success) {
        mProgressDialog.dismiss();

        if(mHelper.getContext().getClass() == ForgotPasswordActivity.class){
            //Finish the call activity.
            Activity activity = (Activity)mHelper.getContext();
            activity.finish();
        }else {
           // startDashBoardCategoryActivity();
        }
    }

    private void startDashBoardCategoryActivity() {
        Activity activity = (Activity) mHelper.getContext();
        Intent intent = new Intent(mHelper.getContext(), DashBoardActivity.class);
        // intent.putExtra(LoginActivity.EXTRA_EMAIL, mEmailView.getText().toString());
        intent.putExtra(LoginActivity.EXTRA_EMAIL,"Test@email.com");
        activity.setResult(Activity.RESULT_OK);

        activity.startActivity(intent);
        activity.finish();
    }
}
