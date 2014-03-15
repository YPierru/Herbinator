package moteur;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class Saisie {

	private JFrame fenetre;
	private JLabel labelLargeur;
	private JTextField champLargeur;
	private JLabel labelHauteur;
	private JTextField champHauteur;
	private JLabel labelMot;
	private JTextField champMot;
	private JRadioButton boutonModeLibre;
	private JRadioButton boutonModePro;
	private JCheckBox boutonFullScreen;
	private ButtonGroup boutonsMode;
	private JButton boutonQuitter;
	private JButton boutonGenerer;
	private HProperties hProp;
	private HFrame herbinF;

	public Saisie(HFrame f) {

		this.herbinF = f;
		this.fenetre = new JFrame("Commandes d'Herbinator-3000");

		this.labelLargeur = new JLabel("Largeur (600-1200)");
		this.champLargeur = new JTextField(4);
		this.champLargeur.addKeyListener(new KeyOnSaisie(this));
		this.labelHauteur = new JLabel("Hauteur (400-800)");
		this.champHauteur = new JTextField(3);
		this.champHauteur.addKeyListener(new KeyOnSaisie(this));
		this.labelMot = new JLabel("Mot à traduire (3-10 lettres minuscules)");
		this.champMot = new JTextField(10);
		this.champMot.addKeyListener(new KeyOnSaisie(this));

		this.boutonModeLibre = new JRadioButton("Mode Libre", true);
		this.boutonModeLibre.addKeyListener(new KeyOnSaisie(this));
		this.boutonModePro = new JRadioButton("Mode Pro");
		this.boutonModePro.addKeyListener(new KeyOnSaisie(this));
		this.boutonFullScreen = new JCheckBox("Plein écran");
		this.boutonFullScreen.addKeyListener(new KeyOnSaisie(this));
		this.boutonsMode = new ButtonGroup();
		this.boutonsMode.add(boutonModeLibre);
		this.boutonsMode.add(boutonModePro);

		this.boutonQuitter = new JButton("Quitter");
		this.boutonGenerer = new JButton("Générer");
		this.boutonGenerer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generate();
			}
		});

		this.boutonFullScreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setFieldFullScreen();
			}
		});
		
		this.boutonQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fenetre.dispose();
			}
		});

		this.fenetre.setLayout(new GridLayout(6, 2));

		this.fenetre.add(labelLargeur);
		this.fenetre.add(champLargeur);
		this.fenetre.add(labelHauteur);
		this.fenetre.add(champHauteur);
		this.fenetre.add(labelMot);
		this.fenetre.add(champMot);
		this.fenetre.add(boutonModeLibre);
		this.fenetre.add(boutonModePro);
		this.fenetre.add(boutonFullScreen);
		this.fenetre.add(boutonQuitter);
		this.fenetre.add(boutonGenerer);

		this.fenetre.pack();
		this.fenetre.setVisible(true);
		this.fenetre.addKeyListener(new KeyOnSaisie(this));
	}

	public boolean checkChamps() {
		boolean largeurOk = false;
		boolean hauteurOk = false;
		boolean motOk = true;
		int largeur;
		int hauteur;
		String strError = "Erreur(s) : ";

		if(!isFullScreen()){
			try {
				largeur = Integer.parseInt(this.champLargeur.getText());
	
				if (largeur >= 600 && largeur <= 1200) {
					largeurOk = true;
				} else {
					strError += "\n-largeur incorrecte";
				}
			} catch (NumberFormatException e) {
				strError += "\n-largeur n'est pas un nombre, ";
			}
	
			try {
				hauteur = Integer.parseInt(this.champHauteur.getText());
	
				if (hauteur >= 400 && hauteur <= 800) {
					hauteurOk = true;
				} else {
					strError += "\n-hauteur incorrecte";
				}
			} catch (NumberFormatException e) {
				strError += "\n-hauteur n'est pas un nombre";
			}
		}

		if (!this.champMot.getText().matches("[a-z]+")) {
			motOk = false;
			strError += "\n-mot saisie n'est pas alphabétique, ou contient des majuscules";
		}

		if (this.champMot.getText().length() < 3) {
			motOk = false;
			strError += "\n-mot saisie trop petit";
		}

		if (this.champMot.getText().length() > 10) {
			motOk = false;
			strError += "\n-mot saisie trop grand";
		}

		if (!strError.equals("Erreur(s) : ")) {
			JOptionPane.showMessageDialog(null, strError);
		}

		if (!isFullScreen()) {
			return largeurOk && hauteurOk && motOk;
		} else {
			return motOk;
		}
	}
	
	public void generate(){
		// On vérifie la validité de la saisie.
		// On cache la fenêtre, on instancie HProp, on l'envoi à la
		// fenêtre principale
		if (checkChamps()) {
			fenetre.setVisible(false);
			if (!isFullScreen()) {
				hProp = new HProperties(Integer.parseInt(champLargeur
						.getText()), Integer.parseInt(champHauteur
						.getText()), champMot.getText(), isModeLibre(),
						isFullScreen());
			} else {
				hProp = new HProperties(champMot.getText(),
						isModeLibre(), isFullScreen());
			}
			herbinF.addDraw(hProp);
			destroy();
		}
	}
	
	public void setFieldFullScreen(){
		if(champHauteur.isEnabled() && champLargeur.isEnabled()){
			champHauteur.setText(""+this.herbinF.getFrame().getToolkit().getScreenSize().getHeight());
			champHauteur.setEnabled(false);
			champLargeur.setText(""+this.herbinF.getFrame().getToolkit().getScreenSize().getWidth());
			champLargeur.setEnabled(false);
		}
		else{
			champHauteur.setText("");
			champHauteur.setEnabled(true);
			champLargeur.setText("");
			champLargeur.setEnabled(true);
		}
	}
	
	public HProperties gethProp() {
		return this.hProp;
	}

	public void destroy() {
		this.fenetre.dispose();
	}

	public JFrame getFrame() {
		return this.fenetre;
	}

	public JButton getButton() {
		return this.boutonGenerer;
	}

	public JButton getButtonStop() {
		return this.boutonQuitter;
	}

	public boolean isModeLibre() {
		return boutonModeLibre.isSelected();
	}

	public boolean isFullScreen() {
		return boutonFullScreen.isSelected();
	}
}

class KeyOnSaisie implements KeyListener {

	private Saisie saisie;

	public KeyOnSaisie(Saisie s) {
		this.saisie = s;
	}

	@Override
	public void keyTyped(KeyEvent ke) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == 10) {
			this.saisie.getButton().doClick();
		}
		if (ke.getKeyCode() == 27) {
			this.saisie.getButtonStop().doClick();
		}
	}
}
