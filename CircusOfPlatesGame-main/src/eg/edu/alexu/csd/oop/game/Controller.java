package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.world.Caretaker;
import eg.edu.alexu.csd.oop.game.world.Circus;

import javax.swing.*;
import java.awt.*;

import static eg.edu.alexu.csd.oop.game.Main.logger;

public class Controller {
    private int level;
    private long pause =0;
    private long resume = 0;
    Controller(int level) {
        this.level = level;
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New Game");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
//        JButton undo = new JButton("Undo");
        resumeMenuItem.setEnabled(false);
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
//        menuBar.add(undo);
        Circus circus = new Circus(1100, 600 , level);
        final GameEngine.GameController gameController = GameEngine.start("Circus of plates", circus, menuBar, Color.BLACK);
        CurrentGame currentGame = new CurrentGame(level , gameController);
        ActionListenerCommand clickNew = new ActionNewGame(currentGame);
        ActionListenerCommand clickPause = new ActionPause(currentGame);
        ActionListenerCommand clickResume = new ActionResume(currentGame);
        MenuOptions menuOptions = new MenuOptions(clickNew , clickPause , clickResume);
        Context context = new Context(pauseMenuItem , resumeMenuItem);
        newMenuItem.addActionListener(e -> menuOptions.clickNew());
        pauseMenuItem.addActionListener(e ->{
            menuOptions.clickPause();
            pause = System.currentTimeMillis();
            context.setPauseState();
            context.update();
            context.setResumeState();
            context.update();
            logger.info(circus.getStatus());
        } );
        resumeMenuItem.addActionListener(e -> {
            menuOptions.clickResume();
            resume = System.currentTimeMillis() - pause;
            Circus.MAX_TIME += resume;
            context.setPauseState();
            context.update();
            context.setResumeState();
            context.update();
            logger.info(circus.getStatus());
        });
//        undo.addActionListener(e ->{
//            if(!Caretaker.snapshots.isEmpty()){
//                Circus oldCircus = Caretaker.snapshots.pop().getOldCircus();
//                gameController.changeWorld(oldCircus);
//            }
//        });
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
