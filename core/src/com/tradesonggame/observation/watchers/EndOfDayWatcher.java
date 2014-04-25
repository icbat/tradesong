package com.tradesonggame.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.tradesonggame.assetReferences.TextureAssets;
import com.tradesonggame.utility.Constants;
import com.tradesonggame.screens.components.PopupNotification;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.SoundAssets;
import com.tradesonggame.observation.Notification;
import com.tradesonggame.observation.Watcher;

public class EndOfDayWatcher implements Watcher {

    private final Sound endOfDaySound = Tradesong.getSound(SoundAssets.CHURCHBELL);

    @Override
    public void handleNotification(Notification notification) {
        if (shouldAct(notification)) {
            endOfDaySound.play();
            final int soundEffectLength = 3;
            Tradesong.clock.scheduleNonRepeatingTask(new Timer.Task() {
                @Override
                public void run() {
                    Tradesong.state.inventory().addMoney(Constants.RENT_AMOUNT.value());
                    Tradesong.clock.startDay();
                    Tradesong.popupQueue.addPopup(new PopupNotification() {

                        @Override
                        protected String makeTitleText() {
                            return "Another day ended!";
                        }

                        @Override
                        protected Table addContentBox() {
                            Table table = new Table(Tradesong.uiStyles);
                            table.add("New requests were generated").colspan(2).row();
                            table.add("Rent was paid:  " + Constants.RENT_AMOUNT.value());
                            table.add(new Image(TextureAssets.ITEMS.getRegion(4,30)));
                            return table;
                        }
                    });
                }
            }, soundEffectLength);
        }
    }

    private boolean shouldAct(Notification notification) {
        return notification.getAction().equalsIgnoreCase("DAY-ENDED");
    }
}
