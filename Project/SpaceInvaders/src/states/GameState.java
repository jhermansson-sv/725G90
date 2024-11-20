package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class represents a state of the game. These states are different views
 * in the game, which contain their own separate logic.
 *
 * Examples of states are: menu, playing, paused, game over and many more
 *
 * This Class is an abstract class that defines a couple of general functions
 * that are used by the state machine to delegate work to the state.
 *
 * The responsibilities of a state are roughly speaking separated into three
 * categories: - Input from the user (keypresses, mouse clicks, etc.) - Internal
 * logic (move sprites, check for collision etc.) - Rendering (drawing objects
 * to the screen)
 *
 * These steps are represented by the functions: - keyPressed (input step) -
 * update (logic step) - draw (rendering step)
 *
 * To define a valid state, that state has to be derived from this class, and
 * needs to override the three functions mentioned above.
 */

public abstract class GameState {

	protected GameModel model;

	public GameState(GameModel model) {
		this.model = model;
	}

	public abstract void update();

	public abstract void draw(GraphicsContext g);

	public abstract void keyPressed(KeyEvent key);
	
	public abstract void mouseMoved(MouseEvent move);

	public abstract void mouseClicked(MouseEvent click);

	public void drawBg(GraphicsContext g, Color color) {
		g.setFill(color);
		g.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	public abstract void activate();

	public abstract void deactivate();
}
