package org.itintern2albemarle.albemarleservicesappandroid;


/**
 * Created by itintern2 on 7/28/2015.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class SplashActivity extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 3500;
    private static boolean firstRun = true;
    private Intent mainIntent;

    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        Thread splashscreen = new Thread() {
            public void run() {
                try {
                    // Thread will sleep for a few seconds
                    // IE, splashscreen displays for a few seconds on first run
                    // but should not show up on subsequent runs
                    if(firstRun) {
                        sleep(SPLASH_DISPLAY_LENGTH);
                        firstRun = false;
                    } else {
                        sleep(0);
                    }

                    // After a few seconds redirect to another intent
                    SplashActivity.this.startActivity(mainIntent);

                    //Remove activity
                    finish();

                } catch (Exception e) {

                }
            }
        };
        // start thread
        splashscreen.start();
    }
}
