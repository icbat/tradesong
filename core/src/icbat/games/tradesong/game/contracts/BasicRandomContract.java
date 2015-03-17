package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workers.WorkerImpl;

/***/
public class BasicRandomContract implements Contract {
    private final Item requiredItem;

    public BasicRandomContract(Item requiredItem) {
        this.requiredItem = requiredItem;
    }

    @Override
    public boolean canComplete(PlayerHoldings holdings) {
        return holdings.getStorage().contains(requiredItem);
    }

    @Override
    public void completeContract(PlayerHoldings holdings) {
        if (!canComplete(holdings)) {
            throw new IllegalStateException("Dev error! contract tried to complete w/o requirements");
        }

        holdings.removeFromStorage(requiredItem);
        holdings.getWorkers().addWorker(new WorkerImpl());
    }
}
