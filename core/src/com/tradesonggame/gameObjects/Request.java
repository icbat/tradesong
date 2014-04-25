package com.tradesonggame.gameObjects;

import com.badlogic.gdx.Gdx;
import com.tradesonggame.Tradesong;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Request {
    LinkedList<String> requirements = new LinkedList<String>();
    LinkedList<String> rewards = new LinkedList<String>();
    int rewardMoney = 0;
    private Rarity rarity;

    public Request(Rarity rarity, LinkedList<String> requirements, LinkedList<String> rewards, int rewardMoney) {
        this.rarity = rarity;
        this.requirements = requirements;
        this.rewards = rewards;
        this.rewardMoney = rewardMoney;
    }

    public boolean canComplete() {
        return inventoryContainsAllRequirements() && wouldNotOverflowInv();
    }

    public boolean inventoryContainsAllRequirements() {
        List<String> matchList = Tradesong.state.inventory().getMatchList(requirements);
        List<String> copyOfRequirements = new ArrayList<String>(requirements);

        for (String requirement : copyOfRequirements) {
            boolean wasRemoved = matchList.remove(requirement);
            if (!wasRemoved) {
                return false;
            }
        }

        return true;
    }

    private boolean wouldNotOverflowInv() {
        return Tradesong.state.inventory().slotsFree() >= rewards.size();
    }

    /**
     * Checks for completion, and then gives you rewards and kills it from the list..
     * */
    public boolean completeRequest() {
        if (canComplete() && Tradesong.state.getRequestList().contains(this)) {
            Gdx.app.debug("reward items", rewards.toString());

            for (String rewardItem : rewards){
                Tradesong.state.inventory().addItem(rewardItem);
            }

            Gdx.app.debug("reward money", rewardMoney + "");
            Gdx.app.debug("money before", Tradesong.state.inventory().getMoney() + "");
            Tradesong.state.inventory().addMoney(rewardMoney);
            Gdx.app.debug("money after", Tradesong.state.inventory().getMoney() + "");

            Tradesong.state.getRequestList().remove(this);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return rarity + " request: " +
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
