package testpackage;

import java.util.*;

public class ModeController {
//    final int TIME_TYPE_CURRENT_TIME = 0;
//    final int TIME_TYPE_TIMER = 1;
//    final int TIME_TYPE_STOPWATCH = 2;

    final int TIME_POINTER_YEAR = 0;
    final int TIME_POINTER_MONTH = 1;
    final int TIME_POINTER_DAY = 2;
    final int TIME_POINTER_HOUR = 3;
    final int TIME_POINTER_MINUTE = 4;
    final int TIME_POINTER_SECOND = 5;

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
        switch (mode){
            case Info.TIMEKEEPING :
                if(selectedModeNum[mode/10]){
                    return ((TimeKeepingMode)selectedMode[mode/10]).getValue();
                }

                return null;

            case Info.TIMER:
                return null;

            case Info.STOPWATCH :
                return null;
        }
        return null;
    }

    public void increaseTimeValue(int time_type, int pointer){
        switch (time_type){
            case TIME_TYPE_CURRENT_TIME :
                break;

            case TIME_TYPE_TIMER :
                break;

            case TIME_TYPE_STOPWATCH :
                break;
        }

    }

    public void decreaseTimeValue(int time_type, int pointer){
        switch (time_type){
            case TIME_TYPE_CURRENT_TIME :
                break;

            case TIME_TYPE_TIMER :
                break;

            case TIME_TYPE_STOPWATCH :
                break;
        }
    }

    public void moveTimerPointer(int time_type){
        switch (time_type){
            case TIME_TYPE_CURRENT_TIME :
                break;

            case TIME_TYPE_TIMER :
                break;

            case TIME_TYPE_STOPWATCH :
                break;
        }
    }

    public Time saveTimeValue(Time time, int index, int time_type){
        switch (time_type){
            case TIME_TYPE_CURRENT_TIME :
                return null;

            case TIME_TYPE_TIMER :
                return null;

            case TIME_TYPE_STOPWATCH :
                return null;
        }
        return null;
    }

    public void upData(int type, int num) {
        // TODO implement here
    }

    /**
     * @param int index 
     * @return
     */
    public Object getValue(int index) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @param Object object 
     * @return
     */
    public Object saveValue(int index, Object object) {
        // TODO implement here
        return null;
    }

}