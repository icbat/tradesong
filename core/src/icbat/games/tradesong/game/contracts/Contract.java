package icbat.games.tradesong.game.contracts;

import com.badlogic.gdx.scenes.scene2d.Actor;
import icbat.games.tradesong.game.PlayerHoldings;

/***/
public interface Contract {
    boolean canComplete(PlayerHoldings holdings);

    void completeContract(PlayerHoldings holdings);

    Actor getActor();
}

