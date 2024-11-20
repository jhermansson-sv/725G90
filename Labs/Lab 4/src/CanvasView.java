 

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CanvasView extends Canvas {
	private Model model;

	private GraphicsContext gc = this.getGraphicsContext2D();

	public CanvasView(int x, int y, Model model) {
		super(x, y);
		this.model = model;	

		//TODO: Komplettering: You should add the drawn shape
		//to your model as well. See comment in model.
	// Målar när musen är klickad
	this.setOnMouseClicked(e->{
		model.drawShapes(gc, e.getX(), e.getY());
	});

	this.setOnMouseDragged(e-> {
		model.drawShapes(gc, e.getX(), e.getY());
	});
	}

}
