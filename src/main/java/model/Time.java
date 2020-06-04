package model;

public class Time {
    public Time() {
        year = 0;
        month = 0;
        day = 0;
        hour = 0;
        minute = 0;
        second = 0;
    }

    public int year;
    public int month;
    public int day;
    public int hour;
    public int minute;
    public int second;

    public void valueUp(int mode, int pointer) {

        if (pointer == Info.TIME_POINTER_NULL) {
            switch (mode) {
                case Info.TIMEKEEPING: //current time tic
                    this.second++;
                    if (second == 60) {
                        second = 0;
                        minute++;
                    }
                    if (minute == 60) {
                        minute = 0;
                        hour++;
                    }
                    if (hour == 24) {
                        hour = 0;
                        day++;
                    }
                    if (day > Info.DAY_OF_MONTH[month]) {
                        day = 1;
                        month++;
                    }
                    if (month == 13) {
                        month = 1;
                        year++;
                    }
                    if (year == 2100) {
                        year = 1900;
                    }
                    break;

                case Info.STOPWATCH: //start stopwatch
                    this.second++;
                    if (second == 60) {
                        second = 0;
                        minute++;
                    }
                    if (minute == 60) {
                        minute = 0;
                        hour++;
                    }
                    if (hour == 24) {
                        hour = 0;
                    }
                    break;
            }
        } else { //all setting
            switch (pointer) {
                case Info.TIME_POINTER_YEAR:
                    year++;
                    if (year > 2099) year = 1900;
                    break;

                case Info.TIME_POINTER_MONTH:
                    month++;
                    if (month > 12) month = 1;
                    break;

                case Info.TIME_POINTER_DAY:
                    day++;
                    if (day > Info.DAY_OF_MONTH[month]) day = 1;
                    break;

                case Info.TIME_POINTER_HOUR:
                    hour++;
                    if (hour > 23) hour = 0;
                    break;

                case Info.TIME_POINTER_MINUTE:
                    minute++;
                    if (minute > 59) minute = 0;
                    break;

                case Info.TIME_POINTER_SECOND:
                    second++;
                    if (second > 59) second = 0;
                    break;
            }
        }
    }

    public void valueDown(int pointer) {

        if (pointer == Info.TIME_POINTER_NULL) {//start timer
            this.second--;
            if (second < 0) {
                second = 59;
                minute--;
            }
            if (minute < 0) {
                minute = 59;
                hour--;
            }
            if (hour < 0) {
                hour = 0;
                minute = 0;
                second = 0;
            }
        } else { //all setting
            switch (pointer) {
                case Info.TIME_POINTER_YEAR:
                    year--;
                    if (year < 1900) year = 2099;
                    break;

                case Info.TIME_POINTER_MONTH:
                    month--;
                    if (month < 1) month = 12;
                    break;

                case Info.TIME_POINTER_DAY:
                    day--;
                    if (day < 1) day = Info.DAY_OF_MONTH[month];
                    break;

                case Info.TIME_POINTER_HOUR:
                    hour--;
                    if (hour < 0) hour = 23;
                    break;

                case Info.TIME_POINTER_MINUTE:
                    minute--;
                    if (minute < 0) minute = 59;
                    break;

                case Info.TIME_POINTER_SECOND:
                    second--;
                    if (second < 0) second = 59;
                    break;
            }
        }
    }
}