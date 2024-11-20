package testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This is an abstract class that represents the different PowerUps in the game.
 * The main functionality consists of a checkPlayerCollision() method, that calculates the distance between the player
 * and the enemy, and if the x and y coordinates "collide", it returns a true boolean value, which we later can use for lots of things,
 * like adding powerUps to the player
 */
public abstract class PowerUp {

	private Image powerUp;
	private double speed = 2.0;
	private double posX, posY;
	private int size;

	public PowerUp(Image powerUp, double posX, double posY, int Size) {
		this.powerUp = powerUp;
		this.setPosX(posX);
		this.setPosY(posY);
		this.setSize(Size);
	}
	
	public boolean checkPlayerCollision(Aircraft player) {
	    double distance = Math.sqrt(Math.pow(getPosX() - player.getPosX(), 2) + Math.pow(getPosY() - player.getPosY(), 2));
	    return distance < (size + player.getSize()) / 2.0;
	}

	public abstract void powerUpEffect(Aircraft player);
	
	public void delegate(GraphicsContext g) {
		if (g != null) {
			drawYourself(g);
		}
	}

	public void drawYourself(GraphicsContext g) {
		g.drawImage(powerUp, getPosX(), getPosY(), getSize(), getSize());
	}

	public void update() {
		setPosY(getPosY() + speed);
	}

	public double getSize() {
		return this.size;
	}

	public double getPosY() {
		return this.posY;
	}

	public double getPosX() {
		return this.posX;
	}

	public void setSize(int size) {
		this.size = size;

	}

	public void setPosY(double posY) {
		this.posY = posY;

	}

	public void setPosX(double posX) {
		this.posX = posX;
	}
}
