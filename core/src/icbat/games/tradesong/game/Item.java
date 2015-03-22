package icbat.games.tradesong.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class Item {
    private final String name;

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Actor getActor() {
        final Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        return new Label(name, labelStyle);
    }

    public Item spawnClone() {
        return new Item(name);
    }

    public boolean isCloneOf(Item otherItem) {
        if (otherItem == null) {
            return false;
        }
        return this.getName().equals(otherItem.getName());
    }
}
