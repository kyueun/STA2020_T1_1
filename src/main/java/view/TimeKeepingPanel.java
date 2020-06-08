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

        curScheduleLabel.setFont(new Font("SanSerif", Font.PLAIN, 30));
        curScheduleLabel.setText("");
        curScheduleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curScheduleLabel);

        curTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 75));
        curTimeLabel.setText("Loading...");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curTimeLabel);

        curDateLabel.setFont(new Font("SanSerif", Font.PLAIN, 50));
        curDateLabel.setText("");
        curDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curDateLabel);

        this.add(menuPanel);
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        if (objects == null) {
            System.out.println("GUI: objects is null");
            this.setVisible(false);
        } else {
            Time curTime = (Time) objects[1];

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

                curScheduleLabel.setText(scheduleType + " " + String.format("%02d", recentSchedule.scheduleTime.month) + "." + String.format("%02d", recentSchedule.scheduleTime.day) + " " + String.format("%02d", recentSchedule.scheduleTime.hour) + ":" + String.format("%02d", recentSchedule.scheduleTime.minute));
            } else {
                curScheduleLabel.setText("No Schedule");
            }
            curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));
            curDateLabel.setText(String.format("%04d", curTime.year) + "." + String.format("%02d", curTime.month) + "." + String.format("%02d", curTime.day));

            menuPanel.setDisplay(0, enableMode);
        }
    }


}
