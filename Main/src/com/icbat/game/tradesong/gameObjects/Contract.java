package com.icbat.game.tradesong.gameObjects;

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

    public void completeContract() {
        // TODO impl
    }

    public void addOutputToInventory() {
        // TODO impl
    }

    @Override
    public String toString() {
        return "Contract{" +
                "requirements=" + requirements +
                ", rewards=" + rewards +
                ", rewardMoney=" + rewardMoney +
                '}';
    }

    public List<Item> getRequirements() {
        return requirements;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
