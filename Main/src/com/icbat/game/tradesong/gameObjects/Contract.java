package com.icbat.game.tradesong.gameObjects;

import com.icbat.game.tradesong.Tradesong;

import java.util.ArrayList;
import java.util.List;

/**
 * Way to represent/interact with a contract. Making these is left to a factory elsewhere.
 * */
public class Contract {
    List<Item> requirements = new ArrayList<Item>();
    List<Item> rewards = new ArrayList<Item>();
    int rewardMoney = 0;
    private Rarity rarity;

    private Contract(){}

    public Contract(Rarity rarity, List<Item> requirements, List<Item> rewards, int rewardMoney) {
        this.rarity = rarity;
        this.requirements = requirements;
        this.rewards = rewards;
        this.rewardMoney = rewardMoney;
    }

    public boolean canComplete(List<Item> inputs) {
        return inputs.size() == requirements.size() && inputs.containsAll(requirements);
    }

    /**
     * Checks for completion, and then gives you rewards and kills it from the list..
     * */
    public void completeContract(List<Item> inputs) {
        if (canComplete(inputs) && Tradesong.contractList.contains(this)) {
            for (Item rewardItem : rewards){
                Tradesong.inventory.addItem(rewardItem);
            }

            Tradesong.contractList.remove(this);


        }
    }

    public void addOutputToInventory() {
        // TODO impl
    }

    @Override
    public String toString() {
        return rarity + " contract";
    }

    public List<Item> getRequirements() {
        return requirements;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
