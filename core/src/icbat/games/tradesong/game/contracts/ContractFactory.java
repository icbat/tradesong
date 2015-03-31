package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.Item;

import java.util.List;
import java.util.Random;

/***/
public class ContractFactory {

    private final List<Item> possibleItems;
    private final Random random;

    public ContractFactory(List<Item> possibleItems, Random random) {
        if (possibleItems.isEmpty()) {
            throw new IllegalStateException("Dev error, contract factory requires some possible reward items!");
        }
        this.random = random;
        this.possibleItems = possibleItems;
    }

    public Contract buildRandomItemContract() {
        final Item requiredItem = getRandomItem();
        Item reward = null;
        while (reward == null || reward.equals(requiredItem)) {
            reward = getRandomItem();
        }
        return new ContractImpl(requiredItem, new ItemReward(reward));
    }

    private Item getRandomItem() {
        if (possibleItems.size() <= 1) {
            throw new IllegalStateException("Dev error! There needs to be more than 1 possible item to make contracts");
        }
        return possibleItems.get(random.nextInt(possibleItems.size()));
    }

    public Contract buildRandomMoneyContract() {
        return new ContractImpl(getRandomItem(), new MoneyReward(random.nextInt(50) + 70));
    }

    public Contract buildRandomContract() {
        if (random.nextBoolean()) {
            return buildRandomItemContract();
        } else {
            return buildRandomMoneyContract();
        }
    }
}
