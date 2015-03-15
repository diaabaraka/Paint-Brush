import java.awt.Color;
import java.awt.Graphics;

public abstract class Shape {
	
	protected int xStart;
	protected int yStart;
	protected int xEnd;
	protected int yEnd;
	protected Color color;

	public int getxStart() {
		return xStart;
	}

	public int getyStart() {
		return yStart;
	}

	public int getxEnd() {
		return xEnd;
	}

	public int getyEnd() {
		return yEnd;
	}

	public Color getColor() {
		return color;
	}

	public void setxStart(int xStart) {
		this.xStart = xStart;
	}

	public void setyStart(int yStart) {
		this.yStart = yStart;
	}

	public void setxEnd(int xEnd) {
		this.xEnd = xEnd;
	}

	public void setyEnd(int yEnd) {
		this.yEnd = yEnd;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	abstract void draw(Graphics g);
	abstract boolean contain(int x,int y);

}
