 

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ColorPicker extends VBox {

	Color[] colorArray = { Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.PURPLE, Color.ORANGE, Color.CYAN,
			Color.YELLOW, Color.LIGHTGREEN };
	private ColorBox selectedColorBox;

	public ColorPicker(Model model) {

		setPadding(new Insets(10));
		setSpacing(10);

		for (Color color : colorArray) {
			ColorBox colorBox = new ColorBox(color, model);
			colorBox.setOnMouseClicked(e -> {
				if (selectedColorBox != null) {
					selectedColorBox.highlight(false);
				}
				selectedColorBox = colorBox;
				selectedColorBox.highlight(true);
				model.setColor(color);
			});
			getChildren().add(colorBox);
		}
	}
}
