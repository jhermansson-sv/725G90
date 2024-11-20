import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Model model = new Model();
		CanvasView canvasView = new CanvasView(800, 800, model);

		primaryStage.setTitle("Paint App");

		// Skapar en grupp
		Group root = new Group();

		// Skapar canvas, lägger in i gruppen
		root.getChildren().add(canvasView);

		// Skapar en panel längst ner, lägger in i gruppen
		Panel panel = new Panel(canvasView.getGraphicsContext2D(), primaryStage, canvasView);

		// Ställer in panel efter preferenser
		panel.setPrefSize(800, 50);
		panel.setLayoutY(canvasView.getHeight() - panel.getPrefHeight());
		root.getChildren().add(panel);

		// Skapar en sidopanel
		SidePanel sidePanel = new SidePanel(model);
		sidePanel.setPrefSize(75, 800);
		sidePanel.setLayoutX(725);
		sidePanel.setLayoutY(canvasView.getHeight() - sidePanel.getPrefHeight());
		root.getChildren().add(sidePanel);


		Scene scene = new Scene(root, 800, 800);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
