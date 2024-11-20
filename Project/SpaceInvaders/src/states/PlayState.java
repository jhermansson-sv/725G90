package states;

import testing.AddHealth;
import testing.Aircraft;
import testing.Enemy;
import testing.EnemyBoss;
import testing.HighScore;
import testing.PowerUp;
import testing.Shot;
import testing.SlowPowerUp;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.util.Duration;

import static constants.Constants.SCREEN_HEIGHT;
import static constants.Constants.SCREEN_WIDTH;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Melvin Palmquist & Jonathan Hermansson
 * This state represents the Playing State of the Game The main responsibility
 * of this class is to; - create Game Objects - update Game Objects - draw Game
 * Objects Game Objects are for instance; players, enemies, npc's, etc...
 *
 * The PlayState can also be thought off as a blue print where data is loaded
 * into some container from a file or some other type of data storage.
 *
 */
public class PlayState extends GameState {

	private boolean levelTwo = false;
	private boolean generatePowerup = true;
	private int score;
	private String scoreText, healthText, levelText;
	private Color bgColor, fontColor;
	private double moveX;
	private Aircraft player;
	private Image playerShip, enemyShip, healthImg, slowImg, enemyBoss, backgroundGif, heartImg;
	private ArrayList<Shot> shots;
	private ArrayList<Enemy> enemies;
	private ArrayList<PowerUp> powerUps;
	private ArrayList<Integer> targetScoresHealth, targetScoresSlow;
	private ArrayList<ArrayList> targetScores;

	String audioFilePath = "lasershot.mp3";
	String audioFilePathSong = "bloodsugar.mp3";

	Media sound = new Media(new File(audioFilePath).toURI().toString());
	Media soundSong = new Media(new File(audioFilePathSong).toURI().toString());

	MediaPlayer mediaPlayer = new MediaPlayer(sound);
	MediaPlayer mediaPlayerSong = new MediaPlayer(soundSong);

	public PlayState(GameModel model) {
		super(model);

		try {
			playerShip = new Image(new FileInputStream("spaceship.png"));
			enemyShip = new Image(new FileInputStream("enemyship.png"));
			enemyBoss = new Image(new FileInputStream("enemyboss.png"));
			healthImg = new Image(new FileInputStream("h-banana.png"));
			slowImg = new Image(new FileInputStream("freeze.png"));
			heartImg = new Image(new FileInputStream("heart.png"));
			backgroundGif = new Image(new FileInputStream("scroller.gif"));
		} catch (FileNotFoundException e) {
			System.out.println("Unable to find image-files!");
		}

		fontColor = Color.WHITE;
		bgColor = Color.MAGENTA.deriveColor(0, 1, 1, 0.05);

		targetScoresHealth = new ArrayList<>(Arrays.asList(5, 20, 50, 100, 200, 300, 500));
		targetScoresSlow = new ArrayList<>(Arrays.asList(35, 75, 150, 250, 350, 450, 500000));
		targetScores = new ArrayList<>(Arrays.asList(targetScoresHealth, targetScoresSlow));
		powerUps = new ArrayList<>();
		enemies = new ArrayList<>();
		shots = new ArrayList<>();

		player = new Aircraft(playerShip, 0.0, 650.0, 100);

		configMediaplayer();
	}

	@Override
	public void draw(GraphicsContext g) {
		g.drawImage(backgroundGif, -100, 0, 1100, 800);
		drawBg(g, bgColor);

		player.delegate(g);

		if (powerUps != null) {
			for (PowerUp powerUp : powerUps) {
				powerUp.delegate(g);
			}
		}

		if (shots != null) {
			for (Shot shot : shots) {
				shot.delegate(g);
			}
		}

		if (enemies != null) {
			for (Enemy enemy : enemies) {
				enemy.delegate(g);
			}
		}

		drawUI(g);
		checkLevel();
	}

