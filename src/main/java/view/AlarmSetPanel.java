package view;

import model.Info;
import model.Time;

import javax.swing.*;
import java.awt.*;

public class AlarmSetPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    JLabel curTimeLabel = new JLabel();
    AlarmTimePanel alarmTimePanel = new AlarmTimePanel();
    MenuPanel menuPanel = new MenuPanel();

    public AlarmSetPanel() {
        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 40));
        curTimeLabel.setText("10:12:40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, alarmTimePanel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(alarmTimePanel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }

    class AlarmTimePanel extends JPanel {
        JLabel hourLabel = new JLabel();
        JLabel minuteLabel = new JLabel();
        JLabel secondLabel = new JLabel();
        JLabel colon1 = new JLabel();
        JLabel colon2 = new JLabel();

        public AlarmTimePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.WHITE);

            hourLabel.setFont(new Font("SanSerif", Font.PLAIN, 75));
            hourLabel.setText("03");
            hourLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(hourLabel);

            colon1.setFont(new Font("SanSerif", Font.PLAIN, 75));
            colon1.setText(":");
            colon1.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon1);

            minuteLabel.setFont(new Font("SanSerif", Font.PLAIN, 75));
            minuteLabel.setText("07");
            minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(minuteLabel);

            colon2.setFont(new Font("SanSerif", Font.PLAIN, 75));
            colon2.setText(":");
            colon2.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon2);

            secondLabel.setFont(new Font("SanSerif", Font.PLAIN, 75));
            secondLabel.setText("31");
            secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(secondLabel);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        Time curTime = (Time) objects[0];
        Time curAlarmTime = (Time) objects[1];
       // System.out.println(curAlarmTime.day);
        int pointer = (int) objects[2];

        JLabel[] labels = new JLabel[3];
        int idx = -1;

        labels[0] = alarmTimePanel.hourLabel;
        labels[1] = alarmTimePanel.minuteLabel;
        labels[2] = alarmTimePanel.secondLabel;

        curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));

        System.out.println("alarm: " + curAlarmTime);
        System.out.println("alarm: " + curAlarmTime.minute);
        alarmTimePanel.hourLabel.setText(String.format("%02d", curAlarmTime.hour));
        alarmTimePanel.minuteLabel.setText(String.format("%02d", curAlarmTime.minute));
        alarmTimePanel.secondLabel.setText(String.format("%02d", curAlarmTime.second));

        switch (pointer) {
            case Info.TIME_POINTER_NULL: // 0
                System.out.println("GUI: Pointer Error! (Null)");
                break;
            case Info.TIME_POINTER_HOUR: // 1
                idx = 0;
                break;
            case Info.TIME_POINTER_MINUTE: // 2
                idx = 1;
                break;
            case Info.TIME_POINTER_SECOND: // 3
                idx = 2;
                break;
            default:
                System.out.println("GUI: Pointer Error! (Default)");
                break;
        }

        // check pointer
        for (int i = 0; i < 3; i++) {
            if (i == idx) {
                GUI.underline(labels[i]);
            } else {
                GUI.deleteUnderline(labels[i], new Font("SanSerif", Font.PLAIN, 75));
            }
        }

        menuPanel.setDisplay(3, enableMode);
    }
}
