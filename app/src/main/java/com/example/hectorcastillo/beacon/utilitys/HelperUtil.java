package com.example.hectorcastillo.beacon.utilitys;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by hector castillo on 14/1/16.
 */
public class HelperUtil {

    /**
     * Toggle the soft keyboard to show or hide on screen.
     *
     * @param context
     *            Application context.
     * @param editText
     *            EditText to focus for soft keyboard.
     * @param show
     *            True shows the keyboard, False hides the keyboard.
     */
    public static void toggleSoftKeyboard(Activity context, View editText, boolean show) {
        InputMethodManager softInputManager = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);

        if (show) {
            editText.requestFocus();

            softInputManager.showSoftInput(editText, 0);
        } else {
            softInputManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }
}
