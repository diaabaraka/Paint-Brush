import java.awt.Color;
import java.awt.Graphics;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ellipse extends Shape {
	protected int width;
	protected int height;

	public Ellipse(int xStart1, int yStart1, int xEnd1, int yEnd1, Color color1) {
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
		int x = Math.min(xStart, xEnd);
		int y = Math.min(yStart, yEnd);

		g.drawOval(x, y, width, height);

	}

	@Override
	boolean contain(int x, int y) {
		Ellipse2D.Double ob = new Ellipse2D.Double(Math.min(xStart, xEnd),
				Math.min(yStart, yEnd), width, height);
		if (ob.contains(x, y)) {
			return true;
		}
		return false;

	}

}
