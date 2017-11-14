package com.example.guertz.tp3.Tools.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

import com.example.guertz.tp3.Tools.PageInterfaces.EditTextImeBackListener;

/**
 * Created by guertz on 11/08/17.
 */

public class EditTextBackEvent extends EditText {

    private EditTextImeBackListener mOnImeBack;

    public EditTextBackEvent(Context context) {
        super(context);
    }

    public EditTextBackEvent(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextBackEvent(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK &&
                event.getAction() == KeyEvent.ACTION_UP) {
            eventDispatcher();
        }
        return super.dispatchKeyEvent(event);
    }

    public void eventDispatcher() {
        if (mOnImeBack != null)
            mOnImeBack.onImeBack(this, this.getText().toString());
    }

    public void setOnEditTextImeBackListener(EditTextImeBackListener listener) {
        mOnImeBack = listener;
    }

}
