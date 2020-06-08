package model;

import java.text.SimpleDateFormat;
import java.util.*;

public class WorldTimeMode extends Mode {

    private WorldTime[] worldTimeList;
    private SimpleDateFormat[] countries;


    public WorldTimeMode() {
        worldTimeList = new WorldTime[6];
        countries = new SimpleDateFormat[6];
        for(int i=0; i<6; i++) countries[i] = new SimpleDateFormat("HH:mm:ss");
    }

    public WorldTime[] getValue() {
        return worldTimeList;
    }

    public void updateWorldTime() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        countries[0].setTimeZone(TimeZone.getTimeZone("America/New_York"));
        countries[1].setTimeZone(TimeZone.getTimeZone("Europe/London"));
        countries[2].setTimeZone(TimeZone.getTimeZone("Europe/Paris"));
        countries[3].setTimeZone(TimeZone.getTimeZone("Europe/Rome"));
        countries[4].setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        countries[5].setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));

        worldTimeList[0] = new WorldTime("[GMT-5][New York] " + countries[0].format(date));
        worldTimeList[1] = new WorldTime("[GMT+0][London] " + countries[1].format(date));
        worldTimeList[2] = new WorldTime("[GMT+2][Paris] " + countries[2].format(date));
        worldTimeList[3] = new WorldTime("[GMT+2][Rome] " + countries[3].format(date));
        worldTimeList[4] = new WorldTime("[GMT+8][Shanghai] " + countries[4].format(date));
        worldTimeList[5] = new WorldTime("[GMT+9][Tokyo] " + countries[5].format(date));

    }

}