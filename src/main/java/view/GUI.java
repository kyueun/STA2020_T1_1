package view;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    TimeKeepingPanel timeKeepingPanel = new TimeKeepingPanel();
    JButton buttonA = new JButton("A");
    JButton buttonB = new JButton("B");
    JButton buttonC = new JButton("C");
    JButton buttonD = new JButton("D");

    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();


    public GUI() {
        this.setTitle("Schedule DWS");
        this.setBounds(100, 100, 600, 400);
        this.setVisible(true);

        // set layout
        this.setLayout(gridBagLayout);

        gridBagConstraints.fill = GridBagConstraints.BOTH;

        buttonA.setBackground(Color.WHITE);
        buttonB.setBackground(Color.WHITE);
        buttonC.setBackground(Color.WHITE);
        buttonD.setBackground(Color.WHITE);

        setComponentLayout(buttonA, 0, 0, 1, 1, 0.1, 0.1);
        setComponentLayout(buttonB, 5, 0, 1, 1, 0.1, 0.1);
        setComponentLayout(buttonC, 0, 3, 1, 1, 0.1, 0.1);
        setComponentLayout(buttonD, 5, 3, 1, 1, 0.1, 0.1);

        JPanel tempPanel1 = new JPanel();
        tempPanel1.setBackground(Color.WHITE);

        JPanel tempPanel2 = new JPanel();
        tempPanel2.setBackground(Color.WHITE);

        setComponentLayout(tempPanel1, 0, 1, 1, 2, 0.1, 0.2);
        setComponentLayout(tempPanel2, 5, 1, 1, 2, 0.1, 0.2);
        setComponentLayout(timeKeepingPanel, 1, 0, 4, 4, 0.4, 0.4);

        Container container = this.getContentPane();
        container.add(buttonA);
        container.add(buttonB);
        container.add(buttonC);
        container.add(buttonD);
        container.add(tempPanel1);
        container.add(tempPanel2);
        container.add(timeKeepingPanel);

        // set exit button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void setComponentLayout(JComponent component, int x, int y, int weight, int height, double weightx, double weighty) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = weight;
        gridBagConstraints.gridheight = height;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;

        gridBagLayout.setConstraints(component, gridBagConstraints);
    }
}
