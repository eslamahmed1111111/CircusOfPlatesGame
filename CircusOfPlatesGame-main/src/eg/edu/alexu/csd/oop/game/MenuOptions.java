package eg.edu.alexu.csd.oop.game;

class MenuOptions {
    private ActionListenerCommand actionNewGame;
    private ActionListenerCommand actionPause;
    private ActionListenerCommand actionResume;

    MenuOptions(ActionListenerCommand actionNewGame, ActionListenerCommand actionPause, ActionListenerCommand actionResume) {
        this.actionNewGame = actionNewGame;
        this.actionPause = actionPause;
        this.actionResume = actionResume;
    }
    void clickNew(){
        actionNewGame.execute();
    }
    void clickPause(){
        actionPause.execute();
    }
    void clickResume(){
        actionResume.execute();
    }
}
