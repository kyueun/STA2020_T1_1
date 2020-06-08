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
        scheduleTime.month = 1;
        scheduleTime.day = 1;
        scheduleType = Info.SCH_TYPE_CLA;
    }
}