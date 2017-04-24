package IHM.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.SynchronousQueue;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import IHM.Fenetre;
import Objet.OffreStage;

public class JDialogProfilOffre extends JDialog {
	
	Font policeTimesRoman = new Font("TimesRoman", Font.ITALIC, 20);
	JDialogProfilOffre me = this;
	
	JPanel acceuil = new JPanel();
	JPanel description = new JPanel();
	JPanel J1 = new JPanel();
	JPanel J2 = new JPanel();
	JPanel J3 = new JPanel();
	JPanel J4 = new JPanel();
	JPanel J5 = new JPanel();
	JPanel J6 = new JPanel();
	JPanel J7 = new JPanel();
	JPanel J8 = new JPanel();

	JLabel bienvenue = new JLabel("Détails de l'Offre");
	
	JLabel libelle = new JLabel("Libellé :");
	JLabel domaineoffre = new JLabel("Domaine offre :");
	JLabel date = new JLabel("Date (jj/mm/aaaa) :");
	JLabel duréeoffre = new JLabel("Durée offre (mois) :");
	
	JLabel tlibelle = new JLabel();
	JLabel tdomaineoffre = new JLabel();
	JLabel tdate = new JLabel();
	JLabel tduréeoffre = new JLabel();
		
	SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
	
	boolean disponible = true;
	
	JButton retour = new JButton ("Retour");
	JButton desc = new JButton ("Description");
	
	public JDialogProfilOffre(final OffreStage o){
		
		 build();
		 this.setSize(260, 260);
		 this.setTitle("Résumé de l'Offre");
		 this.setVisible(true);
		 this.setLocationRelativeTo(null);
		 
		 tlibelle.setText(o.getLibelléOffre());
		 tdomaineoffre.setText(o.getDomaineOffre());
		 tdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(o.getDateDébutOffre()));
		 tduréeoffre.setText(String.valueOf(o.getDuréeOffre()));
		 
		 retour.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event){
				 
					me.setVisible(false);
			 }
		 });
		 
		 desc.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event){
				 
				 	Fenetre f = new Fenetre ();
					f.setTitle("Liste de mes Offres de Stage");
					f.setSize(650, 400);
					JDialogDescription d = new JDialogDescription(o);
					f.add(d);
					f.getContentPane().add(new JScrollPane(d.textArea), BorderLayout.CENTER);
			 }
		 });
		 
	}
	
	public void build() {

		this.setLayout(new FlowLayout());

		this.add(acceuil);
		this.add(description);
		this.add(J2);
		this.add(J4);
		this.add(J5);
		this.add(J6);
		this.add(J3);
		this.add(J7);
		
		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);

		J2.add(libelle);
		J2.add(tlibelle);
		
		J3.add(desc);

		J4.add(domaineoffre);
		J4.add(tdomaineoffre);
		
		J5.add(date);
		J5.add(tdate);

		J6.add(duréeoffre);
		J6.add(tduréeoffre);
		
		J7.add(retour);
	}
	
	
	
}