package testpackage;

import java.util.*;

public class ModeController {

    private Schedule curSchedule;
    private Alarm curAlarm;
    private Time curTimer;
    private Time curTime;
    private Time curStopwatch;
    private Time runningTimer;
    private Alarm[] runningAlarm;
    private Time runningStopwatch;
    private Schedule recentSchedule;
    private int curMode;
    private boolean[] selectedModeNum;
    private Mode[] selectedMode;
    private Beep beep;

    public ModeController(Time time, Mode[] modes) {
        this.curMode = Info.TIMEKEEPING;
        this.curTime = time;
        this.selectedMode = new Mode[6];
        this.selectedModeNum = new boolean[6];

        for(int i=0; i<4; i++){
            this.selectedMode[i] = modes[i];
            this.selectedModeNum[i] = true;
        }

        for(int i=4; i<6; i++){
            this.selectedMode[i] = null;
            this.selectedModeNum[i] = false;
        }

        this.beep = new Beep();
    }

    public Time loadTime(int mode){
        switch (mode) {
            case Info.TIMEKEEPING:
                if (selectedModeNum[mode / 10]) {
                    return ((TimeKeepingMode) selectedMode[mode / 10]).getValue();
                }

                return null;

            case Info.TIMER:
                if (this.selectedModeNum[mode / 10] == true) {
                    return ((TimerMode) (this.selectedMode[mode / 10])).getValue(0);
                }
                return null;

            case Info.STOPWATCH:
                if (this.selectedModeNum[mode / 10] == true) {
                    return ((StopwatchMode) (this.selectedMode[mode / 10])).getValue(0);
                }
                return null;

            default:
                return null;
        }
    }

    public void increaseTimeValue(int time_type, int pointer){
        switch (time_type) {
            case Info.TIMEKEEPING :
                switch (pointer) {
                    case Info.TIME_POINTER_YEAR :
                        this.curTime.year++;

                        // ~ Use Case 6. A3-1
                        if (this.curTime.year > 2099) this.curTime.year = 1900;

                        // ~ Use Case 6. A3-3 (leaf year)
                        GregorianCalendar cldYear = new GregorianCalendar(this.curTime.year, this.curTime.month - 1, 1);
                        if (this.curTime.day < cldYear.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                            this.curTime.day = 1;
                        }
                        break;
                    case Info.TIME_POINTER_MONTH:
                        this.curTime.month++;

                        // ~ Use Case 6. A3-1
                        if (this.curTime.month > 12) this.curTime.month = 1;   // January => 1, ... , December => 12

                        // ~ Use Case 6. A3-3
                        GregorianCalendar cldMonth = new GregorianCalendar(this.curTime.year, this.curTime.month - 1, 1);
                        if (this.curTime.day < cldMonth.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                            this.curTime.day = 1;
                        }
                        break;
                    case Info.TIME_POINTER_DAY:
                        this.curTime.day++;

                        // ~ Use Case 6. A3-1
                        GregorianCalendar cldDay = new GregorianCalendar(this.curTime.year, this.curTime.month - 1, 1);
                        if (this.curTime.day < cldDay.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                            this.curTime.day = 1;
                        }
                        break;
                    case Info.TIME_POINTER_HOUR:
                        this.curTime.hour = (this.curTime.hour + 1) % 24;
                        break;
                    case Info.TIME_POINTER_MINUTE:
                        this.curTime.minute = (this.curTime.minute + 1) % 60;
                        break;
                    case Info.TIME_POINTER_SECOND:
                        this.curTime.second = (this.curTime.second + 1) % 60;
                        break;
                }
                break;

            case Info.TIMER :
                switch (pointer) {
                    case Info.TIME_POINTER_HOUR:
                        this.curTimer.hour = (this.curTimer.hour + 1) % 24;
                        break;
                    case Info.TIME_POINTER_MINUTE:
                        this.curTimer.minute = (this.curTimer.minute + 1) % 60;
                        break;
                    case Info.TIME_POINTER_SECOND:
                        this.curTimer.second = (this.curTimer.second + 1) % 60;
                        break;
                }
                break;

            case Info.STOPWATCH :
                this.curStopwatch.second++;

                if (this.curStopwatch.second > 59) {
                    this.curStopwatch.second = 0;
                    this.curStopwatch.minute++;

                    if (this.curStopwatch.minute > 59) {
                        this.curStopwatch.minute = 0;
                        this.curStopwatch.hour++;

                        if (this.curStopwatch.hour > 23) {
                            this.curStopwatch.hour = 0;
                        }
                    }
                }
                break;
        }
    }

