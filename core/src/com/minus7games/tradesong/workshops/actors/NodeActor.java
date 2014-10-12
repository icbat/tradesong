package com.minus7games.tradesong.workshops.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.Tradesong;
import com.minus7games.tradesong.workshops.CraftingStep;
import com.minus7games.tradesong.workshops.Node;
import com.minus7games.tradesong.workshops.StepLink;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/** Actor representing a node. */
public class NodeActor extends Group {
    private final Node node;
    private final Map<Actor, CraftingStep> potentialSteps = new HashMap<Actor, CraftingStep>();
    private final Map<Actor, CraftingStep> currentSteps = new HashMap<Actor, CraftingStep>();
    private Image droppableSpace;
    public static final int USABLE_SPACE_PADDING = 10;

    public Map<Actor, CraftingStep> getPotentialSteps() {
        return potentialSteps;
    }

    public NodeActor(Node node) {
        this.node = node;
        this.setPosition(0, 0);
        this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        redraw();
    }

    public void redraw() {
        this.clear();
        potentialSteps.clear();
        currentSteps.clear();
        setupChildActors();
    }

    private void setupChildActors() {
        float usableMinX = 0;
        float usableMinY = 0;
        float usableWidth = Gdx.graphics.getWidth();
        float usableHeight = Gdx.graphics.getHeight();
        Table topTable = setupTopTable();
        this.addActor(topTable);
        usableHeight -= topTable.getPrefHeight();

        Table possibleStepsTable = setupPossibleStepsTable();
        this.addActor(possibleStepsTable);
        float height = possibleStepsTable.getPrefHeight();
        usableHeight -= height;
        usableMinY += height;

        droppableSpace = usableBackground(usableMinX, usableMinY, usableWidth, usableHeight);
        this.addActor(droppableSpace);
        Actor inputBox = inputBox(usableMinX, usableMinY, usableHeight);
        node.getInputBuffer().setPosition(inputBox.getX(), inputBox.getY());
        this.addActor(inputBox);
        Actor outputBox = outputBox(usableWidth, usableMinY, usableHeight);
        node.getOutputBuffer().setPosition(outputBox.getX(), outputBox.getY());
        this.addActor(outputBox);

        addInUseActors();
        addLinkActors();
    }

    private void addLinkActors() {
        for (StepLink link : node.getLinks()) {
            this.addActor(link.getActor());
        }
    }

    private Image usableBackground(float usableX, float usableY, float usableWidth, float usableHeight) {
        Image image = new Image((com.badlogic.gdx.graphics.Texture) Tradesong.assets.get("1p.png"));
        image.setBounds(usableX + USABLE_SPACE_PADDING, usableY + USABLE_SPACE_PADDING, usableWidth - USABLE_SPACE_PADDING * 2, usableHeight - USABLE_SPACE_PADDING * 2);
        image.setColor(Color.DARK_GRAY);
        return image;
    }

    private Actor inputBox(float usableX, float usableY, float usableHeight) {
        Actor inputActor = node.getInputBuffer().getActor();
        inputActor.setPosition(usableX + USABLE_SPACE_PADDING * 1.5f, usableY + usableHeight /2);
        return inputActor;
    }

    private Actor outputBox(float usableWidth, float usableMinY, float usableHeight) {
        Actor actor = node.getOutputBuffer().getActor();
        actor.setPosition(usableWidth - actor.getWidth() - USABLE_SPACE_PADDING * 1.5f , usableMinY + usableHeight / 2);
        return actor;
    }

    private void addInUseActors() {
        for (CraftingStep step : node.getCurrentSteps()) {
            Gdx.app.debug("Displaying in-use step ("+step.getDisplayName()+") at", "" + step.getX() + ", " + step.getY());
            Actor actor = step.getActor();
            actor.setPosition(step.getX(), step.getY());
            currentSteps.put(actor, step);
            this.addActor(actor);
        }
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
        possibleSteps.pad(20);
        possibleSteps.setFillParent(true);
        possibleSteps.align(Align.bottom);
        int maxColumns = 10;
        possibleSteps.add("Possible Crafting Steps").colspan(maxColumns).row();
        int i = 1;
        for (CraftingStep step : node.getPossibleCraftSteps()) {
            Gdx.app.debug("Showing crafting step", step.getDisplayName());
            Actor stepActor = step.getActor();
            stepActor.setTouchable(Touchable.enabled);
            potentialSteps.put(stepActor, step);
            possibleSteps.add(stepActor).pad(5);
            if (i++ >= maxColumns) {
                possibleSteps.row();
            }
        }
        return possibleSteps;
    }

    public Image getDroppableSpace() {
        return droppableSpace;
    }

    public Map<Actor, CraftingStep> getCurrentSteps() {
        return currentSteps;
    }

    public void remove(Actor actor) {
        this.currentSteps.remove(actor);
        final Collection<CraftingStep> values = currentSteps.values();
        node.setCurrentSteps(values);
    }
}