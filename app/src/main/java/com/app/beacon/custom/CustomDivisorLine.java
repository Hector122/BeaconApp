package com.app.beacon.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.beacon.R;

/**
 * Created by hector castillo on 11/2/16.
 *
 * Custom class that make a divisor line betwee Manage grid layout.
 */
public class CustomDivisorLine extends RecyclerView.ItemDecoration {
    //Reference to the divisor line in drawable
    private Drawable mDivisorLine;

    /***
     * Constructor
     *
     * @param context activity context.
     */
    public CustomDivisorLine(Context context) {
        mDivisorLine = ContextCompat.getDrawable(context, R.drawable.divisor_line);

    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getRight();

        int childCount = parent.getChildCount();

        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivisorLine.getIntrinsicHeight();

            mDivisorLine.setBounds(left, top, right, bottom);
            mDivisorLine.draw(canvas);

        }
    }
}
