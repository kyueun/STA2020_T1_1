package model;

import org.junit.Test;
import view.GUI;

import static org.junit.Assert.*;

public class BeepTest {
    GUI gui = new GUI();
    Beep beep = new Beep(gui);

    @Test
    public void Beep(){
        assertEquals(1, beep.beepPopup(Info.ALARM).size());
        assertEquals(2, beep.beepPopup(Info.TIMER).size());
    }

    @Test
    public void MuteBeep(){
        assertEquals(1, beep.beepPopup(Info.ALARM).size());
        assertEquals(2, beep.beepPopup(Info.TIMER).size());
        assertEquals(1, beep.muteTopBeep().size());
        assertEquals(0, beep.muteTopBeep().size());
    }
}
