package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workshops.Workshop;

/***/
public class AddWorkersButton extends TextButton {
    public AddWorkersButton(Workshop workshop, WorkerPool spareWorkers) {
        super("Assign worker", TradesongGame.skin);
        addListener(new AddWorkersListener(workshop, spareWorkers));
    }

    class AddWorkersListener extends ClickListener {
        private final Workshop workshop;
        private final WorkerPool spareWorkers;

        public AddWorkersListener(Workshop workshop, WorkerPool spareWorkers) {
            this.workshop = workshop;
            this.spareWorkers = spareWorkers;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (spareWorkers.hasWorkers()) {
                workshop.getWorkers().addWorker(spareWorkers.removeWorker());
            }
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
