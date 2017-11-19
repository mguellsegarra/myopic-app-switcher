package cat.mguellsegarra.myopic;

import android.content.Context;
import android.support.annotation.DrawableRes;

import com.wonderkiln.blurkit.BlurKit;

public class Myopic {
    private static int DEFAULT_BLUR_RADIUS = 25;
    private static Myopic ourInstance;
    private MyopicMode mode = MyopicMode.BLUR;
    private int overlayDrawableId;
    private int blurRadius = 25;

    public static void init(Context context) {
        if (ourInstance != null) {
            return;
        }

        ourInstance = new MyopicBlur(DEFAULT_BLUR_RADIUS);
        BlurKit.init(context);
    }

    public static void initBlurMode(Context context, int blurRadius) {
        if (ourInstance != null) {
            return;
        }

        ourInstance = new MyopicBlur(blurRadius);
        BlurKit.init(context);
    }

    public static void initOverlayMode(Context context, @DrawableRes int overlayDrawableId) {
        if (ourInstance != null) {
            return;
        }

        ourInstance = new MyopicOverlay(overlayDrawableId);
        BlurKit.init(context);
    }


    public static Myopic getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("No instance of Myopic was found, did you forget to call init()?");
        }

        return ourInstance;
    }

    public MyopicMode getMode() {
        return mode;
    }

    protected void setOverlayDrawableId(@DrawableRes int drawableId) {
        overlayDrawableId = drawableId;
    }

    protected void setBlurRadius(int radius) {
        blurRadius = radius;
    }

    protected void setMode(MyopicMode mode) {
        this.mode = mode;
    }

    public @DrawableRes int getOverlayDrawable() {
        return overlayDrawableId;
    }

    public int getBlurRadius() {
        return blurRadius;
    }
}
