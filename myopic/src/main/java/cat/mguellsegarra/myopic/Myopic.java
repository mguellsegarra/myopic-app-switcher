package cat.mguellsegarra.myopic;

import android.content.Context;
import com.wonderkiln.blurkit.BlurKit;

public class Myopic {
    private static Myopic ourInstance;

    public static void init(Context context) {
        if (ourInstance != null) {
            return;
        }

        ourInstance = new Myopic();
        BlurKit.init(context);
    }

    public static Myopic getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("No instance of Myopic was found, did you forget to call init()?");
        }

        return ourInstance;
    }
}
