package IHM.Autre;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import DAO.AdminDAO;
import DAO.CandidatureDAO;
import DAO.EntrepriseDAO;
import DAO.EtudiantDAO;
import DAO.Md5;
import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.JDialog.JDialogCreerEntreprise;
import IHM.JDialog.JDialogCreerEtudiant;
import IHM.PanelPrincipaux.PanelAdmin;
import IHM.PanelPrincipaux.PanelEntreprise;
import IHM.PanelPrincipaux.PanelEtudiant;
import Objet.Admin;
import Objet.Candidature;
import Objet.Entreprise;
import Objet.Etudiant;

public class Log extends JDialog {
	//Pour se connecter ou s'inscrire
	
	JPanel debut = new JPanel();
	JLabel etre = new JLabel("Vous êtes : ");
	
	SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/YYYY");
	
	JPanel J1 = new JPanel();
	JPanel J2 = new JPanel();
	JPanel J3 = new JPanel();
	JPanel J4 = new JPanel();
	JLabel ID = new JLabel("ID : ");
	JLabel MDP = new JLabel("MDP :");
	JLabel error = new JLabel("Identifiant | Mot de passe incorrect");
	JButton seconnecter = new JButton("Se connecter");
	JButton nouveau = new JButton("S'inscrire");
	JTextField saisieID = new JTextField("Cliquez ici", 10);
	JPasswordField saisieMDP = new JPasswordField(10);
	public String pseudoClient = new String();
	JComboBox choix = new JComboBox();
	Log me = this;
	
	String choixFinal;
	Fenetre mere = new Fenetre();
	
	public static String mdpSaisis;
	
