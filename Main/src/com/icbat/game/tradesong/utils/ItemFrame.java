package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.icbat.game.tradesong.Tradesong;

/**
 * Class to handle the generic, reusable item frame and its behaviors. In particular, it should be able to change color
 * for rarity.
 * */
public class ItemFrame extends Image{

    public ItemFrame () {
        super(Tradesong.getTexture(Tradesong.TEXTURE.FRAME));
    }

}
