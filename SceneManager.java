package games.mindless.idlegame;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Andrew on 10/25/2017.
 */

public class SceneManager {
    public static ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVE_SCENE;
    
    public SceneManager(Context context) {
        ACTIVE_SCENE = 0;
        scenes.add(new MenuScene(context));
        scenes.add(new GameplayScene());
        scenes.add(new GameOverScene(context));
    }

    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVE_SCENE).receiveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVE_SCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVE_SCENE).draw(canvas);
    }
}
