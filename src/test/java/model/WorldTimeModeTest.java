package model;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.*;

public class WorldTimeModeTest {
    WorldTimeMode worldTimeMode = new WorldTimeMode();

    @Test
    public void makeWorldTimeMode(){
        worldTimeMode.updateWorldTime();
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        SimpleDateFormat tmp = new SimpleDateFormat("HH:mm:ss");

        String[] worldName = {
                "America/New_York",
                "Europe/London",
                "Europe/Paris",
                "Europe/Rome",
                "Asia/Shanghai",
                "Asia/Tokyo"
        };

        for(int i=0; i<6; i++) {
            tmp.setTimeZone(TimeZone.getTimeZone(worldName[i]));
            String worldTimeValue = worldTimeMode.getValue()[i].cityInfo;
            worldTimeValue.substring(worldTimeValue.length() - 8);
            assertEquals(tmp.format(date), worldTimeValue.substring(worldTimeValue.length() - 8));
        }
    }
}
