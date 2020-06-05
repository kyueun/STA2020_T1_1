package model;

import view.AlarmListPanel;
import view.GUI;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 *
 **/
public class DWS {
    private GUI gui;
    private int listPointer;
    private int pointer;
    private int mode;
    private Beep beep;
    private ModeController controller;
    private Time time;

    int timeStart, timeEnd;
    int timeOut;

    private int input;

    public DWS() {

        time = new Time();
        Mode[] modes = new Mode[6];

        initTime(time);
        initMode(modes, time);

        pointer = Info.TIME_POINTER_NULL;
        listPointer = Info.LIST_POINTER_0;


        Time t = syncTime(time);
        controller = new ModeController(t, modes);

        ((TimeKeepingMode) controller.getSelectedMode()[0]).saveValue(syncTime(time));


        this.mode = Info.TIMEKEEPING;

        //  gui.setListener("A", pressButtonA());
    }

    public void setGui(GUI gui) {
        this.gui = gui;
        beep = new Beep(gui);
    }

    public void start() {
        LocalTime timeStart, timeEnd; //real 1 sec?
        boolean flag; // input existence
        int input; //input in gui

        timeStart = LocalTime.now();
        timeOut = 0;

        Object[] screenValue = new Object[]{controller.getRecentSchedule(), time}; // display data

        while (true) {
            input = -1;
            flag = false;

            controller.increaseTimeValue(Info.TIMEKEEPING, Info.TIME_POINTER_NULL, time);
            if (controller.isRunningTimer()) controller.decreaseTimeValue(Info.TIMER, Info.TIME_POINTER_NULL);
            if (controller.isRunningStopwatch())
                controller.increaseTimeValue(Info.STOPWATCH, Info.TIME_POINTER_NULL, null);

            System.out.println("curtIMER= " + controller.getCurTimer().second);
            System.out.println("TIME" + new Time().second);
            if (controller.isRunningTimer() && (controller.getCurTimer() != null) && compareTime(controller.getCurTimer(), new Time())) { //timer beep
                System.out.println("beep");

                beep.beepPopup(Info.BEEP_TYPE_TIMER);
                controller.setRunningTimer(false);
            }

            if(controller.getRunningAlarmList().size()!=0){
                Iterator<Alarm> itr = controller.getRunningAlarmList().iterator();

                while (itr.hasNext()) { //alarm beep

                    Alarm alarm = itr.next();
                   // System.out.println("alarm: " + alarm.alarmTime.hour + "hour " + alarm.alarmTime.minute + "minute " + alarm.alarmTime.second);

                    if (compareTime(alarm.alarmTime, time)) {
                        System.out.println("itr2222asdfD");
                        beep.beepPopup(Info.BEEP_TYPE_ALARM);
                        break;
                    }

                }
            }


            System.out.println("TIme: " + time.year + "." + time.month + "." + time.day + " " + time.hour + ":" + time.minute + ":" + time.second);

            do {
                timeEnd = LocalTime.now();

                if (!flag) {
                    input = this.gui.getInput();
                    if (input != -1) flag = true;
                }


            } while (Duration.between(timeStart, timeEnd).getSeconds() < 1);
            System.out.println("input= " + input + "// list size= "+controller.getRunningAlarmList().size());

            Iterator<Alarm> itr = controller.getRunningAlarmList().iterator();
            System.out.print("AlarmList: ");
            while(itr.hasNext()) System.out.print(itr.next().alarmTime.minute+ ", ");
            System.out.println("--------------");

            if (input == -1) { //nothing in
                if (!(mode == Info.TIMER && controller.isRunningTimer()) || !(mode == Info.STOPWATCH && controller.isRunningStopwatch()))
                    timeOut++;

                if (timeOut == 60) {
                    mode = Info.TIMEKEEPING;
                    timeOut = 0;
                }

                switch (mode) {
                    case Info.TIMEKEEPING: //show current time
                        screenValue = new Object[]{controller.getRecentSchedule(), time};
                        System.out.println("DWS: time keeping");
                        break;

                    case Info.TIMEKEEPINGSET:
                        System.out.println("time: " + time);
                        System.out.println("curTime: " + controller.getCurTime());
                        screenValue = new Object[]{controller.getCurTime(), pointer};
                        break;

                    case Info.TIMER: //show timer time
                        screenValue = new Object[]{time, controller.getCurTimer(), pointer};
                        break;

                    case Info.STOPWATCH: //show timer time
                        screenValue = new Object[]{time, controller.getCurStopwatch()};
                        break;

                    case Info.ALARM:

                        screenValue = new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};
                        break;

                    case Info.ALARMSET:
                        screenValue = new Object[]{time, controller.getCurAlarm().alarmTime, pointer};
                        break;

                    case Info.SCHEDULE:
                        screenValue = new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), listPointer};
                        break;

                    case Info.SCHEDULESET:
                        screenValue = new Object[]{time, controller.getCurSchedule().scheduleTime, pointer};
                        break;

                    case Info.WORLDTIME:
                        screenValue = new Object[]{((WorldTimeMode) controller.getSelectedMode()[Info.WORLDTIME / 10]).getValue()};
                        break;

                    case Info.SELECTMODE:
                        screenValue = new Object[]{controller.getSelectedModeNum(), listPointer};
                        break;
                }
            } else { // button input exist
                timeOut = 0;

                System.out.println("beeplist size: " + beep.beepList.size());
                if (!beep.beepList.empty()) {
                    System.out.println("beeplist not empty");
                    beep.muteTopBeep();
                } else {
                    switch (input) {
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

            this.gui.display(mode, screenValue, beep.beepList, controller.getSelectedModeNum()); // (Required) modify object

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
                return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};

            case Info.ALARMSET:
                increaseValue();
                return new Object[]{time, controller.getCurAlarm().alarmTime, pointer};

            case Info.SCHEDULE:
                moveListPointer(1);
                return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), listPointer};

            case Info.SCHEDULESET:
                increaseValue();
                return new Object[]{time, controller.getCurSchedule().scheduleTime, pointer};

            case Info.SELECTMODE:
                moveListPointer(1);
                return new Object[]{controller.getSelectedModeNum(), listPointer};

            default:
                break;
        }
        System.out.println("DWS: short A, mode " + mode);
        return null;
    }

    /**
     * @return
     */
    public Object[] pressButtonB() {
        System.out.println("DWS: short B, mode " + mode);

        switch (mode) {
            case Info.TIMEKEEPING: //enterSetting
                mode = Info.TIMEKEEPINGSET;
                pointer = Info.TIME_POINTER_HOUR;
                return new Object[]{enterSettingMode(), pointer};

            case Info.TIMEKEEPINGSET: //exitSetting
                time = (Time) saveValue();
                mode = Info.TIMEKEEPING;
                return new Object[]{controller.getRecentSchedule(), time};

            case Info.TIMER:
                if (controller.isRunningTimer()) {
                    controller.setRunningTimer(false);
                } else {
                    pointer = Info.TIME_POINTER_NULL;
                    controller.setRunningTimer(true);
                }
                // controller.decreaseTimeValue(mode, Info.TIME_POINTER_NULL);
                return new Object[]{time, controller.getCurTimer(), Info.TIME_POINTER_NULL};

            case Info.STOPWATCH:
                if (controller.isRunningStopwatch()) {
                    controller.setRunningStopwatch(false);
                } else {
                    controller.setRunningStopwatch(true);
                }
                return new Object[]{time, controller.getCurStopwatch()};

            case Info.ALARM: //enter setting - modify alarm
                if (((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList().size() == 0) {
                    return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};
                }
                mode = Info.ALARMSET;
                pointer = Info.TIME_POINTER_HOUR;
                return new Object[]{time, enterSettingMode(), pointer};

            case Info.ALARMSET:
                System.out.println("DWS: Save Alarm");
                saveValue();
                mode = Info.ALARM;
                return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), Info.LIST_POINTER_0};

            case Info.SCHEDULE: //enter setting - modify schedule
                if (((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList().size() == 0) {
                    return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), listPointer};
                }
                mode = Info.SCHEDULESET;
                pointer = Info.TIME_POINTER_HOUR;
                return new Object[]{time, enterSettingMode(), pointer};

            case Info.SCHEDULESET:
                if (controller.isAvailable()) {
                    saveValue();
                    mode = Info.SCHEDULE;
                    return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), Info.LIST_POINTER_NULL};
                }
                return new Object[]{time, controller.getCurSchedule(), pointer};

            case Info.SELECTMODE:
                boolean[] temp = controller.getSelectedModeNum();
                temp[listPointer] = !temp[listPointer];

                controller.setSelectedModeNum(temp);
                return new Object[]{controller.getSelectedModeNum(), listPointer};

            default:
                return null;
        }
    }

    public Object[] pressButtonC() {
        System.out.println("DWS: short C, mode " + mode);

        switch (mode) {
            case Info.TIMEKEEPINGSET: //decrease
                decreaseValue();
                return new Object[]{controller.getCurTime(), pointer};

            case Info.TIMER:
                movePointer();
                return new Object[]{time, controller.getCurTimer(), pointer};

            case Info.ALARM:
                moveListPointer(0);
                return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};

            case Info.ALARMSET:
                decreaseValue();
                return new Object[]{time, controller.getCurAlarm().alarmTime, pointer};

            case Info.SCHEDULE:
                moveListPointer(0);
                return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), listPointer};

            case Info.SCHEDULESET:
                decreaseValue();
                return new Object[]{time, controller.getCurSchedule().scheduleTime, pointer};

            case Info.SELECTMODE:
                moveListPointer(0);
                return new Object[]{controller.getSelectedModeNum(), listPointer};

            default:
                return null;
        }
    }


    public Object[] pressButtonD() {
        System.out.println("DWS: short D, mode " + mode);

        switch (mode) {

            case Info.TIMEKEEPINGSET: //move pointer
                movePointer();
                return new Object[]{controller.getCurTime(), pointer};

            case Info.ALARMSET: //move pointer
                movePointer();
                return new Object[]{time, controller.getCurAlarm().alarmTime, pointer};

            case Info.SCHEDULESET: //move pointer
                movePointer();
                return new Object[]{time, controller.getCurSchedule().scheduleTime, pointer};

            case Info.SELECTMODE:
                if (controller.canSelect()) {
                    controller.refreshMode();

                    mode = Info.TIMEKEEPING;
                    return new Object[]{controller.getRecentSchedule(), time};
                } else return new Object[]{controller.getSelectedMode(), Info.LIST_POINTER_NULL};

            default: //change mode
                changeMode();

                switch (mode) {
                    case Info.TIMEKEEPING: //show current time
                        return new Object[]{controller.getRecentSchedule(), time};

                    case Info.TIMER: //show timer time
                        return new Object[]{time, controller.getCurTimer(), Info.TIME_POINTER_NULL};

                    case Info.STOPWATCH: //show timer time
                        return new Object[]{time, controller.getCurStopwatch()};

                    case Info.ALARM:
                        return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), Info.LIST_POINTER_0};

                    case Info.SCHEDULE:
                        return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), Info.LIST_POINTER_NULL};

                    case Info.WORLDTIME:
                        return new Object[]{((WorldTimeMode) controller.getSelectedMode()[Info.WORLDTIME / 10]).getValue()};
                }
                return null;
        }
    }

    public Object[] pressLongButtonA() {
        switch (mode) {
            case Info.ALARM:
                if (((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).toggleAlarm(listPointer)) {
                    return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};
                }
                return null;

            default:
                return null;
        }
    }

    public Object[] pressLongButtonB() {
        switch (mode) {
            case Info.TIMER: //reset timer
                if (!controller.isRunningTimer()) {
                    controller.setCurTimer(new Time());
                    ((TimerMode) controller.getSelectedMode()[Info.TIMER / 10]).saveValue(controller.getCurTimer());
                }
                return new Object[]{time, controller.getCurTimer(), Info.TIME_POINTER_NULL};

            case Info.STOPWATCH:
                if (!controller.isRunningStopwatch()) {
                    controller.setCurStopwatch(new Time());
                    ((StopwatchMode) controller.getSelectedMode()[Info.STOPWATCH / 10]).saveValue(controller.getCurStopwatch());
                }
                return new Object[]{time, controller.getCurStopwatch()};

            case Info.ALARM: //enter setting - add alarm
                if (((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).isFull()) {
                    return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), Info.LIST_POINTER_NULL};
                } else {
                    mode = Info.ALARMSET;
                    pointer = Info.TIME_POINTER_HOUR;
                    listPointer = -1;
                    return new Object[]{time, enterSettingMode(), pointer};
                }

            case Info.SCHEDULE: //enter setting - add schedule
                if (((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).isFull()) {
                    return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), Info.LIST_POINTER_NULL};
                } else {
                    mode = Info.SCHEDULESET;
                    pointer = Info.TIME_POINTER_HOUR;
                    listPointer = -1;
                    return new Object[]{time, enterSettingMode(), pointer};
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
                    return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};
                }
                return new Object[]{time, ((AlarmMode) controller.getSelectedMode()[Info.ALARM / 10]).getList(), listPointer};

            case Info.SCHEDULE:
                if (controller.deleteTime(mode, listPointer)) {
                    return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), listPointer};
                }
                return new Object[]{time, ((ScheduleMode) controller.getSelectedMode()[Info.SCHEDULE / 10]).getList(), listPointer};

        }
        System.out.println("DWS: long C, mode " + mode);
        return null;
    }

    public Object[] pressLongButtonD() {
        if (mode == Info.TIMEKEEPING) {
            mode = Info.SELECTMODE;
            listPointer = Info.LIST_POINTER_1;
            return new Object[]{controller.getSelectedModeNum(), listPointer};
        }
        System.out.println("DWS: long D, mode " + mode);
        return new Object[]{controller.getRecentSchedule(), time};
    }

    private Object enterSettingMode() {
        switch (mode) {
            case Info.TIMEKEEPINGSET:
                controller.setCurTime(syncTime(time));
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
        controller.increaseTimeValue(mode, pointer, null);

        switch (mode) {
            case Info.TIMEKEEPINGSET:
                return controller.getCurTime();

            case Info.TIMER:
                return controller.getCurTimer();

            case Info.STOPWATCH:
                return controller.getCurStopwatch();

            case Info.ALARMSET:
                return controller.getCurAlarm().alarmTime;

            case Info.SCHEDULESET:
                return controller.getCurSchedule().scheduleTime;

            default:
                return null;
        }
    }


    private Object decreaseValue() {
        controller.decreaseTimeValue(mode, pointer);

        switch (mode) {
            case Info.TIMEKEEPINGSET:
                return controller.getCurTime();

            case Info.TIMER:
                return controller.getCurTimer();

            case Info.STOPWATCH:
                return controller.getCurStopwatch();

            case Info.ALARMSET:
                return controller.getCurAlarm().alarmTime;

            case Info.SCHEDULESET:
                return controller.getCurSchedule().scheduleTime;

            default:
                return null;
        }
    }

    private void movePointer() {
        pointer++;

        switch (mode) {
            case Info.TIMEKEEPINGSET:
                if (pointer > Info.TIME_POINTER_DAY) pointer = Info.TIME_POINTER_HOUR;
                break;

            case Info.TIMER:
                if (pointer > Info.TIME_POINTER_SECOND) pointer = Info.TIME_POINTER_HOUR;
                break;

            case Info.ALARMSET:
                if (pointer > Info.TIME_POINTER_SECOND) pointer = Info.TIME_POINTER_HOUR;
                break;

            case Info.SCHEDULESET:
                if (pointer > Info.TIME_POINTER_SCHETYPE) pointer = Info.TIME_POINTER_MONTH;
                else if (pointer > Info.TIME_POINTER_DAY) pointer = Info.TIME_POINTER_HOUR;
                break;
        }
    }

    private void moveListPointer(int state) {
        if (state == 1) {
            listPointer--;
        } else {
            listPointer++;
        }

        switch (mode) {
            case Info.ALARM:
                if (listPointer > Info.LIST_POINTER_3) listPointer = Info.LIST_POINTER_3;
                else if (listPointer < Info.LIST_POINTER_0) listPointer = Info.LIST_POINTER_0;
                break;

            case Info.SCHEDULE:
                if (listPointer > Info.LIST_POINTER_3) listPointer = Info.LIST_POINTER_3;
                else if (listPointer < Info.LIST_POINTER_0) listPointer = Info.LIST_POINTER_0;
                break;

            case Info.SELECTMODE:
                if (listPointer > Info.LIST_POINTER_5) listPointer = Info.LIST_POINTER_5;
                else if (listPointer < Info.LIST_POINTER_1) listPointer = Info.LIST_POINTER_1;
                break;
        }
        return;
    }

    private Object saveValue() {
        controller.saveTimeValue(listPointer, mode);

        switch (mode) {
            case Info.TIMEKEEPINGSET:
                return ((TimeKeepingMode) controller.getSelectedMode()[mode / 10]).getValue();

            case Info.TIMER:
                return ((TimerMode) controller.getSelectedMode()[mode / 10]).getValue();

            case Info.STOPWATCH:
                return ((StopwatchMode) controller.getSelectedMode()[mode / 10]).getValue();

            case Info.ALARMSET:
                return ((AlarmMode) controller.getSelectedMode()[mode / 10]).getValue(listPointer);

            case Info.SCHEDULESET:
                return ((ScheduleMode) controller.getSelectedMode()[mode / 10]).getValue(listPointer);

            default:
                return null;
        }
    }

    private void changeMode() {
        do {
            mode += 10;
            if (mode > Info.SCHEDULE) mode = Info.TIMEKEEPING;
        } while (!controller.getSelectedModeNum()[mode / 10]);
    }

    private static Time syncTime(Time time) {
        Time t = new Time();
        t.year = time.year;
        t.month = time.month;
        t.day = time.day;
        t.hour = time.hour;
        t.minute = time.minute;
        t.second = time.second;

        return t;
    }

    private static void initTime(Time time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        Date date = new Date();
        String stringTimes[] = format.format(date).split(":");

        time.year = Integer.parseInt(stringTimes[0]);
        time.month = Integer.parseInt(stringTimes[1]);
        time.day = Integer.parseInt(stringTimes[2]);
        time.hour = Integer.parseInt(stringTimes[3]);
        time.minute = Integer.parseInt(stringTimes[4]);
        time.second = Integer.parseInt(stringTimes[5]);
    }

    private static void initMode(Mode modes[], Time time) {
        modes[0] = new TimeKeepingMode(time);
        //  modes[0] =  (TimeKeepingMode)modes[0];
        modes[1] = new TimerMode();
        modes[2] = new StopwatchMode();
        modes[3] = new AlarmMode();
        modes[4] = new WorldTimeMode();
        modes[5] = new ScheduleMode();
    }

    private static boolean compareTime(Time t1, Time t2) {
        if(t1.hour!=t2.hour) return false;
        if(t1.minute!=t2.minute) return false;
        if(t1.second!=t2.second) return false;

        return true;
    }

}