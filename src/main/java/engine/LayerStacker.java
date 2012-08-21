package main.java.engine;

import java.util.Stack;

import javax.swing.JFrame;

public class LayerStacker {
	private Stack<Layer> stack;
	public JFrame window;
	
	public LayerStacker(JFrame theWindow){
		stack = new Stack<Layer>();
		window = theWindow;
	}
	
	public Layer pop() {
		Layer holder = stack.pop();
		holder.OnUnload(this);
		return holder;
	}
	
	public void push(Layer input) {
		stack.push(input);
		input.OnLoad(this);
	}
	
	public Layer peek() {
		return stack.peek();
	}
	
	public Layer peek(int i) {
		return stack.elementAt(i);
	}

	@Override
	public String toString() {
		return "LayerStacker \n[stack=" + stack + "\nwindow=" + window + "]\n---\n";
	}
	
}
