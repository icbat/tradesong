package com.minus7games.tradesong.screens;

import com.minus7games.tradesong.Node;

/** Screen to represent a node */
public class NodeScreen extends ScreenWithHUD {

    private final Node node;

    public NodeScreen(Node node) {
        this.node = node;
        stage.addActor(node.getActor());
    }
}
