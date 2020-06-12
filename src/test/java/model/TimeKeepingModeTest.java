package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

    @Test
    public void validHourTimeKeepingMode(){
        Time time = new Time();
        TimeKeepingMode timeKeepingMode = new TimeKeepingMode(time);

        time.hour=23;
        timeKeepingMode.getValue().valueUp(Info.TIMEKEEPINGSET, Info.TIME_POINTER_HOUR);

        assertEquals(0, timeKeepingMode.saveValue(time).hour);

        time.hour=0;
        timeKeepingMode.getValue().valueDown(Info.TIMEKEEPINGSET, Info.TIME_POINTER_HOUR);
        assertEquals(23, timeKeepingMode.saveValue(time).hour);
    }

    @Test
    public void validMinuteTimeKeepingMode(){
        Time time = new Time();
        TimeKeepingMode timeKeepingMode = new TimeKeepingMode(time);

        time.minute=59;
        timeKeepingMode.getValue().valueUp(Info.TIMEKEEPINGSET, Info.TIME_POINTER_MINUTE);

        assertEquals(0, timeKeepingMode.saveValue(time).minute);

        time.minute=0;
        timeKeepingMode.getValue().valueDown(Info.TIMEKEEPINGSET, Info.TIME_POINTER_MINUTE);
        assertEquals(59, timeKeepingMode.saveValue(time).minute);
    }

    @Test
    public void validSecondTimeKeepingMode(){
        Time time = new Time();
        TimeKeepingMode timeKeepingMode = new TimeKeepingMode(time);

        time.second=59;
        timeKeepingMode.getValue().valueUp(Info.TIMEKEEPINGSET, Info.TIME_POINTER_SECOND);

        assertEquals(0, timeKeepingMode.saveValue(time).second);

        time.second=0;
        timeKeepingMode.getValue().valueDown(Info.TIMEKEEPINGSET, Info.TIME_POINTER_SECOND);
        assertEquals(59, timeKeepingMode.saveValue(time).second);
    }

    @Test
    public void validYearTimeKeepingMode(){
        Time time = new Time();
        TimeKeepingMode timeKeepingMode = new TimeKeepingMode(time);

        time.year=2099;
        timeKeepingMode.getValue().valueUp(Info.TIMEKEEPINGSET, Info.TIME_POINTER_YEAR);

        assertEquals(1900, timeKeepingMode.saveValue(time).year);

        time.year=1900;
        timeKeepingMode.getValue().valueDown(Info.TIMEKEEPINGSET, Info.TIME_POINTER_YEAR);
        assertEquals(2099, timeKeepingMode.saveValue(time).year);
    }

    @Test
    public void validDateTimeKeepingMode(){
        Time time = new Time();
        TimeKeepingMode timeKeepingMode = new TimeKeepingMode(time);

        time.month=12;
        timeKeepingMode.getValue().valueUp(Info.TIMEKEEPINGSET, Info.TIME_POINTER_MONTH);
        assertEquals(1, timeKeepingMode.saveValue(time).month);

        time.month=1;
        timeKeepingMode.getValue().valueDown(Info.TIMEKEEPINGSET, Info.TIME_POINTER_MONTH);
        assertEquals(12, timeKeepingMode.saveValue(time).month);

        time.day=Info.DAY_OF_MONTH[time.month];
        timeKeepingMode.getValue().valueUp(Info.TIMEKEEPINGSET, Info.TIME_POINTER_DAY);
        assertEquals(1, timeKeepingMode.saveValue(time).day);

        time.day=1;
        timeKeepingMode.getValue().valueDown(Info.TIMEKEEPINGSET, Info.TIME_POINTER_DAY);
        assertEquals(Info.DAY_OF_MONTH[time.month], timeKeepingMode.saveValue(time).day);
    }

}
