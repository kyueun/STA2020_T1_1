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

        DWS dws = new DWS();

        dws.setGui(new GUI());

        dws.start();
    }


}
