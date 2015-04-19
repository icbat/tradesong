package icbat.games.tradesong.game;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import icbat.games.tradesong.engine.Prototype;

public class Item implements Prototype<Item> {
    private final String name;
    private final int basePrice;

    public Item(String name, int basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public Actor getActor() {
        final Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont();
        return new Label(name, labelStyle);
    }

    @Override
    public Item spawnClone() {
        return new Item(name, basePrice);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (basePrice != item.basePrice) return false;
        return name.equals(item.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + basePrice;
        return result;
    }
}
