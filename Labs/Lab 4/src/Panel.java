 

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Panel extends HBox {
	Button buttonCurrent = new Button("Clear");

	public Panel(GraphicsContext gc, Stage primaryStage, Canvas canvasView) {
		super();

		setPadding(new Insets(10));
		setSpacing(10);
		setStyle("-fx-background-color: #D5D6D5;");

		buttonCurrent.setPrefSize(75, 25);

		getChildren().addAll(buttonCurrent);

		//TODO: Komplettering: Should also clear the list in
		//the model. See comment in model.
		buttonCurrent.setOnMouseClicked(e -> {
			gc.clearRect(0, 0, 800, 800);
		});

	}

}
