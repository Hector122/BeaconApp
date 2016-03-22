package com.app.beacon.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hector castillo on 11/2/16.
 */
public class CustomItemDecorator extends RecyclerView.ItemDecoration {
    private final int mSpace;

    private final boolean isCircle;

    public CustomItemDecorator(int mSpace) {
        this.mSpace = mSpace;
        isCircle = false;
    }

    public CustomItemDecorator(int mSpace, boolean isCircle) {
        this.mSpace = mSpace;
        this.isCircle = isCircle;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(isCircle){
            final int margin = mSpace - 15;
            outRect.bottom = margin;
            outRect.left = margin ;
            outRect.right = margin;
            outRect.top = margin;

        } else {
            outRect.bottom = mSpace;
            outRect.left = mSpace;
            outRect.right = mSpace;
            outRect.top = mSpace;
        }


        //super.getItemOffsets(outRect, view, parent, state);
    }
}
