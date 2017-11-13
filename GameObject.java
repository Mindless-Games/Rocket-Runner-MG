package games.mindless.idlegame;

import android.graphics.Canvas;

/**.
 * Created by Andrew on 10/15/2017.
 */

public interface GameObject {
    public void draw(Canvas canvas);
    public void update();
}
