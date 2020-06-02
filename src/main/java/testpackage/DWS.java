package testpackage;

import view.GUI;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.*;

/**
 *
 *
 **/
 public class DWS{

    // private UI ui;
    private int index;
    private int pointer;
    private int mode;
    private int object_type;
    private int time_type;

    private ModeController controller;
    private Time time;
    Mode modes[];

    int timeStart, timeEnd;

    GUI gui = new GUI();
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


    public void start(){

        Object object = time;
        gui.display(mode, object);

        timeStart = LocalTime.now().getSecond();

        while(true){
            input = -1;

            do{
                timeEnd = LocalTime.now().getSecond();
                if(flag==false) {
                    input = gui.getinput();
                    if(input!=-1) flag = true
                }

            }while(timeEnd-timeStart<1);


//            switch(input){
//                case Info.A:
//                    object = pressButtonA();
//                    break;
//                case Info.B:
//                    object = pressButtonB();
//                    break;
//                case Info.C:
//                    object = pressButtonC();
//                    break;
//                case Info.D:
//                    object = pressButtonD();
//                    break;
//                case Info.LONGB:
//                    object = pressLongButtonB();
//                    break;
//                case Info.LONGD:
//                    object =pressLongButtonD();
//                    break;
//                default:
//                    break;
//            }

            timeStart = timeEnd;
            gui.trigger(displayNum, time);
            gui.setinput(-1);
        }
    }
    /**
     * @return
     */
    public Object pressButtonA() {
        switch(mode){
            case Info.TIMEKEEPINGSET:

                return enterSettingMode();
            case Info.TIMER:

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

                break;
            case Info.STOPWATCH:

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
        switch(mode){
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
        return;
    }

    /**
     * @return
     */
    private int movePointer() {
        // TODO implement here
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
        // TODO implement here
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
        // TODO implement here
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