package view;

import testpackage.DWS;
import testpackage.Info;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private int input;
    int mode;
    TimeKeepingPanel timeKeepingPanel = new TimeKeepingPanel();
    TimerPanel timerPanel = new TimerPanel();
    StopWatchPanel stopWatchPanel = new StopWatchPanel();
    AlarmListPanel alarmListPanel = new AlarmListPanel();
    WorldTimePanel worldTimePanel = new WorldTimePanel();
    ScheduleListPanel scheduleListPanel = new ScheduleListPanel();
    JPanel[] modePanel = new JPanel[6];
    JButton buttonA = new JButton("A");
    JButton buttonB = new JButton("B");
    JButton buttonC = new JButton("C");
    JButton buttonD = new JButton("D");
    JPanel curMode;

    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public GUI() {
        this.setTitle("Schedule DWS");
        this.setBounds(0, 0, 616, 439);
        this.setVisible(true);

        DWS sys = new DWS();

        int object;
        int displayNum;
        boolean pressed = false;

        System.out.println("please...");

        // Set Current Mode
        // 0. timeKeepingPanel
        // 1. timerPanel
        // 2. stopWatchPanel
        // 3. alarmListPanel
        modePanel[0] = timeKeepingPanel;
        modePanel[1] = timerPanel;
        modePanel[2] = stopWatchPanel;
        modePanel[3] = alarmListPanel;
        modePanel[4] = worldTimePanel;
        modePanel[5] = scheduleListPanel;
        curMode = timeKeepingPanel;
        mode = 0;

        // set layout
        this.setLayout(null);

        // gridBagConstraints.fill = GridBagConstraints.BOTH;

        buttonA.setBackground(Color.WHITE);
        buttonB.setBackground(Color.WHITE);
        buttonC.setBackground(Color.WHITE);
        buttonD.setBackground(Color.WHITE);

        buttonA.setFont(new Font("SanSerif", Font.PLAIN, 30));
        buttonB.setFont(new Font("SanSerif", Font.PLAIN, 30));
        buttonC.setFont(new Font("SanSerif", Font.PLAIN, 30));
        buttonD.setFont(new Font("SanSerif", Font.PLAIN, 30));

        buttonA.setBounds(0, 0, 100, 100);
        buttonB.setBounds(500, 0, 100, 100);
        buttonC.setBounds(0, 300, 100, 100);
        buttonD.setBounds(500, 300, 100, 100);

        Container container = this.getContentPane();
        container.add(buttonA);
        container.add(buttonB);
        container.add(buttonC);
        container.add(buttonD);

        for (int i = 0; i < 6; i++) {
            if (curMode == modePanel[i]) {
                modePanel[i].setVisible(true);
            } else {
                modePanel[i].setVisible(false);
            }
            modePanel[i].setBorder(new LineBorder(Color.BLACK, 2));
            modePanel[i].setBounds(100, 0, 400, 400);
            container.add(modePanel[i]);
        }

        // Set Button Listener
        buttonA.addActionListener(new ButtonAListener());
        buttonB.addActionListener(new ButtonBListener());
        buttonC.addActionListener(new ButtonCListener());
        buttonD.addActionListener(new ButtonDListener());

        // set exit button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public int getInput() {
        return input;
    }

    public void setInput(int input) {
        this.input = input;
    }

    public static void setComponentLayout(GridBagLayout gridBagLayout, GridBagConstraints gridBagConstraints, JComponent component, int x, int y, int weight, int height, double weightx, double weighty) {
        gridBagConstraints.gridx = x;
        gridBagConstraints.gridy = y;
        gridBagConstraints.gridwidth = weight;
        gridBagConstraints.gridheight = height;
        gridBagConstraints.weightx = weightx;
        gridBagConstraints.weighty = weighty;

        gridBagLayout.setConstraints(component, gridBagConstraints);
    }

    public void display(int mode, Object[] objects) {
        // display
//        System.out.println("Display is Called with mode: " + mode + ", object: " + object.toString());
    }

    public void changePanel() {
        curMode.setVisible(false);
        if (mode == 5) {
            this.mode = 0;
        } else {
            this.mode++;
        }
        curMode = modePanel[this.mode];
        curMode.setVisible(true);
    }

    class ButtonAListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonA) {
                System.out.println("Button A is clicked!");
            }
        }
    }

    class ButtonBListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonB) {
                System.out.println("Button B is clicked!");
            }
        }
    }

    class ButtonCListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonC) {
                System.out.println("Button C is clicked!");
            }
        }
    }

    class ButtonDListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buttonD) {
                System.out.println("Button D is clicked!");
                changePanel();
            }
        }
    }
}
