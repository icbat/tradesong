package com.minus7games.tradesong.workshops.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private final Image droppableSpace;

    public NodeActor(Node node) {
        this.node = node;
        this.setPosition(0, 0);
        this.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Table topTable = setupTopTable();
        this.addActor(topTable);

        Table possibleStepsTable = setupPossibleStepsTable();

        float height = Gdx.graphics.getHeight() - topTable.getPrefHeight() - possibleStepsTable.getPrefHeight();
        droppableSpace = setupUsableSpace(Gdx.graphics.getWidth(), height, 0, possibleStepsTable.getPrefHeight());
        this.addActor(droppableSpace);

        addInUseActors();
        setupDragAndDrop();

        this.addActor(possibleStepsTable);
    }

    private void setupDragAndDrop() {
        Gdx.app.log("Node Actor", "Setting up Drag and Drop");
        for (final Actor possibleStep : this.potentialSteps) {
            dragAndDrop.addSource(new DragAndDrop.Source(possibleStep) {
                @Override
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    Gdx.app.log("drag started", this.getActor().toString());
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setDragActor(possibleStep);
                    return payload;
                }
            });
            Gdx.app.log("dnd source added for", possibleStep.toString());
        }
        dragAndDrop.addTarget(new DragAndDrop.Target(droppableSpace) {
            @Override
            public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                getActor().setColor(Color.GREEN);
                return true;
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                Gdx.app.log("Node actor", "Something was dropped!");
            }
        });

    }

    private void addInUseActors() {
        for (CraftingStepInUse step : node.getCurrentSteps()) {
            Actor actor = step.get().getActor();
            actor.setPosition(step.getX(), step.getY());
            actualSteps.add(actor);
        }
    }

    private Image setupUsableSpace(float width, float height, float x, float y) {
        Image image = new Image((com.badlogic.gdx.graphics.Texture) Tradesong.assets.get("1p.png"));
        image.setSize(width, height);
        image.setPosition(x, y);
        image.setColor(Color.DARK_GRAY);
        return image;
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