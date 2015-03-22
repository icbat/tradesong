package icbat.games.tradesong.game.contracts;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;

/***/
public class ContractImpl implements Contract {
    private final Item requiredItem;
    private final ContractReward reward = new WorkerReward();

    public ContractImpl(Item requiredItem) {
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
        holdings.getStorage().remove(requiredItem);
        reward.addRewardToHoldings(holdings);
    }

    @Override
    public Actor getActor() {
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        return new Label("Bring me one " + requiredItem.getName() + "!", style);
    }
}
