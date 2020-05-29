package view;

import javax.swing.*;
import java.awt.*;

public class TimeKeepingPanel extends JPanel {
    JLabel curSchedule = new JLabel();
    JLabel curTime = new JLabel();
    JLabel curDate = new JLabel();

    public TimeKeepingPanel() {
        this.setLayout(new GridLayout(4,1));
        this.setBackground(Color.WHITE);

        curSchedule.setFont(new Font("SanSerif", Font.PLAIN, 25));
        curSchedule.setText("ETC 05.25 MON 13:30:20");
        curSchedule.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curSchedule);

        curTime.setFont(new Font("SanSerif", Font.PLAIN, 75));
        curTime.setText("10 : 12 : 40");
        curTime.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curTime);

        curDate.setFont(new Font("SanSerif", Font.PLAIN, 50));
        curDate.setText("2020.01.01");
        curDate.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(curDate);

        this.add(GUI.menuPanel);
    }
}
