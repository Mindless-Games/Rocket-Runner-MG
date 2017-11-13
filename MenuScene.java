package games.mindless.idlegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Created by Andrew on 10/25/2017.
 */

public class MenuScene implements Scene {

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;

    public static int highScore;

    private Rect r = new Rect();

    private Drawable playButton;
    private Drawable title;

    public MenuScene(Context context) {

        mInterstitialAd = new InterstitialAd(context);
        mInterstitialAd.setAdUnitId("ca-app-pub-1468984730288931/2958165719");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());
        mInterstitialAd.show();

        title = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.title);
        title.setBounds((Constants.SCREEN_WIDTH/2)-(Constants.SCREEN_WIDTH/3) , 100,
                (Constants.SCREEN_WIDTH/2)+(Constants.SCREEN_WIDTH/3), (6*Constants.SCREEN_HEIGHT/12));


        playButton = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.playbutton);
        playButton.setBounds((Constants.SCREEN_WIDTH/2)-(Constants.SCREEN_WIDTH/5), (5*Constants.SCREEN_HEIGHT/8)-(Constants.SCREEN_HEIGHT/20),
                (Constants.SCREEN_WIDTH/2)+(Constants.SCREEN_WIDTH/5), (5*Constants.SCREEN_HEIGHT/8)+(Constants.SCREEN_HEIGHT/20));


        highScore = MySettings.getHighScore(context);
        System.out.println(highScore);
    }


    @Override
    public void terminate(){
        SceneManager.ACTIVE_SCENE = 0;
    }

    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(playButton.getBounds().contains((int)event.getX(), (int)event.getY())) {
                    SceneManager.scenes.remove(1);
                    SceneManager.scenes.add(1, new GameplayScene());
                    SceneManager.ACTIVE_SCENE = 1;
                    ObstacleManager.score = 0;
                }
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        title.draw(canvas);
        playButton.draw(canvas);

        Paint paint = new Paint();
        paint.setTextSize(75);
        paint.setColor(Color.WHITE);
        //drawCenter(canvas, paint, "highscore: " + highScore);
    }


    @Override
    public void update() {

    }

    private void drawCenter(Canvas canvas, Paint paint, String text) {
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
