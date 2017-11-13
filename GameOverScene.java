package games.mindless.idlegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;

/**
 * Created by Andrew on 10/25/2017.
 */

public class GameOverScene implements Scene, RewardedVideoAdListener {

    private Drawable replayButton;
    private Drawable menuButton;
    private Drawable reviveButton;

    private Rect r = new Rect();

    private RewardedVideoAd mRewardedVideoAd;

    GameOverScene(Context context) {

        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-1468984730288931/2219799117",
                new AdRequest.Builder().build());


        reviveButton = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.spaceships_012);
        reviveButton.setBounds((Constants.SCREEN_WIDTH/2)-(Constants.SCREEN_WIDTH/5), (2*Constants.SCREEN_HEIGHT/8)-(Constants.SCREEN_HEIGHT/10),
                (Constants.SCREEN_WIDTH/2)+(Constants.SCREEN_WIDTH/5), (2*Constants.SCREEN_HEIGHT/8)+(Constants.SCREEN_HEIGHT/10));

        replayButton = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.replaybutton);
        replayButton.setBounds((Constants.SCREEN_WIDTH/2)-(Constants.SCREEN_WIDTH/5), (5*Constants.SCREEN_HEIGHT/8)-(Constants.SCREEN_HEIGHT/20),
                (Constants.SCREEN_WIDTH/2)+(Constants.SCREEN_WIDTH/5), (5*Constants.SCREEN_HEIGHT/8)+(Constants.SCREEN_HEIGHT/20));

        menuButton = Constants.CURRENT_CONTEXT.getResources().getDrawable(R.drawable.menubutton);
        menuButton.setBounds((Constants.SCREEN_WIDTH/2)-(Constants.SCREEN_WIDTH/5), (3*Constants.SCREEN_HEIGHT/4)-(Constants.SCREEN_HEIGHT/20),
                (Constants.SCREEN_WIDTH/2)+(Constants.SCREEN_WIDTH/5), (3*Constants.SCREEN_HEIGHT/4)+(Constants.SCREEN_HEIGHT/20));

        if(ObstacleManager.score > MySettings.getHighScore(context)) {
            MainActivity.highScore = ObstacleManager.score;

            MySettings.setHighScore(context, ObstacleManager.score);
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        reviveButton.draw(canvas);
        menuButton.draw(canvas);
        replayButton.draw(canvas);

        Paint paint = new Paint();
        paint.setTextSize(150);
        paint.setColor(Color.WHITE);
        drawCenter(canvas,paint,"" + ObstacleManager.score);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(replayButton.getBounds().contains((int)event.getX(), (int)event.getY())) {
                    SceneManager.scenes.remove(1);
                    SceneManager.scenes.add(1, new GameplayScene());
                    SceneManager.ACTIVE_SCENE = 1;
                    ObstacleManager.score = 0;
                }

                if(menuButton.getBounds().contains((int)event.getX(), (int)event.getY())) {
                    SceneManager.ACTIVE_SCENE = 0;
                }

                if (reviveButton.getBounds().contains((int)event.getX(), (int)event.getY())) {
                    mRewardedVideoAd.show();
                }
        }

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

    private void loadRewardedVideoAd() {
        //mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(context);
        mRewardedVideoAd.setRewardedVideoAdListener(this);
        mRewardedVideoAd.loadAd("ca-app-pub-1468984730288931/2219799117",
                new AdRequest.Builder().build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {

    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

        SceneManager.scenes.remove(1);
        SceneManager.scenes.add(1, new GameplayScene());
        SceneManager.ACTIVE_SCENE = 1;

        loadRewardedVideoAd();
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
