package cat.mguellsegarra.myopic;

import android.graphics.Bitmap;
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

    @Override
    protected void onPause() {
        super.onPause();
        ((ViewGroup)this.getWindow().getDecorView()).addView(blurredView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((ViewGroup)this.getWindow().getDecorView()).removeView(blurredView);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        final View view = this.getWindow().getDecorView();

        view.post(new Runnable() {
            @Override
            public void run() {
                blurredView = getBlurredView();
            }
        });
    }

    private View getBlurredView() {
        final View view = this.getWindow().getDecorView().getRootView();
        Bitmap blurredBitmap;
        blurredBitmap = BlurKit.getInstance().blur(view, 10);
        ImageView imageView = new ImageView(this);
        RelativeLayout.LayoutParams paramsImage = new RelativeLayout.LayoutParams(blurredBitmap.getWidth(), blurredBitmap.getHeight());
        paramsImage.addRule(RelativeLayout.ALIGN_TOP);
        paramsImage.addRule(RelativeLayout.ALIGN_LEFT);
        imageView.setLayoutParams(paramsImage);
        Drawable drawable = new BitmapDrawable(blurredBitmap);
        imageView.setImageDrawable(drawable);
        return imageView;
    }
}
