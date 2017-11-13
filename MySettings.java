package games.mindless.idlegame;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Andrew on 11/2/2017.
 */

public class MySettings {

    public static int highScore;
    private static String PREF_NAME = "prefs";


    public MySettings() {
        // Blank
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static Integer getHighScore(Context context) {
        //return getPrefs(context).getInt(String.valueOf(R.string.saved_high_score), 0);
        int myIntValue = getPrefs(context).getInt("saved_high_score", 0);
        return  myIntValue;
    }

    public static void setHighScore(Context context, Integer input) {
        SharedPreferences.Editor editor = getPrefs(context).edit();
        editor.putInt("saved_high_score", input);
        editor.apply();
    }
}
