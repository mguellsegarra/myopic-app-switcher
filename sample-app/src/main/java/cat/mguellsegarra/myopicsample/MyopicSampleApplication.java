package cat.mguellsegarra.myopicsample;

import android.app.Application;

import cat.mguellsegarra.myopic.Myopic;

/**
 * Created by marc on 18/11/2017.
 */

public class MyopicSampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Myopic.init(this);
    }
}
