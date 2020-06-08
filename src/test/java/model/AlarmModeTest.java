package model;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class AlarmModeTest {
    AlarmMode alarmMode = new AlarmMode();

    @Test
    public void makingAlarmMode(){
        assertEquals(new ArrayList<Alarm>(), alarmMode.getList());
    }
    @Test
    public void setAlarm(){
        Time tmp = new Time();

        assertEquals(tmp, alarmMode.saveValue(-1, tmp).alarmTime);
        assertEquals(1, alarmMode.getList().size());
    }

    @Test
    public void modifyAlarm(){
        Time tmp = new Time();
        alarmMode.saveValue(-1, tmp);

        tmp.hour = 1;
        assertEquals(tmp, alarmMode.saveValue(0, tmp).alarmTime);
    }

    @Test
    public void toggleAlarm(){
        Time tmp = new Time();
        alarmMode.saveValue(-1, tmp);

        //turn off a alarm
        alarmMode.toggleAlarm(0);
        assertFalse(alarmMode.getValue(0).enable);

        //turn on a alarm
        alarmMode.toggleAlarm(0);
        assertTrue(alarmMode.getValue(0).enable);
    }

    @Test
    public void isFull(){
        for(int i=0; i<4; i++){
            assertFalse(alarmMode.isFull());
            Time tmp = new Time();
            tmp.hour = i;
            alarmMode.saveValue(-1, tmp);
        }

        assertTrue(alarmMode.isFull());
    }

    @Test
    public void sortAlarm() {
        //insert alarms in reverse order. (4,3,2,1)
        for (int i = 0; i < 4; i++) {
            Time tmpTime = new Time();
            tmpTime.hour = 4 - i;
            alarmMode.saveValue(-1, tmpTime);
        }

        //checking alarms are in regular order.
        for(int i=0; i<4; i++)
            assertEquals(i+1, alarmMode.getList().get(i).alarmTime.hour);
    }

    @Test
    public void deleteAlarm(){
        assertFalse(alarmMode.deleteValue(0));

        Time tmp = new Time();
        alarmMode.saveValue(-1, tmp);

        assertTrue(alarmMode.deleteValue(0));
    }
}
