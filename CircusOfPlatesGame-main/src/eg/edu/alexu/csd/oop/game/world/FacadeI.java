package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface FacadeI {

    void checkPool() ;

     boolean intersect(GameObject o1, GameObject o2) ;

    boolean refreshAll();


}
