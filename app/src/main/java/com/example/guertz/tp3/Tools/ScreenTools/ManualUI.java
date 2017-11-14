package com.example.guertz.tp3.Tools.ScreenTools;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.guertz.tp3.Tools.ScreenTools.PageStarter;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by enterfaceteam on 2017-09-13.
 */

public class ManualUI {

    public static int DESIGN_WIDTH = 375;
    public static int DESIGN_HEIGHT = 667;


    private RelativeLayout overScrollview;
    private ScrollView scrollview;
    private RelativeLayout objectContainer;
    private AppCompatActivity appReference;
    private float deviceWidth,deviceHeight;
    private float designWidth,designHeight;
    private float lastElementY;

    public ManualUI(AppCompatActivity app) {
        appReference = app;
        Display display = appReference.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        deviceWidth = size.x;
        deviceHeight = size.y;
        scrollview = new ScrollView(app);
        lastElementY = 0;
        overScrollview = new RelativeLayout(app);
    }

    public void setDesignSize(float width,float height) {
        this.designHeight = height;
        this.designWidth = width;
    }

    public void setPosition(View object,float x,float y,float width,float height) {
        object.setLayoutParams(new LayoutParams((int)width, (int)height));
        object.setX(x);
        object.setY(y);
    }

    public float rw(float val) {
        return (val * deviceWidth) / designWidth;
    }

    public float rh(float val) {
        return (val * deviceHeight) / designHeight;
    }

    public void removeTopBar() {
        //Remove title bar
        appReference.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }
    public RelativeLayout getRelativeLayout() { return this.objectContainer; }
    public void setLastElementY(float f) {
        this.lastElementY = f;
    }

    public final AppCompatActivity getAppReference() {
        return appReference;
    }

    public void setFont(View view,String fontName) {
        if(view instanceof TextView) {
            ((TextView)view).setTypeface(Typeface.createFromAsset(appReference.getAssets(), "fonts/"+fontName+".ttf"));
        }
    }
    static public int DD_MXM_YYYY = 0;
    static public int MMM_DD_YYYY = 1;
    static public String prettyDate(String date,boolean capitalMonth,int mode) {
        ArrayList<String> month = new ArrayList<String>();
        month.add("javier");
        month.add("février");
        month.add("mars");
        month.add("avril");
        month.add("mai");
        month.add("juin");
        month.add("juillet");
        month.add("août");
        month.add("septembre");
        month.add("octobre");
        month.add("novembre");
        month.add("décembre");

        String monthIndex = date.substring(5,7);
        String months = month.get(Integer.parseInt(monthIndex) - 1);
        String day = date.substring(8,10);
        if(day.charAt(0) == '0') {
            day = day.substring(1,2);
        }
        String year = date.substring(0,4);
        date = date.substring(0,10);
        if(capitalMonth) {
            months = months.substring(0,1).toUpperCase() + months.substring(1);
        }
        if(mode == DD_MXM_YYYY)
            return day +" " + months + " " + year;
        else
        {
            String monthAb = "";
            if(months.length() <= 4)
                monthAb = months;
            else
                monthAb = months.substring(0,3) + ".";

            return monthAb + " " + day + ", " + year;
        }
    }

    public int argb(int a,int r,int g,int b) {
       return ((a & 0xFF) << 24) + ((r & 0xFF) << 16) + ((g & 0xFF) << 8) + (b & 0xFF);
    }

