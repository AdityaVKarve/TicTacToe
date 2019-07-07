package com.Aditya.Game;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.geom.Line2D;

import com.Aditya.AI.AI;
import com.Aditya.controls.Mouse;
import com.Aditya.gfx.Draw;
import com.Aditya.states.GameStateManager;
import com.Aditya.states.MenuState;


import javax.swing.JFrame;


public class Game extends Canvas implements Runnable, KeyListener{
	
	static int scale = 4;
	static int width = 100;
	static int gameMode = 0;
	private int height = 100;
	private Color bgColor= new Color(0x494e6b);
	private Thread thread;
	private JFrame frame;
	private boolean isRunning = false;
	private Draw draw;
	static char[] tileRoster ={' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
	private GameStateManager gsm;
	private MenuState menu;
	private int key;
	static boolean turn = false;
	private char X = 'X';
	private char O = 'O';
	AI ai = new AI();
	static int currentSelection = 0;
	Mouse mouse=new Mouse();
	private String[] options= {"Single Player","Multi Player","Quit"};
	public Game() {
		frame = new JFrame();
		Dimension dimension = new Dimension(width * scale, height * scale);
		setPreferredSize(dimension);
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		addKeyListener(this);
		gsm = new GameStateManager();
	}
	
	public synchronized void start()
	{
		isRunning=true;
		thread=new Thread(this,"Display");
		thread.start();
	}
	
	public synchronized void stop()
	{
		isRunning=false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run() {
		// TODO Auto-generated method stub
		long lastTime=System.nanoTime();
		final double ns=1000000000.0/60.0;
		long timer1s=System.currentTimeMillis();
		double delta=0;
		int frames=0;
		int updates=0;
		requestFocus();
		while(isRunning)
		{
			long now=System.nanoTime();
			delta=(now-lastTime)/ns;
			//System.out.println("Running...");
			if(delta>=1)
			{
				lastTime=System.nanoTime();
				delta--;
				updates++;
				update();	//Updates game mathematics like player position, 60 times a second
			}
			render();	//Draws things to the screen, this happens infinite times
			frames++;	
			if(System.currentTimeMillis()-timer1s>=1000)
			{
				//System.out.println("FPS-"+frames+"\nups-"+updates);
				frames=0;
				updates=0;
				timer1s+=1000;
			}
		}
		stop();
	}
	public void render() {
		
		
		BufferStrategy bs=getBufferStrategy();	//This allocates a location in the RAM for the storage of frames before display, to smoothen performance
		if(bs==null)	//We only want to create a buffer strategy if there isn't one, else it's pointless
		{
			createBufferStrategy(3);	//We choose 3 as that means 2 back buffers, where we can work on next frame while the current frame is yet to be displayed
			return;
		}
		Graphics g=bs.getDrawGraphics();	//Creates a link b/w graphics & Buffer
		
		//Draw here
		if(gameMode == 0) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width*scale, width * scale);
			g.setFont(new Font("Helvetica", Font.ITALIC,50));
			g.setColor(Color.black);
			g.drawString("TIC TAC TOE", width*scale/2-155, 50);
			g.setFont(new Font("Helvetica", Font.ITALIC,30));
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
				g.drawString(options[i], width*scale/2 - 100,140+(i+1)*50 );
			}
		}
		else {
		g.setColor(bgColor);
		g.fillRect(0, 0, width * scale, height * scale);
		g.setColor(Color.WHITE);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(width * scale / 3, 0, width * scale / 3, height * scale);
		g2.drawLine(width * scale / 3 * 2, 0, width * scale / 3 * 2, height * scale);
		g2.drawLine(0, height * scale /3, width * scale , height * scale / 3);
		g2.drawLine(0, height * scale * 2 /3, width * scale , height * scale * 2 / 3);
		int offset = 30;
		int trueWidth = width * scale;	//Use only if width = height
		int cells[] = {trueWidth /6 - offset, trueWidth /6 + offset, trueWidth /6 - offset, trueWidth/2 + offset, trueWidth / 6 - offset, trueWidth*5/6 + offset,
				trueWidth/2 - offset, trueWidth/6 + offset, trueWidth /2 - offset, trueWidth/2 + offset, trueWidth / 2 - offset, trueWidth*5/6 + offset,
				trueWidth*5/6 - offset, trueWidth/6 + offset, trueWidth *5/6 - offset, trueWidth/2 + offset, trueWidth * 5/6 - offset, trueWidth * 5/6 + offset};
		g.setColor(Color.WHITE);
		g.setFont(new Font("Helvetica", Font.BOLD, 100));
		if(tileRoster[0] == 'O' && tileRoster[4] == 'O' && tileRoster[8] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
			}
		
		if(tileRoster[6] == 'O' && tileRoster[4] == 'O' && tileRoster[2] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[0] == 'O' && tileRoster[3] == 'O' && tileRoster[6] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[1] == 'O' && tileRoster[4] == 'O' && tileRoster[7] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[2] == 'O' && tileRoster[5] == 'O' && tileRoster[8] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[0] == 'O' && tileRoster[1] == 'O' && tileRoster[2] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[3] == 'O' && tileRoster[4] == 'O' && tileRoster[5] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[6] == 'O' && tileRoster[7] == 'O' && tileRoster[8] == 'O') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		
		if(tileRoster[0] == 'X' && tileRoster[4] == 'X' && tileRoster[8] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player X wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[6] == 'X' && tileRoster[4] == 'X' && tileRoster[2] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player X wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[0] == 'X' && tileRoster[3] == 'X' && tileRoster[6] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player X wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[1] == 'X' && tileRoster[4] == 'X' && tileRoster[7] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[2] == 'X' && tileRoster[5] == 'X' && tileRoster[8] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player X wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[0] == 'X' && tileRoster[1] == 'X' && tileRoster[2] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player O wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[3] == 'X' && tileRoster[4] == 'X' && tileRoster[5] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player X wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		if(tileRoster[6] == 'X' && tileRoster[7] == 'X' && tileRoster[8] == 'X') {
			g.setColor(Color.GREEN);
			g.fillRect(0, 0, width*scale, width*scale);
			g.setColor(Color.white);
			g.setFont(new Font("Helvetica", Font.ITALIC,20));
			g.drawString("Player X wins!", width*scale/2 - 100, width*scale/2);
			for(int i = 0; i < 9; i ++) {
				tileRoster[i] = ' ';
			}
		}
		for(int i = 0; i < 9; i ++) {
			if(tileRoster[i] == 'X') {
				g.drawString("X", cells[2*i], cells[2*i+1]);
			}
			if(tileRoster[i] == 'O') {
				g.drawString("O", cells[2*i], cells[2*i+1]);
			}
		}
		}
		g.dispose();
		bs.show();
	}
	
