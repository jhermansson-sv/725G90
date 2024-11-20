package states;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class represents the state when the player has lost the game.
 * it's a simple state that shows a picture and a "you lose" text
 * we can also move on from this screen and play again, or end the game
 */
public class GameOverState extends GameState {
	
	private Image banana;
	
	private Color fontColor;
	private Color bgColor;
	private String informationText;

	public GameOverState(GameModel model) {
		super(model);
		
		bgColor = Color.BLACK;
		fontColor = Color.RED;
		
		informationText = "You lost.\nPress Enter To Play Again\nEscape to go to the main menu";
		
		try {
			banana = new Image(new FileInputStream("h-banana.png"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void draw(GraphicsContext g) {
		drawBg(g, bgColor);
		g.drawImage(banana, 500,500 + 150, 100, 100);
		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(informationText, SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2);
	}

	@Override
	public void keyPressed(KeyEvent key) {
			System.out.println("Trycker på " + key.getText() + " i MenuState");

			if (key.getCode() == KeyCode.ENTER) {
				model.switchState(new PlayState(model));
			} else if (key.getCode() == KeyCode.ESCAPE) {
				model.switchState(new MenuState(model));
			}
		}
	
	@Override
	public void mouseMoved(MouseEvent move) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent click) {
		// TODO Auto-generated method stub
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
	}

}
