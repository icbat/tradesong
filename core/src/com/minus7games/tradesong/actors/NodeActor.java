package com.minus7games.tradesong.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.minus7games.tradesong.CraftingStep;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.Node;

/** Actor representing a node. */
public class NodeActor extends Group {
    private final Node node;

    public NodeActor(Node node) {
        this.node = node;
        this.setPosition(0, 0);
        this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Table topTable = setupTopTable();
        this.addActor(topTable);
        Table possibleStepsTable = setupPossibleStepsTable();
        this.addActor(possibleStepsTable);
    }

    private Table setupTopTable() {
        Table topTable = new Table(GameSkin.get());
        topTable.setFillParent(true);
        topTable.align(Align.top);
        topTable.add(node.getDisplayName());
        return topTable;
    }

    private Table setupPossibleStepsTable() {
        Table possibleSteps = new Table(GameSkin.get());
        possibleSteps.setFillParent(true);
        possibleSteps.align(Align.bottom);
        int maxColumns = 10;
        possibleSteps.add("Possible Crafting Steps").colspan(maxColumns).row();
        int i = 1;
        for (CraftingStep step : node.getPossibleCraftSteps()) {
            Gdx.app.debug("Showing crafting step", step.getDisplayName());
            possibleSteps.add(step.getActor()).pad(5);
            if (i++ >= maxColumns) {
                possibleSteps.row();
            }
        }
        return possibleSteps;
    }
}