	public void update() {
		if(gameMode == 0) {
			if(key==KeyEvent.VK_DOWN)
			{
				currentSelection++;
				try {
					thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(currentSelection>=options.length)
					currentSelection=0;
			}
			else if(key==KeyEvent.VK_UP)
			{
				currentSelection--;
				try {
					thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(currentSelection<0)
					currentSelection=options.length;
			}
			if(key==KeyEvent.VK_ENTER)
			{
				if(currentSelection == 0) {
					gameMode = 1;
				}
				if(currentSelection == 1) {
					gameMode = 2;
				}
				if(currentSelection == 2) {
					System.exit(0);
				}
			}
		}
		if(gameMode==1){
			if(turn == false)
			{
				if(Mouse.getB()==1) {
				System.out.println("Click");
				int x = Mouse.getX();
				int y = Mouse.getY();
				char ret;
				ret = X;
				if(x < width * scale / 3 && y < height * scale / 3)
					tileRoster[0]=ret;
				if(x < width * scale / 3 && (y > height * scale / 3 && y < height * scale * 2 /3))
					tileRoster[1]=ret;
				if(x < width * scale / 3 && y > height * scale * 2 / 3)
					tileRoster[2]=ret;
				if((x > width * scale / 3 && x < 2 * width * scale /3) && y < height * scale / 3)
					tileRoster[3]=ret;
				if((x > width * scale / 3 && x < 2 * width * scale /3) && (y > height * scale / 3 && y < height * scale * 2 /3))
					tileRoster[4]=ret;
				if((x > width * scale / 3 && x < 2 * width * scale /3) && y > height * scale * 2 / 3)
					tileRoster[5]=ret;
				if(x > width * scale *2 / 3 && y < height * scale  / 3)
					tileRoster[6]=ret;
				if(x > width * scale *2 / 3 && (y > height * scale / 3 && y < height * scale * 2 /3))
					tileRoster[7]=ret;
				if(x > width * scale *2 / 3 && y > height * scale * 2 / 3)
					tileRoster[8]=ret;
				turn =!turn;
				try {
					thread.sleep(500);
					render();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			if(turn == true) {
				try {
					thread.sleep(500);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			System.out.println("AI turn");
			tileRoster=ai.move(tileRoster, O);
			turn = ! turn;
			render();
			}
			
		}
		if(gameMode == 2) {
			if(turn == false)
			{
				if(Mouse.getB()==1) {
				System.out.println("Click");
				int x = Mouse.getX();
				int y = Mouse.getY();
				char ret;
				ret = X;
				if(x < width * scale / 3 && y < height * scale / 3)
					tileRoster[0]=ret;
				if(x < width * scale / 3 && (y > height * scale / 3 && y < height * scale * 2 /3))
					tileRoster[1]=ret;
				if(x < width * scale / 3 && y > height * scale * 2 / 3)
					tileRoster[2]=ret;
				if((x > width * scale / 3 && x < 2 * width * scale /3) && y < height * scale / 3)
					tileRoster[3]=ret;
				if((x > width * scale / 3 && x < 2 * width * scale /3) && (y > height * scale / 3 && y < height * scale * 2 /3))
					tileRoster[4]=ret;
				if((x > width * scale / 3 && x < 2 * width * scale /3) && y > height * scale * 2 / 3)
					tileRoster[5]=ret;
				if(x > width * scale *2 / 3 && y < height * scale  / 3)
					tileRoster[6]=ret;
				if(x > width * scale *2 / 3 && (y > height * scale / 3 && y < height * scale * 2 /3))
					tileRoster[7]=ret;
				if(x > width * scale *2 / 3 && y > height * scale * 2 / 3)
					tileRoster[8]=ret;
				turn =!turn;
				try {
					thread.sleep(500);
					render();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
			if(turn == true) {
				if(Mouse.getB()==1) {
					System.out.println("Click");
					int x = Mouse.getX();
					int y = Mouse.getY();
					char ret;
					ret = O;
					if(x < width * scale / 3 && y < height * scale / 3)
						tileRoster[0]=ret;
					if(x < width * scale / 3 && (y > height * scale / 3 && y < height * scale * 2 /3))
						tileRoster[1]=ret;
					if(x < width * scale / 3 && y > height * scale * 2 / 3)
						tileRoster[2]=ret;
					if((x > width * scale / 3 && x < 2 * width * scale /3) && y < height * scale / 3)
						tileRoster[3]=ret;
					if((x > width * scale / 3 && x < 2 * width * scale /3) && (y > height * scale / 3 && y < height * scale * 2 /3))
						tileRoster[4]=ret;
					if((x > width * scale / 3 && x < 2 * width * scale /3) && y > height * scale * 2 / 3)
						tileRoster[5]=ret;
					if(x > width * scale *2 / 3 && y < height * scale  / 3)
						tileRoster[6]=ret;
					if(x > width * scale *2 / 3 && (y > height * scale / 3 && y < height * scale * 2 /3))
						tileRoster[7]=ret;
					if(x > width * scale *2 / 3 && y > height * scale * 2 / 3)
						tileRoster[8]=ret;
					turn =!turn;
					try {
						thread.sleep(500);
						render();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			}
		}
	}
	public static int getDimension() {
		return scale*width;
	}
	public static void main(String args[]){
		Game game=new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Tic Tac Toe");
		game.frame.add(game);	//We can add game to window as Game extends canvas
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Doing this closes the program with the window
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		
		game.start();
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		key = arg0.getKeyCode();
		System.out.println("Key pressed");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		key = -1;
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
