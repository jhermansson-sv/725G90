package testing;

import java.awt.Canvas;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class is the main enemy, which extends the aircraft class. This way, we dont't need
 * seperate methods to draw ourselves out, we can just use the player classes methods.
 * We only add a "update" method, that controls how the enemy moves, 
 * and a "getBottomReached()" method that checks if the enemy is out of screen.
 */


public class Enemy extends Aircraft {

	private int speed = 0;
	private boolean bottomReached = false;
	private boolean frozen;
	Image ship;

	public Enemy(Image ship, double posX, double posY, int size, int speed) {
		super(ship, posX, posY, size);
		this.ship = ship;
		this.setSpeed((speed/15)+2);
		
	}

	public void update() {

		if (!isDestroyed() && !isExploding()) {
			if (!frozen) {
			setPosY(getPosY() + getSpeed());
			}
			setPosY(getPosY() + 0);
		}

		if (getPosY() > 750) {
			this.setDestroyed(true);
			bottomReached = true;
		}
	}

	public Boolean getBottomReached() {
		return bottomReached;
	}
	
	public void setBottomReached() {
		this.bottomReached = true;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setFrozen() {
		this.frozen = true;
	}
	
	public boolean getFrozen() {
		return this.frozen;
	}
	
}