package com.app.beacon.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by hector castillo on 11/2/16.
 */
public class CustomItemDecorator extends RecyclerView.ItemDecoration {
    private final int mSpace;


    public CustomItemDecorator(int mSpace){
        this.mSpace = mSpace;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mSpace;
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.top = mSpace;


        //super.getItemOffsets(outRect, view, parent, state);
    }
}
