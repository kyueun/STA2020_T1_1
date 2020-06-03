package testpackage;

import java.util.*;

public class ModeController {

    private Time curTime;
    private Time curTimer;
    private Time curStopwatch;
    private Alarm curAlarm;
    private Schedule curSchedule;

    private boolean runningTimer;
    private boolean runningStopwatch;
    private boolean[] runningAlarm;
    private Schedule recentSchedule;

    private boolean[] selectedModeNum;
    private Mode[] selectedMode;

    private Beep beep;

    public ModeController(Time time, Mode[] modes) {
        this.curTime = time;
        this.selectedMode = new Mode[6];
        this.selectedModeNum = new boolean[6];

        this.runningTimer = false;
        this.runningStopwatch = false;
        this.runningAlarm = new boolean[]{false, false, false, false};

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
            case Info.TIMEKEEPINGSET:
                if (selectedModeNum[mode / 10]) {
                    return ((TimeKeepingMode) selectedMode[mode / 10]).getValue();
                }
                return null;

            case Info.TIMER:
                if (this.selectedModeNum[mode / 10]) {
                    return ((TimerMode) (this.selectedMode[mode / 10])).getValue();
                }
                return null;

            case Info.STOPWATCH:
                if (this.selectedModeNum[mode / 10]) {
                    return ((StopwatchMode) (this.selectedMode[mode / 10])).getValue();
                }
                return null;

            case Info.ALARMSET:
                if (this.selectedModeNum[mode / 10]) {
                    return this.selectedMode[mode / 10].getValue(0);
                }
                return null;

            case Info.SCHEDULESET:
                if (this.selectedModeNum[mode / 10]) {
                    return ((StopwatchMode) (this.selectedMode[mode / 10])).getValue(0);
                }
                return null;

            default:
                return null;
        }
    }

    public void increaseTimeValue(int mode, int pointer){
        switch (mode) {
            case Info.TIMEKEEPING :
                this.curTime.valueUp(mode, pointer);
                break;

            case Info.TIMEKEEPINGSET :
                this.curTime.valueUp(mode, pointer);
                break;

            case Info.TIMER :
                this.curTimer.valueUp(mode, pointer);
                break;

            case Info.STOPWATCH :
                this.curStopwatch.valueUp(mode, pointer);
                break;

            case Info.ALARMSET :
                this.curAlarm.alarmTime.valueUp(mode, pointer);
                break;

            case Info.SCHEDULESET :
                this.curSchedule.scheduleTime.valueUp(mode, pointer);
                this.curSchedule.scheduleType = this.curSchedule.scheduleTime.second;
                break;
        }
    }

    public void decreaseTimeValue(int mode, int pointer){
        switch (mode) {
            case Info.TIMEKEEPINGSET :
                this.curTime.valueDown(pointer);
                break;

            case Info.TIMER :
                this.curTimer.valueDown(pointer);
                break;

            case Info.ALARMSET :
                this.curAlarm.alarmTime.valueDown(pointer);
                break;

            case Info.SCHEDULESET :
                this.curSchedule.scheduleTime.valueDown(pointer);
                break;
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

    public void saveTimeValue(int index, int mode){
        switch (mode){
            case Info.TIMEKEEPINGSET :
                ((TimeKeepingMode)(this.selectedMode[mode/10])).saveValue(curTime);
                break;

            case Info.TIMER :
                ((TimerMode)(this.selectedMode[mode/10])).saveValue(curTimer);
                break;

            case Info.STOPWATCH :
                ((StopwatchMode)(this.selectedMode[mode/10])).saveValue(curStopwatch);
                break;

            case Info.ALARMSET :
                ((AlarmMode)(this.selectedMode[mode/10])).saveValue(index, curAlarm);
                break;

            case Info.SCHEDULESET :
                ((ScheduleMode)(this.selectedMode[mode/10])).saveValue(index, curSchedule);
                break;


        }
    }



    public boolean isRunningTimer() {
        return runningTimer;
    }

    public boolean isRunningStopwatch() {
        return runningStopwatch;
    }


    public Time getCurTime() {
        return curTime;
    }
    public Time getCurTimer() {
        return curTimer;
    }

    public Time getCurStopwatch() {
        return curStopwatch;
    }

    public Alarm getCurAlarm() {
        return curAlarm;
    }

    public Schedule getCurSchedule() {
        return curSchedule;
    }

    public Schedule getRecentSchedule() {
        return recentSchedule;
    }

    public boolean[] getRunningAlarm() {
        return runningAlarm;
    }

    public Mode[] getSelectedMode() {
        return selectedMode;
    }

    public boolean[] getSelectedModeNum() {
        return selectedModeNum;
    }

    public Beep getBeep() {
        return beep;
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