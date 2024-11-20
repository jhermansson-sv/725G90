 

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class SidePanel extends VBox {

	public SidePanel(Model model) {
		
		setStyle("-fx-background-color: #D5D6D5;");
		setAlignment(Pos.TOP_CENTER);
		
		Text colorText = new Text("Färg");
		getChildren().add(colorText);
		
		ColorPicker colorPicker = new ColorPicker(model);
		getChildren().add(colorPicker);

		Text shapeText = new Text("Former");
		getChildren().add(shapeText);
		
		ShapePicker shapePicker = new ShapePicker(model);
		getChildren().add(shapePicker);
		
	}
}