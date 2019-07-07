package com.Aditya.states;

import java.awt.Graphics;

public abstract class GameState 
{
	public GameStateManager gsm;
	
	public GameState(GameStateManager gsm)
	{
		this.gsm=gsm;
	}
	public abstract void init();
	public abstract void draw(Graphics g);
	public abstract void tick();
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
	
}
