package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    Tradesong gameInstance;

    public LevelStage(Tradesong gameInstance, MapProperties properties) {
        this.gameInstance = gameInstance;
        this.properties = properties;
    }

    @Override
    public void layout() {
        addExits();
    }

    private void addExits() {
        String exitsBlob = (String)properties.get("exits");

        String[] exitInfoClumps = exitsBlob.split(";");

        Gdx.app.log("exits", "Adding " + exitInfoClumps.length + "exits");

        for (String exitInfo : exitInfoClumps) {
            String[] exitInfoExploded = exitInfo.split(",");

            Point coords = new Point(exitInfoExploded[0], exitInfoExploded[1]);
            coords.translateToMapStage((Integer)properties.get("height"));

            ExitArrow arrow = new ExitArrow(exitInfoExploded[2], exitInfoExploded[3]);
            Image arrowImage = arrow.getImage();

            arrowImage.setPosition(coords.getX(), coords.getY());
            arrowImage.addListener(new MapTransitionListener(gameInstance, arrow));
            this.addActor(arrowImage);
        }
    }

    /** Class for the making exit arrows rotating to face as set */
    class ExitArrow {

        Direction facing = null;
        private Image image;
        String destination;


        ExitArrow(String directionString, String destinationMapName) {
            this.destination = destinationMapName;

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

        private void makeImage() {
            Sprite sprite = new Sprite(Tradesong.getTexture(Tradesong.TEXTURE.MAP_ARROW));

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

    class MapTransitionListener extends ClickListener {

        Tradesong gameInstance;
        ExitArrow exit;

        MapTransitionListener(Tradesong gameInstance, ExitArrow exit) {
            this.gameInstance = gameInstance;
            this.exit = exit;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            gameInstance.goToMap(exit.destination);

            return super.touchDown(event, x, y, pointer, button);
        }
    }

}
