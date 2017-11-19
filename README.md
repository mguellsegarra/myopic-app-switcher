# Myopic
 [ ![Download](https://api.bintray.com/packages/mguellsegarra/myopic/cat.mguellsegarra%3Amyopic/images/download.svg) ](https://bintray.com/mguellsegarra/myopic/cat.mguellsegarra%3Amyopic/_latestVersion)  
  
🕵️  Replace Android app switcher thumbnail with blur or with your customized image overlay  
  
![myopic](https://github.com/mguellsegarra/myopic-app-switcher/raw/master/myopic_gif.gif)

## Code

Myopic internally handles `onPause()` and `onResume()` methods in the activity lifecyle in order to insert an overlaying view when the app goes to the background.

This overlaying view can be an automatic generated blurred image of your current screen or an image that you'll have to specify by passing a drawable id.

The blur is accomplished thanks to [BlurKit](https://github.com/wonderkiln/blurkit-android) library.

## Installing

Add JCenter in gradle project file:

```groovy
    repositories {
        jcenter()
    }
```

Add this line to your module project:

```groovy
compile 'cat.mguellsegarra:myopic:0.14'
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

## Contributing 

Feel free to open any pull request if you'd like to add new features or improve Myopic :)

## License 

The MIT License (MIT)

Copyright (c) 2017 Marc Güell Segarra
