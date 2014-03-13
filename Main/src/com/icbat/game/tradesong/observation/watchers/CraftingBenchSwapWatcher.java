package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

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
