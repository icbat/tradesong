package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.workers.WorkerPool;

/***/
public class RemoveWorkersButton extends TextButton {

    public RemoveWorkersButton(WorkerPool targetWorkerPool, WorkerPool spareWorkers) {
        super("Remove worker", TradesongGame.skin);
        addListener(new RemoveWorkersListener(targetWorkerPool, spareWorkers));
    }

    class RemoveWorkersListener extends ClickListener {
        private final WorkerPool targetWorkerPool;
        private final WorkerPool spareWorkers;

        public RemoveWorkersListener(WorkerPool targetWorkerPool, WorkerPool spareWorkers) {
            this.targetWorkerPool = targetWorkerPool;
            this.spareWorkers = spareWorkers;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            if (targetWorkerPool.hasWorkers()) {
                spareWorkers.addWorker(targetWorkerPool.removeWorker());
            }
            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
