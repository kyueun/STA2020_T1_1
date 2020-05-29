package view;

import javax.swing.*;
import java.awt.*;

public class TimeKeepingPanel extends JPanel {
    public TimeKeepingPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.add(new JLabel("TimeKeeping"));

        this.setBackground(Color.BLUE);
    }
}
