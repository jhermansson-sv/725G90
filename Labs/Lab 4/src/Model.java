 
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Model {

  //TODO: Komplettering: Your model should have a list with
  //Shape-objects corresponding to every shape drawn on your
  //paintsurface. This is a requirement in the lab. Everytime a shape
  //is drawn, it should also be added to the models list, to separete
  //the data and the graphics.
	private Shapes currentShape = new Circle(25, 25, Color.BLACK, 25);
	private Color color;
	
	public void drawShapes(GraphicsContext gc, double mouseX, double mouseY) {	
		currentShape.setX(mouseX);
		currentShape.setY(mouseY);
		currentShape.setColor(color);
		currentShape.draw(gc);
		}
	
	public void setColor(Color newColor) {
		this.color = newColor;
	}
	
	public void setShape(Shapes newShape) {
		this.currentShape = newShape;
	}
	
	public Shapes getShape() {
		return currentShape;
	}
	
	public Model getModel() {
		return this;
	}
}
