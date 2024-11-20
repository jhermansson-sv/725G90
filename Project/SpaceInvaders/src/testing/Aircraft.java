package testing;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class represents the main player in the game, aka the object that can:
 * SHOOT, MOVE, RECIEVE POWERUPS, DIE/LOSE 
 * It's a pretty simple class, all we do is print ourselves out to the screen, and we add a shoot function,
 * that returns a shot everytime mouse click is triggered
 */
public class Aircraft {

	private double posX, posY;
	private int size;
	private int health = 3;
	private boolean destroyed, exploding;
	private Image ship;
	private Image explosion;

	public Aircraft(Image ship, double posX, double posY, int Size) {
		this.ship = ship;
		this.setPosX(posX);
		this.setPosY(posY);
		this.setSize(Size);

		try {
			explosion = new Image(new FileInputStream("explosion.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	public Shot shoot(int adjustX, boolean levelUp) {
		return new Shot(getPosX() - adjustX, getPosY() + 30, levelUp);
	}

	public void delegate(GraphicsContext g) {
		if (g != null) {
			drawYourself(g);
		}
	}

	public void drawYourself(GraphicsContext g) {
		if (!isExploding()) {
			g.drawImage(ship, getPosX() - 47.5, getPosY(), getSize(), getSize());
		} else {
			g.drawImage(explosion, getPosX() - 47.5, getPosY(), getSize(), getSize());			
		}
	}

	public void update() {
	}

	public double getPosX() {
		return posX;
	}

	public void setPosX(double posX) {
		this.posX = posX;
	}

	public double getPosY() {
		return posY;
	}

	public void setPosY(double posY) {
		this.posY = posY;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(boolean destroyed) {
		this.destroyed = destroyed;
	}

	public boolean isExploding() {
		return exploding;
	}

	public void setExploding(boolean exploding) {
		this.exploding = exploding;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setFrozen() {
	}

}
