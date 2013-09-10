package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.icbat.game.tradesong.screens.SettingsScreen;
import com.icbat.game.tradesong.utils.TextureAssets;

import java.util.ArrayList;
import java.util.HashSet;


/** Class to keep track of game state data. */
public class GameStateManager {
    private Inventory inventory = new Inventory();
    private HashSet<Item> allKnownItems = new HashSet<Item>();
    private HashSet<Recipe> allKnownRecipes = new HashSet<Recipe>();
    private HashSet<Workshop> allWorkshops = new HashSet<Workshop>();

    public static final String PATH_ITEMS = "items.csv";
    public static final String PATH_RECIPES = "recipes.csv";

    private HashSet<LeveledParameter> leveledParameters = new HashSet<LeveledParameter>();

    public static int money = 0;

    public static Music currentMusic;

    public GameStateManager() {
        // Set up default parameters
        leveledParameters.add(new LeveledParameter(Tradesong.getParamDelayGather(), 3));
        leveledParameters.add(new LeveledParameter(Tradesong.getParamDelayCraft(), 3));

        // Load data and initialize
        loadItems( Tradesong.getTexture(TextureAssets.ITEMS) );
        loadRecipes();
        findWorkshops();


    }

    public static void updateMusic(Music newSong) {
        Preferences preferences = Gdx.app.getPreferences(SettingsScreen.PREFERENCES);

        currentMusic = newSong;

        currentMusic.setVolume(preferences.getInteger(SettingsScreen.KEY_MUSIC_VOL, 50));
        currentMusic.play();
        currentMusic.setLooping(true);
    }

    public static void updateMusic() {
        updateMusic(currentMusic);
    }

    public int getMoney() {
        return money;
    }

    public void addMoney(int income) {
        money += income;

    }

    public LeveledParameter getParameterByName(String name) {
        for (LeveledParameter param : leveledParameters) {
            if (param.getParameterName().equals(name))
                return param;
        }

        return null;
    }

    //    /** Saves to a file.
//     *
//     * @param filename of saveGame
//     * @return true if saveGame seems successful*/
//    public boolean saveGame(FileHandle filename) {
//        return false;
//    }
//
//    /** Loads from a file.
//     *
//     * @param filename of saveGame.
//     * @return true if loadGame seems successful
//     * */
//    public boolean loadGame(FileHandle filename){
//        return false;
//    }

    /** Load in items from CSV file assets
     *
     * @see assets/items.csv
     * */
    public void loadItems(Texture texture) {

        // Load the main file
        String itemBlob = Gdx.files.internal(PATH_ITEMS).readString();
        String[] lineOfSpec = itemBlob.split("\n");

        // Declaring for memory usage
        String[] properties;
        String name, description;
        int x, y, rarity, maxStack, basePrice;

        for (String line : lineOfSpec) {
            properties = line.split(",");

            if (!properties[0].equals("string_itemName")) {

                // public Item(String itemName, String description, Texture texture,  int maxStack, int rarity, int spriteX, int spriteY) {
                name = properties[0];
                description = properties[1];
                x = Integer.parseInt(properties[4].trim());
                y =  Integer.parseInt(properties[5].trim());
                rarity =  Integer.parseInt(properties[3].trim());
                maxStack =  Integer.parseInt(properties[2].trim());
                basePrice = Integer.parseInt(properties[6].trim());

                allKnownItems.add( new Item(name, description, texture, maxStack, rarity, x, y, basePrice) );

            }

        }
    }

    /** Load in recipes from CSV file assets
     *
     * Depends on (and must be run AFTER) loadItems()
     * */
    public void loadRecipes() {

        // Load the main file
        String itemBlob = Gdx.files.internal(PATH_RECIPES).readString();
        String[] lineOfSpec = itemBlob.split("\n");

        // Declaring for memory usage
        String[] properties;
        String outputString, workshop, inputTemp;
        Item output;
        ArrayList<Item> recipe;


        for (String line : lineOfSpec) {
            properties = line.split(",");

            // Trim all the properties
            for (int j = 0; j < properties.length; ++j) {
                properties[j] = properties[j].trim();

            }

            if (!properties[0].equals("output item")) {
                recipe = new ArrayList<Item>();

                // output item, workshop, in1, [in2], [in3]
                outputString = properties[0];
                output = getItemByName(outputString);

                workshop = properties[1];

                for (int i = 2; i < properties.length; ++i) {
                    inputTemp = properties[i];
                    recipe.add(getItemByName(inputTemp));

                }

                allKnownRecipes.add( new Recipe(output, workshop, recipe) );

                recipe.clear();

            }



        }
    }

    private void findWorkshops() {
        for (Recipe recipe : allKnownRecipes) {
            allWorkshops.add( new Workshop(recipe.workshop) );
        }
    }

    public Inventory getInventory() {
        return inventory;
    }

    /** @return A new copy of the item by name or null if it was not found */
    public Item getItemByName(String name) {
        for (Item item : allKnownItems) {
            if (item.getItemName().equals(name))
                return new Item(item);
        }

        return null;
    }

    public HashSet<Recipe> getAllKnownRecipes() {
        return allKnownRecipes;
    }
}
