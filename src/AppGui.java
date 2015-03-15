import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Stack;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AppGui extends JFrame {
	public static boolean notExisted;
	public static Class loadedClass;
	public static int choice;
	public static boolean flag;

	public static Stack<Shape> history = new Stack<>();
	public static Stack<Shape> undoHistory = new Stack<>();
	// panels
	public static JPanel drawingPanel;
	private JPanel btnPanel;
	private JPanel btnNewShapesPanel;
	private ArrayList<JButton>newShapes;;
	//
	public static Color col = Color.BLACK;
	public static Color coll = null;
	// buttons
	private JButton btnLine;
	private JButton btnRectangle;
	private JButton btnSquare;
	private JButton btnCircle;
	private JButton btnEllipse;
	private JButton btnRightTriangle;
	private JButton btnEquilTriangle;
	private JButton btnChooseColor;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnEditColor;
	private JButton btnLoad;

	public AppGui() {
		super("Drawing Panel");
		
		notExisted=false;
		newShapes=new ArrayList<>();

		flag = false;
		choice = -1;

		//
		btnPanel = new JPanel();
		
		btnNewShapesPanel = new JPanel();
		btnPanel.setLayout(new GridLayout(8, 1));
		//
		btnLine = new JButton("Line");
		btnRectangle = new JButton("Rectangle");
		btnSquare = new JButton("Square");
		btnCircle = new JButton("Circle");
		btnEllipse = new JButton("Ellipse");
		btnRightTriangle = new JButton("RightTri");
		btnEquilTriangle = new JButton("EquilTri");
		btnChooseColor = new JButton("Color");
		btnUndo = new JButton("Undo");
		btnRedo = new JButton("Redo");
		btnEditColor = new JButton("Edit Color");
		btnLoad = new JButton("Load new Shapes");
		
		// btnCircle.setIcon(new
		// ImageIcon("E:\\New folder (2)\\paint elrasaaaaam\\src\\download (1).jpg"));

		//
		btnPanel.add(btnLine);
		btnPanel.add(btnRectangle);
		btnPanel.add(btnSquare);
		btnPanel.add(btnCircle);
		btnPanel.add(btnEllipse);
		btnPanel.add(btnRightTriangle);
		btnPanel.add(btnEquilTriangle);
		btnPanel.add(btnChooseColor);
		
		//
		drawingPanel = new Paint();
		drawingPanel.add(btnUndo);
		drawingPanel.add(btnRedo);
		drawingPanel.add(btnEditColor);
		drawingPanel.add(btnLoad);
		
		

		//
		add(btnPanel, BorderLayout.EAST);
		add(btnNewShapesPanel, BorderLayout.WEST);
		add(drawingPanel, BorderLayout.CENTER);
		// finish constructing the GUI.....................

		btnLine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 0;
				flag = true;

			}
		});
		btnRectangle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 1;
				flag = true;

			}
		});

		btnSquare.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 2;
				flag = true;

			}
		});
		btnCircle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 3;
				flag = true;

			}
		});
		btnEllipse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 4;
				flag = true;

			}
		});
		btnRightTriangle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 5;
				flag = true;

			}
		});
		btnEquilTriangle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				choice = 6;
				flag = true;

			}
		});
		btnChooseColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				col = JColorChooser.showDialog(null, "Please choose a color !",
						col);

			}
		});
		btnUndo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (history.size() == 0) {
					return;
				}

				undoHistory.push(history.pop());
				drawingPanel.repaint();

			}
		});
		btnRedo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (undoHistory.size() == 0) {
					return;
				}
				history.push(undoHistory.pop());
				drawingPanel.repaint();

			}
		});
		btnEditColor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				choice = 7;
				flag = true;

			}
		});
		btnLoad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileDialog = new JFileChooser("E:\\");
				fileDialog.setFileFilter(new FileNameExtensionFilter(
						"Java Files", "class"));
				int openChoice = fileDialog.showOpenDialog(null);
				if (openChoice == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileDialog.getSelectedFile();
				
					
					
						URL classUrl;
						try {
							classUrl = selectedFile.toURL();
							
							
							final String className = (classUrl + "").substring(
									(classUrl + "").lastIndexOf('/')+1,
									(classUrl + "").length() - 6);
						
							URL[] classUrls = { classUrl };

							URLClassLoader ucl = new URLClassLoader(classUrls);
							try {
							 Class loadedClass= ucl.loadClass(className);	
//							 loadedClass
//								.newInstance();
							 newShapes.add(new JButton(className));
							 
							 
							 
							 btnNewShapesPanel.add(newShapes.get(newShapes.size()-1));
							 btnNewShapesPanel.revalidate();
							 
							 
							 newShapes.get(newShapes.size()-1).addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {

										if (className.equals("Line")) {
											choice = 0;
											flag = true;
											notExisted=true;
											
										}
										else if (className.equals("Rectangle")) {
											choice = 1;
											flag = true;
											notExisted=true;
											
										}
										else if (className.equals("Square")) {
											choice = 2;
											flag = true;
											notExisted=true;
											
										}
										else if (className.equals("Circle")) {
											choice = 3;
											flag = true;
											notExisted=true;
											
										}
										else if (className.equals("Ellipse")) {
											choice = 4;
											flag = true;
											notExisted=true;
											
										}
										else if (className.equals("RightTriangle")) {
											choice = 5;
											flag = true;
											notExisted=true;
											
										}
										else if (className.equals("EuilateralTriangle")) {
											choice = 6;
											flag = true;
											notExisted=true;
											
										}

									}
								});
								

								
						
						 

							} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SecurityException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

							
							
							
						} catch (MalformedURLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						
				}

			}
		});

	}

	public static void main(String[] args) {
		AppGui run = new AppGui();
		run.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		run.setSize(800, 600);
		run.setVisible(true);
		run.setLocationRelativeTo(null);

	}

}
