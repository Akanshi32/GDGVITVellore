package com.gdgvitvellore.gdgvitvellore.Control.Font;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by tanmay on 20/1/17.
 */

public class MontserratEditText extends EditText {
    public MontserratEditText(Context context) {
        super(context);
        init();
    }

    public MontserratEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MontserratEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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
