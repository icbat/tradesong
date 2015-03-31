package icbat.games.tradesong.engine;

import java.util.List;
import java.util.Random;

/***/
public class RandomGenerator<Type> {
    private final List<Type> possibleItems;
    private final Random random;

    public RandomGenerator(List<Type> possibleItems, Random random) {
        this.possibleItems = possibleItems;
        this.random = random;
    }

    public Type getNext() {
        if (possibleItems.isEmpty()) {
            throw new IllegalStateException("Dev error, random generator used with an empty set");
        }
        return possibleItems.get(random.nextInt(possibleItems.size()));
    }

    public Type getNextDifferent(Type compareTo) {
        if (possibleItems.size() <= 1) {
            throw new IllegalStateException("Dev error, not enough items to find a unique one!");
        }

        Type found;
        do {
            found = getNext();
        } while (found.equals(compareTo));
        return found;
    }
}
