 

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ShapeBox extends Pane {

	public ShapeBox(Shapes shape, Model model) {

		setBorder(new Border(
				  new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setEffect(new DropShadow(5, Color.BLACK));
		setPrefSize(50, 50);
		
		Canvas canvas = new Canvas(100, 100);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		this.setOnMouseClicked(e -> {
			model.setShape(shape);
		});

		shape.draw(gc);

		this.getChildren().add(canvas);
	}

	public void highlight(boolean isSelected) {
		if (isSelected) {
			setStyle("-fx-border-color: black; -fx-border-width: 3px;");
		} else {
			setStyle("");
		}
	}
}
