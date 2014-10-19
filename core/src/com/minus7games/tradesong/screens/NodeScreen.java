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
    protected void resetStage() {
        super.resetStage();
        nodeActor = (NodeActor) node.getActor();
        setupDragAndDrop();
        stage.addActor(nodeActor);
    }

    private void setupDragAndDrop() {
        Gdx.app.debug("Node Actor", "Setting up Drag and Drop");
        DragAndDrop dragAndDrop = new DragAndDrop();

        addPotentialSteps(dragAndDrop);
        addInUseSteps(dragAndDrop);
        addDroppableSpace(dragAndDrop);
    }

    private void addPotentialSteps(DragAndDrop dragAndDrop) {
        for (final Map.Entry<Actor, CraftingStep> entry : nodeActor.getPotentialSteps().entrySet()) {
            final CraftingStep payloadObject = entry.getValue();
            final Actor dragActor = entry.getValue().getActor();
            final Actor owningActor = entry.getKey();
            dragAndDrop.addSource(new DragAndDrop.Source(owningActor) {
                @Override
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    Gdx.app.debug("drag started", this.getActor().toString());
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setObject(payloadObject);
                    payload.setDragActor(dragActor);
                    return payload;
                }
            });
            Gdx.app.debug("dnd source added for 'potential'", entry.getValue().getDisplayName());
        }
    }

    private void addInUseSteps(DragAndDrop dragAndDrop) {
        for (final Map.Entry<Actor, CraftingStep> entry : nodeActor.getCurrentSteps().entrySet()) {
            final CraftingStep craftingStep = entry.getValue();
            final Actor dragActor = entry.getKey();
            dragAndDrop.addSource(new DragAndDrop.Source(dragActor) {
                @Override
                public DragAndDrop.Payload dragStart(InputEvent event, float x, float y, int pointer) {
                    Gdx.app.log("drag started", this.getActor().getName());
                    DragAndDrop.Payload payload = new DragAndDrop.Payload();
                    payload.setObject(craftingStep);
                    payload.setDragActor(dragActor);
                    nodeActor.remove(dragActor);
                    return payload;
                }
            });
            Gdx.app.debug("dnd source added for 'in use'", craftingStep.getDisplayName());
        }
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
                nodeActor.redraw();
                setupDragAndDrop();
            }

            @Override
            public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
                final float y1 = payload.getDragActor().getY();
                final float x1 = payload.getDragActor().getX();
                Gdx.app.debug("droppable space", "Something was dropped at " + x1 + ", " + y1);
                node.addToWorkflow((CraftingStep) payload.getObject(), x1, y1);
                nodeActor.redraw();
                setupDragAndDrop();
            }
        });
    }

}
