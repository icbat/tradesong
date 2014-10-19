package com.minus7games.tradesong;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Matchers;

import static org.mockito.Mockito.*;

public class ScreenManagerTest {


    protected ScreenManager screenManager;
    protected Tradesong game;

    @Before
    public void setUp() throws Exception {
        // Necessary so log statements won't throw NPE
        Gdx.app = mock(Application.class);
        game = mock(Tradesong.class);
        screenManager = new ScreenManager(game);
    }

    @Test
    public void goBack_nothingAdded_doesNothing() throws Exception {
        screenManager.goBack();

        verify(game, never()).setScreen(Matchers.<Screen>any());
    }

    @Test
    public void goBack_oneThingAdded_goesToIt() throws Exception {
        Screen screen = mock(Screen.class);
        screenManager.moveToScreen(screen, true);

        screenManager.goBack();

        verify(game, times(2)).setScreen(screen);
    }

    @Test
    public void moveButDontAddToChain() throws Exception {
        Screen screen = mock(Screen.class);
        screenManager.moveToScreen(screen, false);

        screenManager.goBack();

        verify(game, times(1)).setScreen(screen);
    }

    @Test
    public void goBack_getsLastAdded() throws Exception {
        Screen firstScreen = mock(Screen.class);
        Screen secondScreen = mock(Screen.class);
        screenManager.moveToScreen(firstScreen, true);
        screenManager.moveToScreen(secondScreen, true);

        screenManager.goBack();

        verify(game, times(2)).setScreen(secondScreen);
        verify(game, times(1)).setScreen(firstScreen);
    }

}