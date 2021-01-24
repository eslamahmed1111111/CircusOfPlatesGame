package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.AddShapes;

import java.util.ArrayList;

import static eg.edu.alexu.csd.oop.game.Main.logger;

class FlyWeight {
    private ArrayList<GameObject> shapes = new ArrayList<>();
    private int level;

    FlyWeight(int num) {
        this.level = num;
    }

        ArrayList<GameObject> createPlates(int width, int height) {
        Class addShapes = AddShapes.class;
        try {
            AddShapes addShapes1 = (AddShapes) addShapes.newInstance();
            addShapes1.addGifts(shapes , level , 1100 , 600);
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return shapes;
    }


}

