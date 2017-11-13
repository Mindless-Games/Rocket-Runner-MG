package games.mindless.idlegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Andrew on 10/16/2017.
 */

public class Obstacle implements GameObject {

    private Rect rectangle;
    private Rect rectangle2;
    private int color;

    private Animation leftObstacle;
    private Animation rightObstacle;
    private AnimationManager animManager;
    private AnimationManager animManager2;




    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap) {
        this.color = color;
        rectangle = new Rect(0, startY, startX, startY + rectHeight);  //l,r,t,b
        rectangle2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);

        BitmapFactory bf = new BitmapFactory();
        Bitmap leftObstacleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.spacebuilding_014);
        Bitmap rightObstacleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.spacebuilding_015);

        leftObstacle = new Animation(new Bitmap[]{leftObstacleImg}, 1);
        rightObstacle = new Animation(new Bitmap[]{rightObstacleImg}, 1);

        animManager = new AnimationManager(new Animation[]{leftObstacle});
        animManager2 = new AnimationManager(new Animation[]{rightObstacle});
    }

    public Rect getRectangle(){
        return rectangle;
    }

    public void incrementY(float y) {
        rectangle.top += y;
        rectangle.bottom += y;
        rectangle2.top += y;
        rectangle2.bottom += y;
    }

    public boolean playerCollide(RectPlayer player) {
        return Rect.intersects(rectangle, player.getRectangle()) || Rect.intersects(rectangle2, player.getRectangle());
    }


    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
        canvas.drawRect(rectangle2,paint);
        animManager.draw(canvas, rectangle);
        animManager2.draw(canvas, rectangle2);
    }

    @Override
    public void update(){
        animManager.update();
        animManager2.update();
    }

    public void update(Point point){
        float oldLeft = rectangle.left;

        int state = 0;
        if(rectangle.left - oldLeft > 5) {
            state = 0;
        }
        else if(rectangle.left - oldLeft <-5){
            state = 0;
        }

        animManager.playAnim(state);
        animManager.update();
    }
}