	@Override
	public void update() {

		player.setPosX(moveX);
		player.update();

		for (int i = 0; i < powerUps.size(); i++) {
			PowerUp powerUp = powerUps.get(i);
			powerUp.update();

			if (powerUp.checkPlayerCollision(player)) {
				powerUp.powerUpEffect(player);
				for (Enemy enemy : enemies) {
					powerUp.powerUpEffect(enemy);
				}
				powerUps.remove(powerUp);
				generatePowerup = true;
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			Enemy enemy = enemies.get(i);
			enemy.update();

			if (enemy.isDestroyed()) {
				enemies.remove(i);
				i--;
			}

			if (enemy.getBottomReached()) {
				player.setHealth(player.getHealth() - 1);
			}
		}

		for (int i = 0; i < shots.size(); i++) {
			Shot shot = shots.get(i);
			shot.update();

			if (shot.isOutOfSight()) {
				shots.remove(i);
				i--;
				continue;
			}

			for (int j = 0; j < enemies.size(); j++) {
				Enemy enemy = enemies.get(j);
				if (shot.checkEnemyCollision(enemy)) {
					enemy.setExploding(true);
					score++;
					enemy.setDestroyed(true);
					shots.remove(i);
					i--;
					break;
				}
			}
		}
		powerUpGenerator();
		gameOver();
	}

	public void powerUpGenerator() {
		for (ArrayList<Integer> list : targetScores) {
			if (list.contains(score) && generatePowerup) {
				powerUps.add(powerups());
				generatePowerup = false;
				break;
			}
		}
	}

	public PowerUp powerups() {
		Random ran = new Random();
		if (targetScoresHealth.contains(score)) {
			return new AddHealth(heartImg, ran.nextInt(SCREEN_WIDTH - 100), 0.0, 50);
		} else if (targetScoresSlow.contains(score)) {
			return new SlowPowerUp(slowImg, ran.nextInt(SCREEN_WIDTH - 100), 0.0, 50);
		}
		return null;
	}

	public void gameOver() {
		if (player.getHealth() == 0) {
			saveHighscore();
			mediaPlayerSong.stop();
			model.switchState(new GameOverState(model));
		}
	}

	public void startEnemyGeneration() {
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
		scheduler.scheduleAtFixedRate(() -> {
			enemies.add(enemies());
		}, 0, 500, TimeUnit.MILLISECONDS);
	}

	public void drawUI(GraphicsContext g) {
		scoreText = String.valueOf(score);
		healthText = String.valueOf(player.getHealth());

		g.setFill(fontColor);
		g.setFont(new Font(30));
		g.fillText(scoreText, 50, 50);
		g.fillText(healthText, 900, 50);

		g.drawImage(heartImg, 925, 18, 40, 40);
		g.drawImage(enemyShip, 10, 18, 40, 40);

		if (!levelTwo) {
			levelText = ("Level 1");
		} else {
			levelText = ("Level 2");
		}

		g.fillText(levelText, 25, 750);
	}

	@Override
	public void keyPressed(KeyEvent key) {
		if (key.getCode() == KeyCode.ESCAPE)
			model.switchState(new MenuState(model));
	}

	public void mouseMoved(MouseEvent move) {
		moveX = move.getX();
	}

	public void mouseClicked(MouseEvent click) {
		if (levelTwo) {
			shots.add(player.shoot(29, levelTwo));
			shots.add(player.shoot(-27, levelTwo));
		} else {
			shots.add(player.shoot(0, levelTwo));
		}
		mediaPlayer.play();
		mediaPlayer.setOnEndOfMedia(() -> {
			mediaPlayer.stop();
			mediaPlayer.seek(mediaPlayer.getStartTime());
		});
	}

	public Enemy enemies() {
		Random ran = new Random();
		if (levelTwo) {
			return new EnemyBoss(enemyBoss, ran.nextInt(SCREEN_WIDTH - 100), 0, 75, score);
		}
		return new Enemy(enemyShip, ran.nextInt(SCREEN_WIDTH - 100), 0, 75, score);
	}

	public void configMediaplayer() {
		mediaPlayer.setVolume(0.1);
		mediaPlayer.setBalance(0.0);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayerSong.setVolume(0.2);
		mediaPlayerSong.setBalance(0.0);
		mediaPlayerSong.setCycleCount(MediaPlayer.INDEFINITE);
	}

	public void saveHighscore() {
		HighScore highScore = new HighScore(score);
		File file = new File("highscore.txt");
		List<Integer> highScores = new ArrayList<>();

		if (file.exists()) {
			try {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextInt()) {
					highScores.add(scanner.nextInt());
				}
				scanner.close();
			} catch (IOException e) {
				System.err.println("Error reading high scores from file: " + e.getMessage());
			}
		}

		if (highScores.isEmpty() || score > highScores.get(highScores.size() - 1)) {
			highScores.add(score);
			Collections.sort(highScores, Collections.reverseOrder());
		}

		try {
			FileWriter fileWriter = new FileWriter(file);
			for (int highScore1 : highScores) {
				fileWriter.write(highScore1 + "\n");
			}
			fileWriter.close();
		} catch (IOException e) {
			System.err.println("Error saving high scores to file: " + e.getMessage());
		}
	}

	public void checkLevel() {
		if (this.score > 115) {
			levelTwo = true;
		}
	}

	@Override
	public void activate() {
		startEnemyGeneration();
		mediaPlayerSong.play();
	}

	@Override
	public void deactivate() {
		mediaPlayerSong.stop();
		score = 0;
	}

}
