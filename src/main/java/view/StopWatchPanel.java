package view;

import model.Info;
import model.Time;

import javax.swing.*;
import java.awt.*;

public class StopWatchPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    JLabel curTimeLabel = new JLabel();
    StopWatchTimePanel stopWatchTimePanel = new StopWatchTimePanel();
    MenuPanel menuPanel = new MenuPanel();

    public StopWatchPanel() {
        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(GUI.font40);
        curTimeLabel.setText("10:12:40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, stopWatchTimePanel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(stopWatchTimePanel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }

    class StopWatchTimePanel extends JPanel {
        JLabel hourLabel = new JLabel();
        JLabel minuteLabel = new JLabel();
        JLabel secondLabel = new JLabel();
        JLabel colon1 = new JLabel();
        JLabel colon2 = new JLabel();

        public StopWatchTimePanel() {
            this.setLayout(new FlowLayout(FlowLayout.CENTER));
            this.setBackground(Color.WHITE);

            hourLabel.setFont(GUI.font75);
            hourLabel.setText("00");
            hourLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(hourLabel);

            colon1.setFont(GUI.font75);
            colon1.setText(":");
            colon1.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon1);

            minuteLabel.setFont(GUI.font75);
            minuteLabel.setText("00");
            minuteLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(minuteLabel);

            colon2.setFont(GUI.font75);
            colon2.setText(":");
            colon2.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(colon2);

            secondLabel.setFont(GUI.font75);
            secondLabel.setText("00");
            secondLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(secondLabel);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        Time curTime = (Time) objects[0];
        Time curStopTime = (Time) objects[1];

        curTimeLabel.setText(String.format("%02d", curTime.hour) + ":" + String.format("%02d", curTime.minute) + ":" + String.format("%02d", curTime.second));

        stopWatchTimePanel.hourLabel.setText(String.format("%02d", curStopTime.hour));
        stopWatchTimePanel.minuteLabel.setText(String.format("%02d", curStopTime.minute));
        stopWatchTimePanel.secondLabel.setText(String.format("%02d", curStopTime.second));

        menuPanel.setDisplay(2, enableMode);
    }
}
