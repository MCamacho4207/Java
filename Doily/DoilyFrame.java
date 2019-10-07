import javax.swing.*;
import java.awt.*;

public class DoilyFrame extends JFrame{
	
	public DoilyFrame(String s) {
		super(s);
	}
	
	public void initialise() {
		
		//Creating the window of the GUI
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creating the content pane where we will store all the separate panels of the GUI
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		
        
		//Adding the different panels to the contentPanel
		PaintBoard canvas = new PaintBoard();
		Gallery panelGal = new Gallery();
		ControlPanel featurePanel = new ControlPanel(canvas, panelGal);
	
		contentPanel.add(canvas, BorderLayout.CENTER);
		contentPanel.add(featurePanel, BorderLayout.SOUTH);
		contentPanel.add(panelGal.getGalleryPanel(), BorderLayout.EAST);
		
		//Adding the content panel to the window
		this.setContentPane(contentPanel);
		
		//Setting the size and visibility of the window
		this.setSize(1200, 1001);
		this.setVisible(true);
	}	
}

