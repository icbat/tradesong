package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.PlayerHoldings;

/***/
public class MoneyReward implements ContractReward {
    private final int amount;

    public MoneyReward(int amount) {
        this.amount = amount;
    }

    @Override
    public void addRewardToHoldings(PlayerHoldings holdings) {
        holdings.addCurrency(amount);
    }
}
