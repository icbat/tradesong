package main.java.engine;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface GameScreen {
	void OnLoad(GameScreenManager sender);
	void OnUnload(GameScreenManager sender);
    //void OnUpdateFrame(FrameEventArgs e, KeyboardDevice Keyboard, MouseDevice Mouse);
    //void OnRenderFrame(FrameEventArgs e);
//    void OnResize(Event e, Resolution newResolution);
//    void OnKeyDown(Object sender, KeyEvent e );
//    void OnKeyUp(Object sender, KeyEvent e);
//    void OnMouseDown(Object sender, MouseEvent e);
//    void OnMouseUp(Object sender, MouseEvent e);
    
}
