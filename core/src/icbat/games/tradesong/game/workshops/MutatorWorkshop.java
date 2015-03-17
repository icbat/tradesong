package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

import java.util.*;

/**
 * Takes one more more items as input and outputs 1 new item
 */
public class MutatorWorkshop implements ItemProducer, ItemConsumer {

    private final Item output;
    private final List<Item> ingredients = new ArrayList<Item>();
    private final Deque<Item> inputQueue = new ArrayDeque<Item>();
    private final Deque<Item> outputQueue = new ArrayDeque<Item>();
    private WorkerPool workerPool = new WorkerPoolImpl();

    public MutatorWorkshop(Item output, Item... ingredients) {
        this.output = output;
        if (ingredients.length <= 0) {
            throw new IllegalStateException("Dev error! Mutator attempted to be created without any ingredients!");
        }
        Collections.addAll(this.ingredients, ingredients);
    }

    @Override
    public void takeTurn() {
        for (int i = 0; i < workerPool.size(); ++i) {
            if (isStockedWithProperIngredients()) {
                consumeIngredients();
                outputQueue.add(output);
            }
        }
    }

    private void consumeIngredients() {
        for (Item ingredient : ingredients) {
            inputQueue.removeFirstOccurrence(ingredient);
        }
    }

    private boolean isStockedWithProperIngredients() {
        final Deque<Item> requirements = new ArrayDeque<Item>(ingredients);
        for (Item input : inputQueue) {
            for (Item requiredIngredient : requirements) {
                if (input.equals(requiredIngredient)) {
                    requirements.removeFirstOccurrence(requiredIngredient);
                    break;
                }
            }
        }

        return requirements.isEmpty();
    }

    @Override
    public String getWorkshopName() {
        return output.getName() + " Assembler";
    }

    @Override
    public WorkerPool getWorkers() {
        return this.workerPool;
    }

    @Override
    public boolean hasOutput() {
        return !outputQueue.isEmpty();
    }

    @Override
    public Item getNextOutput() {
        if (!hasOutput()) {
            throw new IllegalStateException("Dev error! " + this.getWorkshopName() + "'s output accessed with an empty output");
        }
        return outputQueue.removeFirst();
    }

    @Override
    public boolean acceptsInput(Item input) {
        return ingredients.contains(input);
    }

    @Override
    public void sendInput(Item input) {
        if (!acceptsInput(input)) {
            throw new IllegalStateException("Dev error! " + input.getName() + "is not acceptable by " + this.getWorkshopName());
        }

        inputQueue.add(input);
    }

    protected Collection<Item> getInputQueue() {
        return inputQueue;
    }

    @Override
    public Actor getActor() {
        Table layout = new Table();
        Label.LabelStyle basicStyle = new Label.LabelStyle();
        basicStyle.font = new BitmapFont();
        layout.add(new Label(getWorkshopName(), basicStyle)).row();
        layout.add(new Label(" Workers:" + getWorkers().size(), basicStyle));
        return layout;
    }

    @Override
    public MutatorWorkshop spawnClone() {
        Item[] clonesIngredients = new Item[ingredients.size()];
        for (int i = 0; i < ingredients.size(); ++i) {
            clonesIngredients[i] = ingredients.get(i).spawnClone();
        }
        return new MutatorWorkshop(output.spawnClone(), clonesIngredients);
    }
}
