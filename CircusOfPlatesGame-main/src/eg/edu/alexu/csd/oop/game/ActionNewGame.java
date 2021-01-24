package eg.edu.alexu.csd.oop.game;

public class ActionNewGame implements ActionListenerCommand {
    private CurrentGame currentGame;

    public ActionNewGame(CurrentGame currentGame) {
        this.currentGame = currentGame;
    }

    @Override
    public void execute() {
        currentGame.newGame();
    }
}
