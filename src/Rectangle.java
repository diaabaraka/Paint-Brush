import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Rectangle extends Shape {
	protected int width;
	protected int height;

	public Rectangle(int xStart1, int yStart1, int xEnd1, int yEnd1,
			Color color1) {
		xStart = xStart1;
		yStart = yStart1;
		xEnd = xEnd1;
		yEnd = yEnd1;
		color = color1;

	}

	@Override
	void draw(Graphics g) {
		g.setColor(color);
		width = Math.abs(xStart - xEnd);
		height = Math.abs(yStart - yEnd);
		g.drawRect(Math.min(xStart, xEnd), Math.min(yStart, yEnd), width,
				height);

	}
	@Override
	boolean contain(int x, int y) {
		Rectangle2D.Double ob = new Rectangle2D.Double(Math.min(xStart, xEnd),
				Math.min(yStart, yEnd), width, height);
		if (ob.contains(x, y)) {
			return true;
		}
		return false;
		
	}

}
