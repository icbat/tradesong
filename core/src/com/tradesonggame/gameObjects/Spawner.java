package com.tradesonggame.gameObjects;

import com.tradesonggame.gameObjects.collections.Items;

public interface Spawner {
    public Items.Item spawnOneItem();
    public void start();
    public void stop();
}
