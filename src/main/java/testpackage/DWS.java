package testpackage;

import view.AlarmListPanel;
import view.GUI;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 *
 *
 **/
 public class DWS{
    private GUI gui;
    private int listPointer;
    private int pointer;
    private int mode;
    private int object_type;
    private int time_type;
    private Beep beep;

    private ModeController controller;
    private Time time;
    Mode modes[];

    int timeStart, timeEnd;

    private int input;

    public DWS () {
        //gui = new GUI();
        time = new Time();
        modes = new Mode[6];

        initTime(time);
        initMode(modes, time);

        pointer = Info.TIME_POINTER_NULL;
        listPointer = Info.LIST_POINTER_0;

        ((TimeKeepingMode) modes[0]).saveValue(time);
        controller = new ModeController(time, modes);

        beep = new Beep();

        this.mode = Info.TIMEKEEPING;

        //  gui.setListener("A", pressButtonA());

    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public void start() {
        LocalTime timeStart, timeEnd; //실제 1초 체
        boolean flag; //input 유
        int input; //input in gui

        timeStart = LocalTime.now();
        Object[] screenValue = new Object[]{controller.getRecentSchedule(), time}; //display 할 값

        while(true) {
            input = -1;
            flag = false;

            controller.increaseTimeValue(Info.TIMEKEEPING, Info.TIME_POINTER_NULL);
            if(controller.isRunningTimer()) controller.decreaseTimeValue(Info.TIMER, Info.TIME_POINTER_NULL);
            if(controller.isRunningStopwatch()) controller.increaseTimeValue(Info.STOPWATCH, Info.TIME_POINTER_NULL);

            if((controller.getCurTimer()!=null) && controller.getCurTimer().equals(new Time())){ //timer beep
                beep.beepPopup(Info.BEEP_TYPE_TIMER);
            }

            Iterator<Alarm> itr = controller.getRunningAlarmList().iterator();
            while(itr.hasNext()){ //alarm beep
                if(itr.equals(controller.getCurTime())){
                    beep.beepPopup(Info.BEEP_TYPE_ALARM);
                }
            }
           // System.out.println("TIme: "+time.year+"년"+time.month+"월"+time.day+"일"+time.hour+"시"+time.minute+"분"+time.second+"초");

            do {
                timeEnd = LocalTime.now();

                if(!flag) {
                    input = this.gui.getInput();
                    if(input!=-1) flag = true;
                }

            }while(Duration.between(timeStart, timeEnd).getSeconds()<1);

            if(input==-1) { //nothing in
                switch(mode) {
                    case Info.TIMEKEEPING: //show current time
                        screenValue = new Object[]{controller.getRecentSchedule(), time};
                        break;

                    case Info.TIMER: //show timer time
                        screenValue = new Object[]{time, controller.getCurTimer()};
                        break;

                    case Info.STOPWATCH: //show timer time
                        screenValue = new Object[]{time, controller.getCurStopwatch()};
                        break;

                    case Info.ALARM:
                        screenValue = new Object[]{time, ((AlarmMode)controller.getSelectedMode()[Info.ALARM/10]).getList()};
                        break;

                    case Info.ALARMSET:
                        screenValue = new Object[]{time, controller.getCurAlarm()};
                        break;

                    case Info.SCHEDULE:
                        screenValue = new Object[]{time, ((ScheduleMode)controller.getSelectedMode()[Info.SCHEDULE/10]).getList()};
                        break;

                    case Info.SCHEDULESET:
                        screenValue = new Object[]{time, controller.getCurSchedule()};
                        break;
                }
            }
            else { // button input exist
                if(!beep.beepList.empty()){
                    beep.muteTopBeep();
                }else{
                    switch(input) {
                        case Info.A:
                            screenValue = pressButtonA();
                            break;
                        case Info.B:
                            screenValue = pressButtonB();
                            break;
                        case Info.C:
                            screenValue = pressButtonC();
                            break;
                        case Info.D:
                            screenValue = pressButtonD();
                            break;
                        case Info.LONGA:
                            screenValue = pressLongButtonA();
                            break;
                        case Info.LONGB:
                            screenValue = pressLongButtonB();
                            break;
                        case Info.LONGC:
                            screenValue = pressLongButtonC();
                            break;
                        case Info.LONGD:
                            screenValue = pressLongButtonD();
                            break;
                        default:
                            screenValue = null;
                            break;
                    }
                }
            }
            this.gui.display(mode, screenValue, beep.beepList); // (Required) modify object
            this.gui.setInput(-1);
            timeStart = timeEnd;
        }
    }

    public Object[] pressButtonA() {
            switch (mode) {
                case Info.TIMEKEEPINGSET: //increase
                    increaseValue();
                    return new Object[]{controller.getCurTime(), pointer};

                case Info.TIMER:
                    increaseValue();
                    return new Object[]{time, controller.getCurTimer(), pointer};

                case Info.ALARM:
                    moveListPointer(1);
                    return new Object[]{controller.getCurTime(), ((AlarmMode)controller.getSelectedMode()[Info.ALARM/10]).getList(), listPointer};
                case Info.ALARMSET:
                    increaseValue();
                    return new Object[]{controller.getCurTime(), controller.getCurAlarm(), pointer};

                case Info.SCHEDULE:
                    moveListPointer(1);
                    return new Object[]{controller.getCurTime(), ((ScheduleMode)controller.getSelectedMode()[Info.SCHEDULE/10]).getList(), listPointer};

                    case Info.SCHEDULESET:
                    increaseValue();
                    return new Object[]{controller.getCurTime(), controller.getCurSchedule(), pointer};

                case Info.SELECTMODE:
                    moveListPointer(1);
                    return new Object[]{controller.getSelectedModeNum(), listPointer};

                default:
                    break;
            }
            return null;
        }
    /**
     * @return
     */
    public Object[] pressButtonB() {
        switch(mode){
            case Info.TIMEKEEPING: //enterSetting
                mode = Info.TIMEKEEPINGSET;
                pointer = Info.TIME_POINTER_HOUR;
                return new Object[]{enterSettingMode(), pointer};

            case Info.TIMEKEEPINGSET: //exitSetting
                time = (Time) saveValue();
                mode = Info.TIMEKEEPING;
                return new Object[]{controller.getRecentSchedule(), time};

            case Info.TIMER:
                if(controller.isRunningTimer()){
                    controller.setRunningTimer(false);
                }else{
                    controller.setRunningTimer(true);
                }
               // controller.decreaseTimeValue(mode, Info.TIME_POINTER_NULL);
                return new Object[]{time, controller.getCurTimer()};

            case Info.STOPWATCH:
                if(controller.isRunningStopwatch()){
                    controller.setRunningStopwatch(false);
                }else{
                    controller.setRunningStopwatch(true);
                }
                return new Object[]{time, controller.getCurStopwatch()};

            case Info.ALARM:

                break;
            case Info.ALARMSET:

                break;
            case Info.SCHEDULE: //enter setting - modify schedule
                mode = Info.SCHEDULESET;
                pointer = Info.TIME_POINTER_HOUR;
                return new Object[]{enterSettingMode(), pointer};

            case Info.SCHEDULESET:
                if(controller.isAvailable()){
                    Schedule temp = (Schedule) saveValue();
                    mode = Info.SCHEDULE;
                    return new Object[]{controller.getCurTime(), ((ScheduleMode)controller.getSelectedMode()[Info.SCHEDULE/10]).getList()};
                }
                return null;

            case Info.SELECTMODE:
                boolean[] temp = controller.getSelectedModeNum();
                temp[listPointer] = !temp[listPointer];
                controller.setSelectedModeNum(temp);
                return new Object[]{controller.getSelectedModeNum(), listPointer};

            default:
                break;
        }
        return null;
    }

    /**
     * @return
     */
    public Object[] pressButtonC() {
        switch(mode){
            case Info.TIMEKEEPINGSET: //decrease
               decreaseValue();
                return new Object[]{controller.getCurTime(), pointer};

            case Info.TIMER:
                movePointer();
                return new Object[]{time, controller.getCurTimer(), pointer};

            case Info.ALARM:
                moveListPointer(0);
                return new Object[]{controller.getCurTime(), ((AlarmMode)controller.getSelectedMode()[Info.ALARM/10]).getList(), listPointer};

            case Info.ALARMSET:
                decreaseValue();
                return new Object[]{controller.getCurTime(), controller.getCurAlarm(), pointer};

            case Info.SCHEDULE:
                moveListPointer(0);
                return new Object[]{controller.getCurTime(), ((ScheduleMode)controller.getSelectedMode()[Info.SCHEDULE/10]).getList(), listPointer};

            case Info.SCHEDULESET:
                decreaseValue();
                return new Object[]{controller.getCurTime(), controller.getCurSchedule(), pointer};

            case Info.SELECTMODE:
                moveListPointer(0);
                return new Object[]{controller.getSelectedModeNum(), listPointer};

            default:
                return null;
        }
    }


    public Object[] pressButtonD() {
        switch (mode) {

            case Info.TIMEKEEPINGSET: //move pointer
                movePointer();
                return new Object[]{controller.getCurTime(), pointer};

            case Info.ALARMSET: //move pointer
                movePointer();
                return new Object[]{controller.getCurTime(), controller.getCurAlarm(), pointer};

            case Info.SCHEDULESET: //move pointer
                movePointer();
                return new Object[]{controller.getCurTime(), controller.getCurSchedule(), pointer};

            case Info.SELECTMODE:
                if(controller.canSelect()){
                    mode = Info.TIMEKEEPING;
                    return new Object[]{controller.getRecentSchedule(), controller.getCurTime()};
                }
                else return new Object[]{controller.getSelectedMode()};

            default: //change mode
                changeMode();

                switch(mode) {
                    case Info.TIMEKEEPING: //show current time
                        return new Object[]{controller.getRecentSchedule(), time};

                    case Info.TIMER: //show timer time
                        return new Object[]{time, controller.getCurTimer()};

                    case Info.STOPWATCH: //show timer time
                        return new Object[]{time, controller.getCurStopwatch()};

                    case Info.ALARM:
                        return new Object[]{time, ((AlarmMode)controller.getSelectedMode()[Info.ALARM/10]).getList()};

                    case Info.SCHEDULE:
                        return new Object[]{time, ((ScheduleMode)controller.getSelectedMode()[Info.SCHEDULE/10]).getList()};

                    case Info.WORLDTIME:
                        return new Object[]{((WorldTimeMode)controller.getSelectedMode()[Info.WORLDTIME/10]).getValue()};
                }
                return null;
        }
    }

    public Object[] pressLongButtonA() {
        switch (mode) {
            case Info.ALARM:
                if (((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).toggleAlarm(listPointer)) {
                    return new Object[]{controller.getCurTime(), ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};
                }
                return null;

            default:
                return null;
        }
    }

    public Object[] pressLongButtonB() {
        switch(mode){
            case Info.TIMER: //reset timer
                if(!controller.isRunningTimer()){
                    ((TimerMode) controller.getSelectedMode()[Info.TIMER/10]).saveValue(new Time());
                }
                return new Object[]{time, controller.getCurTimer()};

            case Info.STOPWATCH:
                if(!controller.isRunningTimer()){
                    ((StopwatchMode) controller.getSelectedMode()[Info.STOPWATCH/10]).saveValue(new Time());
                }
                return new Object[]{time, controller.getCurStopwatch()};

            case Info.ALARM: //enter setting - add alarm
                if(((AlarmMode)controller.getSelectedMode()[Info.ALARM/10]).isFull()){
                    return null;
                }else{
                    mode = Info.ALARMSET;
                    pointer = Info.TIME_POINTER_HOUR;
                    listPointer = -1;
                    return new Object[]{enterSettingMode(), pointer};
                }

            case Info.SCHEDULE: //enter setting - add schedule
                if(((ScheduleMode)controller.getSelectedMode()[Info.SCHEDULE/10]).isFull()){
                    return null;
                }else{
                    mode = Info.SCHEDULESET;
                    pointer = Info.TIME_POINTER_HOUR;
                    listPointer = -1;
                    return new Object[]{enterSettingMode(), pointer};
                }

            default:
                break;
        }
        return null;
    }

    public Object[] pressLongButtonC() {
        switch (mode) {
            case Info.ALARM:
                if (controller.deleteTime(mode, listPointer)) {
                    return new Object[]{controller.getCurTime(), ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};
                }
                return null;

            case Info.SCHEDULE:
                if (controller.deleteTime(mode, listPointer)) {
                    return new Object[]{controller.getCurTime(), ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), listPointer};
                }
                return null;

            default:
                return null;
        }
    }

    public Object[] pressLongButtonD() {
        if(mode==Info.TIMEKEEPING){
            mode=Info.SELECTMODE;
            listPointer = Info.LIST_POINTER_1;
        }
        return new Object[]{controller.getSelectedModeNum()};
    }

    private Object enterSettingMode() {
        switch (mode) {
            case Info.TIMEKEEPINGSET:
                return controller.loadTime(mode, -1);

            case Info.ALARMSET:
                return controller.loadTime(mode, listPointer);

            case Info.SCHEDULESET:
                return controller.loadTime(mode, listPointer);

            default:
                return null;
        }
    }

    private Object increaseValue() {
        controller.increaseTimeValue(mode, pointer);

        switch (mode){
            case Info.TIMEKEEPINGSET:
                return controller.getCurTime();

            case Info.TIMER:
                return controller.getCurTimer();

            case Info.STOPWATCH:
                return controller.getCurStopwatch();

            case Info.ALARMSET:
                return controller.getCurAlarm();

            case Info.SCHEDULESET:
                return controller.getCurSchedule();

            default:
                return null;
        }
    }


    private Object decreaseValue() {
        controller.decreaseTimeValue(mode, pointer);

        switch (mode){
            case Info.TIMEKEEPINGSET:
                return controller.getCurTime();

            case Info.TIMER:
                return controller.getCurTimer();

            case Info.STOPWATCH:
                return controller.getCurStopwatch();

            case Info.ALARMSET:
                return controller.getCurAlarm();

            case Info.SCHEDULESET:
                return controller.getCurSchedule();

            default:
                return null;
        }
    }

    private void movePointer() {
        pointer++;

        switch (mode){
            case Info.TIMEKEEPINGSET:
                if(pointer>Info.TIME_POINTER_DAY) pointer = Info.TIME_POINTER_HOUR;
                break;

            case Info.TIMER:
                if(pointer>Info.TIME_POINTER_SECOND) pointer = Info.TIME_POINTER_HOUR;
                break;

            case Info.ALARMSET:
                if(pointer>Info.TIME_POINTER_SECOND) pointer = Info.TIME_POINTER_HOUR;
                break;

            case Info.SCHEDULESET:
                if(pointer>Info.TIME_POINTER_SCHETYPE) pointer = Info.TIME_POINTER_MONTH;
                else if(pointer>Info.TIME_POINTER_DAY) pointer = Info.TIME_POINTER_HOUR;
                break;
        }
    }

    private void moveListPointer(int state) {
        if(state==1){
            listPointer++;
        }else{
            listPointer--;
        }

        switch (mode){
            case Info.ALARM:
                if(listPointer>Info.LIST_POINTER_3) listPointer = Info.LIST_POINTER_3;
                else if(listPointer<Info.LIST_POINTER_0) listPointer = Info.LIST_POINTER_0;
                break;

            case Info.SCHEDULE:
                if(listPointer>Info.LIST_POINTER_3) listPointer = Info.LIST_POINTER_3;
                else if(listPointer<Info.LIST_POINTER_0) listPointer = Info.LIST_POINTER_0;
                break;

            case Info.SELECTMODE:
                if(listPointer>Info.LIST_POINTER_5) listPointer = Info.LIST_POINTER_5;
                else if(listPointer<Info.LIST_POINTER_1) listPointer = Info.LIST_POINTER_1;
                break;
        }
        return;
    }

    private Object saveValue() {
        controller.saveTimeValue(listPointer, mode);

        switch (mode){
            case Info.TIMEKEEPINGSET:
                return ((TimeKeepingMode)controller.getSelectedMode()[mode/10]).getValue();

            case Info.TIMER:
                return ((TimerMode)controller.getSelectedMode()[mode/10]).getValue();

            case Info.STOPWATCH:
                return ((StopwatchMode)controller.getSelectedMode()[mode/10]).getValue();

            case Info.ALARMSET:
                return ((AlarmMode)controller.getSelectedMode()[mode/10]).getValue(listPointer);

            case Info.SCHEDULESET:
                return ((ScheduleMode)controller.getSelectedMode()[mode/10]).getValue(listPointer);

            default:
                return null;
        }
    }

    private void selectMode() {

    }

    private void changeMode() {
        do{
            mode+=10;
            if(mode>Info.WORLDTIME) mode=Info.TIMEKEEPING;
        }while(!controller.getSelectedModeNum()[mode/10]);
    }

    private static void initTime(Time time){
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy:MM:dd:HH:mm:ss");
        Date date = new Date();
        String stringTimes[] = format.format(date).split(":");

        time.year = Integer.parseInt(stringTimes[0]);
        time.month = Integer.parseInt(stringTimes[1]);
        time.day = Integer.parseInt(stringTimes[2]);
        time.hour = Integer.parseInt(stringTimes[3]);
        time.minute = Integer.parseInt(stringTimes[4]);
        time.second = Integer.parseInt(stringTimes[5]);
    }

    private static void initMode(Mode modes[], Time time){
        modes[0] = new TimeKeepingMode(time);
        //  modes[0] =  (TimeKeepingMode)modes[0];
        modes[1] = new TimerMode();
        modes[2] = new StopwatchMode();
        modes[3] = new AlarmMode();
        modes[4] = new ScheduleMode();
        modes[5] = new WorldTimeMode();
    }

}