package view;

import javax.swing.*;
import java.awt.*;

public class AlarmListPanel extends JPanel {
    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    ListPanel listPanel;
    MenuPanel menuPanel = new MenuPanel();
    JLabel curTimeLabel = new JLabel();
    JLabel alarm1Label = new JLabel();
    JLabel alarm2Label = new JLabel();
    JLabel alarm3Label = new JLabel();
    JLabel alarm4Label = new JLabel();

    public AlarmListPanel() {
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

            alarm1Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            alarm1Label.setText("[OFF] 13:30:20");
            alarm1Label.setBackground(Color.GRAY);
            alarm1Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarm1Label);

            alarm2Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            alarm2Label.setText("[OFF] 13:20:42");
            alarm2Label.setBackground(Color.WHITE);
            alarm2Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarm2Label);

            alarm3Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            alarm3Label.setText("");
            alarm3Label.setBackground(Color.WHITE);
            alarm3Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarm3Label);

            alarm4Label.setFont(new Font("SanSerif", Font.PLAIN, 30));
            alarm4Label.setText("");
            alarm4Label.setBackground(Color.WHITE);
            alarm4Label.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(alarm4Label);
        }
    }
}
