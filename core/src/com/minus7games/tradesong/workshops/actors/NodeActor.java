package com.minus7games.tradesong.workshops.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.Tradesong;
import com.minus7games.tradesong.workshops.CraftingStep;
import com.minus7games.tradesong.workshops.CraftingStepInUse;
import com.minus7games.tradesong.workshops.Node;

import java.util.ArrayList;
import java.util.List;

/** Actor representing a node. */
public class NodeActor extends Group {
    private final Node node;
    private final List<Actor> potentialSteps = new ArrayList<Actor>();
    private final List<Actor> actualSteps = new ArrayList<Actor>();
    private final DragAndDrop dragAndDrop = new DragAndDrop();
    private Image droppableSpace;
    private Actor inputBox;
    private float usableMinX = 0;
    private float usableMinY = 0;
    private float usableWidth = Gdx.graphics.getWidth();
    private float usableHeight = Gdx.graphics.getHeight();

    public NodeActor(Node node) {
        this.node = node;
        this.setPosition(0, 0);
        this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        setupChildActors();
        setupDragAndDrop();
    }

    private void setupChildActors() {
        Table topTable = setupTopTable();
        this.addActor(topTable);
        addOnTop(topTable.getPrefHeight());

        Table possibleStepsTable = setupPossibleStepsTable();
        this.addActor(possibleStepsTable);
        addOnBottom(possibleStepsTable.getPrefHeight());

        inputBox = inputBox(usableMinX, usableMinY, usableHeight);
        this.addActor(inputBox);
        addOnLeft(inputBox.getWidth());
        droppableSpace = usableBackground(usableMinX, usableMinY, usableWidth, usableHeight);
        this.addActor(droppableSpace);

        addInUseActors();
    }

    private void addOnBottom(float height) {
        usableHeight -= height;
        usableMinY += height;
    }

    private void addOnTop(float height) {
        usableHeight -= height;
    }

    private void addOnLeft(float adjustBy) {
        usableMinX += adjustBy;
        usableWidth -= adjustBy;
    }

    private Image usableBackground(float usableX, float usableY, float usableWidth, float usableHeight) {
        Image image = new Image((com.badlogic.gdx.graphics.Texture) Tradesong.assets.get("1p.png"));
        final int padding = 10;
        image.setBounds(usableX + padding, usableY + padding, usableWidth - padding, usableHeight - padding);
        image.setColor(Color.DARK_GRAY);
        return image;
    }

    private Actor inputBox(float usableX, float usableY, float usableHeight) {
        Actor inputActor = node.getInputBuffer().getActor();
        inputActor.setPosition(usableX, usableY + usableHeight /2);
        return inputActor;
    }

    private void setupDragAndDrop() {
        Gdx.app.log("Node Actor", "Setting up Drag and Drop");
        for (Actor possibleStep : this.potentialSteps) {

            dragAndDrop.addSource(new DragAndDrop.Source(possibleStep) {
                @Override
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    Gdx.app.log("drag started", this.getActor().toString());
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setDragActor(copyOf(getActor()));
                    return payload;
                }
            });
            Gdx.app.log("dnd source added for", possibleStep.toString());
        }
        dragAndDrop.addTarget(new DragAndDrop.Target(droppableSpace) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GRAY);
                return true;
            }

            @Override
            public void reset(DragAndDrop.Source source, DragAndDrop.Payload payload) {
                super.reset(source, payload);
                getActor().setColor(Color.DARK_GRAY);
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Gdx.app.log("Node actor", "Something was dropped at " + x + ", " + y);
            }
        });

    }

    private Actor copyOf(Actor actor) {
        Label oldLabel = (Label) actor;
        return new Label(oldLabel.getText(), GameSkin.get());
    }

    private void addInUseActors() {
        for (CraftingStepInUse step : node.getCurrentSteps()) {
            Actor actor = step.get().getActor();
            actor.setPosition(step.getX(), step.getY());
            actualSteps.add(actor);
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
            potentialSteps.add(stepActor);
            possibleSteps.add(stepActor).pad(5);
            if (i++ >= maxColumns) {
                possibleSteps.row();
            }
        }
        return possibleSteps;
    }
}