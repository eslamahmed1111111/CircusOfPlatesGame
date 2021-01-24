package eg.edu.alexu.csd.oop.game.world;

public class Snapshot {
    private Circus circus;

    public Snapshot(Circus circus) {
        this.circus = circus.clone();
    }

    public Circus getOldCircus() {
        return circus;
    }
}
