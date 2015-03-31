package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.engine.RandomGenerator;
import icbat.games.tradesong.game.Item;

import java.util.Random;

/***/
public class ContractFactory {

    private final Random random;
    private final RandomGenerator<Item> itemGenerator;

    public ContractFactory(Random random, RandomGenerator<Item> itemGenerator) {
        this.itemGenerator = itemGenerator;
        this.random = random;
    }

    public Contract buildRandomItemContract() {
        final Item requiredItem = itemGenerator.getNext();
        Item reward = itemGenerator.getNextDifferent(requiredItem);
        return new ContractImpl(requiredItem, new ItemReward(reward));
    }

    public Contract buildRandomMoneyContract() {
        return new ContractImpl(itemGenerator.getNext(), new MoneyReward(random.nextInt(50) + 70));
    }

    public Contract buildRandomContract() {
        if (random.nextBoolean()) {
            return buildRandomItemContract();
        } else {
            return buildRandomMoneyContract();
        }
    }
}
