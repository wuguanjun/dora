package dora.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class TimeUtils {

    public final static String FORMAT_TYPE_DATE = "yyyy.MM.dd"; //yyyy.MM.dd
    public final static String FORMAT_TYPE_DATE_2 = "yyyy-MM-dd";   //yyyy-MM-dd
    public final static String FORMAT_TYPE_DATE_3 = "yyyyMMdd"; //yyyyMMdd
    public final static String FORMAT_TYPE_TIME = "HH:mm:ss";   //HH:mm:ss
    public final static String FORMAT_TYPE_TIME_2 = "HHmmss";   //HHmmss

    private TimeUtils() {
    }

    // <editor-folder desc="日期时间转换">

    public static String getTimeString(String formatType) {
        return getTimeString(getTimeLong(), formatType);
    }

    public static String getTimeString(Date data, String formatType) {
        return new SimpleDateFormat(formatType, Locale.getDefault()).format(data);
    }

    public static String getTimeString(long currentTime, String formatType) {
        Date date = getTimeDate(currentTime, formatType);
        return getTimeString(date, formatType);
    }

    public static Date getTimeDate(String formatType) {
        return getTimeDate(getTimeLong(), formatType);
    }

    public static Date getTimeDate(String strTime, String formatType) {
        Date date = null;
        try {
            date = new SimpleDateFormat(formatType, Locale.getDefault()).parse(strTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static Date getTimeDate(long currentTime, String formatType) {
        Date dateOld = new Date(currentTime);
        String sDateTime = getTimeString(dateOld, formatType);
        return getTimeDate(sDateTime, formatType);
    }

    public static long getTimeLong() {
        return System.currentTimeMillis();
    }

    public static long getTimeLong(String strTime, String formatType) {
        Date date = getTimeDate(strTime, formatType);
        if (date == null) {
            return 0;
        } else {
            return getTimeLong(date);
        }
    }

    public static long getTimeLong(Date date) {
        return date.getTime();
    }

    // </editor-folder>

    public static int getDaysOfMonth(int year, int month) {
        int result;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                result = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                result = 30;
                break;
            default:
                if (isLeapYear(year)) {
                    result = 29;
                } else {
                    result = 28;
                }
                break;
        }
        return result;
    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }
}
