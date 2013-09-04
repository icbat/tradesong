package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.icbat.game.tradesong.Tradesong;

public class MainMenuStage extends AbstractStage {

    private final Tradesong gameInstance;
    private TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
    private Table table = new Table();

    public MainMenuStage(final Tradesong gameInstance) {
        this.gameInstance = gameInstance;

        this.buttonStyle.font = new BitmapFont();
        this.buttonStyle.fontColor = Color.WHITE;
        this.buttonStyle.overFontColor = Color.BLUE;

        this.table.setFillParent(true);
        this.addActor(this.table);

        layout();
    }

    @Override
    public void layout() {

        table.clear();

        table.add(newStartButton());
        table.row();
        table.add(newSettingsButton());
        table.row();
        table.add(newExitButton());
    }

    private TextButton newStartButton() {
        TextButton startButton = new TextButton("Start game", buttonStyle);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameInstance.goToScreen(Tradesong.ScreenTypes.TOWN);
            }
        });

        return startButton;
    }

    private TextButton newSettingsButton() {
        TextButton settingsButton = new TextButton("Settings", buttonStyle);

        settingsButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameInstance.goToScreen(Tradesong.ScreenTypes.SETTINGS);
            }
        });

        return settingsButton;
    }

    private TextButton newExitButton() {
        TextButton exitButton = new TextButton("Exit game", buttonStyle);

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        return exitButton;
    }


}
