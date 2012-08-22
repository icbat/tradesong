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
		System.out.print("## popping!\n");
		GameScreen holder = stack.pop();
		holder.OnUnload(this);
		System.out.print("## Success! Popped:  " + holder + "\n##\n");
		return holder;
	}
	
	public void push(GameScreen input) {
		System.out.print("## Pushing:  " + input + "\n");
		stack.push(input);
		input.OnLoad(this);
		window.update(window.getGraphics());
		System.out.print("## Success!\n\n");
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
