package view;

import javax.swing.*;
import java.awt.*;

public class ScheduleListPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    ListPanel listPanel;
    MenuPanel menuPanel = new MenuPanel();
    JLabel curTimeLabel = new JLabel();
    JLabel schedule1Label = new JLabel();
    JLabel schedule2Label = new JLabel();
    JLabel schedule3Label = new JLabel();
    JLabel schedule4Label = new JLabel();

    public ScheduleListPanel() {
        listPanel = new ListPanel();

        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 40));
        curTimeLabel.setText("10 : 12 : 40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, listPanel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(listPanel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }

    class ListPanel extends JPanel {
        public ListPanel() {
            this.setLayout(new GridLayout(4, 1));
            this.setBackground(Color.WHITE);

            schedule1Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            schedule1Label.setText("ETC 05.25 MON 13:30:20");
            schedule1Label.setBackground(Color.GRAY);
            schedule1Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(schedule1Label);

            schedule2Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            schedule2Label.setText("CLA 05.27 WED 15:20:42");
            schedule2Label.setBackground(Color.WHITE);
            schedule2Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(schedule2Label);

            schedule3Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            schedule3Label.setText("");
            schedule3Label.setBackground(Color.WHITE);
            schedule3Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(schedule3Label);

            schedule4Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            schedule4Label.setText("");
            schedule4Label.setBackground(Color.WHITE);
            schedule4Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(schedule4Label);
        }
    }
}
