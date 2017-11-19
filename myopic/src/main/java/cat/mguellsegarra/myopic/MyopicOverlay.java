package cat.mguellsegarra.myopic;

import android.support.annotation.DrawableRes;

public class MyopicOverlay extends Myopic {
    public MyopicOverlay(@DrawableRes int drawableId) {
        setMode(MyopicMode.OVERLAY);
        setOverlayDrawableId(drawableId);
    }
}
