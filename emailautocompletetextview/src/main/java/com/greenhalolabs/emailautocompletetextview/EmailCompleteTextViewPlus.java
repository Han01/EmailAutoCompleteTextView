
package com.greenhalolabs.emailautocompletetextview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.greenhalolabs.emailautocompletetextview.util.AccountUtil;

import java.util.List;

/**
 * <p>
 * An editable text view that provides auto completion for the user's e-mail. The list of
 * suggestions is displayed in a drop down menu from which the user can choose an e-mail
 * to replace the content of the edit box with.
 * </p>
 * <p>
 * An "X" button will also be present to the right of the text view if edit box is not
 * empty so that the view can easily be cleared.
 * </p>
 */
public class EmailCompleteTextViewPlus extends AutoCompleteTextView implements
                                                                   View.OnTouchListener,
                                                                   View.OnFocusChangeListener {

    private static final String TAG = EmailCompleteTextViewPlus.class.getName();

    /* Private State */

    private Drawable mTappableDrawable;
    private OnTouchListener mOnTouchListener;
    private OnFocusChangeListener mOnFocusChangeListener;
    private OnClearClicked mOnClearClickListener;

    /* Constructors */

    public EmailCompleteTextViewPlus(Context context) {
        this(context, null);
    }

    public EmailCompleteTextViewPlus(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmailCompleteTextViewPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /* Public Methods */

    @Override public void setOnTouchListener(OnTouchListener l) {
        mOnTouchListener = l;
    }

    @Override public void setOnFocusChangeListener(OnFocusChangeListener l) {
        mOnFocusChangeListener = l;
    }

    /**
     * Sets a listener for when the clear button is clicked.
     * 
     * @param listener the listener.
     */
    public void setOnClearClickListener(OnClearClicked listener) {
        mOnClearClickListener = listener;
    }

    /**
     * Sets the visibility of the clear button.
     * 
     * @param visible true if the clear button should be visible, otherwise, false.
     */
    public void setClearVisible(boolean visible) {
        final Drawable d = (visible ? mTappableDrawable : null);
        final Drawable[] drawables = getCompoundDrawables();
        if (drawables != null) {
            setCompoundDrawables(drawables[0], drawables[1], d, drawables[3]);
        } else {
            Log.w(TAG, "No button is available. Attach one by setting android:drawableRight.");
        }
    }

    /**
     * @return true if the button is visible, otherwise, false.
     */
    public boolean isButtonVisible() {
        final Drawable[] drawables = getCompoundDrawables();
        return (drawables != null && drawables[2] != null);
    }

    /* Private Methods */

    private void init(Context context) {

        // Set clear button
        mTappableDrawable = getCompoundDrawables()[2]; // Right drawable
        if (mTappableDrawable != null) {
            mTappableDrawable.setBounds(0,
                                        0,
                                        mTappableDrawable.getIntrinsicWidth(),
                                        mTappableDrawable.getIntrinsicHeight());
            addTextChangedListener(new DefaultTextChangedListener(this));
            setOnClearClickListener(new DefaultOnButtonClickListener(this));
            super.setOnFocusChangeListener(this);
            super.setOnTouchListener(this);
        }

        // Get e-mails
        final List<String> emailsList = AccountUtil.getAccountEmails(context);
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,
                                                                      android.R.layout.simple_dropdown_item_1line,
                                                                      emailsList);
        setAdapter(adapter);
        setClearVisible(false);
    }

    /* Implemented Methods */

    @Override public void onFocusChange(View v, boolean hasFocus) {

        final EmailCompleteTextViewPlus editText = (EmailCompleteTextViewPlus) v;
        editText.setClearVisible((hasFocus && !TextUtils.isEmpty(editText.getText().toString())));

        if (mOnFocusChangeListener != null) {
            mOnFocusChangeListener.onFocusChange(v, hasFocus);
        }
    }

    @Override public boolean onTouch(View view, MotionEvent event) {

        if (isButtonVisible()) {
            final boolean tappedButton = event.getX() > (getWidth() - getPaddingRight() - mTappableDrawable.getIntrinsicWidth());
            if (tappedButton) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (mOnClearClickListener != null) {
                        mOnClearClickListener.onClick();
                    }
                }
                return true;
            }
        }

        // Propagate
        return (mOnTouchListener != null && mOnTouchListener.onTouch(view, event));
    }

    /* Inner Classes */

    public static final class DefaultOnButtonClickListener implements OnClearClicked {

        private final EmailCompleteTextViewPlus editTextPlus;

        public DefaultOnButtonClickListener(EmailCompleteTextViewPlus editTextPlus) {
            this.editTextPlus = editTextPlus;
        }

        @Override public void onClick() {
            editTextPlus.setText("");
        }
    }

    public static final class DefaultTextChangedListener implements TextWatcher {

        private final EmailCompleteTextViewPlus editTextPlus;

        public DefaultTextChangedListener(EmailCompleteTextViewPlus editTextPlus) {
            this.editTextPlus = editTextPlus;
        }

        @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
            editTextPlus.setClearVisible(!TextUtils.isEmpty(editTextPlus.getText().toString()));
        }

        @Override public void afterTextChanged(Editable s) {
        }
    }
}
