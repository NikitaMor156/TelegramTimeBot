package com.example.botformama.model;

import com.example.botformama.entity.City;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class TimeManager {

    private static final SimpleDateFormat FORMAT = generateFormat();

    private static SimpleDateFormat generateFormat(){
       SimpleDateFormat result = new SimpleDateFormat("HH:mm");
       result.setTimeZone(TimeZone.getTimeZone("UTC"));
       return result;
    }

    public static String getCityTime(City city){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.add(Calendar.HOUR_OF_DAY, city.getTimezone());

        return city.getName() + " time: " + FORMAT.format(calendar.getTime());
    }

}
