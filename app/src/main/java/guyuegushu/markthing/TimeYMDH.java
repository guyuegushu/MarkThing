package guyuegushu.markthing;

import java.util.Calendar;

/**
 * Created by Administrator on 2016/12/16.
 */

public class TimeYMDH {

    private int year = 0;
    private int month = 0;
    private int day = 0;
    private int hour = 0;

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public static boolean isAorP() {

        boolean am = false;
        boolean pm = true;

        TimeYMDH timeYMDH = new TimeYMDH();

        if (timeYMDH.getH() > 17) {
            return pm;
        } else {
            return am;
        }
    }

    public TimeYMDH() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = 1 + c.get(Calendar.MONTH);
        hour = c.get(Calendar.HOUR_OF_DAY);
        day = c.get(Calendar.DAY_OF_MONTH);

    }

    public int getH(){
        return hour;
    }

    public String getYMD() {
        return String.valueOf(year) + "年" + String.valueOf(month) + "月" + String.valueOf(day) + "日";
    }

    public String getYM() {
        return String.valueOf(year) + "年" + String.valueOf(month) + "月";
    }

    public String getMD() {
        return String.valueOf(month) + "月" + String.valueOf(day)  + "日";
    }

    public String getD() {
        return String.valueOf(day)  + "日";
    }

}
