package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Schedule {
    public Time scheduleTime;
    public int scheduleType; // CLA: 0, MEE: 1, EVE: 2, ASL: 3, ETC: 4

    public Schedule() {
        scheduleTime = new Time();
        scheduleType = Info.SCH_TYPE_CLA;
    }

    public String getDayofWeek() {
        String dayOfWeek = "";
        String schedule = String.format("%04d", Integer.toString(this.scheduleTime.year)) + String.format("%02d", Integer.toString(this.scheduleTime.month)) + String.format("%02d", Integer.toString(this.scheduleTime.day));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = null;

        try {
            date = dateFormat.parse(schedule);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int dayNum = calendar.get(Calendar.DAY_OF_WEEK);

        switch (dayNum) {
            case 1:
                dayOfWeek = "SUN";
                break;
            case 2:
                dayOfWeek = "MON";
                break;
            case 3:
                dayOfWeek = "TUE";
                break;
            case 4:
                dayOfWeek = "WED";
                break;
            case 5:
                dayOfWeek = "THU";
                break;
            case 6:
                dayOfWeek = "FRI";
                break;
            case 7:
                dayOfWeek = "SAT";
                break;
        }

        return dayOfWeek;
    }
}