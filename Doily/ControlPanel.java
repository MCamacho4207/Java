import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ControlPanel extends JPanel{
	
	//Member variables if the ControlPanel class
	private PaintBoard canvas;
	private Gallery doilyGallery;
	private JSlider redSlider, greenSlider, blueSlider, sizeSlider, sectorNumChange;
	private JToggleButton eraser, sectorReflect, sectorHide;
	private JButton undo, redo;
	
	//Constructor which sets up and arranges all the components with the default properties
	public ControlPanel(PaintBoard input, Gallery inputGallery) {
		super();
		
		//Setting defining properties of the ControlPanel
		this.setBackground(Color.GRAY);
		this.setPreferredSize(new Dimension(200, 150));
		this.setLayout(new FlowLayout());
		
		canvas = input;
		doilyGallery = inputGallery;
		
		//Creating all the buttons which will allow the appropriate features
		
		//Creating clear JButton and adding the ClearListener
		JButton clear = new JButton("Clear");
		clear.addActionListener(new ClearListener(this));
		
		//Creating the sub-JPanel which will hold the components necessary for adjusting penColor and penSize
		JPanel compositeColor = new JPanel();
		compositeColor.setLayout(new GridLayout(4, 1));
		
		//Creating the redSlider and redTextField and adding the ColorListener 
		JPanel redPanel = new JPanel();
		redSlider = new JSlider(0, 255, 255);
		JTextField redTextField = new JTextField( Integer.toString(canvas.getPenColor().getRed()) );
		redSlider.addChangeListener(new ColorListener(this, redTextField));
		//Combining the components to a redPanel
		redPanel.add(redSlider);
		redPanel.add(redTextField);
		
		//Creating the greenSlider and greenTextField and adding the ColorListener
		JPanel greenPanel = new JPanel();
		greenSlider = new JSlider(0, 255, 255);
		JTextField greenTextField = new JTextField( Integer.toString(canvas.getPenColor().getGreen()) );
		greenSlider.addChangeListener(new ColorListener(this, greenTextField));
		//Combining the components to a greenPanel
		greenPanel.add(greenSlider);
		greenPanel.add(greenTextField);
		
		//Creating the blueSlider and the bluetextField and adding the ColorListener
		JPanel bluePanel = new JPanel();
		blueSlider = new JSlider(0, 255, 255);
		JTextField blueTextField = new JTextField( Integer.toString(canvas.getPenColor().getBlue()) );
		blueSlider.addChangeListener(new ColorListener(this, blueTextField));
		//Combining the components to a bluePanel
		bluePanel.add(blueSlider);
		bluePanel.add(blueTextField);
		
		//Creating the sizeSLider and the sizeTextField and add the SizeListener
		JPanel sizePanel = new JPanel();
		sizeSlider = new JSlider(1, 30, 10);
		JTextField sizeTextField = new JTextField(Integer.toString(canvas.getPenSize()));
		sizeSlider.addChangeListener(new SizeListener(this, sizeTextField));
		//Combining the components to a sizePanel
		sizePanel.add(sizeSlider);
		sizePanel.add(sizeTextField);
		
		//Adding the separate panels the the sub-JPanel
		compositeColor.add(redPanel);
		compositeColor.add(greenPanel);
		compositeColor.add(bluePanel);
		compositeColor.add(sizePanel);
		
		//Creating and setting up the sectorNumChange slider and adding the SectorNumberListener
		sectorNumChange = new JSlider(1, 24, 1);
		sectorNumChange.setPaintTicks(true);
		sectorNumChange.setMajorTickSpacing(2);
		sectorNumChange.setMinorTickSpacing(1);
		sectorNumChange.setPaintLabels(true);
		sectorNumChange.addChangeListener(new SectorNumberListener(this));
		
		//Creating the sectorReflect JToggleButton and adding the RelfectListener
		sectorReflect = new JToggleButton("Reflect Sector");
		sectorReflect.addChangeListener(new ReflectListener(this));
		
		//Creating the sectorHide JToggleButton and adding the HideSectorListener
		sectorHide = new JToggleButton("Hide Sector Lines");
		sectorHide.addChangeListener(new HideSectorListener(this));
		
		//Creating the undo JButton and adding the UndoListener
		undo = new JButton("Undo");
		undo.addActionListener(new UndoListener(this));
		
		//Creating the redo JButton and adding the RedoListener
		redo = new JButton("Redo");
		redo.addActionListener(new RedoListener(this));
		
		//Creating the eraser JToggleButton and adding the EraseListener
		eraser = new JToggleButton("Eraser");
		eraser.addChangeListener(new EraserListener(this));
		
		//Creating the saveDoily JButton and adding the SaveListener
		JButton saveDoily = new JButton("Save Current Doily");
		saveDoily.addActionListener(new SaveListener(this));

		//Adding the components to the ControlPanel
		this.add(clear);
		this.add(compositeColor);
		this.add(sectorNumChange);
		this.add(sectorReflect);
		this.add(sectorHide);
		this.add(undo);
		this.add(redo);
		this.add(eraser);
		this.add(saveDoily);
	}
	
	//GETTER AND SETTER METHODS
	
	public PaintBoard getCanvas() {
		return canvas;
	}
	
	public JSlider getRedSlider() {
		return redSlider;
	}
	
	public JSlider getGreenSlider() {
		return greenSlider;
	}
	
	public JSlider getBlueSlider() {
		return blueSlider;
	}
	
	public JSlider getSizeSlider() {
		return sizeSlider;
	}
	
	public JSlider getSectorChangeSlider() {
		return sectorNumChange;
	}
	
	public JToggleButton getEraser() {
		return eraser;
	}
	
	public JToggleButton getReflectButton() {
		return sectorReflect;
	}
	
	public JToggleButton getHideSectorButton() {
		return sectorHide;
	}
	
	public Gallery getGalley() {
		return doilyGallery;
	}
}

