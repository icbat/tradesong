package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
import com.icbat.game.tradesong.Tradesong;

import java.util.LinkedList;
import java.util.List;

/**
 * Way to represent/interact with a contract. Making these is left to a factory elsewhere.
 * */
public class Contract {
    LinkedList<String> requirements = new LinkedList<String>();
    LinkedList<String> rewards = new LinkedList<String>();
    int rewardMoney = 0;
    private Rarity rarity;

    private  Contract() {}

    public Contract(Rarity rarity, LinkedList<String> requirements, LinkedList<String> rewards, int rewardMoney) {
        this.rarity = rarity;
        this.requirements = requirements;
        this.rewards = rewards;
        this.rewardMoney = rewardMoney;
    }

    public boolean canComplete() {
        List<String> matchList = Tradesong.state.inventory().getMatchList(requirements);

        return matchList.containsAll(requirements) && matchList.size() >= requirements.size(); // TODO handle odd case where you have 3 of X and 1 of Y, but contract requires 1+ x and 2+ y
    }

    /**
     * Checks for completion, and then gives you rewards and kills it from the list..
     * */
    public boolean completeContract(List<String> inputs) {
        if (canComplete() && Tradesong.state.getContractList().contains(this)) {

            Gdx.app.debug("reward items", rewards.toString());

            for (String rewardItem : rewards){
                Tradesong.state.inventory().addItem(rewardItem);
            }

            Gdx.app.debug("reward money", rewardMoney + "");
            Gdx.app.debug("money before", Tradesong.state.inventory().getMoney() + "");
            Tradesong.state.inventory().addMoney(rewardMoney);
            Gdx.app.debug("money after", Tradesong.state.inventory().getMoney() + "");

            Tradesong.state.getContractList().remove(this);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return rarity + " contract: " +
                requirements.toString() +
                "  ::  " +
                rewardMoney + " :: " + rewards.toString();
    }

    public LinkedList<String> getRequirements() {
        return requirements;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public LinkedList<String> getRewardItems() {
        return rewards;
    }

    public Rarity getRarity() {
        return rarity;
    }
}
