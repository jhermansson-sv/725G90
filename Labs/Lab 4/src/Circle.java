 

import javafx.scene.canvas.GraphicsContext;

import javafx.scene.paint.Color;

public class Circle extends Shapes{
	
	private double radius;
	private String ID = "CIRCLE";

	public Circle(double x, double y, Color color, double radius) {
		super(x, y, color, radius, radius);
		this.radius = radius;
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setFill(getColor());
		gc.fillOval(getX() - radius, getY() - radius, radius * 2, radius * 2);
	}
	
	@Override
	public String getID() {
		return this.ID;
	}
}
