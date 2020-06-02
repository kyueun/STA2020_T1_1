package testpackage;

import java.util.*;

public class StopwatchMode extends Mode {
    public StopwatchMode() {
        this.stopwatchTime = null;
    }

    private Time stopwatchTime;

    public Time getValue(int index) {
        return this.stopwatchTime;
    }

    public Time saveValue(Time time) {
        this.stopwatchTime = time;
        return this.stopwatchTime;
    }

}