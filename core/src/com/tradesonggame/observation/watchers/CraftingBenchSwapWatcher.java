package com.tradesonggame.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.SoundAssets;
import com.tradesonggame.observation.Notification;
import com.tradesonggame.observation.Watcher;

/***/
public class CraftingBenchSwapWatcher implements Watcher {
    private final Sound swapSound = Tradesong.getSound(SoundAssets.SCREEN_SWAP);
    @Override
    public void handleNotification(Notification notification) {
        if (notification.getAction().equalsIgnoreCase("CRAFTING-BENCH-SWAPPED")) {
            swapSound.play();
        }
    }
}
