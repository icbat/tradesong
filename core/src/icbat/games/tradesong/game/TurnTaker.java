package icbat.games.tradesong.game;

import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.game.contracts.Contract;
import icbat.games.tradesong.game.contracts.ContractFactory;
import icbat.games.tradesong.game.workshops.ItemConsumer;
import icbat.games.tradesong.game.workshops.ItemProducer;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TurnTaker {
    private final PlayerHoldings holdings;
    private final Collection<Contract> contracts;
    private final ContractFactory contractFactory;
    private final Storage storage;
    private int currentTurn = 1;

    public TurnTaker(PlayerHoldings holdings, Collection<Contract> contracts, ContractFactory contractFactory) {
        this.holdings = holdings;
        this.contracts = contracts;
        this.contractFactory = contractFactory;
        storage = holdings.getStorage();
    }

    public void takeAllTurns() {
        Gdx.app.log("Turn Taker", "Taking turn " + currentTurn);
        moveAllInputs();
        takeTurnOnAllWorkshops();
        moveAllOutputsToStorage();
        if ((currentTurn - 5) % 10 == 0) {
            contracts.add(contractFactory.buildRandomContract());
        }
        currentTurn++;
        Gdx.app.debug("Turn Taker", "Finished taking all turns");
        Gdx.app.debug("Turn Taker", "After taking turns, storage is now holding " + storage.size());
    }

    private void moveAllInputs() {
        if (!storage.getWorkers().hasWorkers()) {
            return;
        }
        int workersSpent = 0;
        List<Item> itemsMoved = new ArrayList<Item>();
        for (ItemConsumer consumer : holdings.getItemConsumers()) {
            if (workersSpent >= storage.getWorkers().size()) {
                break;
            }
            for (Item storedItem : storage.getContents()) {
                if (consumer.acceptsInput(storedItem)) {
                    itemsMoved.add(storedItem);
                    consumer.sendInput(storedItem);
                    workersSpent++;
                    if (workersSpent >= storage.getWorkers().size()) {
                        break;
                    }
                }
            }
        }
        for (Item item : itemsMoved) {
            storage.remove(item);
        }
    }

    private void moveAllOutputsToStorage() {
        int workersSpent = 0;
        for (ItemProducer workshopWithOutput : holdings.getItemProducers()) {
            if (workersSpent >= storage.getWorkers().size()) {
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
