package model;


public class TimeKeepingMode extends Mode {

    private Time currentTime;

    public TimeKeepingMode(Time time) {
        this.currentTime = time;
    }

    public Time getValue() {
        return this.currentTime;
    }

    public Time saveValue(Time time) {
        this.currentTime = time;
        return this.currentTime;
    }

}