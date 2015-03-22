package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workers.WorkerImpl;

/***/
public class WorkerReward implements ContractReward {

    @Override
    public void addRewardToHoldings(PlayerHoldings holdings) {
        holdings.getSpareWorkers().addWorker(new WorkerImpl());
    }
}
