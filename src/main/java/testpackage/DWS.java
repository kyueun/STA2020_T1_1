package testpackage;

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
    private int index;
    private int pointer;
    private int mode;
    private int object_type;
    private int time_type;

    private ModeController controller;
    private Time time;
    Mode modes[];

    int timeStart, timeEnd;

    private int input;

    public DWS () {

        time = new Time();
        modes = new Mode[6];

        initTime(time);
        initMode(modes, time);

        ((TimeKeepingMode) modes[0]).saveValue(time);
        controller = new ModeController(time, modes);

        this.mode = Info.TIMEKEEPING;

        //  gui.setListener("A", pressButtonA());

    }

    public void start() {
        LocalTime timeStart, timeEnd;
        boolean flag;
        int input;

        timeStart = LocalTime.now();
        Object[] screenValue = null;

        while(true) {
            input = -1;
            flag = false;
            controller.increaseTimeValue(Info.TIMEKEEPING, Info.TIME_POINTER_NULL);
            if(controller.isRunningTimer()) controller.increaseTimeValue(Info.TIMER, Info.TIME_POINTER_NULL);
            if(controller.isRunningStopwatch()) controller.increaseTimeValue(Info.STOPWATCH, Info.TIME_POINTER_NULL);

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
                }
            }
            else { // button input exist
                switch(input) {
                    case Info.A:
                        pressButtonA();
                        break;
                    case Info.B:
                        screenValue = new Object[]{pressButtonB()};
                        break;
                    case Info.C:
                        pressButtonC();
                        break;
                    case Info.D:
                        pressButtonD();
                        break;
                    case Info.LONGB:
                        pressLongButtonB();
                        break;
                    case Info.LONGC:
                        pressLongButtonC();
                        break;
                    case Info.LONGD:
                        pressLongButtonD();
                        break;
                    default:
                        screenValue = null;
                        break;
                }
            }
            this.gui.display(mode, screenValue); // (Required) modify object
            this.gui.setInput(-1);
            timeStart = timeEnd;
        }
    }

    public GUI getGui() {
        return gui;
    }

    public void setGui(GUI gui) {
        this.gui = gui;
    }

    public Object pressButtonA() {
            switch (mode) {
                case Info.TIMEKEEPINGSET:

                    return enterSettingMode();
                case Info.TIMER:
                    increaseValue();
                    break;
                case Info.ALARM:

                    break;
                case Info.ALARMSET:

                    break;
                case Info.SCHEDULE:

                    break;
                case Info.SCHEDULESET:

                    break;
                case Info.SELECTMODE:

                    break;
                default:
                    break;
            }
            return null;
        }
    /**
     * @return
     */
    public Object pressButtonB() {
        switch(mode){
            case Info.TIMEKEEPING:
                mode = Info.TIMEKEEPINGSET;
                return enterSettingMode();
            case Info.TIMEKEEPINGSET:
                break;
            case Info.TIMER:
                saveTime();
                //startTimer();
                //pauseTimer();
                break;
            case Info.STOPWATCH:
                //startStopwatch();
                //pauseStopwatch();
                break;
            case Info.ALARM:

                break;
            case Info.ALARMSET:

                break;
            case Info.SCHEDULE:

                break;
            case Info.SCHEDULESET:

                break;
            case Info.SELECTMODE:

                break;
            default:
                break;
        }
        return null;
    }

    /**
     * @return
     */
    public Object pressButtonC() {
        switch(mode){
            case Info.TIMEKEEPINGSET:

                return enterSettingMode();
            case Info.TIMER:
                movePointer();
                break;
            case Info.ALARM:

                break;
            case Info.ALARMSET:

                break;
            case Info.SCHEDULE:

                break;
            case Info.SCHEDULESET:

                break;
            case Info.SELECTMODE:

                break;
            default:
                break;
        }
        return null;
    }

    /**
     * @return
     */

    public Object pressButtonD() {
                switch (mode) {
                    case Info.TIMEKEEPING:
                        return enterSettingMode();
                    case Info.TIMER:

                        break;
                    case Info.STOPWATCH:

                        break;
                    case Info.ALARM:

                        break;
                    case Info.SCHEDULE:

                        break;
                    case Info.WORLDTIME:

                        break;
                    default:
                        break;
                }
                return null;
            }



    public Object pressLongButtonB() {
        switch(mode){
            case Info.SCHEDULE:
                break;
            case Info.TIMER:
                resetTimer();
                break;
            case Info.STOPWATCH:
                resetStopwatch();
                break;
            default:
                break;
        }
        return null;
    }

    public Object pressLongButtonC() {
        switch(mode){
            case Info.SCHEDULE:
                break;
            case Info.TIMER:
                resetTimer();
                break;
            case Info.STOPWATCH:
                resetStopwatch();
                break;
            default:
                break;
        }
        return null;
    }

    public int pressLongButtonD() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    private Object enterSettingMode() {
        switch(mode){
            case Info.TIMEKEEPING:
                return controller.loadTime(mode);
        }

        return null;
    }

    /**
     * @return
     */
    private void increaseValue() {
        // TODO implement here
        switch (mode){
            case Info.TIMER:
                controller.increaseTimeValue(Info.TIMER, pointer);
                break;
            case Info.STOPWATCH:
                controller.increaseTimeValue(Info.STOPWATCH, pointer);
                break;
        }
        return;
    }

    /**
     *
     */
    public void display() {
        // TODO implement here
    }

    /**
     * @return
     */
    private void decreaseValue() {
        // TODO implement here
        switch (mode){
            case Info.TIMER:
               // controller.decreaseTimeValue(Info.TIMER, pointer);
                break;
        }
        return;
    }

    /**
     * @return
     */
    private int movePointer() {
        // TODO implement here
        switch (mode){
            case Info.TIMER:
                pointer++;
                if(pointer == 5) pointer = 3; //3(HOUR), 4(MINUTE), 5(SECOND)
                controller.moveTimerPointer(Info.TIMER);
                break;
        }
        return 0;
    }

    /**
     * @return
     */
    private void saveSchedule() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private boolean deleteSchedule() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    private Object saveTime() {
        // TODO implement here
        switch (mode){
            case Info.TIMER:
                controller.saveTimeValue(null, 0, Info.TIMER);
                break;
        }
        return null;
    }

    /**
     * @return
     */
    private void startTimer() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void pauseTimer() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void resetTimer() {
        controller.saveTimeValue(new Time(), 0, Info.TIMER);
        return;
    }

    /**
     * @return
     */
    private void startStopwatch() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void pauseStopwatch() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void resetStopwatch() {
        controller.saveTimeValue(new Time(), 0, Info.STOPWATCH);
        return;
    }

    /**
     * @return
     */
    private void enableAlarm() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void disableAlarm() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void saveAlarm() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void deleteAlarm() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void changeMode() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void moveListPointer() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private void saveMode() {
        // TODO implement here
        return;
    }

    /**
     * @return
     */
    private boolean selectMode() {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    private void muteBeep() {
        // TODO implement here
        return;
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