package com.icbat.game.tradesong;

import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;

// TODO this needs a lot of error-checking for things like shrinking inventory, etc.
/** Class to keep track of game state data. Also handles saving/loading of said data. */
public class GameStateManager {
    private Inventory inventory = new Inventory();
    private int money = 100;

    public GameStateManager() {

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /** Saves to a file.
     *
     * @param filename of save
     * @return true if save seems successful*/
    public boolean save(FileHandle filename) {
        return false;
    }

    /**Loads from a file.
     *
     * @param filename of save.
     * @return true if load seems successful
     * */
    public boolean load(FileHandle filename){
        return false;
    }

    public Inventory getInventory() {
        return inventory;
    }


}
