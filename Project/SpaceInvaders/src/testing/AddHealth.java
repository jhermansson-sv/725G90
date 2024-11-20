package testing;

import javafx.scene.image.Image;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class extends the powerUp class and gives the player 1+ HP, on collision with an image.
 */
public class AddHealth extends PowerUp {

	public AddHealth(Image powerUp, double posX, double posY, int Size) {
		super(powerUp, posX, posY, Size);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void powerUpEffect(Aircraft player) {
		player.setHealth(player.getHealth() + 1);
	}
}
