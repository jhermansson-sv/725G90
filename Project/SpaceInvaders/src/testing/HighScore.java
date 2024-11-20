package testing;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This class is created because we need it in order the save the highscore to a file.
 * We use the BufferedWriter, which is a built in java.io class that has a FileWriter() method that we can use to save the file.
 */
public class HighScore {

	private int score;

	public HighScore(int score) {
		this.score = score;
	}

	public void saveToFile(File file) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		writer.write(String.valueOf(score));
		writer.close();
	}

}