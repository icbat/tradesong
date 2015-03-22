package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.PlayerHoldings;

/***/
public class MoneyReward implements ContractReward {
    @Override
    public void addRewardToHoldings(PlayerHoldings holdings) {
        holdings.addCurrency(150);
    }
}
