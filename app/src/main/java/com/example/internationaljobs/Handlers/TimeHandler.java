package com.example.internationaljobs.Handlers;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeHandler {
    public static long currentDateInMills()
    {
        Calendar calendar = Calendar.getInstance();
//        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
//                timePicker.getCurrentHour(), timePicker.getCurrentMinute(), 0);
        long startTime = calendar.getTimeInMillis();
//        long days=86400000*21;
//        startTime=startTime + days;

        Log.d("samak",""+startTime);

        return startTime;

    }
    public static int curntYear()
    {
        Calendar calendar=Calendar.getInstance();

        return calendar.get(Calendar.YEAR);
    }

    public static int curntMonth()
    {
        Calendar calendar=Calendar.getInstance();

        return calendar.get(Calendar.MONTH)+1;
    }

    public static int curntDay()
    {
        Calendar calendar=Calendar.getInstance();

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static String getCurentDateInString()
    {

        String m=""+curntMonth();
        if (curntMonth()<10)
            m="0"+curntMonth();
        String d=""+curntDay();
        if (curntDay()<10)
            d="0"+curntDay();

        return curntYear()+"-"+m+"-"+d;
    }

    public static String getDateFromMills(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());


//        Log.d("samak",getDate(startTime,"dd/MM/yyyy"));
    }

    public static long getMillesOfGivenDate(int day,int month,int year)
    {
        Calendar calendar=Calendar.getInstance();
        calendar.set(year,month,day);

        return calendar.getTimeInMillis();
    }


    public static long remaingTimeInMills(long eventTime)
    {
        Date cDate=new Date();

        Date eDate=new Date();
        eDate.setTime(eventTime);


        long diff = eDate.getTime() - cDate.getTime();
        Log.d("haider", String.valueOf(eDate));
        Log.d("haider", String.valueOf(cDate));
        Log.d("haider", String.valueOf(diff));
        long seconds =(diff/1000)%60;
        long minutes=(diff/(1000*60))%60;
        long hours=(diff/(1000*60*60))%24;
        long days=(diff/(1000*60*60*24))%365;

        String countDownText = String.format("%2d Days %2d Hr %2d Min %2d Sec", days, hours, minutes, seconds);
        Log.d("haider", countDownText);

        return diff;
    }

    public static long remaingDays(long eventTime)
    {
        Date cDate=new Date();

        Date eDate=new Date();
        eDate.setTime(eventTime);


        long diff = eDate.getTime() - cDate.getTime();
        Log.d("haider", String.valueOf(eDate));
        Log.d("haider", String.valueOf(cDate));
        Log.d("haider", String.valueOf(diff));
        long seconds =(diff/1000)%60;
        long minutes=(diff/(1000*60))%60;
        long hours=(diff/(1000*60*60))%24;
        long days=(diff/(1000*60*60*24))%365;

        String countDownText = String.format("%2d Days %2d Hr %2d Min %2d Sec", days, hours, minutes, seconds);
        Log.d("haider", countDownText);

        return days;
    }

    public static String remaingTimeInString(long eventTime)
    {
        Date cDate=new Date();

        Date eDate=new Date();
        eDate.setTime(eventTime);


        long diff = eDate.getTime() - cDate.getTime();
        Log.d("haider", String.valueOf(eDate));
        Log.d("haider", String.valueOf(cDate));
        Log.d("haider", String.valueOf(diff));
        long seconds =(diff/1000)%60;
        long minutes=(diff/(1000*60))%60;
        long hours=(diff/(1000*60*60))%24;
        long days=(diff/(1000*60*60*24))%365;

        String countDownText = String.format("%2d Days %2d Hr %2d Min %2d Sec", days, hours, minutes, seconds);
        Log.d("haider", countDownText);

        return countDownText;
    }




}
