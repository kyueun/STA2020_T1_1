package view;

import javax.swing.*;
import java.awt.*;

public class TimerPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    MenuPanel menuPanel = new MenuPanel();
    JLabel curTimeLabel = new JLabel();
    JLabel timerLabel = new JLabel();

    public TimerPanel() {
        this.setLayout(gridBagLayout);
        this.setBackground(Color.WHITE);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        curTimeLabel.setFont(new Font("SanSerif", Font.PLAIN, 40));
        curTimeLabel.setText("10 : 12 : 40");
        curTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, curTimeLabel, 0, 0, 1, 1, 0.1, 0.1);
        this.add(curTimeLabel);

        timerLabel.setFont(new Font("SanSerif", Font.PLAIN, 75));
        timerLabel.setText("03:07:31");
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, timerLabel, 0, 1, 1, 2, 0.1, 0.2);
        this.add(timerLabel);

        GUI.setComponentLayout(this.gridBagLayout, this.gridBagConstraints, menuPanel, 0, 3, 1, 1, 0.1, 0.1);
        this.add(menuPanel);
    }
}
