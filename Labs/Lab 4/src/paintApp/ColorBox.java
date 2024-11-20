 

import javafx.geometry.Insets;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ColorBox extends Pane {

	public ColorBox(Color color, Model model) {
		setBorder(new Border(
				  new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		setEffect(new DropShadow(5, Color.BLACK));
		this.setPrefSize(50, 50);

		BackgroundFill fill = new BackgroundFill(color, null, null);
		Background background = new Background(fill);

		this.setBackground(background);

		this.setOnMouseClicked(e -> {
			model.setColor(color);
		});

	}

	public void highlight(boolean isSelected) {
		if (isSelected) {
			setStyle("-fx-border-color: black; -fx-border-width: 3px;");
		} else {
			setStyle("");
		}
	}
}
