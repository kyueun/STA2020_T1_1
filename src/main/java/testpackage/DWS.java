package testpackage;

import view.GUI;

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

    public DWS (ModeController controller, int mode) {
        this.controller = controller;
        this.mode = mode;
    }

    public void start() {
        LocalTime timeStart, timeEnd;
        boolean flag;
        int input;

        timeStart = LocalTime.now();
        while(true) {
            input = -1;
            flag = false;

            do {
                timeEnd = LocalTime.now();

                if(!flag) {
                    input = this.gui.getInput();
                    if(input!=-1) flag = true;
                }
            }while(Duration.between(timeStart, timeEnd).getSeconds()<1);

            if(input==-1) { //nothing in

            }
            else { // button input exist
                switch(input) {
                    case Info.A:
                        pressButtonA();
                        break;
                    case Info.B:
                        pressButtonB();
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
                }
            }

            this.gui.display(mode, new Object()); // (Required) modify object
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

    public int pressButtonA() {
        // TODO implement here
        switch (mode){
            case Info.TIMER:
                increaseValue();
                break;
        }
        return 0;
    }

    /**
     * @return
     */
    public int pressButtonB() {
        // TODO implement here
        switch (mode){
            case Info.TIMER:
                saveTime();
                //startTimer();
                //pauseTimer();
                break;
            case Info.STOPWATCH:
                //startStopwatch();
                //pauseStopwatch();
                break;
        }
        return 0;
    }

    /**
     * @return
     */
    public int pressButtonC() {
        // TODO implement here
        switch (mode){
            case Info.TIMER:
                movePointer();
                break;
        }
        return 0;
    }

    /**
     * @return
     */
    public int pressButtonD() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int pressLongButtonA() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int pressLongButtonB() {
        // TODO implement here
        switch (mode){
            case Info.TIMER :
                resetTimer();
                break;
            case Info.STOPWATCH:
                resetStopwatch();
                break;
        }
        return 0;
    }

    /**
     * @return
     */
    public int pressLongButtonC() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int pressLongButtonD() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    private Object enterSettingMode() {
        // TODO implement here
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
                controller.decreaseTimeValue(Info.TIMER, pointer);
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

}