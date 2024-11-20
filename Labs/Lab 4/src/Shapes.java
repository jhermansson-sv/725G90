 

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shapes {
	private double x, y;
	private Color color;

  //TODO: Komplettering: base and height is never used here. Create a
  //new constructor for the Shapes where they are needed, or add base
  //and height to your Shapes-class.
	public Shapes(double x, double y, Color color, double base, double height) {
		this.x = x;
		this.y = y;
		this.color = color;
	}

  //TODO: Komplettering: You should never have an empty method, make
  //it abstract instead.
	public void draw(GraphicsContext gc) {
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public static Shapes generate(String id, double x, double y, Color color, double base, double height, double radius) {
		switch (id) {
		case "TRIANGLE":
			return new Triangle(x, y, color, base, height);
		case "RECTANGLE":
			return new Rectangle(x, y, color, base, height);
		default:
			return new Circle(x, y, color, radius);
		}
	}

	public abstract String getID();
	
	
}
