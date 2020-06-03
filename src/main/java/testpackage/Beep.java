package testpackage;

import java.util.*;

public class Beep {
    public int type;
    public Stack<Integer> beepList;

    public Beep() {
        beepList = new Stack<Integer>();
    }

    public Stack<Integer> beepPopup(int type) { //beep 창 1개 추
        beepList.push(type);

        return beepList;
    }

    public Stack<Integer> muteTopBeep() {
        beepList.pop();

        return beepList;
    }
}