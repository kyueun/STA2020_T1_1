package view;

import model.Info;

import javax.swing.*;
import java.awt.*;

public class SelectMenuPanel extends JPanel {
    JLabel timeKeepingModeLabel = new JLabel();
    JLabel timerModeLabel = new JLabel();
    JLabel stopWatchModeLabel = new JLabel();
    JLabel alarmModeLabel = new JLabel();
    JLabel scheduleModeLabel = new JLabel();
    JLabel worldTimeModeLabel = new JLabel();
    String[][] imgPaths = new String[6][2];
    ImageIcon[][] icons = new ImageIcon[6][2];
    JLabel[] menuModelabels = {timeKeepingModeLabel, timerModeLabel, stopWatchModeLabel, alarmModeLabel, worldTimeModeLabel, scheduleModeLabel};

    public SelectMenuPanel() {
        this.setLayout(new GridLayout(6, 1));
        this.setBackground(Color.WHITE);

        imgPaths[0][0] = "./resource/timekeeping0.png";
        imgPaths[0][1] = "./resource/timekeeping1.png";

        imgPaths[1][0] = "./resource/timer0.png";
        imgPaths[1][1] = "./resource/timer1.png";

        imgPaths[2][0] = "./resource/stopwatch0.png";
        imgPaths[2][1] = "./resource/stopwatch1.png";

        imgPaths[3][0] = "./resource/alarm0.png";
        imgPaths[3][1] = "./resource/alarm1.png";

        imgPaths[4][0] = "./resource/worldtime0.png";
        imgPaths[4][1] = "./resource/worldtime1.png";

        imgPaths[5][0] = "./resource/schedule0.png";
        imgPaths[5][1] = "./resource/schedule1.png";

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 2; j++) {
                ImageIcon originIcon = new ImageIcon(imgPaths[i][j]);
                Image originImg = originIcon.getImage();
                Image changedImg = originImg.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
                icons[i][j] = new ImageIcon(changedImg);
            }

            menuModelabels[i].setFont(new Font("SanSerif", Font.PLAIN, 25));
            menuModelabels[i].setIcon(icons[i][0]);
            menuModelabels[i].setOpaque(true);
            menuModelabels[i].setBackground(Color.WHITE);
            menuModelabels[i].setHorizontalAlignment(SwingConstants.LEFT);

            this.add(menuModelabels[i]);
        }

        menuModelabels[1].setBackground(Color.BLACK);

        menuModelabels[0].setText("Time Keeping");
        menuModelabels[1].setText("Timer");
        menuModelabels[2].setText("Stop Watch");
        menuModelabels[3].setText("Alarm");
        menuModelabels[4].setText("World Time");
        menuModelabels[5].setText("Schedule");
    }

    public void setDisplay(Object[] objects) {
        boolean[] enableMode = (boolean[]) objects[0];
        int pointer = (int) objects[1];

        System.out.println("GUI: pointer " + pointer);

        menuModelabels[0].setIcon(icons[0][1]);
        menuModelabels[0].setBackground(Color.WHITE);

        for (int i = 1; i < 6; i++) {
            if (enableMode[i]) {
                menuModelabels[i].setIcon(icons[i][1]);
            } else {
                menuModelabels[i].setIcon(icons[i][0]);
            }
        }

        // check pointer
        for (int i = 0; i < 6; i++) {
            if (i == pointer) {
                menuModelabels[i].setBackground(Color.LIGHT_GRAY);
            } else {
                menuModelabels[i].setBackground(Color.WHITE);
            }
        }
    }
}
