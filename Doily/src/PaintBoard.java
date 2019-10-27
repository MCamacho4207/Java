import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

public class PaintBoard extends JPanel{
	
	//Member variables for the PaintBoard class
	private int penSize = 10;
	private Color penColor = new Color(255, 255, 255);
	private int currentX, currentY, oldX, oldY;
	private int panelHeight, panelWidth;
	private Graphics2D g2;
	private Image image;
	private int numberOfSectors = 4;
	private double angle = 2*Math.PI/numberOfSectors;
	private boolean shouldShowSector = true;
	private boolean shouldReflectSector = false;
	private boolean isEraser = false;
	private ArrayList<Image> undoList, redoList;
	private ArrayList<Circle> circleList;
	private ArrayList<Line> lineList;
	
	public PaintBoard() {
		
		//Initialisation of the PaintBoard with default variables
		this.setBackground(Color.BLACK);
		this.addMouseMotionListener(new PaintListener(this));
		this.addMouseListener(new PaintListener(this));
		undoList = new ArrayList<Image>();
		redoList = new ArrayList<Image>();
		circleList = new ArrayList<Circle>();
		lineList = new ArrayList<Line>();
		panelHeight = 704;
		panelWidth = 932;
	}
	
	public void paintComponent(Graphics gg) {
		Graphics2D g = (Graphics2D) gg;
		
		//If the image is null, we create a new image
		if (image == null) {
			image = this.createImage( panelWidth, panelHeight);
			g2 = (Graphics2D) image.getGraphics();
			
			//Set the rendering hints and clear the board
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			this.clear();
			
		}
		
		//Use the Graphics object of the JPanel to draw our image
		g.drawImage(image, 0, 0 ,null);
		
		//If the the boolean is true we draw sector lines using the Graphics object for the JPanel
		if (shouldShowSector) {
			this.drawSectorLines(g);
		}
		
	}
	
	//Method for drawing a circle 
	public void drawCircle(Graphics2D gg, int x, int y, int radius) {
		gg.setColor(penColor);
		gg.fillOval(x, y, radius*2, radius*2);
	}
	
	//Method for clearing the board
	public void clear() {
		//Sets the color of the Graphics object to the background color then fills a rectangle with size of the JPanel
		g2.setPaint(Color.BLACK);
		g2.fillRect(0, 0, panelWidth, panelHeight);
		g2.setPaint(penColor);
		
		//Clears lists which store previous drawn points
		undoList.clear();
		redoList.clear();
		
		repaint();
	}
	
	//Method which undoes the last drawn point
	public void undo() {
		
		//Adds current image to the redoList
		redoList.add(image);
		
		//Sets the image to the image from the last drawn point
		image = undoList.get(undoList.size() - 1);
		g2 = (Graphics2D) image.getGraphics();
		
		//Removes the previous image from the list
		undoList.remove(undoList.size() - 1);
		
		repaint();
	}
	
	//Method for redoing the last undone point
	public void redo() {
		//Adds the current image to the undoList
		undoList.add(image);
		
		//Sets the current image to the last undone image
		image = redoList.get(redoList.size() - 1);
		g2 = (Graphics2D) image.getGraphics();
		
		//Removes the last undone image from the list
		redoList.remove(redoList.size() - 1);
		
		repaint();
	}
	
	
	//Saves an inputImage to the undoList
	public void saveImage(Image inputImage) {
		//Creates a new BufferedImage similar to the current image
		BufferedImage image = new BufferedImage(panelWidth, panelHeight, BufferedImage.TYPE_INT_RGB);
		Graphics g = image.createGraphics();
		
		//Draws the image to BufferedImage
	    g.drawImage(inputImage, 0, 0, null);
	    
	    //Casts the BufferedImage to an Image and adds it to the undoList
	    this.undoList.add((Image) image);
	}
	
