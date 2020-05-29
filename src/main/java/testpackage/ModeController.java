package testpackage;

import java.util.*;

public class ModeController {
    final int TIME_TYPE_CURRENT_TIME = 0;
    final int TIME_TYPE_TIMER = 1;
    final int TIME_TYPE_STOPWATCH = 2;

    final int TIME_POINTER_YEAR = 0;
    final int TIME_POINTER_MONTH = 1;
    final int TIME_POINTER_DAY = 2;
    final int TIME_POINTER_HOUR = 3;
    final int TIME_POINTER_MINUTE = 4;
    final int TIME_POINTER_SECOND = 5;

    private Time curTimer;
    private Time curStopwatch;
    private Time runningTimer;
    private Time runningStopwatch;

    /**
     * Default constructor
     */
    public ModeController() {
    }

    public Time loadTime(int time_type){
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