/* (c) 2015 Marian Longa. http://marianlonga.com.  */

package com.marianlonga.pong;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Main 
	extends Applet
	implements MouseListener, MouseMotionListener{
	
	private static final long serialVersionUID = 1L;
	final static int 
		FPS = 24;
	static int
		width = 800,  // just in case width and
		height = 600, // height aren't determined
		score1 = 0,
		score2 = 0,
		AIMoveSpeed = 10;
	Player p1, p2;
	Ball b;
	Timer timer;
	
	public void init() {
		//width = getWidth();
		//height = getHeight();
		setSize(width, height);
		
		this.p1 = new Player(20, height / 2 - 35, 10, 70, new Color(200, 240, 200));
		this.p2 = new Player(width - 35, height / 2 - 30, 10, 70, new Color(200, 200, 240));
		this.b = new Ball(width / 2, height / 2, 7, new Color(255, 255, 190));
		
		addMouseListener(this);
		addMouseMotionListener(this);
		
		b.throwBall();
		
		//FPS = 40;
		timer = new Timer();
		timer.schedule(new RepaintTask(), 0, 1000/FPS);

	}
	
	public void paint(Graphics g) {
		drawBackground(g);
		drawPlayer(p1, g);
		drawPlayer(p2, g);
		b.move(p1, p2);
		p2.move(b);
		drawBall(g);
	}
	
	// Drawing functions
	
	public void drawBackground(Graphics g) {
		
		g.setColor(new Color(220, 220, 220));
		g.fillRect(0, 0, width, height);
		
		g.setColor(new Color(230, 230, 230));
		for (int y = 0; y <= height ; y += 30) {
			g.fillRect(width / 2 - 5, y, 10, 20);
		}
		
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, 10);
		g.fillRect(0, height - 10, width, 10);
		
		g.setColor(new Color(150, 100, 100));
		g.fillRect(0, 0, 10, height);
		g.fillRect(width - 10, 0, 10, height);
		
		// show score
		g.setColor(new Color(230, 230, 230));
		Font font = new Font("Dialog", Font.PLAIN, 80);
		g.setFont(font);
		String s1 = Integer.toString(score1);
		String s2 = Integer.toString(score2);
		if (score1 < 10) s1 = "0" + s1;
		if (score2 < 10) s2 = "0" + s2;
		if (score1 >= 100) s1 = "99"; 
		if (score2 >= 100) s2 = "99"; 
		g.drawString(s1, width / 2 - 140, 100);
		g.drawString(s2, width / 2 + 40, 100);
		
	}
	
	public void drawPlayer(Player p, Graphics g) {
		g.setColor(p.getColor());
		g.fillRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
		g.setColor(Color.black);
		g.drawRect(p.getX(), p.getY(), p.getWidth(), p.getHeight());
	}
	
	public void drawBall(Graphics g) {
		g.setColor(this.b.getColor());
		g.fillOval(this.b.getX() - this.b.getRadius(), this.b.getY() - this.b.getRadius(), this.b.getRadius() * 2, this.b.getRadius() * 2);
		g.setColor(Color.black);
		g.drawOval(this.b.getX() - this.b.getRadius(), this.b.getY() - this.b.getRadius(), this.b.getRadius() * 2, this.b.getRadius() * 2);
	}
	
	
	// Timer task class
	
	class RepaintTask extends TimerTask {
		@Override
		public void run() {
			repaint();
		}
		
	}
	
	
	// Mouse events
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int halfWidth = this.p1.getHeight() / 2;
		if (e.getY() - halfWidth < 10) this.p1.setY(10);
		else if (e.getY() + halfWidth > height - 10) this.p1.setY(height - 10 - halfWidth * 2);
		else this.p1.setY(e.getY() - halfWidth);
		e.consume();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
