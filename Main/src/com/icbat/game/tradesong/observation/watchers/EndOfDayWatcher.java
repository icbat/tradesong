package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.utility.Constants;
import com.icbat.game.tradesong.screens.components.PopupNotification;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

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
