package IHM.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import Objet.OffreStage;

public class JDialogDescription extends JPanel {
	
	JDialogDescription me = this;
	JPanel pan = new JPanel();
	public JTextArea textArea = new JTextArea();
	Color color = new Color (220,240,250);

	public JDialogDescription (OffreStage offre) {
		
		super();
		
		this.add(pan);
		
			textArea = new JTextArea(offre.getDescriptionOffre());
			textArea.setFont(new Font("Serif", Font.ITALIC, 16));
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setBackground(color);
			
			pan.add(textArea);
			textArea.setEditable(false);
			
	}
	
}
