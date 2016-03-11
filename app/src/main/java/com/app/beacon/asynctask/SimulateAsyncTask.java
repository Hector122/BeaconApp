package com.app.beacon.asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;

import com.app.beacon.activists.ForgotPasswordActivity;
import com.app.beacon.activists.DashboardContainerActivity;
import com.app.beacon.activists.LoginActivity;

/**
 * Created by Hector_2 on 1/30/2016.
 */
public class SimulateAsyncTask extends AsyncTask<String, Void, Boolean> {
    //Progress Dialog to show the message.
    ProgressDialog mProgressDialog;
    HelperAsync mHelper;

    String mEmail;

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
    protected Boolean doInBackground(String... emails) {
        mEmail = emails[0];

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
            startDashBoardCategoryActivity();
        }
    }

    private void startDashBoardCategoryActivity() {
        Activity activity = (Activity) mHelper.getContext();
        Intent intent = new Intent(mHelper.getContext(), DashboardContainerActivity.class);
        intent.putExtra(LoginActivity.EXTRA_EMAIL, mEmail);
        activity.setResult(Activity.RESULT_OK);

        //start new activity.
        activity.finish();
        activity.startActivity(intent);

    }
}