	//Method for drawing sector lines, given a Graphics2D object
	public void drawSectorLines(Graphics2D g) {
		
		//Sets details of the lines
		g.setPaint(Color.WHITE);
		g.setStroke(new BasicStroke(1));
		
		//Loops though numberOfSectors and draws a line corresponding to a sector while rotating by an appropriate angle
		for (int i = 1; i <= numberOfSectors; i++) {
			if (numberOfSectors != 1) {
				g.drawLine( panelWidth/2, panelHeight/2, panelWidth/2, panelHeight*2);
				g.rotate(angle, panelWidth/2, panelHeight/2);
			}
		}
	}
	
	//Method which draws circles, at a given x and y, rotated around the center of the JPanel by a given angle
	public void drawRotatedCircle(int x, int y, int size, double ang) {
		
		//Special case when numberOfSectors is 1 
		if (numberOfSectors == 1) {
			drawCircle(g2, x - size, y - size, size);
		}
		
		//Loops for each sector and draws the circle
		for (int i = 1; i <= numberOfSectors; i++) {
			
			drawCircle(g2, x - size, y - size, size);
			g2.rotate(ang, panelWidth/2, panelHeight/2);
		}	
	}
	
	//Method which does drawRoattedCircles but also reflects them through the middle of the sector
	public void drawReflectedRotatedCircle(int x, int y, int size, double ang) {
		
		//Draws the rotated circles normally
		drawRotatedCircle(x, y, size, ang);
		
		//Then draws them reflected in each sector
		if (numberOfSectors != 1) {
			drawRotatedCircle(932 - x, y, size, ang);
		}
	}
	
	
	//Method which draws lines, at two given x and y co-ordinates, and rotates them through the center of the JPanel by a given angle
	public void drawRotatedLine(int x, int y, int curX, int curY, double ang) {
		
		//Special case when numberOfSectors is 1
		if (numberOfSectors == 1) {
			g2.drawLine(x, y, curX, curY);
		}
		
		//Loops through each sector and draws a line
		for (int i = 1; i <= numberOfSectors; i++) {
			g2.drawLine(x, y, curX, curY);
			g2.rotate(ang, panelWidth/2, panelHeight/2);
		}
	}
	
	//Method which does drawRotatedLine but also draws the line reflected in each sector
	public void drawReflectedRotatedLine(int x, int y, int curX, int curY, double ang) {
		
		//Draws the lines normally
		drawRotatedLine(x, y, curX, curY, ang);
		
		//Then reflects them in each sector
		if (numberOfSectors != 1) {
			drawRotatedLine(932 - x, y, 932 - curX, curY, ang);
		}
	}
	
	//Clears the board and redraws all saved points in the circleList and lineList
	public void redrawAllPoints() {
		clear();
		
		//Saves the current penColor so we can draw the circles with their respective colors
		Color tempColor = penColor;
		
		//Loops through the circleList and draws all the circles with their properties
		for (Circle c : circleList) {
			//Sets the Graphics2D object color to the color of the circle
			g2.setColor(c.getColor());
			
			//If the circle was reflected, draw the circle reflected through each new sector
			//Else, draw them rotated normally 
			if (c.isReflected()) {
				drawReflectedRotatedCircle(c.getXCoord(), c.getYCoord(), c.getRadius(), angle);
			} else if (!c.isReflected()) {
				drawRotatedCircle(c.getXCoord(), c.getYCoord(), c.getRadius(), angle);
			}
		}
		
		//Loops through the lineList and draws all the lines with their properties
		for (Line l : lineList) {
			//Sets the Graphics2D object color to the color of the line
			g2.setColor(l.getColor());
			g2.setStroke(new BasicStroke(l.getSize()));
			
			//If the line was reflected, draw the line reflected through each new sector
			//Else, draw them rotated normally 
			if (l.isReflected()) {
				drawReflectedRotatedLine(l.getX1(), l.getY1(), l.getX2(), l.getY2(), angle);
			} else if (!l.isReflected()) {
				drawRotatedLine(l.getX1(), l.getY1(), l.getX2(), l.getY2(), angle);
			}
		}
		
		//Changes the penColor back to what it was originally
		penColor = tempColor;
	}
	
