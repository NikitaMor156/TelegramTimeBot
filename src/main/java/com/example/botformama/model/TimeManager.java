package com.example.botformama.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeManager {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("HH:mm");

    public static String getMoscowTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 2);

        return "Moscow time: " + FORMAT.format(calendar.getTime());
    }

    public static String getKievTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, 1);

        return "Kiev time: " + FORMAT.format(calendar.getTime());
    }

    public static String getKaizerslauternAndPragueTime() {
        return "Kaizerslautern/Prague time: " + FORMAT.format(new Date());
    }

    public static String getCanadaClientTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -8);

        return "Oksana's time in Canada: " + FORMAT.format(calendar.getTime());
    }

    public static String getNorthBeyTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -6);

        return "North Bey time: " + FORMAT.format(calendar.getTime());
    }

    public static String getEugeneTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR_OF_DAY, -9);

        return "Eugene time: " + FORMAT.format(calendar.getTime());
    }

}
