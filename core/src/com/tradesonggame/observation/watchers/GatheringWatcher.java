package com.tradesonggame.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.tradesonggame.gameObjects.collections.Items;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.SoundAssets;
import com.tradesonggame.observation.Notification;
import com.tradesonggame.observation.Watcher;
import com.tradesonggame.observation.notifications.GatherNotification;
import com.tradesonggame.observation.notifications.StopNotification;
import com.tradesonggame.utility.Constants;

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

        if (notification instanceof GatherNotification) {
            startGathering((Items.Item) notification.getContents());
        } else if (notification instanceof StopNotification) {
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

    private void startGathering(final Items.Item owner) {
        if (Tradesong.state.inventory().canAdd()) {
            gatherSound.stop();
            completionSound.stop();

            gatherSound.loop();
            gatherTimer.clear();
            gatherTimer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    gatherSound.stop();
                    completionSound.play();
                    Tradesong.state.inventory().addItem(owner.getName());
                    owner.remove();
                }
            }, Constants.GATHER_TIME_BASE.value() * Tradesong.state.getGatherTimeMultiplier());
        }
    }

    private void stopGathering() {
        gatherSound.stop();
        gatherTimer.stop();
        gatherTimer.clear();
    }


}
