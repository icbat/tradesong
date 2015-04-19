package icbat.games.tradesong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import icbat.games.tradesong.engine.*;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class TradesongGame extends Game {
	public static ContractFactory factory;
	public static PlayerHoldings holdings = new PlayerHoldings();
	public static Collection<Workshop> potentialWorkshops = new ArrayList<Workshop>();
	public static TurnTaker turnTaker;
	public static GameSkin skin;
	public static ScreenManager screenManager;
	public static List<Contract> contracts;
	public static MasterList<Item> items;
	private static Item basicItem;
	private static Item betterItem;
	private static Item assembledItem;

	public void setupContracts() {
		factory = new ContractFactory(new Random(), new RandomGenerator<Item>(items.getList(), new Random()));
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
		spareWorkers.addWorker(new WorkerImpl());
	}

	public void setupItems() {
		items = new MasterList<Item>();
		basicItem = new Item("an Item", 300);
		betterItem = new Item("a better item", 1000);
		assembledItem = new Item("Assembled thing", 1500);
		items.add(basicItem);
		items.add(betterItem);
		items.add(assembledItem);
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
		turnTaker = new TurnTaker(holdings, contracts, factory);
		setupWorkshops();
		setupWorkerPool();

		holdings.addCurrency(1000);
		holdings.addWorkshop(new StorefrontWorkshop(holdings));

		screenManager = new SimpleScreenManager(this);

		this.setScreen(new OverviewScreen());
	}
}
