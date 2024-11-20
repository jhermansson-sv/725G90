 

import javafx.geometry.Insets;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ShapePicker extends VBox {

	Shapes[] shapesArray = { 
			new Circle(27.5, 25, Color.BLACK, 22.5), 
			new Rectangle(7.5, 5, Color.BLACK, 40, 40),
			new Triangle(27.5, 25, Color.BLACK, 40, 40) };
	
	private ShapeBox selectedShapeBox;

	public ShapePicker(Model model) {

		setPadding(new Insets(10));
		setSpacing(10);

		for (Shapes shapes : shapesArray) {
			ShapeBox shapeBox = new ShapeBox(shapes, model);
			shapeBox.setOnMouseClicked(e -> {
				if (selectedShapeBox != null) {
					selectedShapeBox.highlight(false);
				}
				selectedShapeBox = shapeBox;
				selectedShapeBox.highlight(true);
				model.setShape(shapes);
			});

			getChildren().add(shapeBox);
		}
	}
}