    public void decreaseTimeValue(int time_type, int pointer){
        switch (time_type) {
            case Info.TIMEKEEPING :
                switch (pointer) {
                    case Info.TIME_POINTER_YEAR:
                        this.curTime.year--;

                        // ~ Use Case 6. A3-2
                        if (this.curTime.year < 1900) this.curTime.year = 2099;

                        // ~ Use Case 6. A3-3 (leaf year)
                        GregorianCalendar cldYear = new GregorianCalendar(this.curTime.year, this.curTime.month - 1, 1);
                        if (this.curTime.day < cldYear.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                            this.curTime.day = 1;
                        }
                        break;
                    case Info.TIME_POINTER_MONTH:
                        this.curTime.month--;

                        // ~ Use Case 6. A3-2
                        if (this.curTime.month < 1) this.curTime.month = 12;   // January => 1, ... , December => 12

                        // ~ Use Case 6. A3-3
                        GregorianCalendar cldMonth = new GregorianCalendar(this.curTime.year, this.curTime.month - 1, 1);
                        if (this.curTime.day < cldMonth.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                            this.curTime.day = 1;
                        }
                        break;
                    case Info.TIME_POINTER_DAY:
                        this.curTime.day--;

                        // ~ Use Case 6. A3-2
                        if (this.curTime.day < 1) {
                            GregorianCalendar cldDay = new GregorianCalendar(this.curTime.year, this.curTime.month - 1, 1);
                            this.curTime.day = cldDay.getActualMaximum(Calendar.DAY_OF_MONTH);
                        }
                        break;
                    case Info.TIME_POINTER_HOUR:
                        this.curTime.hour--;
                        if(this.curTime.hour < 0) this.curTime.hour = 23;
                        break;
                    case Info.TIME_POINTER_MINUTE:
                        this.curTime.minute--;
                        if(this.curTime.minute < 0) this.curTime.minute = 59;
                        break;
                    case Info.TIME_POINTER_SECOND:
                        this.curTime.second--;
                        if(this.curTime.second < 0) this.curTime.second = 59;
                        break;
                }
                break;

            case Info.TIMER :
                this.curTimer.second--;

                if (this.curTimer.second < 0) {
                    this.curTimer.second = 59;
                    this.curTimer.minute--;

                    if (this.curTimer.minute < 0) {
                        this.curTimer.minute = 59;
                        this.curTimer.hour--;
                    }
                }
                break;

                // Stopwatch doesn't use decreaseTimeValue()
        }
    }

    public void moveTimerPointer(int time_type){
        //can't reach parent's time pointer.

        //int curPointer = ((DWS)super).getPointer();
        //curPointer++;
        //if(time_type == 0){
        //    curPointer %= 6;
        //}
        //else{
        //    if(curPointer > 5) curPointer = 3;
        //}
        //
        //((DWS)super).setPointer(curPointer);
    }

    public Time saveTimeValue(Time time, int index, int time_type){
        switch (time_type){
            case Info.TIMEKEEPING :
                if(this.selectedModeNum[0] == true){
                    if(time == null)
                        return ((TimeKeepingMode)(this.selectedMode[0])).saveValue(curTime);
                    else
                        return ((TimeKeepingMode)(this.selectedMode[0])).saveValue(time);
                }
                else
                    return null;
            case Info.TIMER :
                if(this.selectedModeNum[1] == true){
                    if(time == null)
                        return ((TimerMode)(this.selectedMode[1])).saveValue(0, curTimer);
                    else
                        return ((TimerMode)(this.selectedMode[1])).saveValue(0, time);
                }
                else
                    return null;

            case Info.STOPWATCH :
                if(this.selectedModeNum[2] == true){
                    if(time == null)
                        return ((StopwatchMode)(this.selectedMode[2])).saveValue(0, curStopwatch);
                    else
                        return ((StopwatchMode)(this.selectedMode[2])).saveValue(0, time);
                }
                else
                    return null;
        }
        return null;
    }

//    public void upData(int type, int num) {
//        // TODO implement here
//    }
//
//    public Object getValue(int index) {
//        // TODO implement here
//        return null;
//    }
//
//    public Object saveValue(int index, Object object) {
//        // TODO implement here
//        return null;
//    }
}