package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    String[][] imgPaths = new String[6][2];
    ImageIcon[][] icons = new ImageIcon[6][2];
    JLabel[] menuImgs = new JLabel[4];

    public MenuPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));

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
                Image changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                icons[i][j] = new ImageIcon(changedImg);
            }
        }


        for (int i = 0; i < 4; i++) {
            menuImgs[i] = new JLabel();
            this.add(menuImgs[i]);
        }

        menuImgs[0].setIcon(icons[0][1]);
        menuImgs[1].setIcon(icons[0][0]);
        menuImgs[2].setIcon(icons[0][0]);
        menuImgs[3].setIcon(icons[0][0]);
    }

    public void setDisplay(int curMode, boolean[] modeState) { // curMode: timeKeeping(0), ...
        int btnIdx = 0;
        for (int i = 0; i < 6; i++) {
            if (modeState[i]) {
                if (curMode == i) {
                    menuImgs[btnIdx].setIcon(icons[i][1]);
                } else {
                    menuImgs[btnIdx].setIcon(icons[i][0]);
                }
                btnIdx++;
            }
        }
    }
}
