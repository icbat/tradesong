package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.TurnTaker;
import icbat.games.tradesong.game.contracts.Contract;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.Collection;
import java.util.List;

/***/
public class PrototypeLayoutTable extends Table {
    protected final Label.LabelStyle basicLabelStyle = new Label.LabelStyle();
    protected final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
    private final Collection<Workshop> potentialWorkshops;
    private final PlayerHoldings holdings;
    private final List<Contract> contracts;

    public PrototypeLayoutTable(final TurnTaker turnTaker, Collection<Workshop> potentialWorkshops, final PlayerHoldings holdings, List<Contract> contracts) {
        this.contracts = contracts;
        basicLabelStyle.font = new BitmapFont();
        textButtonStyle.font = new BitmapFont();

        this.potentialWorkshops = potentialWorkshops;
        this.holdings = holdings;
        align(Align.top);
        setFillParent(true);

        add(new TurnCounter(turnTaker, basicLabelStyle)).pad(10).align(Align.top).colspan(2);
        row();
        add(new SpareWorkerCounter(holdings, PrototypeLayoutTable.this.basicLabelStyle)).pad(10).align(Align.top);
        add(new MoneyCounter(holdings, this.basicLabelStyle)).pad(10).align(Align.top);

        row();

        add(potentialWorkshops()).pad(10).align(Align.top);
        add(activeWorkshops()).pad(10).align(Align.top);
        row();

        add(contracts()).pad(10).align(Align.top);
        add(storage()).pad(10).align(Align.top);
    }

    private Actor contracts() {
        Table contractDisplay = new Table();
        contractDisplay.add(new Label("Contract List", basicLabelStyle)).colspan(2).pad(10).row();
        for (Contract contract : contracts) {
            contractDisplay.add(contract.getActor()).pad(5);
            contractDisplay.add(new BasicTextButton("<complete>", textButtonStyle, new ContractCompletionListener(contract, holdings)));
            contractDisplay.row();
        }
        return contractDisplay;
    }

    private Actor potentialWorkshops() {
        Table potentialDisplay = new Table();
        final Label header = new Label("Potential Workshops", this.basicLabelStyle);
        potentialDisplay.add(header).colspan(2).pad(10).row();
        for (final Workshop workshop : potentialWorkshops) {
            potentialDisplay.add(workshop.getActor()).pad(5);
            potentialDisplay.add(new BasicTextButton("Add ->", textButtonStyle, new AddWorkshopListener(workshop))).pad(5);
            potentialDisplay.row();
        }
        return potentialDisplay;
    }

    private Actor activeWorkshops() {
        Table activeDisplay = new Table();
        final Label header = new Label("Active Workshops", this.basicLabelStyle);
        activeDisplay.add(header).colspan(2).pad(10).row();
        for (final Workshop workshop : holdings.getWorkshops()) {
            activeDisplay.add(new BasicTextButton("<-", textButtonStyle, new RemoveWorkshopListener(workshop))).pad(5);
            activeDisplay.add(workshop.getActor()).pad(5);
            activeDisplay.add(new BasicTextButton("+", textButtonStyle, new AddWorkersListener(workshop, holdings.getSpareWorkers()))).pad(5);
            activeDisplay.add(new BasicTextButton("-", textButtonStyle, new RemoveWorkersListener(workshop, holdings.getSpareWorkers()))).pad(5);
            activeDisplay.row();
        }
        return activeDisplay;
    }

    private Actor storage() {
        Table storageDisplay = new Table();
        final Label storageHeader = new Label("Storage", this.basicLabelStyle);
        storageDisplay.add(storageHeader).pad(10).row();
        for (Item item : holdings.getStorage().getContents()) {
            storageDisplay.add(item.getActor()).row();
        }
        return storageDisplay;
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

    private class AddWorkersListener extends ClickListener {
        private final Workshop workshop;
        private final WorkerPool spareWorkers;

        public AddWorkersListener(Workshop workshop, WorkerPool spareWorkers) {
            this.workshop = workshop;
            this.spareWorkers = spareWorkers;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (spareWorkers.hasWorkers()) {
                workshop.getWorkers().addWorker(spareWorkers.removeWorker());
            }
            return super.touchDown(event, x, y, pointer, button);
        }
    }

    private class RemoveWorkersListener extends ClickListener {
        private final Workshop workshop;
        private final WorkerPool spareWorkers;

        public RemoveWorkersListener(Workshop workshop, WorkerPool spareWorkers) {
            this.workshop = workshop;
            this.spareWorkers = spareWorkers;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (workshop.getWorkers().hasWorkers()) {
                spareWorkers.addWorker(workshop.getWorkers().removeWorker());
            }
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


