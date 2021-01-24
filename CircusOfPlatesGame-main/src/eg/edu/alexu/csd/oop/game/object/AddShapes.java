package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.util.List;

public class AddShapes {
    public AddShapes() {

    }
    public void addGifts(List<GameObject> moving , int level ,int width ,int height){
        Shape shape;
        for(int i= moving.size() ; i < 10*level ;i++){
            shape = new Gift((int) (Math.random() * width), -1 * (int) (Math.random() * height), "res\\gift" + (i % (level+2) + 1) + ".png");
            moving.add(shape);
            shape = new Plate((int) (Math.random() * width), -1 * (int) (Math.random() * height), "res\\plate" + ((i % (level+2)) + 1) + ".png");
            moving.add(shape);
        }
    }
}
