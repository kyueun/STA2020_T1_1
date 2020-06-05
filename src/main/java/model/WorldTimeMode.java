package model;

import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class WorldTimeMode extends Mode {

    private ArrayList<WorldTime> worldTimeList;

    public WorldTimeMode() {
        worldTimeList = new ArrayList<WorldTime>();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat newyork = new SimpleDateFormat("HH:mm:ss");
        newyork.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        SimpleDateFormat london = new SimpleDateFormat("HH:mm:ss");
        london.setTimeZone(TimeZone.getTimeZone("Europe/London"));
        SimpleDateFormat paris = new SimpleDateFormat("HH:mm:ss");
        paris.setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        SimpleDateFormat rome = new SimpleDateFormat("HH:mm:ss");
        rome.setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        SimpleDateFormat shanghai = new SimpleDateFormat("HH:mm:ss");
        shanghai.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleDateFormat tokyo = new SimpleDateFormat("HH:mm:ss");
        tokyo.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));

        worldTimeList.add(new WorldTime("[GMT-5][New _ork] " + newyork.format(date)));
        worldTimeList.add(new WorldTime("[GMT+0][London] " + london.format(date)));
        worldTimeList.add(new WorldTime("[GMT+2][Paris] " + paris.format(date)));
        worldTimeList.add(new WorldTime("[GMT+2][Rome] " + rome.format(date)));
        worldTimeList.add(new WorldTime("[GMT+8][Shanghai] " + shanghai.format(date)));
        worldTimeList.add(new WorldTime("[GMT+9][Tokyo] " + tokyo.format(date)));
    }

    public ArrayList<WorldTime> getValue() {
        return worldTimeList;
    }

}