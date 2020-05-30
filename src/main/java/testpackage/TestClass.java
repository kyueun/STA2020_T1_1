package testpackage;

import view.GUI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClass {
    //    final static int TIMEKEEPING = 0, TIMER = 1, STOPWATCH = 2, ALARM = 3, SCHEDULE = 4, WORLDTIME = 5;

    public static void main(String args[]){
        Time time = new Time();
        Mode modes[] = new Mode[6];

        // Current Mode for Display
        int mode = Info.TIMEKEEPING;

        initTime(time);
        initMode(modes);

        ModeController controller = new ModeController(time, modes);
        DWS dws = new DWS(controller, Info.TIMEKEEPING);

        // call sub class's method from super class
        ((TimeKeepingMode) modes[0]).saveValue(time);

//        while(true) {
//          }
        DWS.setGui(new GUI());
    }

    private static void initTime(Time time){
        SimpleDateFormat format = new SimpleDateFormat ( "yyyy:MM:dd:HH:mm:ss");
        Date date = new Date();
        String stringTimes[] = format.format(date).split(":");

        time.year = Integer.parseInt(stringTimes[0]);
        time.month = Integer.parseInt(stringTimes[1]);
        time.day = Integer.parseInt(stringTimes[2]);
        time.hour = Integer.parseInt(stringTimes[3]);
        time.minute = Integer.parseInt(stringTimes[4]);
        time.second = Integer.parseInt(stringTimes[5]);
    }

    private static void initMode(Mode modes[]){
        modes[0] = new TimeKeepingMode();
        //  modes[0] =  (TimeKeepingMode)modes[0];
        modes[1] = new TimerMode();
        modes[2] = new StopwatchMode();
        modes[3] = new AlarmMode();
        modes[4] = new ScheduleMode();
        modes[5] = new WorldTimeMode();
    }
}
