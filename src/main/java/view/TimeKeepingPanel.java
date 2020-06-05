package view;

import model.Schedule;
import model.Time;

import javax.swing.*;
import java.awt.*;

public class TimeKeepingPanel extends JPanel {
    MenuPanel menuPanel = new MenuPanel();
    JLabel curScheduleLabel = new JLabel();
    JLabel curTimeLabel = new JLabel();
    JLabel curDateLabel = new JLabel();

    public TimeKeepingPanel() {
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(Color.WHITE);

        curScheduleLabel.setFont(new Font("SanSerif", Font.PLAIN, 25));
        curScheduleLabel.setText("ETC 05.25 MON 13:30");
        curScheduleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curScheduleLabel);

        curTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 75));
        curTimeLabel.setText("10:12:40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curTimeLabel);

        curDateLabel.setFont(new Font("SanSerif", Font.PLAIN, 50));
        curDateLabel.setText("2020.01.01");
        curDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curDateLabel);

        this.add(menuPanel);
    }

    public void setDisplay(Object[] objects) {
        if (objects == null) {
            System.out.println("GUI: objects is null");
        } else {
            Time curTime = (Time) objects[1];
            boolean[] enableMode = (boolean[]) objects[2];

            if (objects[0] != null) {
                Schedule recentSchedule = (Schedule) objects[0];
                String scheduleType;

                switch (recentSchedule.scheduleType) {
                    case 0:
                        scheduleType = "CLA";
                        break;
                    case 1:
                        scheduleType = "MEE";
                        break;
                    case 2:
                        scheduleType = "EVE";
                        break;
                    case 3:
                        scheduleType = "ASL";
                        break;
                    case 4:
                        scheduleType = "ETC";
                        break;
                    default:
                        scheduleType = "ERR";
                        System.out.println("GUI: Schedule Type Error!");
                        break;
                }

                curScheduleLabel.setText(scheduleType + " " + recentSchedule.scheduleTime.month + "." + recentSchedule.scheduleTime.day + " " + recentSchedule.getDayofWeek() + " " + recentSchedule.scheduleTime.hour + ":" + recentSchedule.scheduleTime.minute);
            } else {
                curScheduleLabel.setText("No Schedule");
            }
            curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));
            curDateLabel.setText(String.format("%04d", curTime.year) + "." + String.format("%02d", curTime.month) + "." + String.format("%02d", curTime.day));

            menuPanel.setDisplay(3, enableMode);
        }
    }


}
