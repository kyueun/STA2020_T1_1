package testpackage;

import view.GUI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestClass {
    //    final static int TIMEKEEPING = 0, TIMER = 1, STOPWATCH = 2, ALARM = 3, SCHEDULE = 4, WORLDTIME = 5;

    public static void main(String args[]){
        Time time = new Time();
        Mode modes[] = new Mode[6];

        //display를 위한 현재 모드
        int mode = Info.TIMEKEEPING;

        DWS dws = new DWS();
        //부모 클래스에서 자식 클래스 메소드 호출

        dws.start();
    }


}
