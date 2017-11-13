package games.mindless.idlegame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Andrew on 10/25/2017.
 */

public class RectItem implements GameObject {
    private Rect rectangle;
    private Picture picture;
    private int color;

    public RectItem(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    public RectItem(Rect rectangle, Picture picture) {
        this.rectangle = rectangle;
        this.picture = picture;
    }

    public Rect getRectangle(){
        return rectangle;
    }

    @Override
    public void draw(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);

    }

    @Override
    public void update(){

    }


    public void update(Point point){
        //l,t,r,b
        rectangle.set(point.x - rectangle.width()/2, point.y - rectangle.height()/2, point.x + rectangle.width()/2, point.y + rectangle.height()/2);
    }
}
