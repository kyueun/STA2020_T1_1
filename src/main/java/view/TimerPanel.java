package view;

import model.Info;
import model.Time;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    JLabel curTimeLabel = new JLabel();
    TimerTimePanel timerTimePanel = new TimerTimePanel();
    MenuPanel menuPanel = new MenuPanel();

    public TimerPanel() {
        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(GUI.font40);
        curTimeLabel.setText("10:12:40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, timerTimePanel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(timerTimePanel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }

    class TimerTimePanel extends JPanel {
        JLabel hourLabel = new JLabel();
        JLabel minuteLabel = new JLabel();
        JLabel secondLabel = new JLabel();
        JLabel colon1 = new JLabel();
        JLabel colon2 = new JLabel();

        public TimerTimePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.WHITE);

            hourLabel.setFont(GUI.font75);
            hourLabel.setText("03");
            hourLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(hourLabel);

            colon1.setFont(GUI.font75);
            colon1.setText(":");
            colon1.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon1);

            minuteLabel.setFont(GUI.font75);
            minuteLabel.setText("07");
            minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(minuteLabel);

            colon2.setFont(GUI.font75);
            colon2.setText(":");
            colon2.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon2);

            secondLabel.setFont(GUI.font75);
            secondLabel.setText("31");
            secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(secondLabel);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        Time curTime = (Time) objects[0];
        System.out.println("timer: " + objects[1]);
        Time curTimer = (Time) objects[1];
        int pointer = (int) objects[2];

        JLabel[] labels = new JLabel[3];
        int idx = -1;

        labels[0] = timerTimePanel.hourLabel;
        labels[1] = timerTimePanel.minuteLabel;
        labels[2] = timerTimePanel.secondLabel;

        curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));

        timerTimePanel.hourLabel.setText(String.format("%02d", curTimer.hour));
        timerTimePanel.minuteLabel.setText(String.format("%02d", curTimer.minute));
        timerTimePanel.secondLabel.setText(String.format("%02d", curTimer.second));

        switch (pointer) {
            case Info.TIME_POINTER_NULL: // 0
                System.out.println("GUI: Timer Not Setting Mode");
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
                GUI.deleteUnderline(labels[i], GUI.font75);
            }
        }

        menuPanel.setDisplay(1, enableMode);
    }
}
