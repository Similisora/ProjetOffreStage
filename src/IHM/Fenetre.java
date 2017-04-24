package IHM;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	//La fenêtre qu'on utilisera pour ce programme

	public Fenetre() {

		this.setTitle("Gestion d'Entreprise");
		this.setSize(800,400);
		this.setIconImage(new ImageIcon("Bag Diagonal View-50.png").getImage());
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Je mets ceci en commentaire, pour ne pas fermer toutes les fenetres quand on en ferme une
		//En effet le "pop-up" de la description d'un stage demande une JFrame et non un JDialog (pour le scroll)
		//Si je ferme ce pop-up avec la croix, le programme se fermera aussi, ce qui n'était pas possible
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}