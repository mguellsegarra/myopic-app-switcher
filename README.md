# Myopic
 [ ![Download](https://api.bintray.com/packages/mguellsegarra/myopic/cat.mguellsegarra%3Amyopic/images/download.svg) ](https://bintray.com/mguellsegarra/myopic/cat.mguellsegarra%3Amyopic/_latestVersion)  
  
🕵️  Replace Android app switcher thumbnail with blur or with your customized image overlay  
  
![myopic](https://github.com/mguellsegarra/myopic-app-switcher/raw/master/myopic_gif.gif)

## **WARNING: This behaviour is only working with devices running API 26!** With older versions it's not working, Android doesn't use the last view state when you're going to background, and the overlay or blur isn't appearing in the app switcher :(

## Why this library

After some research, I only found two ways to achieve the modification of the thumbnail that an app has in Android App Switcher:

- Overriding Activity `onCreateThumbnail` method. **Not working anymore** [Source 1](https://stackoverflow.com/questions/11848132/is-there-a-way-to-change-the-thumbnail-of-an-app-in-the-android-task-switcher-l)

- Setting `WindowManager.LayoutParams.FLAG_SECURE` flag in your activity. [Source 1](https://stackoverflow.com/questions/9822076/how-do-i-prevent-android-taking-a-screenshot-when-my-app-goes-to-the-background) [Source 2](https://stackoverflow.com/questions/22435952/android-thumbnail-when-it-goes-to-background). This is the official and the best way to secure your application. You'll prevent Android to take screenshots of your app, so you won't be able to see the thumbnail of your app in the app switcher. Instead you'll see a black window. However, applying this flag, you wan't be able to customize your app's thumbnail while in app switcher, and most important, you won't be able to take screenshots while using your app, and sometimes this is something that you don't want to loose.

## Code

Myopic internally handles `onPause()` and `onResume()`, and other lifecycle methods in the activity in order to insert an overlaying view when the app goes to the background.

This overlaying view can be an automatic generated blurred image of your current screen or an image that you'll have to specify by passing a drawable id.

The blur is accomplished thanks to [BlurKit](https://github.com/wonderkiln/blurkit-android) library.

## Adding the library

Add JCenter in gradle project file:

```groovy
    repositories {
        jcenter()
    }
```

Add this line to your module project:

```groovy
compile 'cat.mguellsegarra:myopic:0.16'
```

You also have to insert these lines in your android default config section:

```groovy
        renderscriptTargetApi 26
        renderscriptSupportModeEnabled true
```

These are needed for BlurKit, and ideally, the target API for RenderScript must match to your `targetSdkVersion` value.

Myopic uses appcompat-v7 support library, version 26.0.1. If you're using another version in your application, or you're getting dependencies problems, you can exclude the version attached to the library, and let Gradle resolve the dependency with your desired version:

```groovy
    compile 'cat.mguellsegarra:myopic:0.16', {
        exclude group: 'com.android.support', module: 'appcompat-v7'
    }
```

## Usage

Initialize Myopic in your sample application:

```java
public class MyopicSampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Default blur mode
        Myopic.init(this);

        // Specific blur radius, by default is 25
        // Myopic.initBlurMode(this, 5);

        // Specific drawable PNG
        // Myopic.initOverlayMode(this, R.drawable.app_switcher_background);
    }
}
```

Extend your activities or your own base activity from `MyopicActivity`:

```java
public class MainActivity extends MyopicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
```

Et voilà!

## Caveats

In blur mode, Myopic will take a capture of your current view on `onPostResume()` method and apply blur from that view.

I tried to take that screenshot in `onPause()` but sadly, I think that the app has not enough time to take the capture and add the view before Android sends the app to background.

For this reason, you'll appreciate that the blurred view sometimes doesn't match the last view you had in your application, because it's the screenshot that was taken just when the activity started.

If you want, you can use this method to manually force update the blur view, in your activity:

`updateBlurredView();`

If somebody knows a way to achieve taking an screenshot just before the app goes to background, just ping me or open a PR.

## Contributing 

Feel free to open any pull request if you'd like to add new features or improve Myopic :)

## License 

The MIT License (MIT)

Copyright (c) 2017 Marc Güell Segarra
