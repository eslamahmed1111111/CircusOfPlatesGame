package eg.edu.alexu.csd.oop.game;

import javax.swing.*;

public class Context {
    public static MenuItem pause;
    public static MenuItem resume;
    public static State state;

    public Context(JMenuItem pause , JMenuItem resume) {
        Context.pause = new MenuItem(pause);
        Context.resume = new MenuItem(resume);
    }

    public void setPauseState(){
        state = pause;
    }
    public void setResumeState(){
        state = resume;
    }
    public void update(){
        state.update();
    }
}
