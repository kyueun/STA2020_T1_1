package view;

import adapter.MouseCustomAdapter;
import model.Info;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;

public class GUI extends JFrame {
    private static int input;
    int modeX, modeY;
    TimeKeepingPanel timeKeepingPanel = new TimeKeepingPanel();
    TimeKeepingSetPanel timeKeepingSetPanel = new TimeKeepingSetPanel();
    TimerPanel timerPanel = new TimerPanel();
    StopWatchPanel stopWatchPanel = new StopWatchPanel();
    AlarmListPanel alarmListPanel = new AlarmListPanel();
    AlarmSetPanel alarmSetPanel = new AlarmSetPanel();
    WorldTimePanel worldTimePanel = new WorldTimePanel();
    ScheduleListPanel scheduleListPanel = new ScheduleListPanel();
    ScheduleSetPanel scheduleSetPanel = new ScheduleSetPanel();
    SelectMenuPanel selectMenuPanel = new SelectMenuPanel();
    JPanel[][] modePanel = new JPanel[8][2];
    JButton buttonA = new JButton("A");
    JButton buttonB = new JButton("B");
    JButton buttonC = new JButton("C");
    JButton buttonD = new JButton("D");
    JPanel curMode;
    Container container;
    ArrayList<BeepPanel> beepPanels = new ArrayList<BeepPanel>();

