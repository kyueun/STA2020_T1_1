package view;

import model.WorldTime;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WorldTimePanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    ListPanel listPanel;
    MenuPanel menuPanel = new MenuPanel();
    JLabel newYorkTimeLabel = new JLabel();
    JLabel londonTimeLabel = new JLabel();
    JLabel parisTimeLabel = new JLabel();
    JLabel romeTimeLabel = new JLabel();
    JLabel beijingTimeLabel = new JLabel();
    JLabel tokyoTimeLabel = new JLabel();

    public WorldTimePanel() {
        listPanel = new ListPanel();

        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, listPanel, 0, 0, 1, 3, 0.1, 0.3);
        this.add(listPanel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }

    class ListPanel extends JPanel {
        public ListPanel() {
            this.setLayout(new GridLayout(6, 1));
            this.setBackground(Color.WHITE);

            newYorkTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 25));
            newYorkTimeLabel.setText("[GMT-5][New York] 13:30:30");
            newYorkTimeLabel.setBackground(Color.WHITE);
            newYorkTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(newYorkTimeLabel);

            londonTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 25));
            londonTimeLabel.setText("[GMT+0][London] 03:51:30");
            londonTimeLabel.setBackground(Color.WHITE);
            londonTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(londonTimeLabel);

            parisTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 25));
            parisTimeLabel.setText("[GMT+2][Paris] 05:51:30");
            parisTimeLabel.setBackground(Color.WHITE);
            parisTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(parisTimeLabel);

            romeTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 25));
            romeTimeLabel.setText("[GMT+2][Rome] 05:51:30");
            romeTimeLabel.setBackground(Color.WHITE);
            romeTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(romeTimeLabel);

            beijingTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 25));
            beijingTimeLabel.setText("[GMT+8][Beijing] 11:51:30");
            beijingTimeLabel.setBackground(Color.WHITE);
            beijingTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(beijingTimeLabel);

            tokyoTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 25));
            tokyoTimeLabel.setText("[GMT+9][Tokyo] 12:51:30");
            tokyoTimeLabel.setBackground(Color.WHITE);
            tokyoTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(tokyoTimeLabel);
        }
    }

    public void setDisplay(Object[] objects, boolean[] enableMode) {
        WorldTime[] worldTimeList = (WorldTime[]) objects[0];

        newYorkTimeLabel.setText(worldTimeList[0].cityInfo);
        londonTimeLabel.setText(worldTimeList[1].cityInfo);
        parisTimeLabel.setText(worldTimeList[2].cityInfo);
        romeTimeLabel.setText(worldTimeList[3].cityInfo);
        beijingTimeLabel.setText(worldTimeList[4].cityInfo);
        tokyoTimeLabel.setText(worldTimeList[5].cityInfo);

        menuPanel.setDisplay(4, enableMode);
    }
}
