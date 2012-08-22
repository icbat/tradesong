package main.java.engine.screens;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import main.java.engine.GameScreen;
import main.java.engine.GameScreenManager;



public final class MainMenu implements GameScreen {
	private JLayeredPane menu;
	private JLabel bg;
	private JButton startButton;
	private JButton loadButton;
	private JButton exitButton;
	
	private Integer backgroundLevel = new Integer(0);
	private Integer menuLevel = new Integer(1);
	
	private int buttonHeight = 50;
	private int buttonWidth = 200;
	private int buttonOffsetFactor = 100;
	
	private Dimension screenSize = new Dimension();
	
	
	
	public void OnLoad(GameScreenManager sender) {
		Container window = sender.window.getContentPane();
		screenSize = window.getSize();
		
		
		menu = new JLayeredPane();
		menu.setSize(window.getSize());
		
		bg = new JLabel(new ImageIcon(getImage(Paths.get("assets/menus/title.png"))));
		bg.setSize(window.getSize());
		
		startButton = makeButton("New Game", -1);
		loadButton = makeButton("Load Game", 0);
		exitButton = makeButton("Exit Game", +1);
		
		
		menu.add(startButton, menuLevel);
		menu.add(loadButton, menuLevel);
		menu.add(exitButton, menuLevel);
		
		menu.add(bg, backgroundLevel);
		menu.setVisible(true);
		window.add(menu);
	}
	
	
	public void OnUnload(GameScreenManager sender) {
		Container content = sender.window.getContentPane();
		menu.setVisible(false);
		menu.remove(bg);
		menu.remove(startButton);
		menu.remove(loadButton);
		menu.remove(exitButton);
		content.remove(menu);
	}
	
	public Image getImage(Path path) {
		Image img = null;
		try {
		    img = ImageIO.read(path.toFile());
		} catch (IOException e) {
			System.out.print("file not found\n"); //TODO useful catch
		}
		return img;
	}
	
	private JButton makeButton(String text, int verticalOffset) {
//		private int buttonHeight = 50;
//		private int buttonWidth = 200;
//		private int buttonOffsetFactor = 100;
//		
//		private Dimension screenSize = new Dimension();
//		
		int centerX = screenSize.width / 2;
		int centerY = screenSize.height / 2;
		
		JButton out = new JButton(text);
		out.setBounds(centerX - (buttonWidth/2),
				centerY + (verticalOffset * buttonOffsetFactor), 
				buttonWidth,
				buttonHeight);
		out.setBackground(Color.cyan);
		
		return out;
	}
		

}
