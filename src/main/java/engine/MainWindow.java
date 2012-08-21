package main.java.engine;

import java.awt.Color;

import javax.swing.JFrame;

public class MainWindow extends JFrame  {

	private static final long serialVersionUID = 2977091407339616875L;

	public MainWindow() {
        setTitle("Hamlet");
        setBackground(Color.cyan);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
