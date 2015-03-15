package icbat.games.tradesong.game.workers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class WorkerPoolImplTest {

    protected WorkerPool pool;

    @Before
    public void setUp() throws Exception {
        pool = new WorkerPoolImpl();
    }

    @Test
    public void addingWorkers() throws Exception {
        assertFalse("pool not empty at start of test", pool.hasWorkers());

        pool.addWorker(mock(Worker.class));

        assertTrue("worker not added", pool.hasWorkers());
    }

    @Test
    public void removingWorker_whenEmpty() throws Exception {
        assertFalse("pool not empty at start of test", pool.hasWorkers());

        try {
            pool.removeWorker();
            fail("was able to remove a worker when none were there, undefined behavior");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void removingWorker_whenOneExists() throws Exception {
        final Worker worker = mock(Worker.class);
        pool.addWorker(worker);

        final Worker removed = pool.removeWorker();

        assertEquals("Worker removed was not the expected one!", worker, removed);

    }
}