package states;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Melvin Palmquist & Jonathan Hermansson 
 * This state is a simple state that shows the high score, which has been saved to a file locally on the pc
 * It's not pretty, but it works
 */

public class HighScoreState extends MenuState {

	private Color fontColor = Color.RED;
	private String scoreText;
	private String highScoreText = "HIGH SCORES: ";

	public HighScoreState(GameModel model) {
		super(model);

	}

	@Override
	public void keyPressed(KeyEvent key) {
		System.out.println("Trycker på " + key.getText() + " i MenuState");

		if (key.getCode() == KeyCode.ESCAPE) {
			model.switchState(new MenuState(model));
		}

	}

	public void draw(GraphicsContext g) {
		drawBg(g, Color.BLACK);

		File file = new File("highscore.txt");
		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(highScoreText, 50, 50);

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int y = 100;

			String line = bufferedReader.readLine();
			while (line != null && !line.isEmpty()) {

				g.setFill(fontColor);
				g.setFont(new Font(30));
				double x = 50;
				double width = g.getFont().getSize() * line.length() * 0.6; // Adjust the width as needed
				double height = 40;
				g.strokeRect(x, y - height + 5, width, height);
				g.fillText(line, x, y);

				y += 40;
				line = bufferedReader.readLine();
			}
			bufferedReader.close();
		} catch (IOException e) {
			System.err.println("Error reading high score file: " + e.getMessage());
		}

	}
}