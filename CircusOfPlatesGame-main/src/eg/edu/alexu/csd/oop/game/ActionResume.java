package eg.edu.alexu.csd.oop.game;

public class ActionResume implements ActionListenerCommand {
    private CurrentGame currentGame;

    public ActionResume(CurrentGame currentGame) {
        this.currentGame = currentGame;
    }

    @Override
    public void execute() {
        currentGame.resume();
        eg.edu.alexu.csd.oop.game.Music.getInstance().stopMusic();
        eg.edu.alexu.csd.oop.game.Music.getInstance().startMusic();
    }
}
