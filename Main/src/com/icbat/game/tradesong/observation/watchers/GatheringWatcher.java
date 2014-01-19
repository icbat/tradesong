package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;
import com.icbat.game.tradesong.observation.notifications.GatherNotification;
import com.icbat.game.tradesong.observation.notifications.StopNotification;
import gameObjects.Item;

/**
 * Responds appropriately to an item node being gathered
 * */
public class GatheringWatcher implements Watcher {

    private Sound gatherSound = Tradesong.getSound(SoundAssets.GATHER_CLINK);
    private Sound completionSound = Tradesong.getSound(SoundAssets.SUCCESS);
    private Timer gatherTimer = new Timer();

    @Override
    public void handleNotification(Notification notification) {
        if (!verifyICare(notification)) {
             return;
        }

        Gdx.app.debug("Gathering Watcher", "handling notification");

        if (notification instanceof GatherNotification) {
            Gdx.app.debug("Gathering Watcher", "Gathering");
            startGathering((Item) notification.getContents());
        } else if (notification instanceof StopNotification) {
            Gdx.app.debug("Gathering Watcher", "Stopping gathering");
            stopGathering();
        }


    }

    private boolean verifyICare(Notification notification) {
        if (notification == null || notification.getAction() == null) {
            return false;
        }

        if (!(notification instanceof GatherNotification) && !(notification instanceof StopNotification)) {
            return false;
        }

        return true;
    }

    private void startGathering(final Item owner) {
        if (Tradesong.inventory.canAdd()) {
            gatherSound.stop();
            completionSound.stop();

            gatherSound.play();
            gatherTimer.clear();
            gatherTimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    gatherSound.stop();
                    completionSound.play();
                    Tradesong.inventory.addItem(new Item(owner));
                    owner.remove();
                }
            }, 2.5f); // TODO change to variable for decrement.
        }
    }

    private void stopGathering() {
        gatherSound.stop();
        gatherTimer.stop();
        gatherTimer.clear();
    }


}
