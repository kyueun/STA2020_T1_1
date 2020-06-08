package model;

import org.junit.Test;
import view.GUI;

import static org.junit.Assert.*;

public class BeepTest {

    @Test
    public void Beep(){
        Beep beep = new Beep(null);
        assertEquals(1, beep.beepPopup(Info.ALARM).size());
        assertEquals(2, beep.beepPopup(Info.TIMER).size());
    }

    @Test
    public void MuteBeep(){
        Beep beep = new Beep(null);
        assertEquals(1, beep.beepPopup(Info.ALARM).size());
        assertEquals(2, beep.beepPopup(Info.TIMER).size());
        assertEquals(1, beep.muteTopBeep().size());
        assertEquals(0, beep.muteTopBeep().size());
    }
}
