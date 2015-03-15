import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Circle extends Ellipse {

	private int diameter;
	private int xx;
	private int yy;

	public int getDiameter() {
		return diameter;
	}

	public int getXx() {
		return xx;
	}

	public int getYy() {
		return yy;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public void setXx(int xx) {
		this.xx = xx;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}

	public Circle(int xStart1, int yStart1, int xEnd1, int yEnd1, Color color1) {
		super(xStart1, yStart1, xEnd1, yEnd1, color1);

	}

	@Override
	void draw(Graphics g) {
		g.setColor(color);
		width = Math.abs(xStart - xEnd);
		height = Math.abs(yStart - yEnd);
		diameter = Math.min(width, height);

		if (xStart > xEnd && yStart > yEnd) {
			g.drawOval(xStart - diameter, yStart - diameter, diameter, diameter);
			xx = xStart - diameter;
			yy = yStart - diameter;
		} else if (xStart > xEnd) {
			g.drawOval(xStart - diameter, yStart, diameter, diameter);
			xx = xStart - diameter;
			yy = yStart;
		} else if (yStart > yEnd) {
			g.drawOval(xStart, yStart - diameter, diameter, diameter);
			xx = xStart;
			yy = yStart - diameter;
		} else {
			g.drawOval(xStart, yStart, diameter, diameter);
			xx = xStart;
			yy = yStart;
		}
	}

	@Override
	boolean contain(int x, int y) {
		Ellipse2D.Double ob = new Ellipse2D.Double(xx, yy, diameter, diameter);
		if (ob.contains(x, y)) {
			return true;
		}
		return false;

	}

}