	//Method for clearing the circleList and lineList
	public void emptyCircleAndLineLists() {
		circleList.clear();
		lineList.clear();
	}
	
	//GETTER AND SETTER METHODS
	
	public Color getPenColor() {
		return penColor;
	}
	
	public void setPenColor(Color c) {
		if (!isEraser) {
			penColor = c;
		}
	}
	
	public void setNumberOfSectors(int i) {
		numberOfSectors = i;
		angle = 2*Math.PI/numberOfSectors;
	}
	
	public void setShouldReflect(boolean input) {
		shouldReflectSector = input;
		repaint();
	}
	
	public void setShouldShowSector(boolean input) {
		shouldShowSector = input;
		repaint();
	}
	
	public void clearRedoList() {
		redoList.clear();
	}
	
	public int getOldX() {
		return oldX;
	}
	
	public void setOldX(int inX) {
		oldX = inX;
	}
	
	public int getOldY() {
		return oldY;
	}
	
	public void setOldY(int inY) {
		oldY = inY;
	} 
	
	public int getCurrentX() {
		return currentX;
	}
	
	public void setCurrentX(int inCurrentX) {
		currentX = inCurrentX;
	}
	
	public int getCurrentY() {
		return currentY;
	}

	public void setCurrentY(int inCurrentY) {
		currentY = inCurrentY;
	}
	
	public Graphics2D getGraphics2D() {
		return g2;
	}
	
	public boolean shouldReflectSector() {
		return shouldReflectSector;
	}
	
	public boolean shouldShowSector() {
		return shouldShowSector;
	}
	
	public int getNumberOfSectors() {
		return numberOfSectors;
	}
	
	public int getPenSize() {
		return penSize;
	}
	
	public void setPenSize(int inputSize) {
		penSize = inputSize;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public Image getCurrentImage() {
		return image;
	}
	
	public void addCircle(Circle c) {
		circleList.add(c);
	}
	
	public void addLine(Line l) {
		lineList.add(l);
	}
	
	public void setIsEraser(boolean inputEraser) {
		isEraser = inputEraser;
	}
	
	public ArrayList<Image> getUndoList() {
		return undoList;
	}
	
	public ArrayList<Image> getRedoList() {
		return redoList;
	}
	
	public ArrayList<Circle> getCircleList() {
		return circleList;
	}
	
	public ArrayList<Line> getLineList() {
		return lineList;
	}
	
	
}

//This class will store all the properties of a circle so we can store and draw circles in our application
class Circle {
	
	//All the variables of the circle we need to keep track of
	int radius;
	int xCoord, yCoord;
	boolean isReflected;
	Color c;
	
	//Constructor which requires all of the information of the circle to be given
	public Circle(int inputRad, int inX, int inY, boolean inputReflect, Color inputC) {
		
		//Saves all the information given in the constructor
		radius = inputRad;
		xCoord = inX;
		yCoord = inY;
		isReflected = inputReflect;
		c = inputC;
	}
	
	//GETTER AND SETTER METHODS
	
	public int getRadius() {
		return radius;
	}
	
	public int getXCoord() {
		return xCoord;
	}
	
	public int getYCoord() {
		return yCoord;
	}
	
	public boolean isReflected() {
		return isReflected;
	}
	
	public Color getColor() {
		return c;
	}
}

//This class will store all the properties of a line so we can store and draw lines in our application
class Line {
	
	//All the variables of the line we need to keep track of
	int firstX, firstY, secondX, secondY;
	int strokeSize;
	boolean isReflected;
	Color c;
	
	//Constructor which requires all of the information of the line to be given
	public Line(int inX1, int inY1, int inX2, int inY2, int inputSize, boolean inputReflect, Color inputC) {
		
		//Saves all the information given in the constructor
		firstX = inX1;
		firstY = inY1;
		secondX = inX2;
		secondY = inY2;
		strokeSize = inputSize;
		isReflected = inputReflect;
		c = inputC;
	}
	
	//GETTER AND SETTER METHODS
	
	public int getX1() {
		return firstX;
	}
	
