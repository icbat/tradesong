package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
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
    public boolean completeContract(List<Item> inputs) {
        if (canComplete(inputs) && Tradesong.contractList.contains(this)) {

            Gdx.app.debug("reward items", rewards.toString());

            for (Item rewardItem : rewards){
                Tradesong.inventory.addItem(new Item(rewardItem));
            }

            Gdx.app.debug("reward money", rewardMoney + "");
            Gdx.app.debug("money before", Tradesong.inventory.getMoney() + "");
            Tradesong.inventory.addMoney(rewardMoney);
            Gdx.app.debug("money after", Tradesong.inventory.getMoney() + "");

            Tradesong.contractList.remove(this);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return rarity + " contract";
    }

    public List<Item> getRequirements() {
        return requirements;
    }
}
