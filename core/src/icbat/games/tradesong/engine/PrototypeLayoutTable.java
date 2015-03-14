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
import icbat.games.tradesong.game.TurnTaker;
import icbat.games.tradesong.game.Workshop;

import java.util.Collection;

/***/
public class PrototypeLayoutTable extends Table {
    private final TurnTaker turnTaker;
    private final Collection<Workshop> potentialWorkshops;
    private final Collection<Item> storage;
    private final Collection<Workshop> workshops;


    public PrototypeLayoutTable(final TurnTaker turnTaker, Collection<Workshop> potentialWorkshops, Collection<Item> storage, Collection<Workshop> workshops) {
        this.turnTaker = turnTaker;
        this.potentialWorkshops = potentialWorkshops;
        this.storage = storage;
        this.workshops = workshops;
        align(Align.top);
        setFillParent(true);

        final Label.LabelStyle basicStyle = new Label.LabelStyle();
        basicStyle.font = new BitmapFont();


        add(new Label("", basicStyle) {
            @Override
            public void draw(Batch batch, float parentAlpha) {
                this.setText("Turn count: " + turnTaker.getCurrentTurn());
                super.draw(batch, parentAlpha);
            }
        }).pad(10).align(Align.top).row();
        add(makePotentialWorkshopsSection(basicStyle)).pad(10).align(Align.top);
        add(makeActiveWorkshopsSection(basicStyle)).pad(10).align(Align.top);
        add(makeStorageSection(basicStyle)).pad(10).align(Align.top);
        add(makeContractSection(basicStyle)).pad(10).align(Align.top);
    }

    private Actor makeContractSection(Label.LabelStyle basicStyle) {
        return new Label("Contract List", basicStyle);
    }

    private Actor makePotentialWorkshopsSection(Label.LabelStyle basicStyle) {
        Table potentialDisplay = new Table();
        final Label header = new Label("Potential Workshops", basicStyle);
        potentialDisplay.add(header).pad(10).row();
        for (final Workshop workshop : potentialWorkshops) {
            potentialDisplay.add(new Label(workshop.getWorkshopName(), basicStyle)).pad(5);
            final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = new BitmapFont();
            final TextButton addButton = new TextButton("Add", textButtonStyle);
            addButton.addListener(new ClickListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    Gdx.app.log("add button", "clicked!");
                    workshops.add(workshop);
                    return super.touchDown(event, x, y, pointer, button);
                }
            });
            addButton.setTouchable(Touchable.enabled);
            potentialDisplay.add(addButton).pad(5);
            potentialDisplay.row();
        }
        return potentialDisplay;
    }

    private Actor makeActiveWorkshopsSection(Label.LabelStyle basicStyle) {
        Table activeDisplay = new Table();
        final Label header = new Label("Active Workshops", basicStyle);
        activeDisplay.add(header).pad(10).row();
        for (Workshop workshop : turnTaker.getActiveWorkshops()) {
            activeDisplay.add(new Label(workshop.getWorkshopName(), basicStyle)).pad(5);
            final TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
            textButtonStyle.font = new BitmapFont();
            final TextButton removeButton = new TextButton("-", textButtonStyle);
            activeDisplay.add(removeButton).pad(5);
            activeDisplay.row();
        }
        return activeDisplay;
    }

    private Actor makeStorageSection(Label.LabelStyle basicStyle) {
        Table storageDisplay = new Table();
        final Label storageHeader = new Label("Storage", basicStyle);
        storageDisplay.add(storageHeader).pad(10).row();
        for (Item item : storage) {
            storageDisplay.add(item.getActor()).row();
        }
        return storageDisplay;
    }
}


