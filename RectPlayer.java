package games.mindless.idlegame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Andrew on 10/15/2017.
 */

public class RectPlayer implements GameObject {

    private Rect rectangle;
    private int color;

    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private AnimationManager animManager;


    public RectPlayer(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;

        //add player animation frames
        BitmapFactory bf = new BitmapFactory();
        Bitmap playerImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.spaceships_010);

        idle = new Animation(new Bitmap[]{playerImg}, 1);
        //if movement animation
        walkRight = new Animation(new Bitmap[]{playerImg}, 0.5f);
        walkLeft = new Animation(new Bitmap[]{playerImg}, 0.5f);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft});
    }

    public Rect getRectangle(){
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas){
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rectangle,paint);
        animManager.draw(canvas, rectangle);
    }

    @Override
    public void update(){
        animManager.update();
    }


    public void update(Point point){
        float oldLeft = rectangle.left;

        //l,t,r,b
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);

        int state = 0;
        if(rectangle.left - oldLeft > 5) {
            state = 1;
        }
        else if(rectangle.left - oldLeft <-5){
            state = 2;
        }

        animManager.playAnim(state);
        animManager.update();
    }
}
