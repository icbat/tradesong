package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.utils.SpacedTable;
import com.icbat.game.tradesong.utils.SpacingActor;

/***/
public class ContractsStage extends BaseStage {
    @Override
    public void layout() {
        super.layout();
        this.clear();

        this.addActor(makeLayoutTable());
    }

    private Table makeLayoutTable() {
        Table table = new Table();
        table.add(new Label("Contracts", Tradesong.uiStyles.getLabelStyle()));
        table.row();
        table.add(new SpacingActor(1, 20));
        table.row();
        table.add(makeContractsDisplay());

        table.setFillParent(true);
        return table;
    }

    private SpacedTable makeContractsDisplay() {
        SpacedTable table = new SpacedTable();

        for (Contract contract : Tradesong.contractList) {
            table.add(new Label(contract.getRarity() + " contract", Tradesong.uiStyles.getLabelStyle()));
            table.spacedRow();
            SpacedTable requirementTable = new SpacedTable();
            for (Item requiredItem : contract.getRequirements()) {
                requirementTable.spacedAdd(new Item(requiredItem));
            }
            table.spacedAdd(requirementTable);
            table.spacedRows(2);
        }

        return table;
    }
}
