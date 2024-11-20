package testing;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * Simple class that uses the same checkEnemyCollision() method from PowerUp, but we check if a SHOT that has been shot
 * by the player, reaches an enemy, instead of a powerUp that collides with the player.
 * We also have an outOfSight variable, that gets set to true if the shot is out of screen, this way we can delete it from the 
 * memory later.
 */
public class Shot {

	private double posX, posY;
	private int size = 15;
	private int speed = 10;
	private boolean outOfSight, levelUp;
	private Image rocket;


	public Shot(double posX, double posY, boolean levelUp) {
		this.posX = posX;
		this.posY = posY;
		this.levelUp = levelUp;

		try {
			rocket = new Image(new FileInputStream("rocket.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	public boolean checkEnemyCollision(Enemy enemy) {
		double distance = Math
				.sqrt(Math.pow(getPosX() - enemy.getPosX(), 2) + Math.pow(getPosY() - enemy.getPosY(), 2));
		return distance < (size + enemy.getSize()) / 2.0;
	}

	public void update() {
		if (!isOutOfSight()) {
			posY -= speed;
		}
		if (posY <= 0) {
			setOutOfSight(true);
		}
	}

	public void delegate(GraphicsContext g) {
		if (g != null) {
			drawYourself(g);
		}
	}

	public void drawYourself(GraphicsContext g) {
		if (levelUp) {
			g.drawImage(rocket, posX, posY, size / 2, size);
		} else {
			g.setFill(Color.LIGHTGOLDENRODYELLOW);
			g.fillRect(posX, posY, 2, 4);
		}
	}

	public boolean isOutOfSight() {
		return outOfSight;
	}

	public void setOutOfSight(boolean outOfSight) {
		this.outOfSight = outOfSight;
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
}
