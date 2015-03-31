package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workshops.Workshop;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/***/
public class BuyWorkshopListenerTest {

    protected BuyWorkshopListener listener;
    protected PlayerHoldings holdings;
    protected Workshop workshop;

    @Before
    public void setUp() throws Exception {
        holdings = mock(PlayerHoldings.class);
        TradesongGame.holdings = holdings;
        workshop = mock(Workshop.class);
        listener = new BuyWorkshopListener(workshop);
    }

    @Test
    public void touchDown_wontWorkWithoutMoney() throws Exception {
        when(workshop.canAfford(anyInt())).thenReturn(false);

        listener.touchDown(mock(InputEvent.class), 0, 0, 0, 0);

        verify(holdings, never()).addWorkshop(any(Workshop.class));
    }

    @Test
    public void touchDown_canAfford() throws Exception {
        when(workshop.canAfford(anyInt())).thenReturn(true);

        listener.touchDown(mock(InputEvent.class), 0, 0, 0, 0);

        verify(holdings).addWorkshop(any(Workshop.class));
    }

    @Test
    public void touchDown_canAfford_subtractsFunds() throws Exception {
        when(workshop.canAfford(anyInt())).thenReturn(true);

        listener.touchDown(mock(InputEvent.class), 0, 0, 0, 0);

        verify(holdings).removeCurrency(anyInt());
    }
}