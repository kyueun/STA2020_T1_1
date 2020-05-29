
import java.util.*;

/**
 * 
 */
public class Mode Controller {

    /**
     * Default constructor
     */
    public Mode Controller() {
    }

    /**
     * 
     */
    private Schedule curSchedule;

    /**
     * 
     */
    private Alarm curAlarm;

    /**
     * 
     */
    private Time curTimer;

    /**
     * 
     */
    private Time curTime;

    /**
     * 
     */
    private Time curStopwatch;

    /**
     * 
     */
    private Time runningTimer;

    /**
     * 
     */
    private Alarm[] runningAlarm;

    /**
     * 
     */
    private Time runningStopwatch;

    /**
     * 
     */
    private Schedule recentSchedule;

    /**
     * 
     */
    private int curMode;

    /**
     * 
     */
    private ArrayList<Mode> selectedMode;

    /**
     * 
     */
    private Beep beep;

    /**
     * 
     */
    public DWS System (User Interface) 1;









    /**
     * @param int index 
     * @return
     */
    public Schedule loadSchedule(void int index) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @param int pointer 
     * @return
     */
    public void increaseScheduleValue(void int index, void int pointer) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @param int pointer 
     * @return
     */
    public void decreaseScheduleValue(void int index, void int pointer) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @return
     */
    public int moveSchedulePointer(void int index) {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public boolean checkScheduleValue() {
        // TODO implement here
        return false;
    }

    /**
     * @param Schedule curSchedule 
     * @param int index 
     * @return
     */
    public Schedule saveScheduleValue(void Schedule curSchedule, void int index) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @return
     */
    public boolean deleteScheduleValue(void int index) {
        // TODO implement here
        return false;
    }

    /**
     * @param int time_type 
     * @return
     */
    public Time loadTime(void int time_type) {
        // TODO implement here
        return null;
    }

    /**
     * @param int time_type 
     * @param int pointer 
     * @return
     */
    public void increaseTimeValue(void int time_type, void int pointer) {
        // TODO implement here
        return null;
    }

    /**
     * @param int time_type 
     * @param int pointer 
     * @return
     */
    public void decreaseTimeValue(void int time_type, void int pointer) {
        // TODO implement here
        return null;
    }

    /**
     * @param int time_type 
     * @return
     */
    public int moveTimePointer(void int time_type) {
        // TODO implement here
        return 0;
    }

    /**
     * @param Time time 
     * @param int index 
     * @param int time_type 
     * @return
     */
    public Time saveTimeValue(void Time time, void int index, void int time_type) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public Schedule calculateSchedule() {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @return
     */
    public Alarm loadAlarm(void int index) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @param int pointer 
     * @return
     */
    public void increaseAlarmValue(void int index, void int pointer) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @param int pointer 
     * @return
     */
    public void decreaseAlarmValue(void int index, void int pointer) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @return
     */
    public int moveAlarmPointer(void int index) {
        // TODO implement here
        return 0;
    }

    /**
     * @param Alarm curAlarm 
     * @param int index 
     * @return
     */
    public Alarm saveAlarmValue(void Alarm curAlarm, void int index) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @return
     */
    public void enableAlarmValue(void int index) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @return
     */
    public void disableAlarmValue(void int index) {
        // TODO implement here
        return null;
    }

    /**
     * @param int index 
     * @return
     */
    public boolean deleteAlarmValue(void int index) {
        // TODO implement here
        return false;
    }

    /**
     * @return
     */
    public int changeModeValue() {
        // TODO implement here
        return 0;
    }

    /**
     * @return
     */
    public int[] loadSelectedMode() {
        // TODO implement here
        return null;
    }

    /**
     * @param int mode 
     * @return
     */
    public void selectModeValue(void int mode) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public boolean checkModeValue() {
        // TODO implement here
        return false;
    }

    /**
     * @param int mode[4] 
     * @return
     */
    public void saveModeValue(void int mode[4]) {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void muteBeepValue() {
        // TODO implement here
        return null;
    }

    /**
     * @return
     */
    public void checkBeep() {
        // TODO implement here
        return null;
    }

}