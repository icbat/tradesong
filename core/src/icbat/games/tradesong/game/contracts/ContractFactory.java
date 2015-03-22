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
        return new ContractImpl(getRandomItem(), new ItemReward(getRandomItem()));
    }

    private Item getRandomItem() {
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
