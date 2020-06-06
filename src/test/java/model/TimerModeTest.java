package model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TimerModeTest {
    TimerMode timerMode = new TimerMode();

    @Test
    public void makeTimerMode(){
        assertEquals(0, timerMode.getValue().year);
        assertEquals(0, timerMode.getValue().month);
        assertEquals(0, timerMode.getValue().day);
        assertEquals(0, timerMode.getValue().hour);
        assertEquals(0, timerMode.getValue().minute);
        assertEquals(0, timerMode.getValue().second);
    }

    @Test
    public void setTimerMode(){
        Time tmp = new Time();
        tmp.hour = 1;
        timerMode.saveValue(tmp);

        assertEquals(0, timerMode.saveValue(tmp).year);
        assertEquals(0, timerMode.saveValue(tmp).month);
        assertEquals(0, timerMode.saveValue(tmp).day);
        assertEquals(1, timerMode.saveValue(tmp).hour);
        assertEquals(0, timerMode.saveValue(tmp).minute);
        assertEquals(0, timerMode.saveValue(tmp).second);
    }
}
