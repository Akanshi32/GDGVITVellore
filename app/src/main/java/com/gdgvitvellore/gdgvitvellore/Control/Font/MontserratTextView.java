package com.gdgvitvellore.gdgvitvellore.Control.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by tanmay on 20/1/17.
 */

public class MontserratTextView extends TextView {
    public MontserratTextView(Context context) {
        super(context);
        init();
    }

    public MontserratTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MontserratTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        if (!isInEditMode()) {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Montserrat-Regular.ttf");
            setTypeface(tf);
        }
    }
}