	public Log () {
		
		mere.setVisible(false);
		build();
		this.setSize(240, 220);
		this.setTitle("Identification Utilisateur");
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
		choix.addItem("Admin");
		choix.addItem("Entreprise");
		choix.addItem("Etudiant");
		
		choixFinal = "Admin";
				
		nouveau.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
					 
				if(choix.getSelectedItem().equals("Etudiant")){
							
					JDialogCreerEtudiant creer = new JDialogCreerEtudiant();
					me.setVisible(false);
							
				} else if(choix.getSelectedItem().equals("Entreprise")){
							
						JDialogCreerEntreprise creer = new JDialogCreerEntreprise();
						me.setVisible(false);

							
				} else if(choix.getSelectedItem().equals("Admin")){
						JOptionPane.showMessageDialog(null, "Vous ne pouvez pas vous inscrire en tant qu'Administrateur",
								"Information", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("Error-50.png"));
				}
						
			}
		});
		
		seconnecter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {

				int pseudoSaisis = 0;
				char[] mdp = saisieMDP.getPassword();
				mdpSaisis = TabtoString(mdp);
				boolean check = false;
				
				try {
					pseudoSaisis = Integer.parseInt(saisieID.getText());
					
				} catch (NumberFormatException e) {
					check = true;
					error.setText("ID non valide");
					error.setVisible(true);
				}
				
				if(!check) {
				
				Md5 md = new Md5(mdpSaisis); // Pour crypter le mot de passe

				EntrepriseDAO dao = new EntrepriseDAO();
				Entreprise e = dao.find(pseudoSaisis);
				
				AdminDAO dao2 = new AdminDAO();
				Admin a = dao2.find(pseudoSaisis);
				
				EtudiantDAO dao3 = new EtudiantDAO();
				Etudiant et = dao3.find(pseudoSaisis);
				
				OffreStageDAO dao4 = new OffreStageDAO();
				CandidatureDAO dao5 = new CandidatureDAO();
				
				Date d1 = null;
				try {
					d1 = dateParser.parse("22/01/2016");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				if (e.getID() == pseudoSaisis && md.getCode().equals(e.getMdp()) && choixFinal.equals("Entreprise")) {		
				
					PanelEntreprise pe = new PanelEntreprise(e,mere);
				
					mere.add(pe);
					mere.setVisible(true);
					me.setVisible(false);
					
					ArrayList <Candidature> liste = new ArrayList <Candidature>();
					dao4.relier2(e);
					boolean flag = false;
					
					//POUR LE POP UP
					for (int i = 0 ; i < e.getListeOffre().size() ; i++) {
						
						liste = dao5.find(e.getListeOffre().get(i).getID());
						
						e.getListeOffre().get(i).getListeCandidature().removeAll(e.getListeOffre().get(i).getListeCandidature());
						
						for (int k = 0 ; k < liste.size() ; k++) {
							e.getListeOffre().get(i).getListeCandidature().add(liste.get(k));
						}
						for (int j = 0 ; j < e.getListeOffre().get(i).getListeCandidature().size() ; j++) {
							
							if (e.getListeOffre().get(i).getListeCandidature().get(j).getStatut().equals("En attente")) {
								flag = true;
							}
						}
					}
					
					if (flag) {
						JOptionPane.showMessageDialog(null, "Candidature(s) en attente",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
				else if (a.getID() == pseudoSaisis && md.getCode().equals(a.getMdp()) && choix.getSelectedItem().equals("Admin")) {	
					
					PanelAdmin pa = new PanelAdmin(a,mere);
					
					mere.add(pa);
					mere.setVisible(true);
					me.setVisible(false);
				}
				
				else if (et.getID() == pseudoSaisis && md.getCode().equals(et.getMdp()) && choixFinal.equals("Etudiant")) {	
					
					PanelEtudiant pet = new PanelEtudiant(et,mere);
					
					mere.add(pet);
					mere.setVisible(true);
					me.setVisible(false);
					
					//Pour affecter à la liste de candidature de l'étudiant ce qu'il y a dans la base de donnée
					ArrayList<Candidature> liste_candidature = new ArrayList<Candidature>();
					liste_candidature = dao5.liste(et.getID());
					et.getListeCandidature().removeAll(et.getListeCandidature());
					for (int i = 0 ; i < liste_candidature.size() ; i++) {
						et.getListeCandidature().add(liste_candidature.get(i));
					}
					
					//Pour voir si l'étudiant a été accepté à un stage
					if (et.isAccept() && et.isMessage()) {
						JOptionPane.showMessageDialog(null, "Vous avez été accepté pour une offre de stage !",
								"Information", JOptionPane.INFORMATION_MESSAGE);
						et.setMessage(false);
						dao3.update(et);
					}
					
					//Pour voir si l'étudiant a été refusé à un stage
					if ((!et.isAccept()) && et.isMessage()) {
						for (int i = 0;i<et.getListeCandidature().size();i++) {
							if (et.getListeCandidature().get(i).getStatut().equals("Refusée")) {
								dao5.delete(et.getListeCandidature().get(i).getID());
								et.getListeCandidature().remove(et.getListeCandidature().get(i));
							}
						}
						JOptionPane.showMessageDialog(null, "Désolé vous avez été refusé pour un stage, la candidature a été retiré de votre liste",
								"Information", JOptionPane.INFORMATION_MESSAGE);
						et.setMessage(false);
						dao3.update(et);
						
					}
				}
				
				else {
					error.setText("Identifiant | Mot de passe incorrect");
					error.setVisible(true);
				}
				
				}
			}
		});
		
		choix.addItemListener(new ItemListener() {
			public void itemStateChanged (ItemEvent e) {
				choixFinal = (String) e.getItem();
			}
		});
		
		saisieID.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				saisieID.setText("");
			}
		});	
	}
	
	public void build() {
		
		this.setLayout(new FlowLayout());
		debut.add(etre);
		debut.add(choix);
		J1.add(ID);
		J1.add(saisieID);
		J2.add(MDP);
		J2.add(saisieMDP);
		J3.add(seconnecter);
		J3.add(nouveau);
		J4.add(error);

		error.setVisible(false);
		error.setForeground(Color.RED);
		
		this.add(debut);
		this.add(J1);
		this.add(J2);
		this.add(J3);
		this.add(J4);
	}
	
	public String TabtoString(char[] tab) {
		int i = 0;
		String str = new String();
		while (i < tab.length) {
			char a = tab[i];
			str += a;
			i++;
		}
		return str;
	}
	
}
