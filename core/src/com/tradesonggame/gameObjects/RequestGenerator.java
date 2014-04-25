package com.tradesonggame.gameObjects;

import com.badlogic.gdx.Gdx;
import com.tradesonggame.Tradesong;
import com.tradesonggame.gameObjects.collections.Items;

import java.util.*;

public class RequestGenerator {
    HashMap<Rarity, LinkedList<String>> prototypes = new HashMap<Rarity, LinkedList<String>>();
    private final Random random = Tradesong.state.getSeededRNG();

    public RequestGenerator(Set<Items.Item> allItemPrototypes) {
        for (Rarity rarity : Rarity.values()) {
            LinkedList<String> itemsAtThisRarity = new LinkedList<String>();
            for (Items.Item item : allItemPrototypes) {
                if (item.getRarity().equals(rarity)){
                    itemsAtThisRarity.add(item.getName());
                }
            }
            prototypes.put(rarity, itemsAtThisRarity);
            Gdx.app.debug("Prototype mapping, rarity: " + rarity.toString(), itemsAtThisRarity.toString());
        }
    }

    public Request generateRequest() {
        Rarity[] rarities = Rarity.values();
        int rarity = random.nextInt(rarities.length);
        return generateRequest(rarities[rarity]);
    }

    public Request generateRequest(Rarity rarity) {
        LinkedList<String> requirements = getRequirements(rarity);
        LinkedList<String> rewards = getRewards(rarity, requirements);
        int moneyReward = getMoneyReward(rarity);
        return new Request(rarity, requirements, rewards, moneyReward);
    }

    private int getMoneyReward(Rarity rarity) {
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

    private LinkedList<String> getRequirements(Rarity rarity) {
        int itemsToFind = 1 + random.nextInt(3);
        return getRandomItemList(rarity, itemsToFind);
    }

    private LinkedList<String> getRewards(Rarity rarity, LinkedList<String> requirements) {
        int itemsToFind = random.nextInt(3);
        if (itemsToFind > 0) {
            return getRandomRewards(rarity, itemsToFind, requirements);
        } else {
            return new LinkedList<String>();
        }
    }

    private LinkedList<String> getRandomItemList(Rarity rarity, int numberOfItems) {
        LinkedList<String> items = new LinkedList<String>();

        for (int i=0; i < numberOfItems; ++i) {
            items.add(getRandomItem(rarity));
        }

        return items;
    }

    private LinkedList<String> getRandomRewards(Rarity rarity, int numberOfItems, LinkedList<String> requirements) {
        LinkedList<String> rewards = new LinkedList<String>();

        for (int i=0; i < numberOfItems; ++i) {
            String randomItem;
            do {
                randomItem = getRandomItem(rarity);
            } while (requirements.contains(randomItem));
            rewards.add(randomItem);
        }

        return rewards;
    }

    private String getRandomItem(Rarity rarity) {
        LinkedList<String> itemsAtRarity = prototypes.get(rarity);
        int randomIndex = random.nextInt(itemsAtRarity.size());
        return itemsAtRarity.get(randomIndex);
    }

    public void makeDailyRequests() {
        ArrayList<Request> requests = new ArrayList<Request>();
        int requestsToday = 3 + random.nextInt(4);
        for (int i=0; i < requestsToday; ++i) {
            Request request = generateRequest();
            requests.add(request);
            Gdx.app.debug("request generated", request.toString());
        }
        Gdx.app.debug("requestss for today", requests.size() + "");
        Tradesong.state.setRequestList(requests);
    }
}
