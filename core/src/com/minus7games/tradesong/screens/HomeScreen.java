package com.minus7games.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.ScreenManager;
import com.minus7games.tradesong.indices.NodeIndex;
import com.minus7games.tradesong.screens.scene2d.LoggedClickListener;
import com.minus7games.tradesong.workshops.Node;

/** A simple screen that takes you to nodes.  */
public class HomeScreen extends ScreenWithHUD {

    @Override
    protected void resetStage() {
        super.resetStage();
        stage.addActor(makeTable());
    }

    private Table makeTable() {
        final Table table = new Table(GameSkin.get());
        table.setFillParent(true);
        table.add("Go to nodes").space(20).row();
        for (Node node : NodeIndex.getAllNodes()) {
            final TextButton goToNodeButton = new TextButton(node.getDisplayName(), GameSkin.get());
            goToNodeButton.addListener(new GoToNodeListener(node));
            table.add(goToNodeButton).row();
        }
        return table;
    }

    private class GoToNodeListener extends LoggedClickListener {

        private final Node node;

        public GoToNodeListener(Node node) {
            super("'Go to " + node.getDisplayName() + "' button");
            this.node = node;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            return super.touchDown(event, x, y, pointer, button);
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            Gdx.app.log("moving to screen", node.getDisplayName());
            ScreenManager.get().moveToScreen(new NodeScreen(node));
        }
    }
}
