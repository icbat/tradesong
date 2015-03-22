package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;

/***/
public class ItemReward implements ContractReward {
    private final Item reward;

    public ItemReward(Item reward) {
        this.reward = reward;
    }

    @Override
    public void addRewardToHoldings(PlayerHoldings holdings) {
        holdings.getStorage().storeItem(reward);
    }

    @Override
    public String name() {
        return "reward: " + reward.getName();
    }
}
