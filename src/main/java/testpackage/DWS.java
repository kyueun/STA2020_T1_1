package testpackage;

import java.time.Duration;
import java.time.LocalTime;
import java.util.*;

/**
 *
 *
 **/
 public class DWS{

    public DWS (ModeController controller, int mode) {
        this.controller = controller;
        this.mode = mode;
    }


    // private UI ui;
    private int index;
    private int pointer;
    private int mode;
    private int object_type;
    private int time_type;
    private ModeController controller;

    /**
     * @return
     */

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
                    input = gui.getInput();
                    if(input!=-1) flag = true;
                }
            }while(Duration.between(timeStart, timeEnd).getSeconds()<60);

            if(input==-1) { //nothing in

            }
            else { // button input exist
                switch(input) {
                    case Info.A:
                        break;
                    case Info.B:
                        break;
                    case Info.C:
                        break;
                    case Info.D:
                        break;
                    case Info.LONGB:
                        break;
                    case Info.LONGD:
                        break;
                }
            }

            gui.display(mode, object);
            gui.setInput(-1);
            timeStart = timeEnd;
        }
    }

    public int pressButtonA() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int pressButtonB() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int pressButtonC() {
        // TODO implement here
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

}