package testpackage;

import java.util.*;

public class TimerMode extends Mode {
    private Time timerTime;

    public TimerMode() {
        this.timerTime = new Time();
    }

    public Time getValue() {
        return this.timerTime;
    }

    public Time saveValue(Time time) {
        this.timerTime = time;
        return this.timerTime;
    }
}