import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class Line extends Shape {
	private int a;
	private int b;

	public Line(int xStart1, int yStart1, int xEnd1, int yEnd1, Color color1) {
		xStart = xStart1;
		yStart = yStart1;
		xEnd = xEnd1;
		yEnd = yEnd1;
		color = color1;

	}

	@Override
	void draw(Graphics g) {
		g.setColor(color);
		g.drawLine(xStart, yStart, xEnd, yEnd);

	}

	@Override
	boolean contain(int x, int y) {
		Point2D startPoint = new Point2D.Double(xStart, yStart);
		Point2D endPoint = new Point2D.Double(xEnd, yEnd);
		Point2D selectPoint = new Point2D.Double(x, y);
		int dist = (int) Line2D.ptLineDist(xStart, yStart, xEnd, yEnd, x, y);

		if (((int) (startPoint.distance(selectPoint) + selectPoint
				.distance(endPoint)) == (int) startPoint.distance(endPoint))
				&& dist == 0) {

			return true;

		}
		return false;
	}

}
