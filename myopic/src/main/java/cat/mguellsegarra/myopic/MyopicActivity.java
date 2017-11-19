package cat.mguellsegarra.myopic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wonderkiln.blurkit.BlurKit;

public class MyopicActivity extends AppCompatActivity {
    private View blurredView;
    private View overlayView;

    @Override
    protected void onPause() {
        super.onPause();
        handle_onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handle_onResume();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        handle_onPostResume();
    }

    private void handle_onPause() {
        if (Myopic.getInstance().getMode() == MyopicMode.BLUR) {
            ((ViewGroup)this.getWindow().getDecorView()).addView(blurredView);
        } else {
            ((ViewGroup)this.getWindow().getDecorView()).addView(overlayView);
        }
    }

    private void handle_onResume() {
        if (Myopic.getInstance().getMode() == MyopicMode.BLUR) {
            ((ViewGroup)this.getWindow().getDecorView()).removeView(blurredView);
        } else {
            ((ViewGroup)this.getWindow().getDecorView()).removeView(overlayView);
        }
    }

    private void handle_onPostResume() {
        final View view = this.getWindow().getDecorView();

        view.post(new Runnable() {
            @Override
            public void run() {
                if (Myopic.getInstance().getMode() == MyopicMode.BLUR) {
                    blurredView = getBlurredView();
                } else {
                    overlayView = getOverlayView();
                }
            }
        });
    }

    private View getBlurredView() {
        final View view = this.getWindow().getDecorView().getRootView();
        Bitmap blurredBitmap = BlurKit.getInstance().blur(view, Myopic.getInstance().getBlurRadius());
        return getFinalView(blurredBitmap);
    }

    private View getOverlayView() {
        return getFinalView(BitmapFactory.decodeResource(this.getResources(), Myopic.getInstance().getOverlayDrawable()));
    }

    private View getFinalView(Bitmap bitmap) {
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams paramsImage =
                new RelativeLayout.LayoutParams(this.getWindow().getDecorView().getRootView().getWidth(),
                this.getWindow().getDecorView().getRootView().getHeight());
        imageView.setLayoutParams(paramsImage);
        Drawable drawable = new BitmapDrawable(bitmap);
        imageView.setImageDrawable(drawable);
        return imageView;
    }
}
