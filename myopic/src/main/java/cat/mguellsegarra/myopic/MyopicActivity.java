package cat.mguellsegarra.myopic;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.wonderkiln.blurkit.BlurKit;

public class MyopicActivity extends AppCompatActivity {
    private View blurredView;
    private View overlayView;
    private boolean goingToBackground = true;

    @Override
    protected void onPause() {
        handle_onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        handle_onResume();
        super.onResume();
    }

    @Override
    protected void onPostResume() {
        handle_onPostResume();
        super.onPostResume();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        handle_startActivity();
        super.startActivityForResult(intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode) {
        handle_startActivity();
        super.startActivityFromFragment(fragment, intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(Fragment fragment, Intent intent, int requestCode, @Nullable Bundle options) {
        handle_startActivity();
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }

    @Override
    public void startActivity(Intent intent) {
        handle_startActivity();
        super.startActivity(intent);
    }

    @Override
    public void startActivity(Intent intent, @Nullable Bundle options) {
        handle_startActivity();
        super.startActivity(intent, options);
    }

    @Override
    public boolean startActivityIfNeeded(@RequiresPermission @NonNull Intent intent, int requestCode) {
        handle_startActivity();
        return super.startActivityIfNeeded(intent, requestCode);
    }

    @Override
    public boolean startActivityIfNeeded(@RequiresPermission @NonNull Intent intent, int requestCode, @Nullable Bundle options) {
        handle_startActivity();
        return super.startActivityIfNeeded(intent, requestCode, options);
    }

    @Override
    public boolean startNextMatchingActivity(@RequiresPermission @NonNull Intent intent) {
        handle_startActivity();
        return super.startNextMatchingActivity(intent);
    }

    @Override
    public boolean startNextMatchingActivity(@RequiresPermission @NonNull Intent intent, @Nullable Bundle options) {
        handle_startActivity();
        return super.startNextMatchingActivity(intent, options);
    }

    @Override
    public void startActivityFromChild(@NonNull Activity child, @RequiresPermission Intent intent, int requestCode) {
        handle_startActivity();
        super.startActivityFromChild(child, intent, requestCode);
    }

    @Override
    public void startActivityFromChild(@NonNull Activity child, @RequiresPermission Intent intent, int requestCode, @Nullable Bundle options) {
        handle_startActivity();
        super.startActivityFromChild(child, intent, requestCode, options);
    }

    @Override
    public void startActivityFromFragment(@NonNull android.app.Fragment fragment, @RequiresPermission Intent intent, int requestCode) {
        handle_startActivity();
        super.startActivityFromFragment(fragment, intent, requestCode);
    }

    @Override
    public void startActivityFromFragment(@NonNull android.app.Fragment fragment, @RequiresPermission Intent intent, int requestCode, @Nullable Bundle options) {
        handle_startActivity();
        super.startActivityFromFragment(fragment, intent, requestCode, options);
    }

    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags) throws IntentSender.SendIntentException {
        handle_startActivity();
        super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags);
    }

    @Override
    public void startIntentSenderFromChild(Activity child, IntentSender intent, int requestCode, Intent fillInIntent, int flagsMask, int flagsValues, int extraFlags, @Nullable Bundle options) throws IntentSender.SendIntentException {
        handle_startActivity();
        super.startIntentSenderFromChild(child, intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options);
    }

    @Override
    public void finish() {
        goingToBackground = false;
        super.finish();
    }

    @Override
    public void onBackPressed() {
        goingToBackground = false;
        super.onBackPressed();
    }

    private void handle_startActivity() {
        goingToBackground = false;
    }

    private void handle_onPause() {
        if (goingToBackground) {
            if (Myopic.getInstance().getMode() == MyopicMode.BLUR) {
                ((ViewGroup)MyopicActivity.this.getWindow().getDecorView()).addView(blurredView);
            } else {
                ((ViewGroup)MyopicActivity.this.getWindow().getDecorView()).addView(overlayView);
            }
        }
    }

    private void handle_onResume() {
        goingToBackground = true;
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
                new RelativeLayout.LayoutParams(
                        this.getWindow().getDecorView().getRootView().getWidth(),
                        this.getWindow().getDecorView().getRootView().getHeight());
        imageView.setLayoutParams(paramsImage);
        Drawable drawable = new BitmapDrawable(this.getResources(), bitmap);
        imageView.setImageDrawable(drawable);
        return imageView;
    }

    public void updateBlurredView() {
        handle_onPostResume();
    }
}
