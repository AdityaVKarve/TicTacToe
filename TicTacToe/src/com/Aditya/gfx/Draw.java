package com.Aditya.gfx;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Draw {
	private int x=0;
	private int y = 0;
	public Draw(int[] posn,int type, Graphics g) {
		x = posn[0];
		y = posn[1];
		render(g);
	}
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD, 32));
		g.drawString("X", x, y);
	}
}
