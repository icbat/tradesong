package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.MapScreen;

/***/
public class Portal extends Image {
    private final String linkedMapName;

    private Portal(String linkedMapName, Integer x, Integer y) {
        super(TextureAssets.ITEMS.getRegion(14, 5));
        this.linkedMapName = linkedMapName;
        this.addListener(new PortalTransitionListener());
        this.setX(x);
        this.setY(y);
    }

    public static Portal makePortal(MapObject mapObject) {
        final MapProperties properties = mapObject.getProperties();
        String linkedMap = (String) properties.get("linkedMap");
        Integer x = (Integer) properties.get("x");
        Integer y = (Integer) properties.get("y");

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
            ((MapScreen)Tradesong.screenManager.getCurrentScreen()).moveToMap(linkedMapName);
        }
    }
}
