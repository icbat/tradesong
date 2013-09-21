package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.utils.*;

public abstract class LevelStage extends AbstractStage {

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

        // These go to different maps
        String exitsBlob = (String)properties.get("mapExits");

        String[] exitInfoClumps = exitsBlob.split(";");

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

        // These go to Overlays
        String overlaysBlob = (String)properties.get("overlayExits");
        if (overlaysBlob != null) {
            String[] overlayClumps = overlaysBlob.split(";");

            for (String exit : overlayClumps) {
                String[] overlayExploded = exit.split(",");

                Point coords = new Point(overlayExploded[0], overlayExploded[1]);
                coords.translateToMapStage((Integer)properties.get("height"));

                ExitArrow arrow = new ExitArrow(overlayExploded[2], ScreenTypes.getTypeFromString(overlayExploded[3]));
                Image arrowImage = arrow.getImage();

                arrowImage.setPosition(coords.getX(), coords.getY());
                arrowImage.addListener(new ScreenMovingListener(arrow.getOverlayDestination(), gameInstance));
                this.addActor(arrowImage);

            }
        }

    }

    /** Class for the making exit arrows rotating to face as set */
    class ExitArrow {

        Direction facing = null;
        private Image image;
        String destination;
        ScreenTypes overlayDestination;

        private ExitArrow(String directionString) {
            facing = Direction.getDirectionFromString(directionString);
            makeImage();

        }

        ExitArrow (String directionString, ScreenTypes destination) {
            this(directionString);
            this.overlayDestination = destination;

        }

        ExitArrow(String directionString, String destinationMapName) {
            this(directionString);
            this.destination = destinationMapName;
            this.overlayDestination = ScreenTypes.LEVEL;
        }

        private void makeImage() {
            Sprite sprite = new Sprite(Tradesong.getTexture(TextureAssets.MAP_ARROW));

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

        ScreenTypes getOverlayDestination() {
            return overlayDestination;
        }

        Image getImage() {
            return image;
        }

    }

    class MapTransitionListener extends ClickListener {

        Tradesong gameInstance;
        ExitArrow exit;

        MapTransitionListener(Tradesong gameInstance, ExitArrow exit) {
            Gdx.app.log("exit", "created");
            this.gameInstance = gameInstance;
            this.exit = exit;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            Gdx.app.log("exit", "touched");

            gameInstance.changeMap(exit.destination);

            return super.touchDown(event, x, y, pointer, button);
        }
    }

}
