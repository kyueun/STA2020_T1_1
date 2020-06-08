package model;

import java.util.*;


public class ModeController {

    private Time curTime;
    private Time curTimer;
    private Time curStopwatch;
    private Alarm curAlarm;
    private Schedule curSchedule;

    private boolean runningTimer;
    private boolean runningStopwatch;
    private ArrayList<Alarm> runningAlarmList;
    private Schedule recentSchedule;

    private boolean[] selectedModeNum;
    private Mode[] selectedMode;


    public ModeController(Time time, Mode[] modes) {
        this.curTime = time;
        this.curTimer = new Time();
        this.curStopwatch = new Time();
        this.curAlarm = new Alarm();
        this.curSchedule = new Schedule();

        this.selectedMode = new Mode[6];
        this.selectedModeNum = new boolean[6];

        this.runningTimer = false;
        this.runningStopwatch = false;
        this.runningAlarmList = new ArrayList<Alarm>();
        this.recentSchedule = null;

        for(int i=0; i<4; i++){
            this.selectedModeNum[i] = true;
        }

        this.selectedMode = modes;

        for(int i=4; i<6; i++){
            this.selectedModeNum[i] = false;
        }
    }

    public Time loadTime(int mode, int listPointer){
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
                    return ((AlarmMode)this.selectedMode[mode / 10]).getValue(listPointer).alarmTime;
                }
                return null;

            case Info.SCHEDULESET:
                if (this.selectedModeNum[mode / 10]) {
                    return ((ScheduleMode)this.selectedMode[mode / 10]).getValue(listPointer).scheduleTime;
                }
                return null;

            default:
                return null;
        }
    }

    public boolean deleteTime(int mode, int index){
        switch(mode){
            case Info.SCHEDULE:
                return ((ScheduleMode)this.selectedMode[mode / 10]).deleteValue(index);

            case Info.ALARM:
                Alarm tempAlarm = ((AlarmMode)this.selectedMode[mode / 10]).getValue(index);
                runningAlarmList.remove(tempAlarm);
                return ((AlarmMode)this.selectedMode[mode / 10]).deleteValue(index);

            default:
                return false;
        }
    }

    public void increaseTimeValue(int mode, int pointer, Time time){
        switch (mode) {
            case Info.TIMEKEEPING :
                time.valueUp(mode, pointer);
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
                this.curTime.valueDown(mode, pointer);
                break;

            case Info.TIMER :
                this.curTimer.valueDown(mode, pointer);
                break;

            case Info.ALARMSET :
                this.curAlarm.alarmTime.valueDown(mode, pointer);
                break;

            case Info.SCHEDULESET :
                this.curSchedule.scheduleTime.valueDown(mode, pointer);
                this.curSchedule.scheduleType = this.curSchedule.scheduleTime.second;
                break;
        }
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
                Alarm alarm = ((AlarmMode)(this.selectedMode[mode/10])).saveValue(index, curAlarm.alarmTime);
                if(index==-1){ //add alarm
                    runningAlarmList.add(alarm);
                }
                curAlarm = new Alarm();
                break;

            case Info.SCHEDULESET :
                ((ScheduleMode)(this.selectedMode[mode/10])).saveValue(index, curSchedule.scheduleTime);
                curSchedule = new Schedule();
                break;
        }
    }

    public void toggleAlarm(int index){
        Alarm temp = ((AlarmMode)(this.selectedMode[Info.ALARM /10])).getValue(index);

        if(((AlarmMode)(this.selectedMode[Info.ALARM /10])).toggleAlarm(index)){
            if(temp.enable){
                runningAlarmList.add(temp);
            }else{
                runningAlarmList.remove(temp);
            }
        }

    }

    public boolean isAvailable(){
        return ((ScheduleMode)(this.selectedMode[Info.SCHEDULE/10])).isAvailAdd(curTime, curSchedule);
    }

    public boolean canSelect() {
        int count=0;
        for(int i=0; i<6; i++){
            if(selectedModeNum[i]){
                count++;
            }
        }
        if(count==4) return true;
        else return false;
    }

    public void refreshMode(){
        for(int i=0; i<6; i++){
            if(!selectedModeNum[i]){
                selectedMode[i] = new Mode();
            }
        }
    }

    public void calculateSchedule(Time time){
        if(((ScheduleMode)selectedMode[Info.SCHEDULE / 10]).getList().size()==0) {
            recentSchedule = null;
        }
        else if(((ScheduleMode)selectedMode[Info.SCHEDULE / 10]).getList().size()==1) {
            recentSchedule = ((ScheduleMode)selectedMode[Info.SCHEDULE / 10]).getList().get(0);
        }
        else if(((ScheduleMode)selectedMode[Info.SCHEDULE / 10]).getList().size()>1) {
            boolean fast = false; //true= if current time faster than recent schedule
            if(time.month>recentSchedule.scheduleTime.month) fast = true;
            else if(time.month>recentSchedule.scheduleTime.month) fast = false;
            else {
                if(time.day>recentSchedule.scheduleTime.day) fast = true;
                else if(time.day>recentSchedule.scheduleTime.day) fast = false;
                else {
                    if(time.hour>recentSchedule.scheduleTime.hour) fast = true;
                    else if(time.hour>recentSchedule.scheduleTime.hour) fast = false;
                    else {
                        if(time.minute>=recentSchedule.scheduleTime.minute) fast = true;
                        else if(time.minute>recentSchedule.scheduleTime.minute) fast = false;
                    }
                }
            }

            if(fast) {
                recentSchedule = ((ScheduleMode)selectedMode[Info.SCHEDULE / 10]).getList().get(0);
                this.calculateSchedule(time);
            }

            else recentSchedule = ((ScheduleMode)selectedMode[Info.SCHEDULE / 10]).getList().get(0);
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

    public ArrayList<Alarm> getRunningAlarmList() {
        return runningAlarmList;
    }

    public Mode[] getSelectedMode() {
        return selectedMode;
    }

    public boolean[] getSelectedModeNum() {
        return selectedModeNum;
    }

    public void setCurTime(Time curTime) {
        this.curTime = curTime;
    }

    public void setCurTimer(Time curTimer) {
        this.curTimer = curTimer;
    }

    public void setCurStopwatch(Time curStopwatch) {
        this.curStopwatch = curStopwatch;
    }

    public void setCurAlarm(Alarm curAlarm) {
        this.curAlarm = curAlarm;
    }

    public void setCurSchedule(Schedule curSchedule) {
        this.curSchedule = curSchedule;
    }

    public void setRunningTimer(boolean runningTimer) {
        this.runningTimer = runningTimer;
    }

    public void setRunningStopwatch(boolean runningStopwatch) {
        this.runningStopwatch = runningStopwatch;
    }

    public void setSelectedModeNum(boolean[] selectedModeNum) {
        this.selectedModeNum = selectedModeNum;
    }

    public void setSelectedMode(Mode[] selectedMode) {
        this.selectedMode = selectedMode;
    }
}