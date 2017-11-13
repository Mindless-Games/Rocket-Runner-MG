package games.mindless.idlegame;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Andrew on 10/25/2017.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
