/* (c) 2015 Marian Longa. http://marianlonga.com.  */

package com.marianlonga.pong;

import java.awt.Color;

public class Player {
	int x, y, width, height;
	Color color;

	public Player(int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void move(Ball b) { // method for moving computer-based player
		int diff = y + height / 2 - b.getY();
		if (diff > 0) { // p2 needs to go up
			if (diff <= Main.AIMoveSpeed) y = b.getY() - height / 2;
			else y -= Main.AIMoveSpeed;
		}
		if (diff < 0) { // p2 needs to go down
			if (-diff <= Main.AIMoveSpeed) y = b.getY() - height / 2;
			else y += Main.AIMoveSpeed;
		}
		if (y < 10) y = 10;
		if (y + height > Main.height - 10) y = Main.height - 10 - height;
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

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
}
