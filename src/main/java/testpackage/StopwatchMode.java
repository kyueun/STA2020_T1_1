package testpackage;

import java.util.*;

public class StopwatchMode extends Mode {
    private Time stopwatchTime;

    public StopwatchMode() {
        stopwatchTime = new Time();
    }

    public Time getValue() {
        return this.stopwatchTime;
    }

    public Time saveValue(Time time) {
        this.stopwatchTime = time;
        return this.stopwatchTime;
    }

}