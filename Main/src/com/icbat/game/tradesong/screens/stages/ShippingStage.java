package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.utils.SpacedTable;

/***/
public class ShippingStage extends InventoryStage {
    protected Label contractLabel = new Label("", Tradesong.uiStyles.getLabelStyle());

    @Override
    public void layout() {
        this.clear();

        SpacedTable layoutTable = new SpacedTable();

        layoutTable.add(new Label("Outgoing shipments", Tradesong.uiStyles.getLabelStyle()));
        layoutTable.spacedRows(3);
        layoutTable.add(makeInventoryTable());
        layoutTable.spacedRows(2);
        layoutTable.add(makeShipmentTable());
        layoutTable.spacedRow();
        layoutTable.add(makeShipmentButton());
        layoutTable.spacedRow();
        layoutTable.add(contractLabel);

        layoutTable.setFillParent(true);
        this.addActor(layoutTable);
    }

    private Actor makeShipmentTable() {
        return null;
    }

    private Actor makeShipmentButton() {
        return null;
    }

    public void updateContractCheck(String whatHits) {
        contractLabel.setText(whatHits);
    }

}
