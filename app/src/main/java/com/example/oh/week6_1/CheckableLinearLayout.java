package com.example.oh.week6_1;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    public CheckableLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);

        if (checkBox.isChecked() != checked)
            checkBox.setChecked(checked);

    }

    @Override
    public boolean isChecked() {
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        return checkBox.isChecked();
    }


    @Override
    public void toggle() {
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        setChecked(checkBox.isChecked() ? false : true);

    }
}
