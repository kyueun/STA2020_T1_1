package model;

import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class ModeControllerTest {
    ModeController modeCon;

    @Before
    public void init(){
        Time time = new Time();

        SimpleDateFormat format = new SimpleDateFormat("yyyy:MM:dd:HH:mm:ss");
        Date date = new Date();
        String stringTimes[] = format.format(date).split(":");

        time.year = Integer.parseInt(stringTimes[0]);
        time.month = Integer.parseInt(stringTimes[1]);
        time.day = Integer.parseInt(stringTimes[2]);
        time.hour = Integer.parseInt(stringTimes[3]);
        time.minute = Integer.parseInt(stringTimes[4]);
        time.second = Integer.parseInt(stringTimes[5]);

        Mode[] modes = new Mode[6];

        modes[0] = new TimeKeepingMode(time);
        modes[1] = new TimerMode();
        modes[2] = new StopwatchMode();
        modes[3] = new AlarmMode();
        modes[4] = new WorldTimeMode();
        modes[5] = new ScheduleMode();

        modeCon = new ModeController(time, modes);
    }

    @Test
    public void loadTime(){
        assertNotNull(modeCon.loadTime(Info.TIMEKEEPINGSET, 0));
        assertNotNull(modeCon.loadTime(Info.TIMER, 0));
        assertNotNull(modeCon.loadTime(Info.STOPWATCH, 0));

        modeCon.saveTimeValue(-1, Info.ALARMSET);
        assertNotNull(modeCon.loadTime(Info.ALARMSET, 0));

        boolean[] tmpModes = {true, true, false, false, true, true};
        modeCon.setSelectedModeNum(tmpModes);
        modeCon.saveTimeValue(-1, Info.SCHEDULESET);
        assertNotNull(modeCon.loadTime(Info.SCHEDULESET, 0));
    }

    @Test
    public void deleteTime(){
        modeCon.saveTimeValue(-1, Info.ALARMSET);
        assertNotNull(modeCon.loadTime(Info.ALARMSET, 0));
        modeCon.deleteTime(Info.ALARM, 0);
        assertTrue(((AlarmMode) modeCon.getSelectedMode()[Info.ALARM/10]).getList().isEmpty());

        boolean[] tmpModes = {true, true, false, false, true, true};
        modeCon.setSelectedModeNum(tmpModes);
        modeCon.saveTimeValue(-1, Info.SCHEDULESET);
        assertNotNull(modeCon.loadTime(Info.SCHEDULESET, 0));
        modeCon.deleteTime(Info.SCHEDULE, 0);
        assertTrue(((ScheduleMode) modeCon.getSelectedMode()[Info.SCHEDULE/10]).getList().isEmpty());
    }

    @Test
    public void increaseTimeValue(){
        Time testTime = new Time();
        testTime.year = 2020;
        testTime.month = 2;
        testTime.day = 28;
        testTime.hour = 23;
        testTime.minute = 59;
        testTime.second = 59;

        modeCon.setCurTime(testTime);
        modeCon.increaseTimeValue(Info.TIMEKEEPINGSET, Info.TIME_POINTER_HOUR, null);
        assertEquals(0, modeCon.getCurTime().hour);

        testTime.hour = 23;
        modeCon.setCurTimer(testTime);
        modeCon.increaseTimeValue(Info.TIMER, Info.TIME_POINTER_HOUR, null);
        assertEquals(0, modeCon.getCurTimer().hour);

        testTime.hour = 23;
        Alarm testAlarm = new Alarm();
        testAlarm.alarmTime = testTime;
        modeCon.setCurAlarm(testAlarm);
        modeCon.increaseTimeValue(Info.ALARMSET, Info.TIME_POINTER_HOUR, null);
        assertEquals(0, modeCon.getCurAlarm().alarmTime.hour);

        testTime.hour = 23;
        Schedule testSchedule = new Schedule();
        testSchedule.scheduleTime = testTime;
        modeCon.setCurSchedule(testSchedule);
        modeCon.increaseTimeValue(Info.SCHEDULESET, Info.TIME_POINTER_HOUR, null);
        assertEquals(0, modeCon.getCurSchedule().scheduleTime.hour);

        testTime.hour = 23;
        modeCon.setCurStopwatch(testTime);
        modeCon.increaseTimeValue(Info.STOPWATCH, Info.TIME_POINTER_NULL, null);
        assertEquals(0, testTime.hour);

        testTime.hour = 23;
        testTime.minute = 59;
        testTime.second = 59;
        modeCon.increaseTimeValue(Info.TIMEKEEPING, Info.TIME_POINTER_NULL, testTime);
        assertEquals(29, testTime.day);

        testTime.year = 2099;
        testTime.month = 12;
        testTime.day = 31;
        testTime.hour = 23;
        testTime.minute = 59;
        testTime.second = 59;
        modeCon.increaseTimeValue(Info.TIMEKEEPING, Info.TIME_POINTER_NULL, testTime);
        assertEquals(1900, testTime.year);
    }

    @Test
    public void decreaseTimeValue(){
        Time testTime = new Time();
        testTime.year = 1900;
        testTime.month = 1;
        testTime.day = 1;

        modeCon.setCurTime(testTime);
        modeCon.decreaseTimeValue(Info.TIMEKEEPINGSET, Info.TIME_POINTER_HOUR);
        assertEquals(23, modeCon.getCurTime().hour);

        testTime.hour = 0;
        Alarm testAlarm = new Alarm();
        testAlarm.alarmTime = testTime;
        modeCon.setCurAlarm(testAlarm);
        modeCon.decreaseTimeValue(Info.ALARMSET, Info.TIME_POINTER_HOUR);
        assertEquals(23, modeCon.getCurAlarm().alarmTime.hour);

        testTime.hour = 0;
        Schedule testSchedule = new Schedule();
        testSchedule.scheduleTime = testTime;
        modeCon.setCurSchedule(testSchedule);
        modeCon.decreaseTimeValue(Info.SCHEDULESET, Info.TIME_POINTER_HOUR);
        assertEquals(23, modeCon.getCurSchedule().scheduleTime.hour);

        testTime.hour = 0;
        testTime.minute = 0;
        testTime.second = 2;
        modeCon.setCurTimer(testTime);
        modeCon.decreaseTimeValue(Info.TIMER, Info.TIME_POINTER_NULL);
        assertEquals(1, modeCon.getCurTimer().second);
        modeCon.decreaseTimeValue(Info.TIMER, Info.TIME_POINTER_NULL);
        assertEquals(0, modeCon.getCurTimer().second);
        modeCon.decreaseTimeValue(Info.TIMER, Info.TIME_POINTER_NULL);
        assertEquals(0, modeCon.getCurTimer().second);
    }

    @Test
    public void saveTimeValue(){
        Time testTime = new Time();
        testTime.hour = 1;

        modeCon.setCurTime(testTime);
        modeCon.saveTimeValue(-1, Info.TIMEKEEPINGSET);
        assertEquals(1, ((TimeKeepingMode)(modeCon.getSelectedMode()[Info.TIMEKEEPINGSET/10])).getValue().hour);

        modeCon.setCurTimer(testTime);
        modeCon.saveTimeValue(-1, Info.TIMER);
        assertEquals(1, ((TimerMode)(modeCon.getSelectedMode()[Info.TIMER/10])).getValue().hour);

        modeCon.setCurStopwatch(testTime);
        modeCon.saveTimeValue(-1, Info.STOPWATCH);
        assertEquals(1, ((StopwatchMode)(modeCon.getSelectedMode()[Info.STOPWATCH/10])).getValue().hour);

        Alarm testAlarm = new Alarm();
        testAlarm.alarmTime = testTime;
        modeCon.setCurAlarm(testAlarm);
        modeCon.saveTimeValue(-1, Info.ALARMSET);
        assertEquals(1, ((AlarmMode)(modeCon.getSelectedMode()[Info.ALARMSET/10])).getValue(0).alarmTime.hour);

        boolean[] tmpModes = {true, true, false, false, true, true};
        modeCon.setSelectedModeNum(tmpModes);
        Schedule testSchedule = new Schedule();
        testSchedule.scheduleTime = testTime;
        modeCon.setCurSchedule(testSchedule);
        modeCon.saveTimeValue(-1, Info.SCHEDULESET);
        assertEquals(1, ((ScheduleMode)(modeCon.getSelectedMode()[Info.SCHEDULESET/10])).getValue(0).scheduleTime.hour);
    }

    @Test
    public void canSelect(){
        boolean[] tmpModes = {true, true, false, false, true, true};
        modeCon.setSelectedModeNum(tmpModes);
        assertTrue(modeCon.canSelect());

        boolean[] tmpModes2 = {true, true, false, false, false, true};
        modeCon.setSelectedModeNum(tmpModes2);
        assertFalse(modeCon.canSelect());

        boolean[] tmpModes3 = {true, true, false, true, true, true};
        modeCon.setSelectedModeNum(tmpModes3);
        assertFalse(modeCon.canSelect());
    }

    @Test
    public void ScheduleTest1_2(){
        Time curTime = new Time();
        curTime.year = 2020;
        curTime.month= 6;
        curTime.day = 1;

        Schedule tmpschedule1 = new Schedule();
        tmpschedule1.scheduleTime.year = 2020;
        tmpschedule1.scheduleTime.month = 6;
        tmpschedule1.scheduleTime.day = 10;
        modeCon.setCurSchedule(tmpschedule1);
        modeCon.saveTimeValue(-1, Info.SCHEDULESET);
        modeCon.calculateSchedule(curTime);
        assertEquals(10, modeCon.getRecentSchedule().scheduleTime.day);

        Schedule tmpschedule2 = new Schedule();
        tmpschedule2.scheduleTime.year = 2020;
        tmpschedule2.scheduleTime.month = 6;
        tmpschedule2.scheduleTime.day = 9;
        modeCon.setCurSchedule(tmpschedule2);
        modeCon.saveTimeValue(-1, Info.SCHEDULESET);
        modeCon.calculateSchedule(curTime);
        assertEquals(9, modeCon.getRecentSchedule().scheduleTime.day);

        Schedule tmpschedule3 = new Schedule();
        tmpschedule3.scheduleTime.year = 2020;
        tmpschedule3.scheduleTime.month = 6;
        tmpschedule3.scheduleTime.day = 11;
        modeCon.setCurSchedule(tmpschedule3);
        modeCon.saveTimeValue(-1, Info.SCHEDULESET);
        modeCon.calculateSchedule(curTime);
        assertEquals(9, modeCon.getRecentSchedule().scheduleTime.day);

        assertEquals(9, ((ScheduleMode)modeCon.getSelectedMode()[Info.SCHEDULE/10]).getList().get(0).scheduleTime.day);
        assertEquals(10, ((ScheduleMode)modeCon.getSelectedMode()[Info.SCHEDULE/10]).getList().get(1).scheduleTime.day);
        assertEquals(11, ((ScheduleMode)modeCon.getSelectedMode()[Info.SCHEDULE/10]).getList().get(2).scheduleTime.day);

        curTime.day = 10;
        modeCon.calculateSchedule(curTime);
        assertEquals(11, modeCon.getRecentSchedule().scheduleTime.day);

        //Test 5-2
        curTime.day = 11;
        modeCon.calculateSchedule(curTime);
        assertNull(modeCon.getRecentSchedule());
    }
}
