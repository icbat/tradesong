package icbat.games.tradesong.game;

import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.game.workshops.ItemConsumer;
import icbat.games.tradesong.game.workshops.ItemProducer;
import icbat.games.tradesong.game.workshops.Workshop;

public class TurnTaker {
    private final PlayerHoldings holdings;
    private final Storage storage;
    private int currentTurn = 1;

    public TurnTaker(PlayerHoldings holdings) {

        this.holdings = holdings;
        storage = holdings.getStorage();
    }

    public void takeAllTurns() {
        Gdx.app.log("Turn Taker", "Taking turn " + currentTurn);
        acceptAnyInputs();
        takeTurnOnAllWorkshops();
        moveAllOutputsToStorage();
        currentTurn++;
        Gdx.app.debug("Turn Taker", "Finished taking all turns");
        Gdx.app.debug("Turn Taker", "After taking turns, storage is now holding " + storage.size());
    }

    // TODO name this better
    private void acceptAnyInputs() {
        for (ItemConsumer consumer : holdings.getItemConsumers()) {
            for (Item storedItem : storage.getContents()) {
                if (consumer.acceptsInput(storedItem)) {
                    consumer.sendInput(storage.remove(storedItem));
                    break;
                }
            }
        }
    }

    private void moveAllOutputsToStorage() {
        int workersSpent = 0;
        // TODO rename holdings getItemCreators to producers
        for (ItemProducer workshopWithOutput : holdings.getItemCreators()) {
            if (workersSpent >= storage.getWorkersAssignedToStorage().size()) {
                break;
            }
            if (workshopWithOutput.hasOutput()) {
                storage.storeItem(workshopWithOutput.getNextOutput());
                workersSpent++;
            }
        }

    }

    private void takeTurnOnAllWorkshops() {
        for (Workshop workshop : holdings.getWorkshops()) {
            workshop.takeTurn();
        }
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
