package icbat.games.tradesong.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.TurnTaker;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.Collection;

/***/
public class PrototypeLayoutTable extends Table {
    protected final Label.LabelStyle basicLabelStyle = new Label.LabelStyle();
    private final Collection<Workshop> potentialWorkshops;
    private final PlayerHoldings holdings;


    public PrototypeLayoutTable(final TurnTaker turnTaker, Collection<Workshop> potentialWorkshops, PlayerHoldings holdings) {
        basicLabelStyle.font = new BitmapFont();

        this.potentialWorkshops = potentialWorkshops;
        this.holdings = holdings;
        align(Align.top);
        setFillParent(true);

        add(new Label("", basicLabelStyle) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                this.setText("Turn count: " + turnTaker.getCurrentTurn());
                super.draw(batch, parentAlpha);
            }
        }).pad(10).align(Align.top).row();
        add(potentialWorkshops()).pad(10).align(Align.top);
        add(activeWorkshops()).pad(10).align(Align.top);
        add(storage()).pad(10).align(Align.top);
        add(contracts()).pad(10).align(Align.top);
    }

    private Actor contracts() {
        return new Label("Contract List", basicLabelStyle);
    }

    private Actor potentialWorkshops() {
        Table potentialDisplay = new Table();
        final Label header = new Label("Potential Workshops", this.basicLabelStyle);
        potentialDisplay.add(header).pad(10).row();
        for (final Workshop workshop : potentialWorkshops) {
            potentialDisplay.add(workshop.getActor()).pad(5);
            final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = new BitmapFont();
            final TextButton addButton = new TextButton("+", textButtonStyle);
            addButton.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("add button", "clicked!");
                    holdings.addWorkshop(workshop);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            addButton.setTouchable(Touchable.enabled);
            potentialDisplay.add(addButton).pad(5);
            potentialDisplay.row();
        }
        return potentialDisplay;
    }

    private Actor activeWorkshops() {
        Table activeDisplay = new Table();
        final Label header = new Label("Active Workshops", this.basicLabelStyle);
        activeDisplay.add(header).pad(10).row();
        for (final Workshop workshop : holdings.getWorkshops()) {
            activeDisplay.add(workshop.getActor()).pad(5);
            final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = new BitmapFont();
            final TextButton removeButton = new TextButton("-", textButtonStyle);
            removeButton.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("add button", "clicked!");
                    holdings.removeWorkshop(workshop);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            activeDisplay.add(removeButton).pad(5);
            activeDisplay.row();
        }
        return activeDisplay;
    }

    private Actor storage() {
        Table storageDisplay = new Table();
        final Label storageHeader = new Label("Storage", this.basicLabelStyle);
        storageDisplay.add(storageHeader).pad(10).row();
        for (Item item : holdings.getStorage()) {
            storageDisplay.add(item.getActor()).row();
        }
        return storageDisplay;
    }
}


