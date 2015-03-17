package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.PlayerHoldings;

/***/
public interface Contract {
    boolean canComplete(PlayerHoldings holdings);

    void completeContract(PlayerHoldings holdings);
}

