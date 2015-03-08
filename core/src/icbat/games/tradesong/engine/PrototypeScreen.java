package icbat.games.tradesong.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import icbat.games.tradesong.game.Workshop;
import icbat.games.tradesong.game.workshops.ProducerWorkshop;

import java.util.ArrayList;
import java.util.Collection;

public class PrototypeScreen implements Screen {

    Collection<Workshop> workshops = new ArrayList<Workshop>();

    public PrototypeScreen() {
        workshops.add(new ProducerWorkshop());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        Gdx.app.debug("proto screen", "resized");
    }

    @Override
    public void show() {
        Gdx.app.debug("proto screen", "shown");
    }

    @Override
    public void hide() {
        Gdx.app.debug("proto screen", "hidden");
    }

    @Override
    public void pause() {
        Gdx.app.debug("proto screen", "paused");
    }

    @Override
    public void resume() {
        Gdx.app.debug("proto screen", "resumed");
    }

    @Override
    public void dispose() {
        Gdx.app.debug("proto screen", "disposed");
    }
}
