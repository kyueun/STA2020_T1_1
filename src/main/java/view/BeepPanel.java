package view;

import model.Info;

import javax.swing.*;
import java.awt.*;

public class BeepPanel extends JPanel {
    int type;
    String imgPaths1 = "./resource/alarm1.png";
    String imgPaths2 = "./resource/timer1.png";
    ImageIcon icons1 = new ImageIcon();
    ImageIcon icons2 = new ImageIcon();
    JLabel iconLabel = new JLabel();
    JLabel typeLabel = new JLabel();

    public BeepPanel(int type) {
        this.type = type;

        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.LIGHT_GRAY);

        this.add(iconLabel);

        typeLabel.setFont(GUI.font75);
        typeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(typeLabel);

        if (type == Info.BEEP_TYPE_ALARM) { // alarm beep
            ImageIcon originIcon = new ImageIcon(imgPaths1);
            Image originImg = originIcon.getImage();
            Image changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icons1 = new ImageIcon(changedImg);
            iconLabel.setIcon(icons1);
            typeLabel.setText("Alarm");
        } else if (type == Info.BEEP_TYPE_TIMER) { // timer beep
            ImageIcon originIcon = new ImageIcon(imgPaths2);
            Image originImg = originIcon.getImage();
            Image changedImg = originImg.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
            icons2 = new ImageIcon(changedImg);
            iconLabel.setIcon(icons2);
            typeLabel.setText("Timer");
        } else {
            System.out.println("GUI: Beep Type Error!");
        }
    }
}
