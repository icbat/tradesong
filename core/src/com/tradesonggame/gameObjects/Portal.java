package com.tradesonggame.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.TextureAssets;
import com.tradesonggame.observation.NotificationManager;
import com.tradesonggame.observation.notifications.PortalTakenNotification;
import com.tradesonggame.observation.watchers.PortalTakenWatcher;
import com.tradesonggame.screens.MapScreen;

/***/
public class Portal extends Image {
    NotificationManager notifications = new NotificationManager();
    private final String linkedMapName;

    private Portal(String linkedMapName, Float x, Float y) {
        super(TextureAssets.ITEMS.getRegion(14, 5));
        notifications.addWatcher(new PortalTakenWatcher());
        this.linkedMapName = linkedMapName;
        this.addListener(new PortalTransitionListener());
        this.setX(x);
        this.setY(y);
    }

    public static Portal makePortal(MapObject mapObject) {
        final MapProperties properties = mapObject.getProperties();
        String linkedMap = (String) properties.get("linkedMap");
        Float x = (Float) properties.get("x");
        Float y = (Float) properties.get("y");

        if (linkedMap == null || x == null || y == null) {
            Gdx.app.error("Error making portal", "Here's what I parsed");
            Gdx.app.error("linkedMap", linkedMap);
            Gdx.app.error("x", x + "");
            Gdx.app.error("y", y + "");
            return null;
        }

        return new Portal(linkedMap, x, y);
    }

    class PortalTransitionListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            Gdx.app.debug("portal clicked", linkedMapName);
            ((MapScreen) Tradesong.screenManager.getCurrentScreen()).moveToMap(linkedMapName);
            notifications.notifyWatchers(new PortalTakenNotification(linkedMapName));
        }
    }
}