    public void setTabBar(String titleText, Drawable imgLeft, Drawable imgRight, final Runnable actionRight) {

        View tabBar = new View(this.appReference);
        tabBar.setBackgroundColor(0xff161616);
        this.setPosition(tabBar,0,0,this.rw(375),this.rh(50));
        this.addOutsideOfScrollView(tabBar);

        ImageView backArrow = new ImageView(appReference);
        this.setPosition(backArrow,this.rw(20),this.rh((50 - 17.5f) / 2),this.rw(10.5f),this.rh(17.5f));
        backArrow.setImageDrawable(imgLeft);
        this.addOutsideOfScrollView(backArrow);
        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PageStarter.finish(appReference);
            }
        });

        TextView title = new TextView(appReference);
        this.setPosition(title,0,0,this.rw(375),this.rh(50));
        title.setGravity(Gravity.CENTER);
        title.setText(titleText);
        title.setTextSize(TypedValue.COMPLEX_UNIT_PX,this.rw(17.5f));
        title.setTextColor(Color.WHITE);
        this.addOutsideOfScrollView(title);

        if(imgRight != null) {

            ImageView rightImage = new ImageView(appReference);
            if(actionRight != null) {
                rightImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        actionRight.run();
                    }
                });
            }

            rightImage.setImageDrawable(imgRight);
            this.setPosition(rightImage,this.rw(336),this.rh((50 - 23.5f) / 2),this.rw(23.5f),this.rw(23.5f));
            this.addOutsideOfScrollView(rightImage);
        }


    }

    public float getTabHeight(){
        return this.rh(50);
    }

    public void addView(View object) {
        objectContainer.addView(object);
        lastElementY = object.getLayoutParams().height + object.getY();
    }

    public void addFrom(View object,View container) {
        object.setX(object.getX() + container.getX());
        object.setY(object.getY() + container.getY());
        this.addView(object);
    }

    public void addOutsideOfScrollView(View view) {
        overScrollview.addView(view);
    }

    public float getLastElementY() {
        return this.lastElementY;
    }

    public int getStatusBarHeight() {
        int result = 0;
        int resourceId = appReference.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = appReference.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    public void setScrollviewHeight(float height) {
        scrollview.getLayoutParams().height = (int)height;
        objectContainer.getLayoutParams().height = (int)height;
    }

    public TextView createHR(float x,float y,float width,float height,int color) {
        TextView hr = new TextView(appReference);
        hr.setBackgroundColor(color);
        this.setPosition(hr,x,y,width,height);
        this.addView(hr);
        return hr;
    }

    public void sizeToFitMultiLine(TextView view,int width) {

        sizeToFit(view);
        int totalSize = view.getLayoutParams().width;
        int height = view.getLayoutParams().height;
        int preHeight = height;
        int nbLine = (totalSize / (width)) + 1;
        height = (preHeight * nbLine);
        height -= this.rh(0.5f) * nbLine;
        view.getLayoutParams().width = width;
        view.getLayoutParams().height = height;
    }

    public void sizeToFit(TextView view) {

        float width = view.getPaint().measureText(view.getText().toString());
        Paint.FontMetrics metrics = view.getPaint().getFontMetrics();
        float height = metrics.bottom - metrics.top;

        if(view.getLayoutParams() != null) {
            view.getLayoutParams().width = (int)width;
            view.getLayoutParams().height = (int)height;
        } else {
            view.setLayoutParams(new LayoutParams((int)width,(int)height));
        }
    }

    public void sizeToFitWidth(TextView view) {
        float width = view.getPaint().measureText(view.getText().toString());
        Paint.FontMetrics metrics = view.getPaint().getFontMetrics();
        if(view.getLayoutParams() != null) {
            view.getLayoutParams().width = (int)width;

        } else {
            view.setLayoutParams(new LayoutParams((int)width,0));
        }
    }

    public void setBackgroundColor(int color) {
        scrollview.setBackgroundColor(color);
    }

    public float maxY(View view) {
        return view.getY() + view.getLayoutParams().height;
    }

    public float maxX(View view) {
        return view.getX() + view.getLayoutParams().width;
    }

    public void setManualView(int pageWidth,int pageHeight) {
        LayoutParams LLParams = new LayoutParams((int)deviceWidth,(int)deviceHeight);
        overScrollview.setLayoutParams(LLParams);
        appReference.setContentView(overScrollview);

        LayoutParams scrollLLParams = new LayoutParams(pageWidth,pageHeight);
        scrollview.setLayoutParams(scrollLLParams);
        overScrollview.addView(scrollview);
        objectContainer = new RelativeLayout(appReference);

        objectContainer.setLayoutParams(scrollLLParams);


        scrollview.addView(objectContainer);
    }
}
