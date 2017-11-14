package com.example.guertz.tp3.Tools.Fonts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

/**
 * Created by Charles on 2017-06-19.
 */

public class ModifyFonts {
    public static void ChangeFontTextViewsBold(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    ChangeFontTextViewsBold(context, child);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(Fonts.LatoBold(context));
            }
        } catch (Exception e) {
        }
    }

    public static void ChangeFontEditTextBold(final Context context, final View v) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    ChangeFontEditTextBold(context, child);
                }
            } else if (v instanceof TextView) {
                ((EditText) v).setTypeface(Fonts.LatoBold(context));
            }
        } catch (Exception e) {
        }
    }

    public static void SetRegularToTextViews(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.LatoRegular(context));
    }

    public static void SetRegularToCheckBox(final Context context, final CheckBox... checkBoxes) {
        for (CheckBox cb : checkBoxes)
            cb.setTypeface(Fonts.LatoRegular(context));
    }

    public static void SetRegularToEditText(final Context context, final EditText... textViews) {
        for (EditText tv : textViews)
            tv.setTypeface(Fonts.LatoRegular(context));
    }

    public static void SetRegularToTextView(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.LatoRegular(context));
    }

    public static void SetRegularToToggleButton(final Context context, final ToggleButton... toggleButtons) {
        for (ToggleButton tb : toggleButtons)
            tb.setTypeface(Fonts.LatoRegular(context));
    }

    public static void SetBoldToTextViews(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.LatoBold(context));
    }

    public static void SetBoldToEditText(final Context context, final EditText... editTexts) {
        for (EditText editText : editTexts)
            editText.setTypeface(Fonts.LatoBold(context));
    }

    public static void SetBoldToTextView(final Context context, final TextView... textViews) {
        for (TextView textView : textViews)
            textView.setTypeface(Fonts.LatoBold(context));
    }

    public static void SetBoldToButtons(final Context context, final Button... buttons) {
        for (Button btn : buttons)
            btn.setTypeface(Fonts.LatoBold(context));
    }

    public static void SetBlackToTextViews(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.LatoBlack(context));
    }

    public static void SetBlackToButtons(final Context context, final Button... buttons) {
        for (Button btn : buttons)
            btn.setTypeface(Fonts.LatoBlack(context));
    }

    public static void SetBlackToEditText(final Context context, final EditText... editTexts) {
        for (EditText editText : editTexts)
            editText.setTypeface(Fonts.LatoBlack(context));
    }

    public static void SetLightToTextViews(final Context context, final TextView... textViews) {
        for (TextView tv : textViews)
            tv.setTypeface(Fonts.LatoLight(context));
    }

    public static void SetLightToButtons(final Context context, final Button... buttons) {
        for (Button btn : buttons)
            btn.setTypeface(Fonts.LatoLight(context));
    }

    public static void SetLightToEditText(final Context context, final EditText... editTexts) {
        for (EditText editText : editTexts)
            editText.setTypeface(Fonts.LatoLight(context));
    }

    public static void SetMontserratBoldToTextViews(final Context context, final TextView... textViews) {
        for (TextView textView : textViews)
            textView.setTypeface(Fonts.MontserratBold(context));
    }
}