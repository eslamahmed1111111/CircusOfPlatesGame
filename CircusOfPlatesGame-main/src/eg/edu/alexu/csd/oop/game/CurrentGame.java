package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.world.Circus;


import static eg.edu.alexu.csd.oop.game.Main.logger;

class CurrentGame {
    private int level;
    private GameEngine.GameController gameController;
    CurrentGame(int level, GameEngine.GameController gameController) {
        this.level = level;
        this.gameController = gameController;
    }

    void newGame(){
        logger.info("New Game!");
        Circus.MAX_TIME = 60 * 1000;
//        If I used changeWorld , after printing gameOver ,you would lose clown control.
        new Controller(level);
//        gameController.changeWorld(new Circus(1100, 600 , level));
    }
    void pause(){
        logger.info("pause game!");
        gameController.pause() ;
    }
    void resume(){
        logger.info("resume game!");
        gameController.resume();
    }
}
