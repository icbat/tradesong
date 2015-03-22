package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;

/***/
public class ItemReward implements ContractReward {
    @Override
    public void addRewardToHoldings(PlayerHoldings holdings) {
        holdings.getStorage().storeItem(new Item("A Reward"));
    }
}
