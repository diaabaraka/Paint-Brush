	import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;


	public class EuilateralTriangle extends Shape{
		public EuilateralTriangle(int xStart1, int yStart1, int xEnd1, int yEnd1,
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

			g.drawLine(xStart,yStart,xEnd,yStart);
			g.drawLine(xStart,yStart,xStart+(xEnd-xStart)/2,yEnd);
			g.drawLine(xEnd,yStart,xStart+(xEnd-xStart)/2,yEnd);

		}
		@Override
		boolean contain(int x, int y) {
			Rectangle2D.Double ob = new Rectangle2D.Double(Math.min(xStart, xEnd),
					Math.min(yStart, yEnd), Math.abs(xStart- xEnd), Math.abs(yStart- yEnd));
			if (ob.contains(x, y)) {
				return true;
			}
			return false;
			
		}

	}
