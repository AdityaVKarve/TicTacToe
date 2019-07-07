package com.Aditya.gfx;
import javax.swing.JFrame;

import com.Aditya.Game.Game;



public class Display {
	static JFrame window=new JFrame();
	public static void main(String args[])
	{
		//JFrame window=new JFrame();
		window.setSize(1024,768);
		window.setVisible(true);
		window.setTitle("STRATGAME");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game g=new Game();
		window.add(g);
	}
	public static void refresh()
	{
		Game g=new Game();
		window.add(g);
		
	}
}