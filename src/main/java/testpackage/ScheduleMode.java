package testpackage;

import java.util.*;

/**
 * 
 */
public class ScheduleMode extends Mode {

    private ArrayList<Schedule> scheduleList;

    public ScheduleMode() {
        scheduleList = new ArrayList<Schedule>();
    }

    public Schedule getValue(int index) {
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