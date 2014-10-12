package com.minus7games.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.minus7games.tradesong.workshops.CraftingStep;
import com.minus7games.tradesong.workshops.Node;
import com.minus7games.tradesong.workshops.actors.NodeActor;

import java.util.Map;

/** Screen to represent a node */
public class NodeScreen extends ScreenWithHUD {

    private final Node node;
    private NodeActor nodeActor;

    public NodeScreen(Node node) {
        this.node = node;
    }

    @Override
    public void resize(int width, int height) {
        stage.clear();
        nodeActor = (NodeActor) node.getActor();
        setupDragAndDrop();
        stage.addActor(nodeActor);
        super.resize(width, height);
    }

    private void setupDragAndDrop() {
        Gdx.app.log("Node Actor", "Setting up Drag and Drop");
        DragAndDrop dragAndDrop = new DragAndDrop();
        for (final Map.Entry<CraftingStep, Actor> entry : nodeActor.getPotentialSteps().entrySet()) {
            final CraftingStep payloadObject = entry.getKey();
            final Actor dragActor = entry.getKey().getActor();
            final Actor owningActor = entry.getValue();
            dragAndDrop.addSource(makeSource(owningActor, dragActor, payloadObject));
            Gdx.app.log("dnd source added for 'potential'", entry.getKey().getDisplayName());
        }
        for (final Map.Entry<CraftingStep, Actor> entry : nodeActor.getCurrentSteps().entrySet()) {
            final CraftingStep craftingStep = entry.getKey();
            final Actor owningActor = entry.getValue();
            final Actor dragActor = entry.getValue();
            dragAndDrop.addSource(makeSource(owningActor, dragActor, craftingStep));
            Gdx.app.log("dnd source added for 'in use'", craftingStep.getDisplayName());
        }

        addDroppableSpace(dragAndDrop);
    }

    private DragAndDrop.Source makeSource(final Actor owningActor, final Actor dragActor, final CraftingStep payloadObject) {
        return new DragAndDrop.Source(owningActor) {
            @Override
            public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                Gdx.app.log("drag started", this.getActor().toString());
                DragAndDrop.Payload payload = new DragAndDrop.Payload();
                payload.setObject(payloadObject);
                payload.setDragActor(dragActor);
                return payload;
            }
        };
    }

    private void addDroppableSpace(DragAndDrop dragAndDrop) {
        dragAndDrop.addTarget(new DragAndDrop.Target(nodeActor.getDroppableSpace()) {
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
                final float y1 = payload.getDragActor().getY();
                final float x1 = payload.getDragActor().getX();
                Gdx.app.log("Node actor", "Something was dropped at " + x1 + ", " + y1);
                node.addToWorkflow((CraftingStep) payload.getObject(), x1, y1);
                nodeActor.redraw();
                setupDragAndDrop();
            }
        });
    }

}
