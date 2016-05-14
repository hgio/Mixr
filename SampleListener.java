import java.io.IOException;
import java.lang.Math;
import com.leapmotion.leap.*;
import com.leapmotion.leap.Gesture.State;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
public class SampleListener extends Listener {
    private double seconds;
    private MediaPlayer mp;
    private double bpm;
    private double bps;
    private int a;
    public void onInit(Controller controller) {
        System.out.println("Initialized");
    }
public SampleListener(MediaPlayer m){
    mp=m;

}
    public void onConnect(Controller controller) {
        System.out.println("Connected");
        controller.enableGesture(Gesture.Type.TYPE_SWIPE);
    }

    public void onDisconnect(Controller controller) {
        //Note: not dispatched when running in a debugger.
        System.out.println("Disconnected");
    }

    public void onExit(Controller controller) {
        System.out.println("Exited");
    }

    public void onFrame(Controller controller) {
        Frame frame = controller.frame();
        GestureList gestures = frame.gestures();
        for (int i = 0; i < gestures.count(); i++) {
            Gesture gesture = gestures.get(i);
            if (gesture.type() == SwipeGesture.classType()){

                bpm = 60/(17.5*gesture.durationSeconds());
                bps=bpm/105;
                if(bps>2){
                    bps=2;
                }
                if(bps<0.5){
                    bps=0.5;
                }
                a++;
                System.out.println(bps);
                if(a%30==0) {
                    mp.setRate(bps);
                }
            }

        }

    }
    public double getSeconds(){
        return bpm;
    }
