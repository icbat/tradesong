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

    public Collection<Workshop> getActiveWorkshops() {
        return workshops;
    }

    public void takeAllTurns() {
        Gdx.app.log("Turn Taker", "Taking all turns");
        for (Workshop workshop : workshops) {
            workshop.takeTurn();
            if (workshop.hasOutput()) {
                final Item output = workshop.getNextOutput();
                storage.add(output);
            }
        }
        Gdx.app.debug("Turn Taker", "Finished taking all turns");
        Gdx.app.debug("Turn Taker", "After taking turns, storage is now holding " + storage.size());
    }
}
