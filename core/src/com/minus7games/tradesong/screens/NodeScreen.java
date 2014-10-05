package com.minus7games.tradesong.screens;

import com.minus7games.tradesong.workshops.Node;

/** Screen to represent a node */
public class NodeScreen extends ScreenWithHUD {

    private final Node node;

    public NodeScreen(Node node) {
        this.node = node;
    }

    @Override
    public void resize(int width, int height) {
        stage.clear();
        stage.addActor(node.getActor());
        super.resize(width, height);
    }
}
