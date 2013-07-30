package com.icbat.game.tradesong;

import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;


/** Class to keep track of game state data. Also handles saving/loading of said data. */
public class GameStateManager {
    private Inventory inventory = new Inventory();
    private ArrayList<Item> allKnownItems = new ArrayList<Item>();
    private ArrayList<Recipe> allKnownRecipes = new ArrayList<Recipe>();

    public static final String PATH_ITEMS = "items.csv";
    public static final String PATH_RECIPES = "recipes.csv";

    private int money = 100;

    public GameStateManager() {
        loadItems(); //TODO check return

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /** Saves to a file.
     *
     * @param filename of saveGame
     * @return true if saveGame seems successful*/
    public boolean saveGame(FileHandle filename) {
        return false;
    }

    /** Loads from a file.
     *
     * @param filename of saveGame.
     * @return true if loadGame seems successful
     * */
    public boolean loadGame(FileHandle filename){
        return false;
    }

    /** Load in items from XML file assets
     *
     * @return true if loadedSuccessfully
     * */
    public boolean loadItems() {

        // Load the main file




        // TODO see about loading from all from a folder to allow for modding/end-user-adding
        return false;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public ArrayList<Item> getAllKnownItems() {
        return allKnownItems;
    }

    public ArrayList<Recipe> getAllKnownRecipes() {
        return allKnownRecipes;
    }
}
