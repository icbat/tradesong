package com.tradesonggame.screens.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.TextureAssets;

public class InfoBlock extends Table {

    public InfoBlock() {
        this.add(new MoneyCounter()).align(Align.left).row();
        this.add(new TimeTracker());
        this.setFillParent(true);
        this.align(Align.top + Align.left);
    }

    private class MoneyCounter extends Table {
        public MoneyCounter() {
            this.add(makeIcon());
            this.add(makeCount());
        }

        private Actor makeIcon() {
            return new Image(TextureAssets.ITEMS.getRegion(4,30));
        }

        private Actor makeCount() {
            return new Label("", Tradesong.uiStyles) {
//                @Override
//                public void draw(SpriteBatch batch, float parentAlpha) {
//                    this.setText(Tradesong.state.inventory().getMoney().toString());
//                    super.draw(batch, parentAlpha);
//                }
            };
        }
    }

    private class TimeTracker extends Table {
        private TimeTracker() {
            this.add(makeIcon());
            this.add(makeClockText());
        }

        private Actor makeIcon() {
            return new Image(TextureAssets.ITEMS.getRegion(14,16));
        }
        private Actor makeClockText() {
            return new Label("", Tradesong.uiStyles) {
//                @Override
//                public void draw(SpriteBatch batch, float parentAlpha) {
//                    this.setText(Tradesong.clock.getTimeString());
//                    super.draw(batch, parentAlpha);
//                }
            };
        }
    }
}
