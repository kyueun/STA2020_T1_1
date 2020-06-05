package view;

import javax.swing.*;
import java.awt.*;

public class SelectModePanel extends JPanel {
    JLabel timeKeepingModeLabel = new JLabel();
    JLabel timerModeLabel = new JLabel();
    JLabel stopWatchModeLabel = new JLabel();
    JLabel alarmModeLabel = new JLabel();
    JLabel scheduleModeLabel = new JLabel();
    JLabel worldTimeModeLabel = new JLabel();
    String[] imgPaths = new String[6];
    ImageIcon[] icons = new ImageIcon[6];
    JLabel[] menuModelabels = {timeKeepingModeLabel, timerModeLabel, stopWatchModeLabel, alarmModeLabel, worldTimeModeLabel, scheduleModeLabel};

    public SelectModePanel() {
        this.setLayout(new GridLayout(6, 1));
        this.setBackground(Color.WHITE);

        imgPaths[0] = "./resource/timekeeping1.png";
        imgPaths[1] = "./resource/timer1.png";
        imgPaths[2] = "./resource/stopwatch1.png";
        imgPaths[3] = "./resource/alarm1.png";
        imgPaths[4] = "./resource/worldtime1.png";
        imgPaths[5] = "./resource/schedule1.png";

        for (int i = 0; i < 6; i++) {
            ImageIcon originIcon = new ImageIcon(imgPaths[i]);
            Image originImg = originIcon.getImage();
            Image changedImg = originImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            icons[i] = new ImageIcon(changedImg);

            menuModelabels[i].setFont(new Font("SanSerif", Font.PLAIN, 25));
            menuModelabels[i].setIcon(icons[i]);
            menuModelabels[i].setBackground(Color.WHITE);
            menuModelabels[i].setHorizontalAlignment(SwingConstants.CENTER);

            this.add(menuModelabels[i]);
        }

        menuModelabels[0].setText("Time Keeping");
        menuModelabels[1].setText("Timer");
        menuModelabels[2].setText("Stop Watch");
        menuModelabels[3].setText("Alarm");
        menuModelabels[4].setText("World Time");
        menuModelabels[5].setText("Schedule");
    }

    public void setDisplay(Object[] objects) {

    }
}
