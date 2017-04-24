package IHM.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.EntrepriseDAO;
import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.JTable.tabledebutstage;
import Objet.Entreprise;
import Objet.OffreStage;

public class JDialogCreerOffreStage extends JDialog {
	
	Font policeTimesRoman = new Font(" TimesRoman ", Font.ITALIC, 20);
	JDialogCreerOffreStage me = this;

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
	JPanel J9 = new JPanel();

	JLabel bienvenue = new JLabel("Veuillez saisir les infomations nécessaires");
	
	JLabel libelle = new JLabel("Libellé ");
	JLabel descriptionoffre = new JLabel("Description :");
	JLabel domaine = new JLabel("Domaine :");
	JLabel date = new JLabel("Date de Début (jj/mm/aaaa) :");
	JLabel duree = new JLabel("Durée (mois) :");
	JLabel valide = new JLabel("Valide :");
	JLabel ID = new JLabel("ID de l'Entreprise correspondante:");
	
	private ButtonGroup choixvalide = new ButtonGroup();
	private JRadioButton dispo = new JRadioButton("Disponible");
	private JRadioButton nondispo = new JRadioButton("Indisponible");

	JTextField tlibelle = new JTextField(15);
	JTextArea tdescription = new JTextArea(20,40);
	JTextField tdomaine = new JTextField(15);
	JTextField tdate = new JTextField(15);
	JTextField tduree = new JTextField(15);
	JTextField tid = new JTextField(15);
	
	JComboBox choix = new JComboBox();
	String choixFinal;

	JLabel error = new JLabel("Champ non remplis !");
	JButton go = new JButton("Créer Offre");
	JButton retour = new JButton ("Retour");
	
	SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
	
	boolean disponible = true;
	
