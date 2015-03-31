package icbat.games.tradesong.engine.screens.components;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import icbat.games.tradesong.TradesongGame;
import icbat.games.tradesong.game.PlayerHoldings;
import icbat.games.tradesong.game.workshops.Workshop;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/***/
public class SellWorkshopListenerTest {

    protected SellWorkshopListener listener;
    protected PlayerHoldings holdings;

    @Before
    public void setUp() throws Exception {
        Gdx.app = mock(Application.class);
        holdings = mock(PlayerHoldings.class);
        TradesongGame.holdings = holdings;

        listener = new SellWorkshopListener(mock(Workshop.class));
    }

    @Test
    public void touchDown() throws Exception {
        listener.touchDown(mock(InputEvent.class), 0, 0, 0, 0);

        verify(holdings).addCurrency(anyInt());
    }
}