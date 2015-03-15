import java.awt.Choice;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class Paint extends JPanel {

	private static final Class Graphics = null;

	public Paint() {
		setBackground(Color.WHITE);
		MouseHandler handler = new MouseHandler();
		addMouseListener(handler);
		addMouseMotionListener(handler);

	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		for (Shape shp : AppGui.history) {
			if (AppGui.notExisted) {
				try {
					Method setColor = AppGui.loadedClass.getMethod(
							"setColor", new Class[] { Color.class });
					Method getColor = AppGui.loadedClass.getMethod(
							"getColor", new Class[] {  });
					
					
					setColor.invoke(AppGui.history.peek(),
							new Object[] { getColor.invoke(AppGui.history.peek(), new Object[] { }) });
					
					
					Method draw = AppGui.loadedClass.getMethod(
							"draw", new Class[] { Graphics  });
				
					draw.invoke(AppGui.history.peek(),
							new Object[] {g });
				} catch (IllegalAccessException | NoSuchMethodException
						| SecurityException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else{
			g.setColor(shp.getColor());
			shp.draw(g);}
		}

	}

	private class MouseHandler implements MouseListener, MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent e) {
			if (AppGui.flag) {
				if (AppGui.notExisted) {
					try {
						Method setX2 = AppGui.loadedClass.getMethod(
								"setxStart", new Class[] { Integer.class });
						Method setY2 = AppGui.loadedClass.getMethod(
								"setYStart", new Class[] { Integer.class });
						setX2.invoke(AppGui.history.peek(),
								new Object[] { e.getX() });
						setY2.invoke(AppGui.history.peek(),
								new Object[] { e.getY() });
					} catch (IllegalAccessException | NoSuchMethodException
							| SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					AppGui.history.peek().setxEnd(e.getX());
					AppGui.history.peek().setyEnd(e.getY());

				}
				AppGui.drawingPanel.repaint();
			}

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			switch (AppGui.choice) {
			case 0:
				if (AppGui.notExisted) {
					try {
				Shape sh=(Shape)AppGui.loadedClass.newInstance();
//						AppGui.history.push((Shape) AppGui.loadedClass
//								.newInstance());
						Method setColor = AppGui.loadedClass.getMethod(
								"setColor", new Class[] { Color.class });
						Method setX1 = AppGui.loadedClass.getMethod(
								"setxStart", new Class[] { Integer.class });
						Method setY1 = AppGui.loadedClass.getMethod(
								"setYStart", new Class[] { Integer.class });
						setColor.invoke(AppGui.history.peek(),
								new Object[] { AppGui.col });
						setX1.invoke(AppGui.history.peek(),
								new Object[] { e.getX() });
						setY1.invoke(AppGui.history.peek(),
								new Object[] { e.getY() });
					} catch (IllegalAccessException
							| NoSuchMethodException | SecurityException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IllegalArgumentException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvocationTargetException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				} else {
					AppGui.history.push(new Line(e.getX(), e.getY(), e.getX(),
							e.getY(), AppGui.col));
					AppGui.flag = true;
					AppGui.notExisted=false;
				}
				break;
			case 1:
				AppGui.history.push(new Rectangle(e.getX(), e.getY(), e.getX(),
						e.getY(), AppGui.col));
				AppGui.flag = true;

				break;
			case 2:
				AppGui.history.push(new Square(e.getX(), e.getY(), e.getX(), e
						.getY(), AppGui.col));
				AppGui.flag = true;

				break;
			case 3:
				AppGui.history.push(new Circle(e.getX(), e.getY(), e.getX(), e
						.getY(), AppGui.col));
				AppGui.flag = true;

				break;
			case 4:
				AppGui.history.push(new Ellipse(e.getX(), e.getY(), e.getX(), e
						.getY(), AppGui.col));
				AppGui.flag = true;

				break;
			case 5:
				AppGui.history.push(new RightTriangle(e.getX(), e.getY(), e
						.getX(), e.getY(), AppGui.col));
				AppGui.flag = true;

				break;
			case 6:
				AppGui.history.push(new EuilateralTriangle(e.getX(), e.getY(),
						e.getX(), e.getY(), AppGui.col));
				AppGui.flag = true;

				break;
			case 7:
				int sizeOfHistory = AppGui.history.size();
				for (int ii = 0; ii < sizeOfHistory; ii++) {
					Shape shp = AppGui.history.get(ii);

					if (shp.contain(e.getX(), e.getY())) {
						AppGui.coll = JColorChooser.showDialog(null,
								"pick you color", AppGui.coll);

						if (AppGui.coll != null) {
							Class<? extends Shape> cla = shp.getClass();

							if (cla.getName() == "Line") {
								AppGui.history.push(new Line(shp.getxStart(),
										shp.getyStart(), shp.getxEnd(), shp
												.getyEnd(), AppGui.coll));
							} else if (cla.getName() == "Rectangle") {

								AppGui.history
										.push(new Rectangle(shp.getxStart(),
												shp.getyStart(), shp.getxEnd(),
												shp.getyEnd(), AppGui.coll));

							} else if (cla.getName() == "Square") {

								AppGui.history.push(new Square(shp.getxStart(),
										shp.getyStart(), shp.getxEnd(), shp
												.getyEnd(), AppGui.coll));

							} else if (cla.getName() == "Circle") {

								AppGui.history.push(new Circle(shp.getxStart(),
										shp.getyStart(), shp.getxEnd(), shp
												.getyEnd(), AppGui.coll));

							} else if (cla.getName() == "Ellipse") {

								AppGui.history.push(new Ellipse(
										shp.getxStart(), shp.getyStart(), shp
												.getxEnd(), shp.getyEnd(),
										AppGui.coll));

							} else if (cla.getName() == "RightTriangle") {

								AppGui.history
										.push(new RightTriangle(
												shp.getxStart(), shp
														.getyStart(), shp
														.getxEnd(), shp
														.getyEnd(), AppGui.coll));

							} else if (cla.getName() == "EuilateralTriangle") {

								AppGui.history
										.push(new EuilateralTriangle(shp
												.getxStart(), shp.getyStart(),
												shp.getxEnd(), shp.getyEnd(),
												AppGui.coll));

							}
						}

						AppGui.drawingPanel.repaint();
						// AppGui.coll=null;
						break;
					}
				}

				break;

			default:
				break;
			}

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {

			AppGui.flag = false;

		}

	}

}
