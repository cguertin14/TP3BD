package com.example.guertz.tp3.Tools.DialogHelper;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.aigestudio.wheelpicker.WheelPicker;
import com.example.guertz.tp3.R;
import com.example.guertz.tp3.Tools.LogicalCode.Command;

import java.util.List;

/**
 * Created by guertz on 16/08/17.
 */

public class DialogHelper {

    private int selectedPosition;
    private String selectedItem;
    private List<String> data;

    public DialogHelper(List<String> data) {
        setData(data);
        setSelectedItem(data.get(0));
        setSelectedPosition(0);
    }

    public void buildWheelPicker(Context ctx, String dialogTitle,final Command toDoOnClosing) {
        WheelPicker picker = new WheelPicker(ctx);
        picker.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        picker.setSelectedItemTextColor(Color.BLACK);
        picker.setData(data);
        picker.setSelectedItemPosition(selectedPosition == -1 ? 0 : selectedPosition);
        picker.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                selectedPosition = position;
                selectedItem = picker.getData().get(position).toString();
            }
        });
        new AlertDialog.Builder(ctx)
                .setTitle(dialogTitle)
                .setView(picker)
                .setPositiveButton("Choisir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (selectedItem == null) {
                            selectedItem = data.get(0);
                            selectedPosition = 0;
                        }
                        toDoOnClosing.execute();
                    }
                })
                .setNegativeButton("Annuler",null)
                .show();
    }

    //region get-set

    public void setData(List<String> data) {
        this.data = data;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public List<String> getData() {
        return data;
    }

    public String getSelectedItem() {
        return selectedItem;
    }

    //endregion
}
