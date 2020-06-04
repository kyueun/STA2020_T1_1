package model;

/**
 * 
 */
public class TimeKeepingMode extends Mode {

    private Time currentTime;

    public TimeKeepingMode(Time time) {
        this.currentTime = time;
    }

    /**
     * @param int index 
     * @return
     */
    public Time getValue() {
        return this.currentTime;
    }

    /**
     * @param int index 
     * @param Time time 
     * @return
     */
    public Time saveValue(Time time) {
        // TODO implement here
        return null;
    }

}