	class StateListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (dispo.isSelected()) {
				disponible = true;
			} else if (nondispo.isSelected()) {
				disponible = false;
			}
		}
	}
	
	//Pour créer une offre de stage d'un point de vue Entreprise (l'ID de l'entreprise lui sera affectée)
	public JDialogCreerOffreStage (final tabledebutstage modele, final EntrepriseDAO dao, final Entreprise e, final Fenetre mere) {
		
		build1();
		this.setSize(555, 620);// On lui donne une taille
		this.setTitle("Ajouter une Offre de Stage"); // On lui donne un titre
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		choix.addItem("Réseau");
		choix.addItem("Développement");
		choix.addItem("Comptabilité");
		choix.addItem("Sécurité");
		
		choixFinal = "Réseau";
		
		dispo.setSelected(true);
		dispo.addActionListener(new StateListener());
		nondispo.addActionListener(new StateListener());
		choixvalide.add(dispo);
		choixvalide.add(nondispo);
		
		retour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				me.setVisible(false);
				mere.setVisible(true);
			}

		});
		
		
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
								
				String slibelle = tlibelle.getText();
				String sdescription = tdescription.getText();
				String sdate = tdate.getText();
				int sduree = 0;
				int sid = e.getID();
				boolean flag = false;
				boolean check = false;
				
				Date DateFinal = null;
				ArrayList<Integer> Liste = dao.lister();
								
				DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
				
				try {
					DateFinal = formater.parse(tdate.getText());
					Date dateSql = new java.sql.Date(DateFinal.getTime());
				} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Date incorrecte \n(JJ/MM/AAAA)", "Information",
						JOptionPane.ERROR_MESSAGE);
						check = true;
				}
				
				if (!check)
				{
				
				try {
					sduree = Integer.parseInt(tduree.getText());
					
				} catch (NumberFormatException e) {
					error.setText("Champ non remplis | Durée incorrecte");
					error.setVisible(true);
				}
				
				for (int i = 0; i < Liste.size(); i++) {

					if (sid == Liste.get(i)) {
						flag = true;
					}
				}
				
				if (flag == false) {
					error.setText("Entreprise inexistante !");
					error.setVisible(true);
				}
				
				if (slibelle.isEmpty() || sdescription.isEmpty() || sdate.isEmpty() || sduree == 0) {
					error.setText("Champ non remplis | Durée incorrecte");
					error.setVisible(true);
				}
				
				
				else {
					
					if (flag) {
					
					OffreStage o = new OffreStage(slibelle,sdescription,choixFinal,DateFinal,sduree,disponible,sid);
					OffreStageDAO dao = new OffreStageDAO();
					dao.create(o);
					//Ajout au jTable
					modele.addOffreStage(o);
					me.setVisible(false);
					mere.setVisible(true);
					JOptionPane.showMessageDialog(null, "Vous venez de créer une offre de stage avec succès !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
			}

		});
		
		choix.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e) {
				choixFinal = (String) e.getItem();
			}
		});
	}

	//Pour créer une offre de stage d'un point de vue Admin (il peut choisir l'entreprise à lui affecter)
	public JDialogCreerOffreStage (final tabledebutstage modele, final EntrepriseDAO dao) {
		
		build();
		this.setSize(555, 620);
		this.setTitle("Ajouter une Offre de Stage");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		choix.addItem("Réseau");
		choix.addItem("Développement");
		choix.addItem("Comptabilité");
		choix.addItem("Sécurité");
		
		choixFinal = "Réseau";
		
		dispo.setSelected(true);
		dispo.addActionListener(new StateListener());
		nondispo.addActionListener(new StateListener());
		choixvalide.add(dispo);
		choixvalide.add(nondispo);
		
		retour.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				me.setVisible(false);
			}

		});
		
		go.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				
				String slibelle = tlibelle.getText();
				String sdescription = tdescription.getText();
				String sdate = tdate.getText();
				int sduree = 0;
				int sid = Integer.parseInt(tid.getText());
				boolean flag = false;
				boolean check = false;
				
				Date DateFinal = null;
				ArrayList<Integer> Liste = dao.lister();
				DateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
								
				try {
					DateFinal = formater.parse(tdate.getText());
					Date dateSql = new java.sql.Date(DateFinal.getTime());
				} catch (ParseException e1) {
						JOptionPane.showMessageDialog(null, "Date incorrecte \n(JJ/MM/AAAA)", "Information",
						JOptionPane.ERROR_MESSAGE);
						check = true;
				}
				
				if (!check) {
				
				try {
					sduree = Integer.parseInt(tduree.getText());
					
				} catch (NumberFormatException e) {
					error.setText("Durée non valide");
					error.setVisible(true);
				}
				
				for (int i = 0; i < Liste.size(); i++) {

					if (sid == Liste.get(i)) {
						flag = true;
					}
				}
				
				if (flag == false) {
					error.setText("Entreprise inexistante !");
					error.setVisible(true);
				}
				
				if (slibelle.isEmpty() || sdescription.isEmpty() || sdate.isEmpty() || sduree == 0) {
					error.setText("Champ non remplis | Durée incorrecte");
					error.setVisible(true);
				}
				
				
				else {
					
					if (flag) {
					
					OffreStage o = new OffreStage(slibelle,sdescription,choixFinal,DateFinal,sduree,disponible,sid);
					OffreStageDAO dao = new OffreStageDAO();
					dao.create(o);
					//Ajout au jTable
					modele.addOffreStage(o);
					me.setVisible(false);
					JOptionPane.showMessageDialog(null, "Vous venez de créer une offre de stage avec succès !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
				
			}

		});
		
		choix.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e) {
				choixFinal = (String) e.getItem();
			}
		});
	}
	
	public void build() {

		this.setLayout(new FlowLayout());

		this.add(acceuil);
		this.add(description);
		this.add(J1);
		this.add(J2);
		this.add(J9);
		this.add(J3);
		this.add(J4);
		this.add(J5);
		this.add(J6);
		this.add(J7);
		this.add(J8);

		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);
		
		error.setVisible(false);
		error.setForeground(Color.RED);
		
		J1.add(libelle);
		J1.add(tlibelle);

		J2.add(descriptionoffre);
		J9.add(tdescription);

		J3.add(domaine);
		J3.add(choix);

		J4.add(date);
		J4.add(tdate);

		J5.add(duree);
		J5.add(tduree);
		
		J6.add(valide);
		J6.add(dispo);
		J6.add(nondispo);
		
		J7.add(ID);
		J7.add(tid);
		
		J8.add(go);
		J8.add(error);
		J8.add(retour);
		
	}
	
	public void build1() {

		this.setLayout(new FlowLayout());

		this.add(acceuil);
		this.add(description);
		this.add(J1);
		this.add(J3);
		this.add(J4);
		this.add(J5);
		this.add(J6);
		this.add(J2);
		this.add(J7);
		this.add(J8);

		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);
		
		error.setVisible(false);
		error.setForeground(Color.RED);
		
		J1.add(libelle);
		J1.add(tlibelle);

		J2.add(descriptionoffre);
		J7.add(new JScrollPane(tdescription),BorderLayout.CENTER);

		J3.add(domaine);
		J3.add(choix);

		J4.add(date);
		J4.add(tdate);

		J5.add(duree);
		J5.add(tduree);
		
		J6.add(valide);
		J6.add(dispo);
		J6.add(nondispo);
		
		J8.add(go);
		J8.add(error);
		J8.add(retour);
		
	}

}
