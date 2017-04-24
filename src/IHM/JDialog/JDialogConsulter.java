package IHM.JDialog;
import java.awt.FlowLayout;

import javax.swing.JDialog;

public class JDialogConsulter extends JDialog{
	
	public JDialogConsulter () {
		
		this.setLayout(new FlowLayout());
		this.setSize(600, 400);// On lui donne une taille
		this.setTitle("Consulter une offre"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
