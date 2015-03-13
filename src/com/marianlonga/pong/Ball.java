/* (c) 2015 Marian Longa. http://marianlonga.com.  */

package com.marianlonga.pong;

import java.awt.Color;
import java.util.Random;

public class Ball {
	
	int x, y, radius, vx, vy;
	final int
		minThrowSpeedX = 10,
		maxThrowSpeedX = 15,
		minThrowSpeedY = 10,
		maxThrowSpeedY = 15,
		maxBounceAngle = 60; // [degrees]
	int generatedSpeedX = 0, generatedSpeedY = 0;
	Color color;
	
	public Ball(int x, int y, int radius, Color color) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.color = color;
		this.vx = this.vy = 0; // velocity
	}
	
	public void throwBall() {
		Random generator = new Random(System.currentTimeMillis());
		// generate velocities in absolute value
		vx = generatedSpeedX = (int) (generator.nextDouble() * (maxThrowSpeedX - minThrowSpeedX + 1) + minThrowSpeedX);
		vy = generatedSpeedY = (int) (generator.nextDouble() * (maxThrowSpeedY - minThrowSpeedY + 1) + minThrowSpeedY);
		// assign signs (not absolute value)
		if (generator.nextInt(2) == 0) vx = -vx;
		if (generator.nextInt(2) == 0) vy = -vy;
		
		// justify AI speed according to ball speed
		Main.AIMoveSpeed = Math.abs(vy) - 4;
	}
	
	public void move(Player p1, Player p2) {
		x += vx;
		y += vy;
		
		// some player lost
		if (x - radius < 10) {
			Main.score2++;
			x = Main.width / 2;
			y = Main.width / 2;
			throwBall();
		}
		if (x + radius > Main.width - 10) {
			Main.score1++;
			x = Main.width / 2;
			y = Main.width / 2;
			throwBall();
		}

		// bounce off the borders
		if (y - radius < 10) {
			y = 10 + radius;
			vy = -vy;
		}
		if (y + radius > Main.height - 10) {
			y = Main.height - 10 - radius;
			vy = -vy;
		}
		
		// TODO based on where the ball hits 1st player it will bounce off at different angle
		// bounce off the 1st player
		if (x - radius <= p1.getX() + p1.getWidth() && y + radius >= p1.getY() && y - radius <= p1.getY() + p1.getHeight()) {
			
			x = p1.getX() + p1.getWidth() + radius;
			
			// calculating speeds using trigonometry
			vx = (int) (Math.sqrt(vx * vx + vy * vy) * Math.cos(calculateOutgoingBounceAngle(p1)));
			vy = - (int) (Math.sqrt(vx * vx + vy * vy) * Math.sin(calculateOutgoingBounceAngle(p1)));
			
			adjustVelocityVector();
			
		}
		// bounce off the 2nd player
		if (x + radius >= p2.getX() && y + radius >= p2.getY() && y - radius <= p2.getY() + p2.getHeight()) {
			
			x = p2.getX() - radius;
			
			// calculating speeds using trigonometry
			vx = (int) (Math.sqrt(vx * vx + vy * vy) * Math.cos(Math.PI - calculateOutgoingBounceAngle(p2)));
			vy = - (int) (Math.sqrt(vx * vx + vy * vy) * Math.sin(Math.PI - calculateOutgoingBounceAngle(p2)));
			
			adjustVelocityVector();
		}
		
	}
	
	public double calculateOutgoingBounceAngle(Player p) {
		double angle = (double) (y - (p.getY() - radius)) / (p.getHeight() + 2 * radius); // percentage
		angle -= 0.5; // percentage with respect to the middle
		angle *= 2; // we have values from -1 to 1;
		angle *= maxBounceAngle * Math.PI / 180;
		return -angle; // like in mathematics (positive angle is "upwards", negative "downwards")
	}
	
	public void adjustVelocityVector() {
		// adjust velocity to have the same vector as given when initial ball throwing
		double coefficient = Math.sqrt(generatedSpeedX * generatedSpeedX + generatedSpeedY * generatedSpeedY) / Math.sqrt(vx * vx + vy * vy);
		vx = (int) ((double) vx * coefficient);
		vy = (int) ((double) vy * coefficient);
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getRadius() {
		return radius;
	}
	public void setRadius(int radius) {
		this.radius = radius;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
