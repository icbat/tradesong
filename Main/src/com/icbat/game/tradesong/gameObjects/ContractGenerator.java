package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Rarity;
import com.icbat.game.tradesong.gameObjects.collections.Items;

import java.util.*;

public class ContractGenerator {
    HashMap<Rarity, LinkedList<String>> prototypes = new HashMap<Rarity, LinkedList<String>>();
    private final Random random = Tradesong.state.getSeededRNG();

    public ContractGenerator(Set<Items.Item> allItemPrototypes) {
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

    public Contract generateContract() {
        Rarity[] rarities = Rarity.values();
        int rarity = random.nextInt(rarities.length);
        return generateContract(rarities[rarity]);
    }

    public Contract generateContract(Rarity rarity) {
        LinkedList<String> requirements = getRequirements(rarity);
        LinkedList<String> rewards = getRewards(rarity);
        int moneyReward = getMoneyReward(rarity);
        return new Contract(rarity, requirements, rewards, moneyReward);
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
        int itemsToFind = 2 + random.nextInt(2);
        return getRandomItemList(rarity, itemsToFind);
    }

    private LinkedList<String> getRewards(Rarity rarity) {
        int itemsToFind = random.nextInt(2);
        if (itemsToFind > 0) {

            return getRandomRewards(rarity, itemsToFind);
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

    private LinkedList<String> getRandomRewards (Rarity rarity, int numberOfItems) {
        LinkedList<String> items = new LinkedList<String>();

        for (int i=0; i < numberOfItems; ++i) {
            String randomItem;
            do {
                randomItem = getRandomItem(rarity);
            } while (items.contains(randomItem));
            items.add(randomItem);
        }

        return items;
    }

    private String getRandomItem(Rarity rarity) {
        LinkedList<String> itemsAtRarity = prototypes.get(rarity);
        int randomIndex = random.nextInt(itemsAtRarity.size());
        return itemsAtRarity.get(randomIndex);
    }

    public void makeDailyContracts() {
        ArrayList<Contract> contracts = new ArrayList<Contract>();
        int contractsToday = 3 + random.nextInt(4);
        for (int i=0; i < contractsToday; ++i) {
            Contract contract = generateContract();
            contracts.add(contract);
            Gdx.app.debug("contract generated", contract.toString());
        }
        Gdx.app.debug("Contracts for today", contracts.size() + "");
        Tradesong.state.setContractList(contracts);
    }
}
