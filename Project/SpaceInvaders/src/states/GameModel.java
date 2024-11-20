package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class represents the current state of the game.
 *
 * This implementation of a state machine keeps a reference to the current state
 * (see /src/states/GameState).
 *
 * Please note: This is just one way to do it, there are several other ways and
 * none of them work for every case, so if you want to implement your own state
 * machine make sure that it can do all the operations that you need for your
 * project.
 *
 * To change state simply call the switchState(GameState nextState) function
 * passing a reference to some other gameState.
 *
 * Initial State: MenuState
 *
 */

public class GameModel {

	private GameState currentState;

	public GameModel() {
		// We start out in the MenuState.
		this.currentState = new MenuState(this);
	}

	/**
	 * Switch to a new state, stored in the 'state' reference.
	 *
	 * This will call 'deactivate' on the current state, then store the new state as
	 * the current state, and finally call 'activate' on the new current state.
	 */
	public void switchState(GameState nextState) {
		currentState.deactivate();	
		currentState = nextState;
		currentState.activate();
	}

	public void keyPressed(KeyEvent key) {
		currentState.keyPressed(key);
	}

	public void mouseMoved(MouseEvent move) {
		currentState.mouseMoved(move);
	}
	
	public void mouseClicked(MouseEvent click) {
		currentState.mouseClicked(click);
	}
	
	public void update() {
		currentState.update();
	}
	
	public void draw(GraphicsContext g) {
		currentState.draw(g);
	}
}
