package main.java.Engine;

import java.util.Stack;

public class StateStacker {
	private static Stack<State> stack;
	
	public StateStacker(){
		stack = new Stack<State>();
	}
	
	public State pop() {
		State holder = stack.pop();
		
		return holder;
	}
	
	public void push(State input) {
		stack.push(input);
	}
	
	public State peek() {
		return stack.peek();
	}
	
	public State peek(int i) {
		return stack.elementAt(i);
	}
}
