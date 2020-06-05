package view;

import model.Alarm;
import model.Schedule;
import model.Time;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScheduleListPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    ListPanel listPanel;
    MenuPanel menuPanel = new MenuPanel();
    JLabel curTimeLabel = new JLabel();
    JLabel schedule1Label = new JLabel();
    JLabel schedule2Label = new JLabel();
    JLabel schedule3Label = new JLabel();
    JLabel schedule4Label = new JLabel();
    JLabel[] scheduleLabels = {schedule1Label, schedule2Label, schedule3Label, schedule4Label};

    public ScheduleListPanel() {
        listPanel = new ListPanel();

        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 40));
        curTimeLabel.setText("10 : 12 : 40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, listPanel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(listPanel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }

    class ListPanel extends JPanel {
        public ListPanel() {
            this.setLayout(new GridLayout(4, 1));
            this.setBackground(Color.WHITE);

            scheduleLabels[0].setFont(new Font("SanSerif", Font.PLAIN, 30));
            scheduleLabels[0].setText("ETC 05.25 MON 13:30:20");
            scheduleLabels[0].setBackground(Color.GRAY);
            scheduleLabels[0].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(scheduleLabels[0]);

            scheduleLabels[1].setFont(new Font("SanSerif", Font.PLAIN, 30));
            scheduleLabels[1].setText("CLA 05.27 WED 15:20:42");
            scheduleLabels[1].setBackground(Color.WHITE);
            scheduleLabels[1].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(scheduleLabels[1]);

            scheduleLabels[2].setFont(new Font("SanSerif", Font.PLAIN, 30));
            scheduleLabels[2].setText("");
            scheduleLabels[2].setBackground(Color.WHITE);
            scheduleLabels[2].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(scheduleLabels[2]);

            scheduleLabels[3].setFont(new Font("SanSerif", Font.PLAIN, 30));
            scheduleLabels[3].setText("");
            scheduleLabels[3].setBackground(Color.WHITE);
            scheduleLabels[3].setHorizontalAlignment(SwingConstants.CENTER);
            this.add(scheduleLabels[3]);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        Time curTime = (Time) objects[0];
        ArrayList<Schedule> scheduleList = null;
        if (objects[1] != null) {
            scheduleList = (ArrayList<Schedule>) objects[1];
        }
        int pointer = (int) objects[2];

        curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));

        if ((scheduleList != null) && (scheduleList.size() != 0)) {
            for (int i = 0; i < 4; i++) {
                Schedule schedule = scheduleList.get(i);

                if (schedule != null) {
                    String scheduleType;

                    switch (schedule.scheduleType) {
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

                    scheduleLabels[i].setText(scheduleType + " " + schedule.scheduleTime.month + "." + schedule.scheduleTime.day + " " + schedule.getDayofWeek() + " " + schedule.scheduleTime.hour + ":" + schedule.scheduleTime.minute);
                } else {
                    scheduleLabels[i].setText("");
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                scheduleLabels[i].setText("");
            }
        }

        menuPanel.setDisplay(5, enableMode);
    }
}