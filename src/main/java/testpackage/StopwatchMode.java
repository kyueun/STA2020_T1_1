package testpackage;

import java.util.*;

public class StopwatchMode extends Mode {
    public StopwatchMode() {
    }

    private Time stopwatchTime;

    public Time getValue(int index) {
        return this.stopwatchTime;
    }

    public Time saveValue(int index, Time time) {
        this.stopwatchTime = time;
        return this.stopwatchTime;
    }

}