	public int getY1() {
		return firstY;
	}
	
	public int getX2() {
		return secondX;
	}
	
	public int getY2() {
		return secondY;
	}
	
	public int getSize() {
		return strokeSize;
	}
	
	public boolean isReflected() {
		return isReflected;
	}
	
	public Color getColor() {
		return c;
	}
}

//PaintListener class which will listen for mouse clicks and drags in order to draw where the mouse is indicating
class PaintListener extends MouseMotionAdapter implements MouseListener{
	
	//Instance of the PaintBoard
	PaintBoard paintPanel;
	
	//Using a constructor to initiate the instance 
	public PaintListener(PaintBoard input) {
		paintPanel = input;
	}
	
	//Method which runs when the mouse is pressed
	public void mousePressed(MouseEvent e) {
		
		//Clears the redoList
		paintPanel.clearRedoList();
		
		//Sets the oldX and oldY positions
		paintPanel.setOldX(e.getX());
		paintPanel.setOldY(e.getY());
		
		//Saves the current image to the undoList
		paintPanel.saveImage(paintPanel.getCurrentImage());
		
		//If the image has a Graphics2D object
		if (paintPanel.getGraphics2D() != null) {
			
			//If shouldReflctsector is true, we draw the circle reflected in the sector and rotated around the origin
			//Else we just draw the circle rotated around the center of the JPanel
			if (paintPanel.shouldReflectSector()) {
				paintPanel.drawReflectedRotatedCircle(paintPanel.getOldX(), paintPanel.getOldY(), paintPanel.getPenSize(), paintPanel.getAngle());
			} else {
				paintPanel.drawRotatedCircle(paintPanel.getOldX(), paintPanel.getOldY(), paintPanel.getPenSize(), paintPanel.getAngle());	
			}	
		}
		
		//We add the circle to the circleList to keep track of
		paintPanel.addCircle(new Circle(paintPanel.getPenSize(), e.getX(), e.getY(), paintPanel.shouldReflectSector(), paintPanel.getPenColor()));
		paintPanel.repaint();
	}
	
	//Method which is ran when the mouse is dragged
	public void mouseDragged(MouseEvent e) {
		
		//Sets the currentX and currentY to the current position of the mouse
		paintPanel.setCurrentX((int) e.getX());
		paintPanel.setCurrentY((int) e.getY());
		
		//If the image has a Graphics2D object
		if (paintPanel.getGraphics2D() != null) {
			
			//Set color and stroke of the Graphics2D object
			paintPanel.getGraphics2D().setStroke(new BasicStroke((int) (paintPanel.getPenSize()*1.4)));
			paintPanel.getGraphics2D().setColor(paintPanel.getPenColor());
			
			//If shouldReflctsector is true, we draw the line reflected in the sector and rotated around the origin
			//Else we just draw the line rotated around the center of the JPanel
			if (paintPanel.shouldReflectSector()) {
				paintPanel.drawReflectedRotatedLine(paintPanel.getOldX(), paintPanel.getOldY(), paintPanel.getCurrentX(), paintPanel.getCurrentY(), paintPanel.getAngle());
			} else {
				paintPanel.drawRotatedLine(paintPanel.getOldX(), paintPanel.getOldY(), paintPanel.getCurrentX(), paintPanel.getCurrentY(), paintPanel.getAngle());
			}
			
			//we add the line to the lineList to keep track of
			paintPanel.addLine(new Line(
					paintPanel.getOldX(), 
					paintPanel.getOldY(), 
					paintPanel.getCurrentX(), 
					paintPanel.getCurrentY(), 
					(int) (paintPanel.getPenSize()*1.4), 
					paintPanel.shouldReflectSector(), 
					paintPanel.getPenColor()));
			
			paintPanel.repaint();
			
			//Set the oldX and oldY to the current mouse position
			paintPanel.setOldX(paintPanel.getCurrentX());
			paintPanel.setOldY(paintPanel.getCurrentY());
		}
	}
	
	//Implemented methods which we do not make use of
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
}