    GridBagLayout gridBagLayout = new GridBagLayout();
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public GUI() {
        this.setTitle("Schedule DWS");
        this.setBounds(0, 0, 616, 439);
        this.setVisible(true);

        System.out.println("please...");

        // Set Current Mode
        // 0. timeKeepingPanel
        // 1. timerPanel
        // 2. stopWatchPanel
        // 3. alarmListPanel
        modePanel[0][0] = timeKeepingPanel;
        modePanel[0][1] = timeKeepingSetPanel;

        modePanel[1][0] = timerPanel;

        modePanel[2][0] = stopWatchPanel;

        modePanel[3][0] = alarmListPanel;
        modePanel[3][1] = alarmSetPanel;

        modePanel[4][0] = worldTimePanel;

        modePanel[5][0] = scheduleListPanel;
        modePanel[5][1] = scheduleSetPanel;

        modePanel[6][0] = selectMenuPanel;
        curMode = selectMenuPanel; // fail
        modeX = 0;
        modeY = 0;

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

        container = this.getContentPane();
        container.add(buttonA);
        container.add(buttonB);
        container.add(buttonC);
        container.add(buttonD);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 2; j++) {
                if (modePanel[i][j] != null) {
                    if (curMode == modePanel[i][j]) {
                        modePanel[i][j].setVisible(true);
                        // System.out.println("GUI: " + modePanel[i][j].toString() + " true");
                    } else {

                    }
                    modePanel[i][j].setVisible(false);
                    modePanel[i][j].setBorder(new LineBorder(Color.BLACK, 2));
                    modePanel[i][j].setBounds(100, 0, 400, 400);
                    container.add(modePanel[i][j]);
                }
            }
        }

        // Set Button Listener
        buttonA.addMouseListener(new ButtonAListener());
        buttonB.addMouseListener(new ButtonBListener());
        buttonC.addMouseListener(new ButtonCListener());
        buttonD.addMouseListener(new ButtonDListener());

        // set exit button
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static int getInput() {
        return input;
    }

    public static void setInput(int input) {
        GUI.input = input;
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

    public static void underline(JLabel label) {
        Font font = label.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        label.setFont(font.deriveFont(attributes));
    }

    public static void deleteUnderline(JLabel label, Font font) {
        label.setFont(font);
    }

    public void display(int mode, Object[] objects, Stack<Integer> beepList, boolean[] selected) {
        for (int i = 0; i < beepPanels.size(); i++) {
            beepPanels.get(i).setVisible(false);
            container.remove(beepPanels.get(i));
        }

        switch (mode) {
            case Info.TIMEKEEPING:
                changePanel(0, 0); // timeKeepingPanel
                timeKeepingPanel.setDisplay(objects, selected);
                break;
            case Info.TIMEKEEPINGSET:
                changePanel(0, 1); // tineKeepingSetPanel
                timeKeepingSetPanel.setDisplay(objects, selected);
                break;
            case Info.TIMER:
                changePanel(1, 0);
                timerPanel.setDisplay(objects, selected);
                break;
            case Info.STOPWATCH:
                changePanel(2, 0);
                stopWatchPanel.setDisplay(objects, selected);
                break;
            case Info.ALARM:
                changePanel(3, 0);
                alarmListPanel.setDisplay(objects, selected);
                break;
            case Info.ALARMSET:
                changePanel(3, 1);
                alarmSetPanel.setDisplay(objects, selected);
                break;
            case Info.WORLDTIME:
                changePanel(4, 0);
                worldTimePanel.setDisplay(objects, selected);
                break;
            case Info.SCHEDULE:
                changePanel(5, 0);
                scheduleListPanel.setDisplay(objects, selected);
                break;
            case Info.SCHEDULESET:
                changePanel(5, 1);
                stopWatchPanel.setDisplay(objects, selected);
                break;
            case Info.SELECTMODE:
                changePanel(6, 0);
                selectMenuPanel.setDisplay(objects);
                break;
            default:
                System.out.println("GUI: display() mode data Error!");
        }

        if (!beepList.empty()) {
            for (int i = 0; i < beepList.size(); i++) {
                BeepPanel beepPanel = new BeepPanel(beepList.get(i));
                beepPanels.add(beepPanel);
                beepPanel.setBorder(new LineBorder(Color.BLACK, 2));
                beepPanel.setBounds(120, 100, 360, 280);
                beepPanel.setVisible(true);
                container.add(beepPanel);
            }
        }
    }

    public void changePanel(int idx1, int idx2) {
        curMode.setVisible(false);
        this.modeX = idx1;
        this.modeY = idx2;
        curMode = modePanel[this.modeX][this.modeY];
        curMode.setVisible(true);
    }

    class ButtonAListener extends MouseCustomAdapter {
        @Override
        public void shortClick(MouseEvent e) {
            if (e.getSource() == buttonA) {
                System.out.println("GUI: short A");
                GUI.setInput(Info.A);
            }
        }

        @Override
        public void longClick(MouseEvent e) {
            if (e.getSource() == buttonA) {
                System.out.println("GUI: long A");
                GUI.setInput(Info.LONGA);
            }
        }
    }

    class ButtonBListener extends MouseCustomAdapter {
        @Override
        public void shortClick(MouseEvent e) {
            if (e.getSource() == buttonB) {
                System.out.println("GUI: short B");
                GUI.setInput(Info.B);
            }
        }

        @Override
        public void longClick(MouseEvent e) {
            if (e.getSource() == buttonB) {
                System.out.println("GUI: long B");
                GUI.setInput(Info.LONGB);
            }
        }
    }

    class ButtonCListener extends MouseCustomAdapter {
        @Override
        public void shortClick(MouseEvent e) {
            if (e.getSource() == buttonC) {
                System.out.println("GUI: short C");
                GUI.setInput(Info.C);
            }
        }

        @Override
        public void longClick(MouseEvent e) {
            if (e.getSource() == buttonC) {
                System.out.println("GUI: long C");
                GUI.setInput(Info.LONGC);
            }
        }
    }

    class ButtonDListener extends MouseCustomAdapter {
        @Override
        public void shortClick(MouseEvent e) {
            if (e.getSource() == buttonD) {
                System.out.println("GUI: short D");
                GUI.setInput(Info.D);
            }
        }

        @Override
        public void longClick(MouseEvent e) {
            if (e.getSource() == buttonD) {
                System.out.println("GUI: long D");
                GUI.setInput(Info.LONGD);
            }
        }
    }
}
