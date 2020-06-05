package model;

import view.BeepPanel;
import view.GUI;

import java.util.*;

public class Beep {
    public int type;
    public Stack<Integer> beepList;
//    public GUI gui;
//    BeepPanel curBeep;

    public Beep(GUI gui) {

        beepList = new Stack<Integer>();
//        this.gui = gui;
    }

    public Stack<Integer> beepPopup(int type) { // 1 beep popup add
        beepList.push(type);
//        curBeep = gui.popUp(type);
        return beepList;
    }

    public Stack<Integer> muteTopBeep() {
        beepList.pop();
//        gui.deletePopUp(curBeep);
        return beepList;
    }
}