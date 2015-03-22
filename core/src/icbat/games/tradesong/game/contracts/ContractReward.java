package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.PlayerHoldings;

/***/
public interface ContractReward {
    void addRewardToHoldings(PlayerHoldings holdings);

    String name();
}
