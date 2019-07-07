package com.Aditya.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.Aditya.Game.Game;


public class MenuState extends GameState {
	private String[] options= {"Single Player","Multi Player","Quit"};
	int currentSelection=0;
	protected MenuState(GameStateManager gsm)
	{
		super(gsm);
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		int dimension = Game.getDimension();
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, dimension, dimension);
		g.setFont(new Font("Helvetica", Font.ITALIC,30));
		g.setColor(Color.black);
		for(int i=0;i<options.length;i++)
		{
			if(i==currentSelection)
			{
				g.setColor(Color.GRAY);
			}
			else
			{
				g.setColor(Color.black);
			}
			//g.drawLine(GamePanel.WIDTH/2, 0, GamePanel.WIDTH/2, GamePanel.HEIGHT);
			g.setFont(new Font("Helvetica", Font.ITALIC,30));
			g.drawString(options[i], dimension/2 - 100,100+(i+1)*20 );
		}
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		if(k==KeyEvent.VK_DOWN)
		{
			currentSelection++;
			if(currentSelection>=options.length)
				currentSelection=0;
		}
		else if(k==KeyEvent.VK_UP)
		{
			currentSelection--;
			if(currentSelection<0)
				currentSelection=options.length;
		}
		if(k==KeyEvent.VK_ENTER)
		{
			if(currentSelection==0)
			{
				
			}
			else if(currentSelection==1)
			{
				
			}
			else if(currentSelection==2)
			{
				System.exit(0);
			}
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}
}
