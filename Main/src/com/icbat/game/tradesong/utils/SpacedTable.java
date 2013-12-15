package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/***/
public class SpacedTable extends Table {

    public void spacedRow(){
        this.row();
        addSpacer();
        this.row();
    }

    public void spacedAdd(Actor actor) {
        this.add(actor);
        addSpacer();
    }

    public void spacedStack(Actor... actors) {
        this.stack(actors);
        addSpacer();
    }

    private void addSpacer() {
        this.add(new SpacingActor());
    }

    /**
     * Adds n rows
     * */
    public void spacedRows(int n) {
        for (int i=0; i<n; ++i) {
            spacedRow();
        }
    }
}
