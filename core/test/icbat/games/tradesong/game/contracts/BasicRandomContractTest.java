package icbat.games.tradesong.game.contracts;

import icbat.games.tradesong.game.Item;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workers.Worker;
import icbat.games.tradesong.game.workers.WorkerPool;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BasicRandomContractTest {
    protected Collection<Item> storage;
    private BasicRandomContract contract;
    private PlayerHoldings holdings = mock(PlayerHoldings.class);
    private Item requiredItem = mock(Item.class);
    private WorkerPool workerPool;

    @Before
    public void setUp() throws Exception {
        contract = new BasicRandomContract(requiredItem);

        storage = mock(Collection.class);
        workerPool = mock(WorkerPool.class);
        when(holdings.getStorageContents()).thenReturn(storage);
        when(holdings.getSpareWorkers()).thenReturn(workerPool);
    }

    @Test
    public void cantComplete_withoutRequiredItems() throws Exception {
        when(storage.contains(requiredItem)).thenReturn(false);

        assertFalse("could complete a contract without any items in storage", contract.canComplete(holdings));
    }

    @Test
    public void canComplete_withGoodInputs() throws Exception {
        when(storage.contains(requiredItem)).thenReturn(true);

        assertTrue("couldn't complete contract despite having the thing", contract.canComplete(holdings));
    }

    @Test
    public void onCompletion_addsWorker() throws Exception {
        when(storage.contains(requiredItem)).thenReturn(true);

        contract.completeContract(holdings);

        verify(workerPool).addWorker(Matchers.<Worker>any());
    }

    @Test
    public void onCompletion_withoutRequirements_throws() throws Exception {
        when(storage.contains(requiredItem)).thenReturn(false);

        try {
            contract.completeContract(holdings);
            fail("contract was allowed to complete w/o requirements!");
        } catch (IllegalStateException ise) {
            assertTrue(true);
        }
    }

    @Test
    public void onCompletion_removesRequirements() throws Exception {
        when(storage.contains(requiredItem)).thenReturn(true);

        contract.completeContract(holdings);

        verify(holdings).removeFromStorage(requiredItem);

    }
}