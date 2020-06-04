package model;

public class TimerMode extends Mode {
    public TimerMode() {
        this.timerTime = null;
    }

    private Time timerTime;

    public Time getValue(int index) {
        return this.timerTime;
    }

    public Time saveValue(Time time) {
        this.timerTime = time;
        return this.timerTime;
    }
}