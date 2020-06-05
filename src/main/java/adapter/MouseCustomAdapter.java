package adapter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

abstract public class MouseCustomAdapter extends MouseAdapter {
    private long mousePressedTime;
    private long delay = 3000;
    private Timer flashTimer;

    public MouseCustomAdapter() {

    }

    public MouseCustomAdapter(long delay) {
        this.delay = delay;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mousePressedTime = e.getWhen();
        if (flashTimer != null) {
            flashTimer.cancel();
        }

        flashTimer = new Timer("flash timer");
        flashTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                e.getComponent().setBackground(Color.LIGHT_GRAY);
            }
        }, delay);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        flashTimer.cancel();
        e.getComponent().setBackground(Color.WHITE);

        if (e.getWhen() - mousePressedTime >= delay) {
            longClick(e);
        } else {
            shortClick(e);
        }
    }

    public abstract void shortClick(MouseEvent e);

    public abstract void longClick(MouseEvent e);
}
