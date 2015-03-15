import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Square extends Rectangle {
	public int xx;

	private int yy;
	private int side;

	public int getXx() {
		return xx;
	}

	public int getYy() {
		return yy;
	}

	public int getSide() {
		return side;
	}

	public void setXx(int xx) {
		this.xx = xx;
	}

	public void setYy(int yy) {
		this.yy = yy;
	}

	public void setSide(int side) {
		this.side = side;
	}

	public Square(int xStart1, int yStart1, int xEnd1, int yEnd1, Color color1) {
		super(xStart1, yStart1, xEnd1, yEnd1, color1);

	}

	@Override
	void draw(Graphics g) {
		g.setColor(color);
		width = Math.abs(xStart - xEnd);
		height = Math.abs(yStart - yEnd);
		side = Math.min(width, height);
		if (xStart > xEnd && yStart > yEnd) {

			g.drawRect(xStart - side, yStart - side, side, side);
			xx = xStart - side;
			yy = yStart - side;
		} else if (xStart > xEnd) {

			g.drawRect(xStart - side, yStart, side, side);
			xx = xStart - side;
			yy = yStart;
		} else if (yStart > yEnd) {
			g.drawRect(xStart, yStart - side, side, side);
			xx = xStart;
			yy = yStart - side;
		} else {
			g.drawRect(xStart, yStart, side, side);
			xx = xStart;
			yy = yStart;
		}

	}

	@Override
	boolean contain(int x, int y) {
		Rectangle2D.Double ob = new Rectangle2D.Double(xx, yy, side, side);
		if (ob.contains(x, y)) {
			return true;
		}
		return false;

	}
}
