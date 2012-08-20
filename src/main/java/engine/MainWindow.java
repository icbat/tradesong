package main.java.engine;

import javax.swing.JFrame;

public class MainWindow extends JFrame  {

	private static final long serialVersionUID = 2977091407339616875L;

	public MainWindow() {
        setTitle("Hamlet");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 320);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
}
