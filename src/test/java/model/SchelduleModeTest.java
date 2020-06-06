package model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;

public class SchelduleModeTest {
    ScheduleMode scheduleMode = new ScheduleMode();

    @Test
    public void makingScheduleMode(){
    assertEquals(new ArrayList<Schedule>(), scheduleMode.getList());
}
    @Test
    public void setSchedule(){
        Time tmp = new Time();
        Schedule tmpSchedule = scheduleMode.saveValue(-1, tmp);

        assertEquals(tmp, tmpSchedule.scheduleTime);
        assertEquals(Info.SCH_TYPE_CLA, tmpSchedule.scheduleType);
        assertEquals(1, scheduleMode.getList().size());
    }

    @Test
    public void deleteSchedule(){
        assertFalse(scheduleMode.deleteValue(0));

        Time tmp = new Time();
        Schedule tmpSchedule = scheduleMode.saveValue(-1, tmp);
        assertTrue(scheduleMode.deleteValue(0));
    }

    @Test
    public void isAvailableAdd(){
        Time curTime = new Time();
        curTime.hour = 1;

        Schedule schedule = new Schedule();
        assertFalse(scheduleMode.isAvailAdd(curTime, schedule));

        Schedule schedule2 = new Schedule();
        schedule2.scheduleTime.hour = 2;
        assertEquals(true, scheduleMode.isAvailAdd(curTime, schedule2));
    }

    @Test
    public void sortSchedule() {
        //insert schedules in reverse order. (4,3,2,1)
        for (int i = 0; i < 4; i++) {
            Time tmpTime = new Time();
            tmpTime.hour = 4 - i;
            scheduleMode.saveValue(-1, tmpTime);
        }

        //checking schedules are in regular order.
        for(int i=0; i<4; i++)
            assertEquals(i+1, scheduleMode.getList().get(i).scheduleTime.hour);
    }
}
