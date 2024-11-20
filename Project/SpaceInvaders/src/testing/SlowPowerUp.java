package testing;

import javafx.scene.image.Image;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * We extend the PowerUp, and create a powerUpEffect() method, that overrides the usual one. 
 * This method calls the setFrozen, which freezes, the current enemies on screen
 */
public class SlowPowerUp extends PowerUp {

	public SlowPowerUp(Image powerUp, double posX, double posY, int Size) {
		super(powerUp, posX, posY, Size);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void powerUpEffect(Aircraft enemy) {
		enemy.setFrozen();
	}
}