//Listener which, when clicked on, clears the PaintBoard
class ClearListener implements ActionListener {
	
	ControlPanel control;
	
	//Constructor which sets the parameter to the member variable for reference
	public ClearListener(ControlPanel input) {
		control = input;
		
	}
	
	//When the button is clicked on, it clears the PaintBoard and empties the circleList and lineList
	@Override
	public void actionPerformed(ActionEvent e) {
		
		control.getCanvas().clear();
		control.getCanvas().emptyCircleAndLineLists();
	}
}

//Listener which is responsible for showing and setting the penColor
class ColorListener implements ChangeListener {
	
	ControlPanel control;
	JTextField colorValue;
	
	//Constructor which sets the inputs to the member variables for reference
	public ColorListener(ControlPanel input, JTextField field) {
		control = input;
		colorValue = field;
	}
	
	//Method which runs on a stateChange and deals with the penColor and updating the sliders appropriately
	@Override
	public void stateChanged(ChangeEvent e) {
		
		//Set the penColor to the current values of the slider
		control.getCanvas().setPenColor(new Color(control.getRedSlider().getValue(), control.getGreenSlider().getValue(), control.getBlueSlider().getValue()));
		
		//Checks the source of the stateChange and decides the correct action
		if (e.getSource().equals(control.getRedSlider())) {
			
			//Since change came from the redSlider, we update the redTextField
			colorValue.setText( Integer.toString(control.getRedSlider().getValue()) );	
			
		} else if (e.getSource().equals(control.getGreenSlider())) {
			
			//Since change came from the greenSlider, we update the greenTextField
			colorValue.setText( Integer.toString(control.getGreenSlider().getValue()) );
			
		} else if (e.getSource().equals(control.getBlueSlider())) {
			
			//Since change came from the blueSlider, we update the blueTextField
			colorValue.setText( Integer.toString(control.getBlueSlider().getValue()) );
		}
	}
}

//Listener which deals with the penSize and updating the slider
class SizeListener implements ChangeListener {
	
	ControlPanel control;
	JTextField sizeBox;
	
	//Constructor which sets the input to the member variables for reference
	public SizeListener(ControlPanel input, JTextField field) {
		control = input;
		sizeBox = field;
	}
	
	//Method which runs on a stateChange which changes penSize and updates the slider
	@Override
	public void stateChanged(ChangeEvent e) {
		
		//Sets the penSize to the current value of the slider
		control.getCanvas().setPenSize(control.getSizeSlider().getValue());
		
		//Changes the value of the sizeTextField to match the current value of the slider
		sizeBox.setText( Integer.toString( control.getCanvas().getPenSize()));
	}
}

//Listener which deals with changing the pen into an eraser
class EraserListener implements ChangeListener {
	
	ControlPanel control;
	Color backColor;
	
	//Constructor which sets the input to the member variables for reference
	public EraserListener(ControlPanel input) {
		control = input;
		backColor = control.getCanvas().getBackground();
	} 
	
