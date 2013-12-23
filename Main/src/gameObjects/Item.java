package gameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Item representation with Prototype functionality via copy constructor.
 * */
public class Item extends Image implements Comparable {
    private final String name;
    private final String description;
    private final TextureRegion icon;
    private final Integer basePrice;

    public Item(String name, String description, TextureRegion icon, Integer basePrice) {
        super(icon);
        this.name = name;
        this.icon = icon;
        this.description = description;
        this.basePrice = basePrice;
    }

    public Item(Item original) {
        this(original.getName(), original.getDescription(), original.getIcon(), original.getBasePrice());
    }

    public Integer getBasePrice() {
        return basePrice;
    }

    public String getDescription() {
        return description;
    }

    public TextureRegion getIcon() {
        return icon;
    }


    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item:" + name;
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (!description.equals(item.description)) return false;
        if (!name.equals(item.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        return result;
    }
}
