 

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Triangle extends Shapes {
    private double base;
    private double height;
    private String ID = "TRIANGLE";

    public Triangle(double x, double y, Color color, double base, double height) {
        super(x, y, color, base, height);
        this.base = base;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.fillPolygon(new double[]{getX(), getX() + base / 2, getX() - base / 2},
                new double[]{getY() - height / 2, getY() + height / 2, getY() + height / 2}, 3);
    }
    
	@Override
	public String getID() {
		return this.ID;
	}
}


