package model;

import java.util.*;

/**
 * 
 */
public class AlarmMode extends Mode {
    private ArrayList<Alarm> alarmList;

    public AlarmMode() {
        alarmList = new ArrayList<Alarm>();
    }

    public boolean isFull(){
        if(alarmList.size()==4) return true;
        return false;
    }

    public ArrayList<Alarm> getList() {
        return alarmList;
    }

    public Alarm getValue(int index) {
        if(index==-1){
            return new Alarm();
        }
        return alarmList.get(index);
    }

    public Alarm saveValue(int index, Time time) {
        if(index==-1){ //add alarm
            Alarm temp = new Alarm();
            temp.alarmTime = time;
            alarmList.add(temp);
            sortAlarmList();

            return temp;
        }else { //modify alarm
            alarmList.get(index).alarmTime = time;
            sortAlarmList();

            return alarmList.get(index);
        }
    }

    public boolean deleteValue(int index) {
        if(alarmList.size()==0){
            return false;
        }
        alarmList.remove(index);

        return true;
    }

    public boolean toggleAlarm(int index) {
        if(alarmList.size()==0){
            return false;
        }
        alarmList.get(index).enable = !alarmList.get(index).enable;

        return true;
    }

    private void sortAlarmList(){
        Collections.sort(alarmList, new Sorting());
    }

    class Sorting implements Comparator<Alarm> {
        @Override
        public int compare(Alarm o1, Alarm o2) { //-1 if o1 is before than o2, 1 if o1 is after than o2
            if (o1.alarmTime.hour < o2.alarmTime.hour) {
                return -1;
            }
            else if (o1.alarmTime.hour == o2.alarmTime.hour) {
                if (o1.alarmTime.minute < o2.alarmTime.minute) {
                    return -1;
                }
                else if (o1.alarmTime.minute == o2.alarmTime.minute) {
                    if (o1.alarmTime.second < o2.alarmTime.second) {
                        return -1;
                    }
                    else if (o1.alarmTime.second == o2.alarmTime.second) {
                        return -1;
                    }
                    else {
                        return 1;
                    }
                }
                else {
                    return 1;
                }
            }
            else  {
                return 1;
            }
        }
    }
}