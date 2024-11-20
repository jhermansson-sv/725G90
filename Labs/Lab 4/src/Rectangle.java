 

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle extends Shapes{
	
    private double width, height;
	private String ID = "RECTANGLE";


    public Rectangle(double x, double y, Color color, double width, double height) {
    	super(x, y, color, width, height);
        this.width = width;
        this.height = height;
    }

    @Override
	public void draw(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.fillRect(getX(), getY(), width, height);
    }

	@Override
	public String getID() {
		return this.ID;
	}
}