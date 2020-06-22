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
    public void validHourAlarmMode(){
        Alarm alarm = new Alarm();
        alarm.alarmTime = new Time();
        alarmMode.getList().add(alarm);
        alarmMode.saveValue(0, new Time());

        alarmMode.getList().get(0).alarmTime.hour=23;
        alarmMode.getValue(0).alarmTime.valueUp(Info.ALARMSET, Info.TIME_POINTER_HOUR);

        assertEquals(0, alarmMode.saveValue(0, alarm.alarmTime).alarmTime.hour);

        alarmMode.getList().get(0).alarmTime.hour=0;
        alarmMode.getValue(0).alarmTime.valueDown(Info.ALARMSET, Info.TIME_POINTER_HOUR);
        assertEquals(23, alarmMode.saveValue(0, alarm.alarmTime).alarmTime.hour);
    }

    @Test
    public void validMinuteAlarmMode(){
        Alarm alarm = new Alarm();
        alarm.alarmTime = new Time();
        alarmMode.getList().add(alarm);
        alarmMode.saveValue(0, new Time());

        alarmMode.getList().get(0).alarmTime.minute=59;
        alarmMode.getValue(0).alarmTime.valueUp(Info.ALARMSET, Info.TIME_POINTER_MINUTE);

        assertEquals(0, alarmMode.saveValue(0, alarm.alarmTime).alarmTime.minute);

        alarmMode.getList().get(0).alarmTime.minute=0;
        alarmMode.getValue(0).alarmTime.valueDown(Info.ALARMSET, Info.TIME_POINTER_MINUTE);
        assertEquals(59, alarmMode.saveValue(0, alarm.alarmTime).alarmTime.minute);
    }

    @Test
    public void validSecondAlarmMode(){
        Alarm alarm = new Alarm();
        alarm.alarmTime = new Time();
        alarmMode.getList().add(alarm);
        alarmMode.saveValue(0, new Time());

        alarmMode.getList().get(0).alarmTime.second=59;
        alarmMode.getValue(0).alarmTime.valueUp(Info.ALARMSET, Info.TIME_POINTER_SECOND);

        assertEquals(0, alarmMode.saveValue(0, alarm.alarmTime).alarmTime.second);

        alarmMode.getList().get(0).alarmTime.second=0;
        alarmMode.getValue(0).alarmTime.valueDown(Info.ALARMSET, Info.TIME_POINTER_SECOND);
        assertEquals(59, alarmMode.saveValue(0, alarm.alarmTime).alarmTime.second);
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
