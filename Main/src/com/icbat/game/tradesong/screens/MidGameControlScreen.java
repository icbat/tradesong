package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.esotericsoftware.tablelayout.Cell;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;

/***/
public class MidGameControlScreen extends AbstractScreen {

    public MidGameControlScreen() {
        stages.add(new MidGameControlStage());
    }

    @Override
    public String getScreenName() {
        return "midGameController";
    }

    private class MidGameControlStage extends Stage {
        private MidGameControlStage() {
            Tradesong.clock.pause();
            Table table = new Table(Tradesong.uiStyles);
            table.setFillParent(true);
            table.add(new Image(TextureAssets.ITEMS.getRegion(12,17)));
            table.add(makeResumeButton()).row();
            table.add(new Image(TextureAssets.ITEMS.getRegion(13,17)));
            table.add(makeSaveButton()).row();
            table.add(new Image(TextureAssets.ITEMS.getRegion(14,4)));
            table.add(makeSettingsButton()).row();
            table.add(new Image(TextureAssets.ITEMS.getRegion(15,16)));
            table.add(makeSaveAndExitButton()).row();

            for(Cell cell : table.getCells()) {
                cell.spaceBottom(20);
                cell.align(Align.left);
                cell.spaceRight(5);
            }

            this.addActor(table);
        }

        private Actor makeResumeButton() {
            final TextButton resumeButton = new TextButton("Resume", Tradesong.uiStyles);
            resumeButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.debug("resume", "clicked");
                    super.clicked(event, x, y);
                    resumeGame();
                }
            });
            return resumeButton;
        }

        private Actor makeSaveButton() {
            return new TextButton("Save and Resume", Tradesong.uiStyles);
        }

        private Actor makeSettingsButton() {
            return new TextButton("Change settings", Tradesong.uiStyles.get("disabled", TextButton.TextButtonStyle.class));
        }

        private Actor makeSaveAndExitButton() {
            return new TextButton("Save and Exit", Tradesong.uiStyles);
        }

        private void resumeGame() {
            Tradesong.clock.resume();
            Tradesong.screenManager.goBack();
        }
    }
}
