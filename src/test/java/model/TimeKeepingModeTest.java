package model;

import org.junit.Test;
import static org.junit.Assert.*;

public class TimeKeepingModeTest {
    @Test
    public void makeTimeKeepingMode(){
        Time time = new Time();
        TimeKeepingMode timeKeepingMode = new TimeKeepingMode(time);
        assertEquals(time, timeKeepingMode.getValue());
    }

    @Test
    public void setTimeKeepingMode(){
        Time time = new Time();
        TimeKeepingMode timeKeepingMode = new TimeKeepingMode(time);

        time.hour = 1;
        assertEquals(time, timeKeepingMode.saveValue(time));
    }
}
