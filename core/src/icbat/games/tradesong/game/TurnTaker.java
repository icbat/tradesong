package icbat.games.tradesong.game;

import com.badlogic.gdx.Gdx;

import java.util.Collection;

public class TurnTaker {
    private final Collection<Workshop> workshops;

    public TurnTaker(Collection<Workshop> workshops) {
        this.workshops = workshops;
    }

    public void takeAllTurns() {
        Gdx.app.log("Turn Taker", "Taking all turns");
        for (Workshop workshop : workshops) {
            workshop.takeTurn();
        }
        Gdx.app.debug("Turn Taker", "Finished taking all turns");
    }
}
