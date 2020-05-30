package view;

import javax.swing.*;
import java.awt.*;

public class StopWatchPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    MenuPanel menuPanel = new MenuPanel();
    JLabel curTimeLabel = new JLabel();
    JLabel stopWatchLabel = new JLabel();

    public StopWatchPanel() {
        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 40));
        curTimeLabel.setText("10 : 12 : 40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        stopWatchLabel.setFont(new Font("SanSerif", Font.PLAIN, 75));
        stopWatchLabel.setText("00:00:00");
        stopWatchLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, stopWatchLabel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(stopWatchLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }
}
