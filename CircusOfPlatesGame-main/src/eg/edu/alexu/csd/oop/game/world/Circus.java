package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.Music;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.object.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static eg.edu.alexu.csd.oop.game.Main.logger;

public class Circus implements World {
    //    private static Circus circus;
    public static int MAX_TIME = 60 * 1000;	// 1 minute
    private int score = 0;
    private long startTime = System.currentTimeMillis();
    private  int width;
    private  int height;
    private  List<GameObject> constant = new LinkedList<>();
    private List<GameObject> moving = new LinkedList<>();
    private  List<GameObject> control = new LinkedList<>();
    private ArrayList<GameObject> rightstickMoving = new ArrayList<>();
    private ArrayList<GameObject> leftstickMoving = new ArrayList<>();
    private FacadeI facade;
    private int level=1;
    Clown clown;
    Circus circus;
    public Circus(){


    }
    public Circus(int screenWidth, int screenHeight , int level) {
        width = screenWidth;
        height = screenHeight;
        this.level = level;
        // control objects (clown)
        clown = new Clown(screenWidth/2, (int)(screenHeight*0.65), "res\\clown3.png");
        control.add(clown);
        constant.add(new Surface(screenWidth/2+120, (int)(screenHeight*0.65+30), "res\\surface.png"));
        constant.add(new Surface(screenWidth/2-17/2,(int)(screenHeight*0.65+30), "res\\surface.png"));
        rightstickMoving.add(constant.get(0));
        leftstickMoving.add(constant.get(1));
        clown = (Clown)control.get(0);
        clown.registerObserver((Observer) constant.get(0));
        clown.registerObserver((Observer) constant.get(1));
        Music.getInstance().stopMusic();
        Music.getInstance().startMusic();
        ReusablePool rp = ReusablePool.getInstance();
        //for level and strategy
        rp.setPool(width, height ,level);
        moving = rp.usePool();
        facade = new Facade(this);
	logger.info("The score now = "+score+" || Time now = " +Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000));

    }

   public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

   /* private boolean intersect(GameObject o1, GameObject o2){
        return Math.abs((o1.getX()+o1.getWidth()/2) - (o2.getX()+o2.getWidth()/2)) <= o2.getWidth()-7 && (Math.abs(o1.getY()-(o2.getY()-o1.getHeight())) == 0);
    }*/
    @Override
    public boolean refresh() {
        facade.checkPool();
        return facade.refreshAll() ;
    }
    //    Change speed according to difficulty
    @Override
    public int getSpeed() {
        return level==1?10:7;
    }
    @Override
    public int getControlSpeed() {
        return 50;
    }
    @Override
    public List<GameObject> getConstantObjects() {
        return constant;
    }
    @Override
    public List<GameObject> getMovableObjects() {
        return moving;
    }
    @Override
    public List<GameObject> getControlableObjects() {
        return control;
    }

    public void setMovableObjects(List<GameObject> moving) {
        this.moving = moving;
    }

    @Override
    public int getWidth() {
        return width;
    }
    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public String getStatus() {
        return "Score=" + score + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);	// update status
    }
    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setConstant(List<GameObject> constant) {
        this.constant = constant;
    }

    public void setControl(List<GameObject> control) {
        this.control = control;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setLevel(int level) {
        this.level = level;
    }


    public List<GameObject> getlstick() {
        return leftstickMoving;
    }

    public List<GameObject> getrstick() {
        return rightstickMoving;
    }

    public void setRightstickMoving(ArrayList<GameObject> rightstickMoving) {
        this.rightstickMoving = rightstickMoving;
    }

    public void setLeftstickMoving(ArrayList<GameObject> leftstickMoving) {
        this.leftstickMoving = leftstickMoving;
    }

    public void setFacade(Facade facade) {
        this.facade = facade;
    }

    public Circus clone(){
        Circus circus =new Circus();
        circus.setWidth(width);
        circus.setHeight(height);
        circus.setLevel(level);
        circus.setScore(this.score);
        circus.setStartTime(this.startTime);
        List<GameObject> constantClone = new LinkedList<>();
        List<GameObject> controlClone = new LinkedList<>();
        List<GameObject> movingClone = new LinkedList<>();
        for(GameObject gameObject : moving){
            Shape shape = (Shape) gameObject;
            if(((Shape) gameObject).getPath().contains("gift")){
                movingClone.add(new Gift(gameObject.getX() , gameObject.getY()-6 , ((Shape) gameObject).getPath()));
            }else{
                movingClone.add(new Plate(gameObject.getX() , gameObject.getY()-6 , ((Shape) gameObject).getPath()));
            }
        }
        controlClone.add(new Clown(control.get(0).getX() , control.get(0).getY() ,"res\\clown3.png") );
        constantClone.add(new Surface(constant.get(0).getX(), constant.get(0).getY() ,"res\\surface.png"));
        constantClone.add(new Surface(constant.get(1).getX(), constant.get(1).getY() ,"res\\surface.png"));
        circus.setConstant(new LinkedList<>(constantClone));
        circus.setControl(new LinkedList<>(controlClone));
        circus.setMovableObjects(movingClone);
        ArrayList<GameObject> rightStack = new ArrayList<>();
        ArrayList<GameObject> leftStack = new ArrayList<>(leftstickMoving);
        rightStack.add(constantClone.get(0));
        leftStack.add(constantClone.get(1));
        circus.setRightstickMoving(rightStack);
        circus.setLeftstickMoving(leftStack);
        circus.setFacade(new Facade(circus));
        Clown clown1 = (Clown) controlClone.get(0);
        clown1.registerObserver((Observer) constantClone.get(0));
        clown1.registerObserver((Observer) constantClone.get(1));

        return circus;
    }


}


