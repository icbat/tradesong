package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/***/
public class SpacedTable extends Table {
    public Actor spacer = new SpacingActor();

    public void spacedRow(){
        this.row();
        this.add(spacer);
        this.row();
    }

    public void spacedAdd(Actor actor) {
        this.add(actor);
        this.add(spacer);
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
