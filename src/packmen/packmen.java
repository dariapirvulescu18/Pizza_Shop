package packmen;

import model.customer;

import javax.swing.*;

public class packmen extends JFrame{
    public int score;
    public customer gamer;
    public game gamee;
    public packmen(customer gamer) {
        game game_creat = new game();
        this.gamee=game_creat;
        this.gamer = gamer;
        add(game_creat);
    }

    public int getScore() {
        gamer.set_score(this.gamee.getScore());
        return this.gamee.getScore();
    }
    public boolean isGameOver(){
        return this.gamee.getGameOver();
    }
}