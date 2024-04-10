package packmen;

import model.Customer;

import javax.swing.*;

public class PacMan extends JFrame{
    public int score;
    public Customer gamer;
    public GamePacMan gamePacMan;
    public PacMan(Customer gamer) {
        GamePacMan gameCreated = new GamePacMan();
        this.gamePacMan =gameCreated;
        this.gamer = gamer;
        add(gameCreated);
    }

    public int getScore() {
        gamer.setScore(this.gamePacMan.getScore());
        return this.gamePacMan.getScore();
    }
    public boolean isGameOver(){
        return this.gamePacMan.getGameOver();
    }
}