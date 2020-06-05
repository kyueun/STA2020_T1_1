package model;

public class Alarm {
    public Time alarmTime;
    public boolean enable;

    public Alarm() {
        alarmTime = new Time();
        enable = true;
    }


}