package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.Constants;

public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen() {
        this.stages.add(new MainMenuStage());
    }

    public static class MainMenuStage extends Stage {

        public MainMenuStage() {
            Tradesong.clock.stop();

            Table table = new Table();
            table.setFillParent(true);

            table.add(makeTitleLabel()).spaceBottom(25).colspan(2).row();

            for (int i=1; i < Constants.NUMBER_OF_SAVE_SLOTS.value(); ++i) {
                table.add(makeSlotButton(i)).spaceRight(5);
                table.add(makeDeleteButton(i)).row();
            }

            table.add(makeSettingsButton()).spaceTop(15).colspan(2).row();
            table.add(makeExitButton()).colspan(2).row();

            this.addActor(table);
        }

        private Actor makeTitleLabel() {
            return new Label("Tradesong", Tradesong.uiStyles);
        }

        private Actor makeSlotButton(final int slotNumber) {
            TextButton start = new TextButton("Slot " + slotNumber, Tradesong.uiStyles);
            start.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    Tradesong.tryLoadingSlot(slotNumber);
                }
            });
            return start;
        }

        private Actor makeDeleteButton(final int slotNumber) {
            final TextButton deleteButton = new TextButton("X", Tradesong.uiStyles.get("disabled", TextButton.TextButtonStyle.class));
            if (Tradesong.slotExists(slotNumber)) {
                deleteButton.setStyle(Tradesong.uiStyles.get("redText", TextButton.TextButtonStyle.class));
                deleteButton.addListener(new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        Tradesong.deleteSlot(slotNumber);
                        deleteButton.setStyle(Tradesong.uiStyles.get("disabled", TextButton.TextButtonStyle.class));
                    }
                });
            }
            return deleteButton;
        }

        private Actor makeSettingsButton() {
            return new TextButton("Settings", Tradesong.uiStyles.get("disabled", TextButton.TextButtonStyle.class));
        }

        private Actor makeExitButton() {
            TextButton exit = new TextButton("Exit", Tradesong.uiStyles);
            exit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    Gdx.app.exit();
                }
            });

            return exit;
        }
    }

    @Override
    public String getScreenName() {
        return "mainMenuScreen";
    }
}
