package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.TurnTaker;
import icbat.games.tradesong.game.contracts.Contract;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.Collection;
import java.util.List;

/***/
public class PrototypeLayoutTable extends Table {
    private final Collection<Workshop> potentialWorkshops;
    private final PlayerHoldings holdings;
    private final List<Contract> contracts;

    public PrototypeLayoutTable(final TurnTaker turnTaker, Collection<Workshop> potentialWorkshops, final PlayerHoldings holdings, List<Contract> contracts) {
        this.contracts = contracts;

        this.potentialWorkshops = potentialWorkshops;
        this.holdings = holdings;
        align(Align.top);
        setFillParent(true);

        add(new TurnCounter(turnTaker)).pad(10).align(Align.top).colspan(2);
        row();
        add(new SpareWorkerCounter(holdings)).pad(10).align(Align.top);
        add(new MoneyCounter(holdings)).pad(10).align(Align.top);

        row();

        add(potentialWorkshops()).pad(10).align(Align.top);
        add(activeWorkshops()).pad(10).align(Align.top);
        row();

        add(contracts()).pad(10).align(Align.top);
        add(new StorageDisplay()).pad(10).align(Align.top);
    }

    private Actor contracts() {
        Table contractDisplay = new Table(TradesongGame.skin);
        contractDisplay.add("Contract List").colspan(2).pad(10).row();
        for (Contract contract : contracts) {
            contractDisplay.add(contract.getActor()).pad(5);
            contractDisplay.add(new BasicTextButton("<complete>", new ContractCompletionListener(contract, holdings)));
            contractDisplay.row();
        }
        return contractDisplay;
    }

    private Actor potentialWorkshops() {
        Table potentialDisplay = new Table(TradesongGame.skin);
        potentialDisplay.add("Potential Workshops").colspan(2).pad(10).row();
        for (final Workshop workshop : potentialWorkshops) {
            potentialDisplay.add(workshop.getActor()).pad(5);
            potentialDisplay.add(new BasicTextButton("Add ->", new AddWorkshopListener(workshop))).pad(5);
            potentialDisplay.row();
        }
        return potentialDisplay;
    }

    private Actor activeWorkshops() {
        Table activeDisplay = new Table(TradesongGame.skin);
        activeDisplay.add("Active Workshops").colspan(2).pad(10).row();
        for (final Workshop workshop : holdings.getWorkshops()) {
            activeDisplay.add(new BasicTextButton("<-", new RemoveWorkshopListener(workshop))).pad(5);
            activeDisplay.add(workshop.getActor()).pad(5);
            activeDisplay.add(new AddWorkersButton(workshop, holdings.getSpareWorkers())).pad(5);
            activeDisplay.add(new RemoveWorkersButton(workshop, holdings.getSpareWorkers())).pad(5);
            activeDisplay.row();
        }
        return activeDisplay;
    }

    private class RemoveWorkshopListener extends ClickListener {
        private final Workshop workshop;

        public RemoveWorkshopListener(Workshop workshop) {
            this.workshop = workshop;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            Gdx.app.log("add button", "clicked!");
            holdings.removeWorkshop(workshop);
            return super.touchDown(event, x, y, pointer, button);
        }
    }

    private class AddWorkshopListener extends ClickListener {
        private final Workshop workshop;

        public AddWorkshopListener(Workshop workshop) {
            this.workshop = workshop;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            holdings.addWorkshop(workshop.spawnClone());
            return super.touchDown(event, x, y, pointer, button);
        }
    }


    private class ContractCompletionListener extends ClickListener {
        private final Contract contract;
        private final PlayerHoldings holdings;

        public ContractCompletionListener(Contract contract, PlayerHoldings holdings) {
            this.contract = contract;
            this.holdings = holdings;
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


