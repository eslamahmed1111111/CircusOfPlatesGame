package eg.edu.alexu.csd.oop.game.world;

import java.util.Stack;

public class Caretaker {
    public static Stack<Snapshot> snapshots = new Stack<>();

    void add(Snapshot snapshot){
        snapshots.push(snapshot);
    }
    public Snapshot getLast(){
        return snapshots.pop();
    }

}
