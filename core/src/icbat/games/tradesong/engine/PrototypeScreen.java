package icbat.games.tradesong.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.TurnTaker;
import icbat.games.tradesong.game.workshops.MutatorWorkshop;
import icbat.games.tradesong.game.workshops.ProducerWorkshop;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.ArrayList;
import java.util.Collection;

public class PrototypeScreen implements Screen {

    public static final int TURN_TIMER = 1;
    protected Item basicItem;
    protected Item betterItem;
    protected Item assembledItem;
    private PlayerHoldings holdings = new PlayerHoldings();
    private Collection<Workshop> potentialWorkshops = new ArrayList<Workshop>();
    private TurnTaker turnTaker;
    private Timer turnTimer = new Timer();
    private Stage stage = new Stage();

    public PrototypeScreen() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        buildStage();
        stage.draw();
        stage.act(delta);
    }

    private void buildStage() {
        stage.clear();
        final Table layout = new PrototypeLayoutTable(turnTaker, potentialWorkshops, holdings);
        stage.addActor(layout);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("proto screen", "resized");
    }

    @Override
    public void show() {
        Gdx.app.debug("proto screen", "shown");
        setupTurnTaker();
        setupItems();
        setupWorkshops();
    }

    private void setupTurnTaker() {
        turnTaker = new TurnTaker(holdings);
        turnTimer.clear();
        turnTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                turnTaker.takeAllTurns();
            }
        }, TURN_TIMER, TURN_TIMER);
        turnTimer.start();
    }

    private void setupItems() {
        basicItem = new Item("an Item");
        betterItem = new Item("a better item");
        assembledItem = new Item("Assembled thing");
    }

    private void setupWorkshops() {
        potentialWorkshops.add(new ProducerWorkshop(basicItem));
        potentialWorkshops.add(new ProducerWorkshop(betterItem, 3));
        potentialWorkshops.add(new MutatorWorkshop(assembledItem, basicItem, betterItem));
    }

    @Override
    public void hide() {
        Gdx.app.debug("proto screen", "hidden");
        turnTimer.stop();
    }

    @Override
    public void pause() {
        Gdx.app.debug("proto screen", "paused");
        turnTimer.stop();
    }

    @Override
    public void resume() {
        Gdx.app.debug("proto screen", "resumed");
        turnTimer.start();
    }

    @Override
    public void dispose() {
        Gdx.app.debug("proto screen", "disposed");
        turnTimer.stop();
        turnTimer.clear();
    }
}
