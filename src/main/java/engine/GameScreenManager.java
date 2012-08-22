package main.java.engine;

import java.util.Stack;

import javax.swing.JFrame;

public class GameScreenManager {
	private Stack<GameScreen> stack;
	public JFrame window;
	
	public GameScreenManager(JFrame theWindow){
		stack = new Stack<GameScreen>();
		window = theWindow;
	}
	
	public GameScreen pop() {
		GameScreen holder = stack.pop();
		holder.OnUnload(this);
		return holder;
	}
	
	public void push(GameScreen input) {
		stack.push(input);
		input.OnLoad(this);
		window.setVisible(true);
	}
	
	public GameScreen peek() {
		return stack.peek();
	}
	
	public GameScreen peek(int i) {
		return stack.elementAt(i);
	}

	@Override
	public String toString() {
		return "LayerStacker \n[stack=" + stack + "\nwindow=" + window + "]\n---\n";
	}
	
}