	//Method which runs on a stateChange which checks the state of the component and decides on an action
	@Override
	public void stateChanged(ChangeEvent e) {
		
		//If the eraser JButton isSelected then change penColor to the background color and change the eraser boolean to true
		//Else if it is not isSelected then set the eraser boolean to false and change the penColor to the values on the sliders
		if (control.getEraser().isSelected()) {
			
			//Sets penColor to the background
			control.getCanvas().setPenColor(backColor);
			
			//Sets the eraser boolean to true
			control.getCanvas().setIsEraser(true);
			
		} else if (!control.getEraser().isSelected()) {
			
			//Sets the eraser boolean to false
			control.getCanvas().setIsEraser(false);
			
			//Sets the penColor to the value on the sliders
			control.getCanvas().setPenColor(new Color(control.getRedSlider().getValue(), control.getGreenSlider().getValue(), control.getBlueSlider().getValue()));
		}
	}	
}

//Listener which deals with changing numberOfSectors in PaintBoard and associated interactions
class SectorNumberListener implements ChangeListener {
	
	ControlPanel control;
	
	//Constructor which sets the input to a member variable for reference
	public SectorNumberListener(ControlPanel input) {
		control = input;
	}

	//Method which runs on a stateChange and changes the numberOfSectors and redraws the PaintBoard
	@Override
	public void stateChanged(ChangeEvent e) {
		
		//Sets the numberOfSectors the current values of the slider
		control.getCanvas().setNumberOfSectors(control.getSectorChangeSlider().getValue());
		
		//Re-draws all points stored in circleList and lineList and rotates it through the new angle
		control.getCanvas().redrawAllPoints();
		
		control.getCanvas().repaint();
	}
	
}

//Listener which deals with if points should be reflected through a sector 
class ReflectListener implements ChangeListener {
	
	ControlPanel control;
	
	//Constructor which sets the input to member variables for reference
	public ReflectListener(ControlPanel input) {
		control = input;
	}
	
	//Method which runs on a stateChange and checks the state of the component and decides on an action
	@Override
	public void stateChanged(ChangeEvent e) {
		
		//If the reflect JButton isSelected then change the shouldReflect boolean to true
		//Else set shouldReflect boolean to false
		if (control.getReflectButton().isSelected()) {
			//Sets the shouldReflect boolean to true
			control.getCanvas().setShouldReflect(true);
			
		} else {
			//Sets the shouldReflect boolean to false
			control.getCanvas().setShouldReflect(false);
		}
	}
}

//Listener which deals with hiding the sector lines
class HideSectorListener implements ChangeListener {
	
	ControlPanel control;
	
	//Constructor which sets the input to the member variables for reference 
	public HideSectorListener(ControlPanel input) {
		control = input;
	}
	
	//Method which runs on a stateChange and checks state of hideSector JToggleButton and decides on an action
	@Override
	public void stateChanged(ChangeEvent e) {
		
		//If sectorHide JToggleButton isSelected then set shouldShowSector to false
		//Else set shouldShowSector to true
		if (control.getHideSectorButton().isSelected()) {
			//Sets the shouldShowSector boolean to false
			control.getCanvas().setShouldShowSector(false);
			
		} else {
			//Sets the shouldShowSector to true
			control.getCanvas().setShouldShowSector(true);
		}
		
		control.getCanvas().repaint();
	}
}

//Listener which deals with the undo feature and its interactions
class UndoListener implements ActionListener {
	
	ControlPanel control;
	
	//Constructor which sets the input to the member variables for reference
	public UndoListener(ControlPanel input) {
		control = input;
	}
	
	//Method which runs when the undo JButton is clicked on, which calls undo from PaintBoard 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//If the the undoList is non-empty, call undo
		if ( control.getCanvas().getUndoList().size() != 0) {
			control.getCanvas().undo();
		}
	}
}

//Listener which deals with the redo feature and its interactions
class RedoListener implements ActionListener {
	
	ControlPanel control;
	
	//Constructor which sets the input to the member variables for reference
	public RedoListener(ControlPanel input) {
		control = input;
	}
	
	//Method which runs when the button is clicked on, which calls redo from PaintBoard 
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//If the redoList is non-empty, then call redo
		if (control.getCanvas().getRedoList().size() != 0) {
			control.getCanvas().redo();
		}
	}
}

//Listener which deals with saving the current image to a gallery 
class SaveListener implements ActionListener {
	
	ControlPanel control;
	
	//Constructor which sets the input to the member variables for reference 
	public SaveListener(ControlPanel input) {
		control = input;
	}
	
	//Method which runs when the button is clicked on, which  calls saveDoily on the current image in the canvas
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Gets the gallery instance and calls saveDoily
		control.getGalley().saveDoily(control.getCanvas().getCurrentImage());
	}
}