package view;

import model.Info;
import model.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.Map;

public class TimeKeepingSetPanel extends JPanel {
    TimePanel timePanel = new TimePanel();
    DatePanel datePanel = new DatePanel();
    MenuPanel menuPanel = new MenuPanel();

    public TimeKeepingSetPanel() {
        JPanel blankPanel = new JPanel();
        this.setLayout(new GridLayout(4, 1));
        this.setBackground(Color.WHITE);

        blankPanel.setBackground(Color.WHITE);
        this.add(blankPanel);

        this.add(timePanel);

        this.add(datePanel);

        this.add(menuPanel);
    }

    class TimePanel extends JPanel {
        JLabel hourLabel = new JLabel();
        JLabel minuteLabel = new JLabel();
        JLabel secondLabel = new JLabel();
        JLabel colon1 = new JLabel();
        JLabel colon2 = new JLabel();

        public TimePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.WHITE);

            hourLabel.setFont(GUI.font75);
            hourLabel.setText("10");
            hourLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(hourLabel);

            colon1.setFont(GUI.font75);
            colon1.setText(":");
            colon1.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon1);

            minuteLabel.setFont(GUI.font75);
            minuteLabel.setText("12");
            minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(minuteLabel);

            colon2.setFont(GUI.font75);
            colon2.setText(":");
            colon2.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon2);

            secondLabel.setFont(GUI.font75);
            secondLabel.setText("40");
            secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(secondLabel);
        }
    }

    class DatePanel extends JPanel {
        JLabel yearLabel = new JLabel();
        JLabel monthLabel = new JLabel();
        JLabel dayLabel = new JLabel();
        JLabel dot1 = new JLabel();
        JLabel dot2 = new JLabel();

        public DatePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.WHITE);

            yearLabel.setFont(GUI.font50);
            yearLabel.setText("2020");
            yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(yearLabel);

            dot1.setFont(GUI.font50);
            dot1.setText(".");
            dot1.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(dot1);

            monthLabel.setFont(GUI.font50);
            monthLabel.setText("01");
            monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(monthLabel);

            dot2.setFont(GUI.font50);
            dot2.setText(".");
            dot2.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(dot2);

            dayLabel.setFont(GUI.font50);
            dayLabel.setText("01");
            dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(dayLabel);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        Time curTime = (Time) objects[0];
        int pointer = (int) objects[1];

        JLabel[] labels = new JLabel[6];
        int idx = -1;

        labels[0] = timePanel.hourLabel;
        labels[1] = timePanel.minuteLabel;
        labels[2] = timePanel.secondLabel;
        labels[3] = datePanel.yearLabel;
        labels[4] = datePanel.monthLabel;
        labels[5] = datePanel.dayLabel;

        timePanel.hourLabel.setText(String.format("%02d", curTime.hour));
        timePanel.minuteLabel.setText(String.format("%02d", curTime.minute));
        timePanel.secondLabel.setText(String.format("%02d", curTime.second));

        datePanel.yearLabel.setText(String.format("%04d", curTime.year));
        datePanel.monthLabel.setText(String.format("%02d", curTime.month));
        datePanel.dayLabel.setText(String.format("%02d", curTime.day));

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
            case Info.TIME_POINTER_YEAR: // 4
                idx = 3;
                break;
            case Info.TIME_POINTER_MONTH: // 5
                idx = 4;
                break;
            case Info.TIME_POINTER_DAY: // 6
                idx = 5;
                break;
            default:
                System.out.println("GUI: Pointer Error! (Default)");
                break;
        }

        // check pointer
        for (int i = 0; i < 6; i++) {
            if (i == idx) {
                GUI.underline(labels[i]);
            } else {
                if ((i >= 0) && (i <= 2)) {
                    GUI.deleteUnderline(labels[i], GUI.font75);
                } else {
                    GUI.deleteUnderline(labels[i], GUI.font50);
                }
            }
        }

        menuPanel.setDisplay(0, enableMode);
    }
}
