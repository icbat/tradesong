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

    public Contract buildRandomContract() {
        final int index = random.nextInt(possibleItems.size());
        return new ContractImpl(possibleItems.get(index));
    }
}
