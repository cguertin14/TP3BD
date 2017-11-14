package com.example.guertz.tp3.Tools.Time;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Charles on 2017-04-18.
 */

public class Time
{
    public static String now()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }

    public static String nowWithSeconds()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static Map<String,Integer> getTimeParams()
    {
        Map<String,Integer> time = new HashMap<>();
        String timeString = now();
        time.put("hour",Integer.parseInt(timeString.substring(0,timeString.indexOf(':'))));
        time.put("minute",Integer.parseInt(timeString.substring(timeString.indexOf(':')+1,timeString.length())));
        return time;
    }

    @Nullable
    public static String getShortDate(String longDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = formatter.parse(longDate);
            return formatter.format(date);
        }
        catch (Exception e)
        {
            Log.e("Erreur parse: ",e.getMessage());
            return null;
        }
    }

    public static String getDateAndTime()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static String getDate()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static String getYear()
    {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(cal.getTime());
    }

    @Nullable
    public static Map<String,String> getDateFromShortDate(String shortDate)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Map<String,String> time = new HashMap<>();
        try
        {
            Date date = formatter.parse(shortDate);
            String newDate = formatter.format(date);
            String month = getMonthForInt(Integer.parseInt(newDate.substring(newDate.indexOf("-") + 1,newDate.length()-3)));
            time.put("year",newDate.substring(0,4));
            time.put("month",month.substring(0,1).toUpperCase() + month.substring(1,month.length()));
            time.put("day",newDate.substring(newDate.lastIndexOf("-") + 1));
            return time;
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Nullable
    public static String getFullDate(String dateString)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date date = formatter.parse(dateString);
            String newDate = formatter.format(date);
            String month = getMonthForInt(Integer.parseInt(newDate.substring(newDate.indexOf("-") + 1,newDate.length()-3)));
            String day = newDate.substring(newDate.lastIndexOf("-") + 1);
            String year = newDate.substring(0,4);
            String s1 = month.substring(0, 1).toUpperCase();
            return s1 + month.substring(1) + ", " + day + ", " + year;
        }
        catch (ParseException e)
        {
            Log.e("Parse error NOW: ", e.getMessage());
            return null;
        }
    }

    @Nullable
    public static String FormatDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            return formatter.format(formatter.parse(date));
        }
        catch (ParseException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @NonNull
    public static Boolean compareDates(String date1, String date2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try
        {
            Date dateToCompare1 = formatter.parse(date1);
            Date dateToCompare2 = formatter.parse(date2);
            return dateToCompare1.before(dateToCompare2);
        }
        catch (ParseException e)
        {
            Log.e("Erreur parse: ",e.getMessage());
            return false;
        }
    }

    public static int getDayOfWeekFromDate(String date)
    {
        try
        {
            Calendar c = Calendar.getInstance();
            Date datetime = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            c.setTime(datetime);
            int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
            return dayOfWeek - 1;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getTimeFromInts(int hour,int minute)
    {
        return String.valueOf(hour + ":" + minute);
    }

    private static String getMonthForInt(int monthNum)
    {
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols(Locale.ENGLISH);
        String [] months = dfs.getMonths();
        if (monthNum >= 1 && monthNum <= 12 )
            month = months[monthNum-1];
        return month;
    }

    public static List<String> getDaysOfWeek()
    {
        List<String> days = new ArrayList<>(Arrays.asList(new DateFormatSymbols(Locale.CANADA_FRENCH).getWeekdays()));
        days.removeAll(Arrays.asList(""));
        for (int i=0;i<days.size();++i)
            days.set(i,days.get(i).substring(0,1).toUpperCase() + days.get(i).substring(1,days.get(i).length()));
        return days;
    }
}