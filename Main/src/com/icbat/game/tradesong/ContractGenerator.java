package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.gameObjects.Rarity;

import java.util.*;

/***/
public class ContractGenerator {
    Map<Rarity, List<Item>> prototypes = new HashMap<Rarity, List<Item>>();

    public ContractGenerator(Set<Item> allItemPrototypes) {
        for (Rarity rarity : Rarity.values()) {
            List<Item> itemsAtThisRarity = new ArrayList<Item>();
            for (Item item : allItemPrototypes) {
                if (item.getRarity().equals(rarity)){
                    itemsAtThisRarity.add(item);
                }
            }
            prototypes.put(rarity, itemsAtThisRarity);
            Gdx.app.debug("Prototype mapping, rarity: " + rarity.toString(), itemsAtThisRarity.toString());
        }
    }

    /**
     * TODO move this to data-driven, I think... This is a lot of nasty hard-coding...
     * */
    public Contract generateContract(Rarity rarity) {
        Random random = new Random();
        List<Item> requirements = getRequirements(rarity, random);
        List<Item> rewards = getRewards(rarity, random);
        int moneyReward = getMoneyReward(rarity, random);
        return new Contract(requirements, rewards, moneyReward);
    }

    private int getMoneyReward(Rarity rarity, Random random) {
        int reward = random.nextInt(150) + 50;
        switch (rarity) {
            case COMMON:
                return reward;
            case UNCOMMON:
                return reward << 1;
            case RARE:
                return reward << 2;
            case ULTRA_RARE:
                return reward << 3;
            default:
                return reward;
        }
    }

    private List<Item> getRequirements(Rarity rarity, Random random) {
        int itemsToFind = 2 + random.nextInt(2);
        return getRandomItemList(rarity, itemsToFind, random);
    }

    private List<Item> getRewards(Rarity rarity, Random random) {
        int itemsToFind = random.nextInt(2);
        if (itemsToFind > 0) {
            return getRandomItemList(rarity, itemsToFind, random);
        } else {
            return new ArrayList<Item>();
        }
    }

    private List<Item> getRandomItemList(Rarity rarity, int numberOfItems, Random random) {
        List<Item> items = new ArrayList<Item>();

        for (int i=0; i < numberOfItems; ++i) {
            items.add(getRandomItem(rarity, random));
        }

        return items;
    }

    private Item getRandomItem(Rarity rarity, Random random) {
        List<Item> itemsAtRarity = prototypes.get(rarity);
        int randomIndex = random.nextInt(itemsAtRarity.size());
        return itemsAtRarity.get(randomIndex);
    }

}
