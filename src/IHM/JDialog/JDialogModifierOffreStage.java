package IHM.JDialog;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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

import DAO.OffreStageDAO;
import IHM.Fenetre;
import IHM.JTable.tablefinalstage;
import Objet.OffreStage;

public class JDialogModifierOffreStage extends JDialog{
	
	Font policeTimesRoman = new Font("TimesRoman", Font.ITALIC, 20);
	JDialogModifierOffreStage me = this;
	
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

	JLabel bienvenue = new JLabel("Veuillez saisir les infomations nécessaires");
	
	JLabel libelle = new JLabel("Libellé :");
	JLabel descriptionoffre = new JLabel("Description offre :");
	JLabel domaineoffre = new JLabel("Domaine offre :");
	JLabel date = new JLabel("Date (jj/mm/aaaa) :");
	JLabel duréeoffre = new JLabel("Durée offre (mois) :");
	JLabel valide = new JLabel("Valide :");
	
	private ButtonGroup choixvalide = new ButtonGroup();
	private JRadioButton dispo = new JRadioButton("Disponible");
	private JRadioButton nondispo = new JRadioButton("Indisponible");
	
	JTextField tlibelle = new JTextField(15);
	JTextArea tdescriptionoffre = new JTextArea(20,40);
	JTextField tdomaineoffre = new JTextField(15);
	JTextField tdate = new JTextField(15);
	JTextField tduréeoffre = new JTextField(15);
	
	JComboBox choix = new JComboBox();
	
	JButton retour = new JButton("Retour");
		
	SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
	
	boolean disponible = true;
	
	class StateListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(dispo.isSelected()){
				disponible = true;
			} else if(nondispo.isSelected()){
				disponible = false;
			}
		}
	}
		
	JButton go = new JButton("Modifier");
	
	public JDialogModifierOffreStage(final OffreStage o, final tablefinalstage table, final Fenetre mere){
		
		 build();
		 this.setSize(560, 620);
		 this.setTitle("Modifier une offre de stage");
		 this.setVisible(true);
		 this.setLocationRelativeTo(null);
		 
		 
		 choix.addItem(o.getDomaineOffre());
		 if(o.getDomaineOffre().equals("Réseau")){
			 choix.addItem("Développement");
			 choix.addItem("Comptabilité");
			 choix.addItem("Sécurité");
		 } else if (o.getDomaineOffre().equals("Comptabilité")){
			 choix.addItem("Développement");
			 choix.addItem("Réseau");
			 choix.addItem("Sécurité");
		 } else if (o.getDomaineOffre().equals("Sécurité")){
			 choix.addItem("Développement");
			 choix.addItem("Comptabilité");
			 choix.addItem("Réseau");
		 }
		 
		 tlibelle.setText(o.getLibelléOffre());
		 tdescriptionoffre.setText(o.getDescriptionOffre());
		 tdomaineoffre.setText(o.getDomaineOffre());
		 tdate.setText(new SimpleDateFormat("dd/MM/yyyy").format(o.getDateDébutOffre()));
		 tduréeoffre.setText(String.valueOf(o.getDuréeOffre()));
		 
		 if(o.isValide()){
			 dispo.setSelected(true);
		 } else {
			 nondispo.setSelected(true);
		 }
		 //dispo.addActionListener(new StateListener());
		 nondispo.addActionListener(new StateListener());
		 choixvalide.add(dispo);
		 choixvalide.add(nondispo);
		 
		 go.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent event){
				 String slibelle = tlibelle.getText();
				 String sdescriptionoffre = tdescriptionoffre.getText();
				 String sdate = tdate.getText();
				 int sduree = 0;
				 String sdomaine = tdomaineoffre.getText();
				 boolean flag = false;
				 boolean check = false;
				 Date DateFinal = null;
					
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
						sduree = Integer.parseInt(tduréeoffre.getText());
						
					} catch (NumberFormatException e) {
						flag = true;
						JOptionPane.showMessageDialog(null, "Erreur : Durée incorrecte",
								"Information", JOptionPane.INFORMATION_MESSAGE);
					}
				 if (!flag) {
				 OffreStageDAO dao = new OffreStageDAO();				 

					if (!slibelle.equals(o.getLibelléOffre())) {
						o.setLibelléOffre(slibelle);
						dao.update(o);
					}
					
					if (!sdescriptionoffre.equals(o.getDescriptionOffre())) {
						o.setDescriptionOffre(sdescriptionoffre);
						dao.update(o);
					}
					
					if (!DateFinal.equals(o.getDateDébutOffre())) {
						o.setDateDébutOffre(DateFinal);
						dao.update(o);
				
					}
					
					if (!(sduree == o.getDuréeOffre())) {
						o.setDuréeOffre(sduree);
						dao.update(o);
					}
					
					if (!choix.getSelectedItem().equals(o.getDomaineOffre())) {
						o.setDomaineOffre((String) choix.getSelectedItem());
						dao.update(o);
					}
					
					if(dispo.isSelected()){
						o.setValide(true);
					} else if (nondispo.isSelected()){
						o.setValide(false);
					}
					dao.update(o);
					
					me.setVisible(false);
					table.setVisible(false);
					mere.setVisible(true);
					
					JOptionPane.showMessageDialog(null, "Vous avez modifié l'offre de stage "+o.getID()+" avec succès !",
							"Information", JOptionPane.INFORMATION_MESSAGE);
				 	}
				}
			 }
		 });
		 
		 retour.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					
					me.setVisible(false);
					mere.setVisible(true);
					
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
		this.add(J7);
		this.add(J3);
		this.add(J1);
		this.add(J8);
		
		acceuil.add(bienvenue);
		bienvenue.setFont(policeTimesRoman);

		J2.add(libelle);
		J2.add(tlibelle);

		JScrollPane scroll = new JScrollPane(tdescriptionoffre);
		scroll.setPreferredSize(new Dimension(450, 50));
		
		J3.add(descriptionoffre);
		//J1.add(tdescriptionoffre);
		J1.add(new JScrollPane(tdescriptionoffre),BorderLayout.CENTER);

		J4.add(domaineoffre);
		J4.add(choix);
		
		J5.add(date);
		J5.add(tdate);

		J6.add(duréeoffre);
		J6.add(tduréeoffre);
		
		
		J7.add(valide);
		J7.add(dispo);
		J7.add(nondispo);
		
		J8.add(go);
		J8.add(retour);
	}
	
	
	
}