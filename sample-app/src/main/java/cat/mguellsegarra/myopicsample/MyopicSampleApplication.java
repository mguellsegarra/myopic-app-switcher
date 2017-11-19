package cat.mguellsegarra.myopicsample;

import android.app.Application;

import cat.mguellsegarra.myopic.Myopic;

public class MyopicSampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Default blur mode
        Myopic.init(this);

        // Specific blur radius
        // Myopic.initBlurMode(this, 5);

        // Specific drawable
        // Myopic.initOverlayMode(this, R.drawable.app_switcher_background);
    }
}
