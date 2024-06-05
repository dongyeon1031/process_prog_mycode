package main;

import java.io.IOException;

public class Main {
	// attribute
	
	// component
	private VMainFrame vmainframe;
	
	// association
	
	// constructors
	public Main() {
		this.vmainframe = new VMainFrame();
		this.vmainframe.setVisible(true);
	}

	// method
	public void initialize() {
		this.vmainframe.initialize();
	}
	
	public void run() {
		
	}
	// main
	public static void main(String[] args) throws IOException {
		Main main = new Main();
		main.initialize();
		main.run();
		
		System.out.println("hello git!!!");
	}
}
