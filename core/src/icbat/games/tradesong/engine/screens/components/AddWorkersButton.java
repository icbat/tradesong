package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.workers.WorkerPool;

/***/
public class AddWorkersButton extends TextButton {
    public AddWorkersButton(WorkerPool targetWorkerPool, WorkerPool spareWorkers) {
        super("Assign worker", TradesongGame.skin);
        addListener(new AddWorkersListener(targetWorkerPool, spareWorkers));
    }

    class AddWorkersListener extends ClickListener {
        private final WorkerPool targetWorkerPool;
        private final WorkerPool spareWorkers;

        public AddWorkersListener(WorkerPool targetWorkerPool, WorkerPool spareWorkers) {
            this.targetWorkerPool = targetWorkerPool;
            this.spareWorkers = spareWorkers;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (spareWorkers.hasWorkers()) {
                targetWorkerPool.addWorker(spareWorkers.removeWorker());
            }
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
