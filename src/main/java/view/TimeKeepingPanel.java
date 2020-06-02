package view;

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
        curScheduleLabel.setText("ETC 05.25 MON 13:30:20");
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
}
