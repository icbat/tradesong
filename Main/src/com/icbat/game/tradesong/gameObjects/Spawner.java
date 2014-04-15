package com.icbat.game.tradesong.gameObjects;

import com.icbat.game.tradesong.gameObjects.collections.Items;

public interface Spawner {
    public Items.Item spawnOneItem();
    public void start();
    public void stop();
}
