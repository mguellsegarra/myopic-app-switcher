package cat.mguellsegarra.myopic;

public class MyopicBlur extends Myopic {
    public MyopicBlur(int radius) {
        setMode(MyopicMode.BLUR);
        setBlurRadius(radius);
    }
}
