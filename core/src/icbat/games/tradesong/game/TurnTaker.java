package icbat.games.tradesong.game;

import com.badlogic.gdx.Gdx;

import java.util.Collection;

public class TurnTaker {
    private final Collection<Workshop> workshops;
    private final Collection<Item> storage;

    public TurnTaker(Collection<Workshop> workshops, Collection<Item> storage) {
        this.workshops = workshops;
        this.storage = storage;
    }

    public void takeAllTurns() {
        Gdx.app.log("Turn Taker", "Taking all turns");
        for (Workshop workshop : workshops) {
            workshop.takeTurn();
        }
        Gdx.app.debug("Turn Taker", "Finished taking all turns");
    }
}
