package states;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Melvin Palmquist & Jonathan Hermansson 
 * This state represents the menu
 * of the Game The main responsibility of this class is to allow the
 * user to swap state to the PlayState
 */
public class MenuState extends GameState {
	/*
	 * The following three variables are just used to show that a change of state
	 * can be made. The same variables also exist in the PlayState, can you think of
	 * a way to make this more general and not duplicate variables?
	 */
	private String informationText;
	private Color bgColor;
	private Color fontColor;
	private Image playerSpaceship;
	private PlayState playState;

	public MenuState(GameModel model) {
		super(model);
		playState = new PlayState(model);
		informationText = "Welcome to Space Invaders\nPress Enter To Play\nEscape to exit\nS for High-Scores";
		bgColor = Color.BLACK;
		fontColor = Color.CYAN;
		
		try {
			playerSpaceship = new Image(new FileInputStream("spaceship.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);

		g.drawImage(playerSpaceship, 200, 300, 200, 200);
		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(informationText, SCREEN_WIDTH / 2, 350);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ENTER) {
			model.switchState(playState);
		} else if (key.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		} else if (key.getCode() == KeyCode.S) {
			model.switchState(new HighScoreState(model));
		}
	}

	public void mouseMoved(MouseEvent move) {
	}

	public void mouseClicked(MouseEvent click) {
	}

	@Override
	public void update() {

	}

	@Override
	public void activate() {

	}

	@Override
	public void deactivate() {

	}
}
