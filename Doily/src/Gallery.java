import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Gallery extends JPanel{
	
	//Member variables for Gallery
	private JScrollPane savedDoillies;
	private JPanel displaySavedDoillies;
	private ArrayList<JToggleButton> storedDoillies;
	
	public Gallery() {
		
		//Creating the galleryPanel which allow us to save and delete dollies
		JPanel galleryPanel = new JPanel();
		
		//Initialising the galleryPanel
		this.initialise(galleryPanel);
		
		//Initialising the ArrayList<JToggleButton>
		storedDoillies = new ArrayList<JToggleButton>();

		//Saving the galleryPanel to the member variables
	    displaySavedDoillies = galleryPanel;
	}
	
	//Method which initialises the given Panel with the default properties of a galleryPanel
	public void initialise(JPanel input) {
		//Setting background, preferred size and layout
		input.setBackground(Color.WHITE);
		input.setPreferredSize(new Dimension(250, 2200));
		input.setLayout(new GridLayout(13, 1));
		
		//Creating a delete JButton and adding a DeleteListener to it
		JButton delete = new JButton("Delete Selected");
		delete.addActionListener(new DeleteListener(this));
		
		//Adding the button to the given Panel
		input.add(delete);
	}
	
	//Method which adds the JToggleButton to the Panel if the ArrayList<JToggleButton> has less than 12 items
	public void addDoilyGallery(JToggleButton c) {
		
		//Checks to see if the ArrayList is short enough 
		if (storedDoillies.size() < 12) {
			//Adds the button to the ArrayList
			storedDoillies.add(c);
			
			//Adds the button to the gallery
			displaySavedDoillies.add(c);
		}
	    
		//Revalidates and repaints
		displaySavedDoillies.revalidate();
		displaySavedDoillies.repaint();
	}
	
	//Saves a JToggleButton to the ArrayList<JtoggleButton> with the icon of an the given image 
	public void saveDoily(Image img) {
		
		//Creating the icon with a scaledInstance of the image
		ImageIcon tempIcon = new ImageIcon(img.getScaledInstance(100, 100, Image.SCALE_DEFAULT));
		
		//Creating a new JToggleButton and setting the icon 
		JToggleButton test = new JToggleButton();
		test.setIcon(tempIcon);

		//Adding the button to the ArrayList<JToggleButton> and repainting
		this.addDoilyGallery(test);
		this.repaint();
	}
	
	//GETTER AND SETTER METHODS
	
	public JPanel getGalleryPanel() {
		
		return displaySavedDoillies;
	}
	
	public ArrayList<JToggleButton> getStoredDoillies() {
		return storedDoillies;
	}
	
}

//Listener which deals with removing the saved images
class DeleteListener implements ActionListener {
	
	Gallery doilyGallery;
	
	//Constructor which sets the input to the member variable for reference
	public DeleteListener(Gallery inputGal) {
		doilyGallery = inputGal;
	}

	/*
	 * Method which is run when the button is clicked on which checks the state of the JToggleButtons in  
	 * the ArrayList<JToggleButton> and removes the appropriate ones(non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		ArrayList<JToggleButton> removeList = new ArrayList<JToggleButton>();
		
		//For each loop which adds a button to a remove list if the button isSelected
		for (JToggleButton image : doilyGallery.getStoredDoillies()) {
			//If the button isSelected, then it adds it to the remove list and removes it from the galleryPanel
			if (image.isSelected()) {
				removeList.add(image);
				doilyGallery.getGalleryPanel().remove(image);
			}
		}
		
		//For each loop which loops through the removeList and removes the button from the ArrayList<JToggleButton>
		for (JToggleButton button : removeList) {
			doilyGallery.getStoredDoillies().remove(button);
		}
		
		//Revalidates and repaints
		doilyGallery.getGalleryPanel().revalidate();
		doilyGallery.getGalleryPanel().repaint();
	}		
}
