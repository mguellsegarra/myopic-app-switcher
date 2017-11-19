# Myopic
🕵️  Replace Android app switcher thumbnail with blur or with your customized image overlay  
 [ ![Download](https://api.bintray.com/packages/mguellsegarra/myopic/cat.mguellsegarra%3Amyopic/images/download.svg) ](https://bintray.com/mguellsegarra/myopic/cat.mguellsegarra%3Amyopic/_latestVersion)  

![myopic](https://github.com/mguellsegarra/myopic-app-switcher/raw/master/myopic_gif.gif)

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
