package eg.edu.alexu.csd.oop.game.world;

public class Originator {
    private Circus circus;

    public  void setOldCircus(Circus circus){
        this.circus = circus.clone();
    }

    public Circus getOldCircus() {
        return circus;
    }
    public Snapshot saveToStack(){
        return new Snapshot(circus);
    }
}
