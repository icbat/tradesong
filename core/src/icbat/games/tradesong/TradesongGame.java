package icbat.games.tradesong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Timer;
import icbat.games.tradesong.engine.GameSkin;
import icbat.games.tradesong.engine.RandomGenerator;
import icbat.games.tradesong.engine.ScreenManager;
import icbat.games.tradesong.engine.SimpleScreenManager;
import icbat.games.tradesong.engine.screens.OverviewScreen;
import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.TurnTaker;
import icbat.games.tradesong.game.contracts.Contract;
import icbat.games.tradesong.game.contracts.ContractFactory;
import icbat.games.tradesong.game.workers.WorkerImpl;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workshops.MutatorWorkshop;
import icbat.games.tradesong.game.workshops.ProducerWorkshop;
import icbat.games.tradesong.game.workshops.StorefrontWorkshop;
import icbat.games.tradesong.game.workshops.Workshop;

import java.util.*;

public class TradesongGame extends Game {

	public static final int TURN_TIMER = 1;
	public static Item basicItem;
	public static Item betterItem;
	public static Item assembledItem;
	public static ContractFactory factory;
	public static PlayerHoldings holdings = new PlayerHoldings();
	public static Collection<Workshop> potentialWorkshops = new ArrayList<Workshop>();
	public static TurnTaker turnTaker;
	public static Timer turnTimer;
	public static GameSkin skin;
	public static ScreenManager screenManager;

	public static List<Contract> contracts;

	public void setupContracts() {
		factory = new ContractFactory(new Random(), new RandomGenerator<Item>(Arrays.asList(basicItem, betterItem, assembledItem), new Random()));
		contracts = new ArrayList<Contract>();
		contracts.add(factory.buildRandomContract());
		contracts.add(factory.buildRandomContract());
		contracts.add(factory.buildRandomContract());
	}

	public void setupWorkerPool() {
		WorkerPool spareWorkers = holdings.getSpareWorkers();
		spareWorkers.addWorker(new WorkerImpl());
		spareWorkers.addWorker(new WorkerImpl());
		spareWorkers.addWorker(new WorkerImpl());
	}

	public void setupTurnTaker() {
		turnTaker = new TurnTaker(holdings, contracts, factory);

		turnTimer = new Timer();
		turnTimer.clear();
		turnTimer.scheduleTask(new Timer.Task() {
			@Override
			public void run() {
				turnTaker.takeAllTurns();
			}
		}, TURN_TIMER, TURN_TIMER);
		turnTimer.start();
	}

	public void setupItems() {
		basicItem = new Item("an Item");
		betterItem = new Item("a better item");
		assembledItem = new Item("Assembled thing");
	}

	public void setupWorkshops() {
		potentialWorkshops.add(new ProducerWorkshop(basicItem));
		potentialWorkshops.add(new ProducerWorkshop(betterItem, 3));
		potentialWorkshops.add(new MutatorWorkshop(assembledItem, basicItem, betterItem));
		potentialWorkshops.add(new StorefrontWorkshop(holdings));
	}

	@Override
	public void create () {
		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		skin = new GameSkin();
		setupItems();
		setupContracts();
		setupTurnTaker();
		setupWorkshops();
		setupWorkerPool();

		screenManager = new SimpleScreenManager(this);

		for (Workshop potential : potentialWorkshops) {
			holdings.addWorkshop(potential);
		}

		this.setScreen(new OverviewScreen());
	}
}
