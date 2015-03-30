package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.workers.WorkerPool;
import icbat.games.tradesong.game.workshops.Workshop;

/***/
public class RemoveWorkersButton extends TextButton {

    public RemoveWorkersButton(Workshop workshop, WorkerPool spareWorkers) {
        super("Remove worker", TradesongGame.skin);
        addListener(new RemoveWorkersListener(workshop, spareWorkers));
    }

    class RemoveWorkersListener extends ClickListener {
        private final Workshop workshop;
        private final WorkerPool spareWorkers;

        public RemoveWorkersListener(Workshop workshop, WorkerPool spareWorkers) {
            this.workshop = workshop;
            this.spareWorkers = spareWorkers;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (workshop.getWorkers().hasWorkers()) {
                spareWorkers.addWorker(workshop.getWorkers().removeWorker());
            }
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
