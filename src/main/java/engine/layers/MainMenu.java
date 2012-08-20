package main.java.engine.layers;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import main.java.engine.Layer;
import main.java.engine.LayerStacker;
import main.java.engine.Resolution;



public final class MainMenu implements Layer {
	private Background bg;
	
	
	public void OnLoad(LayerStacker sender) {
		bg = new Background();
		sender.window.getContentPane().add(bg);
		
		
	}
	
	
	public void OnUnload(LayerStacker sender) {
		sender.window.getContentPane().remove(bg);

	}

	
	public void OnResize(Event e, Resolution newResolution) {
		

	}

	
	public void OnKeyDown(Object sender, KeyEvent e) {
		

	}

	
	public void OnKeyUp(Object sender, KeyEvent e) {
		

	}

	
	public void OnMouseDown(Object sender, MouseEvent e) {
		

	}

	
	public void OnMouseUp(Object sender, MouseEvent e) {
		

	}



	
	private class Background extends Canvas {
		private static final long serialVersionUID = 221977529370055154L;

		public void paint(Graphics g) {
			Dimension d = new Dimension(640, 480);
			g.setColor(Color.black);
			g.fillRect(0, 0, d.width, d.height);
		}
	}
}
