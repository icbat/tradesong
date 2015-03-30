package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.contracts.Contract;

import java.util.List;

/***/
public class ContractsDisplay extends Table {
    public ContractsDisplay() {
        super(TradesongGame.skin);
        add("Contract List").colspan(2).pad(10).row();
        for (Contract contract : TradesongGame.contracts) {
            add(contract.getActor()).pad(5);
            add(new BasicTextButton("<complete>", new ContractCompletionListener(contract, TradesongGame.holdings, TradesongGame.contracts)));
            row();
        }
    }

    /***/
    static class ContractCompletionListener extends ClickListener {
        private final Contract contract;
        private final PlayerHoldings holdings;
        protected List<Contract> contracts;

        public ContractCompletionListener(Contract contract, PlayerHoldings holdings, List<Contract> contracts) {
            this.contract = contract;
            this.holdings = holdings;
            this.contracts = contracts;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.app.debug("can complete?", "" + contract.canComplete(holdings));
            if (contract.canComplete(holdings)) {
                contract.completeContract(holdings);
                contracts.remove(contract);
            }
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
