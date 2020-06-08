package model;

import java.util.*;

/**
 * 
 */
public class ScheduleMode extends Mode {

    private ArrayList<Schedule> scheduleList;

    public ScheduleMode() {
        scheduleList = new ArrayList<Schedule>();
    }

    public boolean isFull(){
        if(scheduleList.size()==4) return true;
        return false;
    }

    public ArrayList<Schedule> getList() {
        return scheduleList;
    }

    public Schedule getValue(int index) {
        if(index==-1){
            return new Schedule();
        }
        return scheduleList.get(index);
    }

    public Schedule saveValue(int index, Time time) {
        if(index==-1){ //add Schedule
            Schedule temp = new Schedule();
            temp.scheduleTime = time;
            temp.scheduleType = time.second;
            scheduleList.add(temp);
            sortScheduleList();

            return temp;
        }else { //modify alarm
            scheduleList.get(index).scheduleTime = time;
            sortScheduleList();

            return scheduleList.get(index);
        }
    }

    public boolean deleteValue(int index) {
        if(scheduleList.size()==0){
            return false;
        }
        scheduleList.remove(index);

        return true;
    }

    public boolean isAvailAdd(Time curTime, Schedule schedule) {
        Schedule time = new Schedule();
        time.scheduleTime = curTime;
        time.scheduleType = schedule.scheduleTime.second; // create same type temp Schedule

        ArrayList temp = new ArrayList<Schedule>();
        temp.add(schedule);
        temp.add(time);

        Collections.sort(temp, new Sorting());

        if(temp.get(0).equals(time)){ // check schedule time
            return true;
        }else{
            return false;
        }
    }

    private void sortScheduleList(){
        Collections.sort(scheduleList, new Sorting());
    }

    class Sorting implements Comparator<Schedule> {
        @Override
        public int compare(Schedule o1, Schedule o2) { //-1 if o1 is before than o2, 1 if o1 is after than o2
            if (o1.scheduleTime.month < o2.scheduleTime.month) {
                return -1;
            }
            else if (o1.scheduleTime.month == o2.scheduleTime.month) {
                if (o1.scheduleTime.day < o2.scheduleTime.day) {
                    return -1;
                }
                else if (o1.scheduleTime.day == o2.scheduleTime.day) {
                    if (o1.scheduleTime.hour < o2.scheduleTime.hour) {
                        return -1;
                    }
                    else if (o1.scheduleTime.hour == o2.scheduleTime.hour) {
                        if(o1.scheduleTime.minute < o2.scheduleTime.minute) {
                            return -1;
                        }
                        else if(o1.scheduleTime.minute == o2.scheduleTime.minute) {
                            if(o1.scheduleType < o1.scheduleType) {
                                return -1;
                            }
                            else if(o1.scheduleType == o1.scheduleType) {
                                return 1;
                            }
                            else {
                                return 1;
                            }
                        }
                        else {
                            return 1;
                        }
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