package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.utils.Point;

public abstract class LevelStage extends AbstractStage {

    // Doesn't entirely make sense for this to here, but it'll do for now
    // Can't be in the inner class without it being static
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    MapProperties properties;

    public LevelStage(MapProperties properties) {
        this.properties = properties;
//        layout(); This needs to happen after more properties have been messed with, or things will need to be much more verbose

    }

    @Override
    public void layout() {
        addExits();
    }

    private void addExits() {
        String exitsBlob = (String)properties.get("exits");

        String[] exitInfoClumps = exitsBlob.split(";");


        for (String exitInfo : exitInfoClumps) {
            String[] exitInfoExploded = exitInfo.split(",");

            Point coords = new Point(exitInfoExploded[0], exitInfoExploded[1]);
            coords.translateToMapStage((Integer)properties.get("height"));

            ExitArrow arrow = new ExitArrow(exitInfoExploded[2]);
            Image arrowImage = arrow.getImage();

            arrowImage.setPosition(coords.getX(), coords.getY());

            this.addActor(arrowImage);
        }
    }

    class ExitArrow {

        Direction facing = null;
        private Image image;

        ExitArrow(String directionString) {
            directionString = directionString.toLowerCase();
            if (directionString.equals("left"))
                facing = Direction.LEFT;

            if (directionString.equals("right"))
                facing = Direction.RIGHT;

            if (directionString.equals("up"))
                facing = Direction.UP;

            if (directionString.equals("down"))
                facing = Direction.DOWN;


            makeImage();
        }


        ExitArrow(Direction facing) {
            this.facing = facing;
            makeImage();
        }

        private void makeImage() {
            Sprite sprite = new Sprite(Tradesong.getMapArrowTexture());

            switch(facing) {
                case LEFT:
                    sprite.rotate90(true);
                    break;
                case RIGHT:
                    sprite.rotate90(false);
                    break;
                case UP:
                    sprite.rotate(180);
                    break;
            }

            image = new Image(new SpriteDrawable(sprite));

        }

        Image getImage() {
            return image;
        }

    }


}
