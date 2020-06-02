package view;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {
    String[] imgPaths = new String[6];
    ImageIcon[] icons = new ImageIcon[6];
    JLabel[] menuImgs = new JLabel[4];

    public MenuPanel() {
        this.setBackground(Color.WHITE);
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(10, 20, 0, 0));

        imgPaths[0] = "./resource/timekeeping1.png";
        imgPaths[1] = "./resource/timer1.png";
        imgPaths[2] = "./resource/stopwatch1.png";
        imgPaths[3] = "./resource/alarm1.png";
        imgPaths[4] = "./resource/worldtime1.png";
        imgPaths[5] = "./resource/schedule1.png";

        for (int i = 0; i < 6; i++) {
            ImageIcon originIcon = new ImageIcon(imgPaths[i]);
            Image originImg = originIcon.getImage();
            Image changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icons[i] = new ImageIcon(changedImg);
        }


        for (int i = 0; i < 4; i++) {
            menuImgs[i] = new JLabel();
            this.add(menuImgs[i]);
        }

        menuImgs[0].setIcon(icons[0]);
        menuImgs[1].setIcon(icons[1]);
        menuImgs[2].setIcon(icons[2]);
        menuImgs[3].setIcon(icons[3]);
    }
}
