package icbat.games.tradesong.game.workshops;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import icbat.games.tradesong.engine.screens.WorkshopScreen;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.ItemStack;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workers.WorkerPoolImpl;

import java.util.*;

/**
 * Takes one more more items as input and outputs 1 new item
 */
public class MutatorWorkshop implements ItemProducer, ItemConsumer {

    private final Item output;
    private final Map<Item, Integer> requirements = new HashMap<Item, Integer>();
    private final List<ItemStack> inputStacks = new ArrayList<ItemStack>();
    private final ItemStack outputQueue;
    private WorkerPool workerPool = new WorkerPoolImpl();

    public MutatorWorkshop(Item output, Item... ingredients) {
        this(output, Arrays.asList(ingredients));
    }

    public MutatorWorkshop(Item output, List<Item> ingredients) {
        this.output = output;
        this.outputQueue = new ItemStack(output, 1);
        if (ingredients.size() <= 0) {
            throw new IllegalStateException("Dev error! Mutator attempted to be created without any ingredients!");
        }

        for (Item ingredient : ingredients) {
            requirements.put(ingredient, 0);
        }

        for (Item ingredient : ingredients) {
            Integer requiredAmount = requirements.get(ingredient);
            requirements.put(ingredient, requiredAmount + 1);
        }

        for (Map.Entry<Item, Integer> requirement : requirements.entrySet()) {
            inputStacks.add(new ItemStack(requirement.getKey(), requirement.getValue()));
        }
    }

    @Override
    public void takeTurn() {
        for (int i = 0; i < workerPool.size(); ++i) {
            if (isStockedWithProperIngredients() && !outputQueue.isFull()) {
                consumeIngredients();
                outputQueue.addItem(output);
            }
        }
    }

    private void consumeIngredients() {
        for (Map.Entry<Item, Integer> requirement : requirements.entrySet()) {
            ItemStack stack = findMatchingItemStack(requirement.getKey(), inputStacks);
            for (int i = 0; i < requirement.getValue(); ++i) {
                stack.removeItem();
            }
        }
    }

    private boolean isStockedWithProperIngredients() {
        boolean isStocked = true;
        for (Map.Entry<Item, Integer> requirement : requirements.entrySet()) {
            final ItemStack stack = findMatchingItemStack(requirement.getKey(), inputStacks);
            if (stack.getSize() < requirement.getValue()) {
                isStocked = false;
            }
        }
        for (ItemStack stack : inputStacks) {
            if (stack.getSize() <= 0) {
                isStocked = false;
            }
        }
        return isStocked;
    }

    @Override
    public boolean acceptsInput(Item input) {
        if (!requirements.keySet().contains(input)) {
            return false;
        }
        return !findMatchingItemStack(input, inputStacks).isFull();
    }

    private ItemStack findMatchingItemStack(Item item, List<ItemStack> stacks) {
        for (ItemStack stack : stacks) {
            if (item.equals(stack.getItem())) {
                return stack;
            }
        }
        throw new IllegalStateException("Could not find an item stack in workshop " + getWorkshopName() + " for item " + item);
    }

    @Override
    public void sendInput(Item input) {
        if (!acceptsInput(input)) {
            throw new IllegalStateException("Dev error! " + this.getWorkshopName() + " does not accept as input: " + input.getName());
        }

        findMatchingItemStack(input, inputStacks).addItem(input);
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
        return outputQueue.removeItem();
    }

    protected List<ItemStack> getInputStacks() {
        return inputStacks;
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
        List<Item> clonesIngredients = new ArrayList<Item>();
        for (Map.Entry<Item, Integer> requirement : requirements.entrySet()) {
            for (int i = 0; i < requirement.getValue(); ++i) {
                clonesIngredients.add(requirement.getKey());
            }
        }
        return new MutatorWorkshop(output.spawnClone(), clonesIngredients);
    }

    @Override
    public void updateInputQueueCapacity(int inputQueueCapacity) {
        for (ItemStack stack : inputStacks) {
            stack.setCapacity(inputQueueCapacity);
        }
    }

    @Override
    public void updateOutputCapacity(int newCapacity) {
        outputQueue.setCapacity(newCapacity);
    }

    @Override
    public Screen getScreen() {
        return new WorkshopScreen(this);
    }
}
