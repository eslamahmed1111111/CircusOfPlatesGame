package eg.edu.alexu.csd.oop.game;

public class ActionPause implements ActionListenerCommand {
    private CurrentGame currentGame;

    public ActionPause(CurrentGame currentGame) {
        this.currentGame = currentGame;
    }

    @Override
    public void execute() {
        currentGame.pause();
        eg.edu.alexu.csd.oop.game.Music.getInstance().stopMusic();
    }
}
