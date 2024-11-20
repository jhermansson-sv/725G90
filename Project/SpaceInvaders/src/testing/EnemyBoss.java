package testing;

import static constants.Constants.SCREEN_WIDTH;
import static constants.Constants.SCREEN_HEIGHT;

import java.util.Random;

import javafx.scene.image.Image;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class extends enemy, but we override the update() method, and adds more complex movement to the enemyBoss. 
 */
public class EnemyBoss extends Enemy {

	private int direction;
	private int speed;

	public EnemyBoss(Image enemyboss, double posX, double posY, int size, int speed) {
		super(enemyboss, posX, posY, size, speed);
		this.speed = speed;
		
		if (getPosX() > 500 && getPosX() < 1000) {
            this.direction = -speed/20;
        } else {
            this.direction = speed/20;
        }
	}

	@Override
	public void update() {

		if (!isDestroyed() && !isExploding()) {
			if (!getFrozen()) {
				setPosY(getPosY() + getSpeed());

				setPosX(getPosX() + direction);
				if (getPosX() + getSize() >= 1000) {
					direction = -speed/20;
				}

				if (getPosX() + getSize() <= 100) {
					direction = speed/20;
				}

			}
			setPosY(getPosY() + 0);
		}

		if (getPosY() > 750) {
			this.setDestroyed(true);
			setBottomReached();
		}
	}

}
