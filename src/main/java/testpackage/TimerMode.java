package testpackage;

import java.util.*;

public class TimerMode extends Mode {
    public TimerMode() {
    }

    private Time timerTime;

    public Time getValue(int index) {
        return this.timerTime;
    }

    public Time saveValue(int index, Time time) {
        this.timerTime = time;
        return null;
    }
}