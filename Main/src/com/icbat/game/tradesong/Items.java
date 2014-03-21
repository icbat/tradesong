package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.Rarity;

import java.io.IOException;
import java.util.HashSet;

/**
 * Set of all spawnable/existing items for quick reference. Get returns a copy of the Item called.
 * */
public class Items {
    private static final int SPRITE_DIMENSION = Constants.SPRITE_DIMENSION.value();
    private HashSet<Item> prototypes = new HashSet<Item>();

    private Items() {}

    public void parseItemPrototypes() {
        XmlReader reader = new XmlReader();
        XmlReader.Element parentElement;
        try {
            parentElement = reader.parse(Gdx.files.internal("items.xml"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Array<XmlReader.Element> items = parentElement.getChildrenByName("item");

        for (XmlReader.Element item : items) {
            prototypes.add(parseItemFromXml(item));
        }
    }

    private Item parseItemFromXml(XmlReader.Element itemXml) {
        String name = itemXml.get("name", "");
        String description = itemXml.get("description", "");

        String rarityString = itemXml.get("rarity", "common");
        Rarity rarity = Rarity.getRarityFromString(rarityString);


        Texture itemSprites = Tradesong.getTexture(TextureAssets.ITEMS);
        Integer spriteX = itemXml.getInt("spriteX", 0);
        Integer spriteY = itemXml.getInt("spriteY", 0);
        TextureRegion icon = new TextureRegion(itemSprites, spriteX * SPRITE_DIMENSION, spriteY * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION);

        Integer basePrice = itemXml.getInt("basePrice", 0);

        return new Item(icon, name, description, rarity, basePrice);
    }



    /**
     * Returns a copy of the item, or null if the name can't be found
     * */
    public Item getItem(String itemName) {
        for (Item item : prototypes) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return new Item(item);
            }
        }
        return null;
    }

    /**
     * Mainly used for testing, this returns the whole list of known item prototypes
     * */
    public HashSet<Item> getAll() {
        return prototypes;
    }

    public static Items parsePrototypes() {
        Items proto = new Items();
        proto.parseItemPrototypes();
        return proto;
    }

    /**
     * Item representation with Prototype functionality via copy constructor.
     * */
    public static class Item extends Image implements Comparable {
        private final TextureRegion icon;
        private final String name;
        private final String description;
        private final Integer basePrice;
        private final Rarity rarity;

        public Item(TextureRegion icon, String name, String description, Rarity rarity, Integer basePrice) {
            super(icon);
            this.icon = icon;
            this.name = name;
            this.rarity = rarity;
            this.description = description;
            this.basePrice = basePrice;
        }

        public Item(Item original) {
            this(original.getIcon(), original.getName(), original.getDescription(), original.getRarity(), original.getBasePrice());
        }

        public Integer getBasePrice() {
            return basePrice;
        }

        public String getDescription() {
            return description;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public int compareTo(Object o) {
            return this.toString().compareTo(o.toString());
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Item)) return false;

            Item item = (Item) o;

            if (!basePrice.equals(item.basePrice)) return false;
            if (!description.equals(item.description)) return false;
            if (!name.equals(item.name)) return false;
            if (rarity != item.rarity) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = name.hashCode();
            result = 31 * result + description.hashCode();
            result = 31 * result + basePrice.hashCode();
            result = 31 * result + rarity.hashCode();
            return result;
        }

        public Rarity getRarity() {
            return this.rarity;
        }

        public TextureRegion getIcon() {
            return icon;
        }
    }
}
