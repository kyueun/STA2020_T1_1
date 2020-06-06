package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class StopwatchModeTest {
    StopwatchMode stopwatchMode = new StopwatchMode();

    @Test
    public void makeStopwatchMode(){
        assertEquals(0, stopwatchMode.getValue().year);
        assertEquals(0, stopwatchMode.getValue().month);
        assertEquals(0, stopwatchMode.getValue().day);
        assertEquals(0, stopwatchMode.getValue().hour);
        assertEquals(0, stopwatchMode.getValue().minute);
        assertEquals(0, stopwatchMode.getValue().second);
    }

    @Test
    public void setStopwatchMode(){
        Time tmp = new Time();
        tmp.hour = 1;
        stopwatchMode.saveValue(tmp);

        assertEquals(0, stopwatchMode.saveValue(tmp).year);
        assertEquals(0, stopwatchMode.saveValue(tmp).month);
        assertEquals(0, stopwatchMode.saveValue(tmp).day);
        assertEquals(1, stopwatchMode.saveValue(tmp).hour);
        assertEquals(0, stopwatchMode.saveValue(tmp).minute);
        assertEquals(0, stopwatchMode.saveValue(tmp).second);
    }
}
