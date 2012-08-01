package main.java.engine;

import java.util.Stack;

public class LayerStacker {
	private static Stack<Layer> stack;
	
	public LayerStacker(){
		stack = new Stack<Layer>();
	}
	
	public Layer pop() {
		Layer holder = stack.pop();
		
		return holder;
	}
	
	public void push(Layer input) {
		stack.push(input);
	}
	
	public Layer peek() {
		return stack.peek();
	}
	
	public Layer peek(int i) {
		return stack.elementAt(i);
	}
}
