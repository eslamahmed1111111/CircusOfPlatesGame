package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Music;
import eg.edu.alexu.csd.oop.game.object.Clown;
import eg.edu.alexu.csd.oop.game.object.Observer;
import eg.edu.alexu.csd.oop.game.object.Shape;
import eg.edu.alexu.csd.oop.game.object.Surface;

import java.awt.*;
import java.util.Iterator;
import java.util.List;

import static eg.edu.alexu.csd.oop.game.Main.logger;


public class Facade implements FacadeI{

    private Circus game;
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private static int MAX_TIME = 60 * 1000;    // 1 minute
    private long startTime = System.currentTimeMillis();
    Originator originator = new Originator();
    Caretaker caretaker = new Caretaker();

    Facade(Circus game) {

        this.game = game;

    }

   public boolean refreshAll(){
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME; // time end and game over
        Surface rSurface = (Surface) game.getConstantObjects().get(0);
        Surface lSurface = (Surface) game.getConstantObjects().get(1);
        Clown clown = (Clown)game.getControlableObjects().get(0);
        clown.notifyObservers();
        ReusablePool pool1 = ReusablePool.getInstance();
        // moving plates
        List<GameObject> moving = game.getMovableObjects();
        Iterator<GameObject> iterator = moving.iterator();
        while (iterator.hasNext()){
//            Changeable
            Shape m = (Shape) iterator.next() ;
            m.setY((m.getY() + 1));
            if(m.getY()==game.getHeight()){
                // reuse the plate in another position
                m.setY(-1 * (int)(Math.random() * game.getHeight()));
                m.setX((int)(Math.random() * game.getWidth()));
            }
            if (!timeout & intersect(m,  game.getrstick().get(game.getrstick().size() - 1))) {
                originator.setOldCircus(game);
                caretaker.add(originator.saveToStack());
                m.setHorizontalOnly(true);
                game.getrstick().add(m);
                rSurface.registerObserver((Observer) m);
                rSurface.notifyObservers();
                if (game.getrstick().size() > 3) {
                    Shape temp1 = (Shape) game.getrstick().get(game.getrstick().size() - 1);
                    Shape temp2 = (Shape) game.getrstick().get(game.getrstick().size() - 2);
                    Shape temp3 = (Shape) game.getrstick().get(game.getrstick().size() - 3);
                    for (int k = 1; k <= 5; k++) {
                        if (temp1.getPath().contains(String.valueOf(k))
                                && temp2.getPath().contains(String.valueOf(k))
                                && temp3.getPath().contains(String.valueOf(k))) {
                            game.setScore(game.getScore()+10);
                            logger.info(game.getStatus());
                            for (int q = 0; q < 3; q++) {
                                game.getMovableObjects().remove(game.getrstick().get(game.getrstick().size() - 1));
                                rSurface.unregisterObserver((Observer) game.getrstick().get(game.getrstick().size() - 1));
                                pool1.readd(game.getrstick().get(game.getrstick().size()-1));
                                game.getrstick().remove(game.getrstick().size() - 1);
                                iterator = moving.iterator();
                            }
                        }
                    }
                }
            }if (!timeout & intersect(m,  game.getlstick().get(game.getlstick().size() - 1))) {
                originator.setOldCircus(game);
                caretaker.add(originator.saveToStack());
                m.setHorizontalOnly(true);
                game.getlstick().add(m);
                lSurface.registerObserver((Observer) m);
                lSurface.notifyObservers();
                if (game.getlstick().size() > 3) {
                    Shape temp1 = (Shape) game.getlstick().get(game.getlstick().size() - 1);
                    Shape temp2 = (Shape) game.getlstick().get(game.getlstick().size() - 2);
                    Shape temp3 = (Shape) game.getlstick().get(game.getlstick().size() - 3);
                    for (int k = 1; k <= 5; k++) {
                        if (temp1.getPath().contains(String.valueOf(k))
                                && temp2.getPath().contains(String.valueOf(k))
                                && temp3.getPath().contains(String.valueOf(k))) {
                            game.setScore(game.getScore()+10);
                            logger.info(game.getStatus());
                            for (int q = 0; q < 3; q++) {
                                game.getMovableObjects().remove(game.getlstick().get(game.getlstick().size() - 1));
                                lSurface.unregisterObserver((Observer) game.getlstick().get(game.getlstick().size() - 1));
                                pool1.readd(game.getlstick().get(game.getlstick().size()-1));
                                game.getlstick().remove(game.getlstick().size() - 1);
                                iterator = moving.iterator();
                            }
                        }
                    }
                }
            }
        }
        rSurface.notifyObservers();
        lSurface.notifyObservers();
        if(timeout){
            eg.edu.alexu.csd.oop.game.Music.getInstance().gameOver();
            eg.edu.alexu.csd.oop.game.Music.getInstance().stopMusic();
        }
        return !timeout;
    }


   public boolean intersect(GameObject o1, GameObject o2){
        return Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o2.getWidth()-7 && (Math.abs(o1.getY()-(o2.getY()-o1.getHeight())) == 0);
    }



     public void checkPool() {
        ReusablePool a = ReusablePool.getInstance();
        if (a.hasElement()) {
            GameObject shape = a.use();
            shape.setX((int) (Math.random() * screenSize.width));
            ((Shape) shape).setHorizontalOnly(false);
            shape.setY(-1 * (int) (Math.random() * screenSize.height));
            game.getMovableObjects().add(shape);
        }
    }




}
