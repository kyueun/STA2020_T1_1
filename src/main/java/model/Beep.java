package model;

import view.BeepPanel;
import view.GUI;

import java.util.*;

public class Beep {
    public int type;
    public Stack<Integer> beepList;

    public Beep(GUI gui) {
        beepList = new Stack<Integer>();
    }

    public Stack<Integer> beepPopup(int type) { // 1 beep popup add
        beepList.push(type);
        return beepList;
    }

    public Stack<Integer> muteTopBeep() {
        beepList.pop();
        return beepList;
    }
}