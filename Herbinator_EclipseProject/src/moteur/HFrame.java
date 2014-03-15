package moteur;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

import HandCapture.Hand;
import draw.JCanvas;

/*
 * Fenéetre principale. Permet toutes les opeerations sur le tableau 
 * La fonction de reset est pour le moment en stand-by
 *
 */

public class HFrame {

	private JFrame frame;

	private JMenuBar menuBar;

	private JMenu menuFichier;
	private JMenu menuEdition;

	private JMenuItem itemGenerate;
	private JMenuItem itemEnregistrerPNG;
	private JMenuItem itemEnregistrer;
	private JMenuItem itemOuvrir;
	private JMenuItem itemRegenerate;
	private JMenuItem itemActiveKinect;
	private JMenuItem itemReset;
	private JMenuItem itemReduire;
	private JMenuItem itemHideCursor;
	private JMenuItem itemShowCursor;
	private JMenuItem itemQuitter;

	private JCanvas jCanvas;// Panel contenant le tableau (graphique)
	private JCanvas firstJC;
	private HProperties hProp;// Objet contenant les parameetres de creeation du
								// tableau
	private Peindre peinture;// Objet contenant le tableau (logique : liste de
								// forme etc)
	private Peindre firstPeinture;
	private MouseEvents mousE;
	private Cursor currentCursor;

	public HFrame() {
		this.frame = new JFrame("eeber Herbin");
		this.frame.setSize(new Dimension(700, 600));
		// this.frame.setResizable(true);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.addKeyListener(new KeyOnHFrame(this));
		this.firstJC = null;
		this.firstPeinture = null;

		this.menuBar = new JMenuBar();

		this.menuFichier = new JMenu("Fichier");
		this.menuEdition = new JMenu("Edition");

		this.itemGenerate = new JMenuItem("Herbinisation");
		this.itemRegenerate = new JMenuItem("Regeeneerer (meeme parameetres)");
		this.itemEnregistrerPNG = new JMenuItem("Sauvegarder (PNG)");
		this.itemEnregistrer = new JMenuItem("Sauvegarder (herbinator)");
		this.itemOuvrir = new JMenuItem("Ouvrir (herbinator)");
		this.itemActiveKinect = new JMenuItem("Activer la Kinect");
		this.itemReset = new JMenuItem("Remise ee 0");
		this.itemHideCursor = new JMenuItem("Cacher la souris");
		this.itemShowCursor = new JMenuItem("Afficher la souris");
		this.itemReduire = new JMenuItem("Quitter fullscreen");
		this.itemQuitter = new JMenuItem("Quitter");

		itemGenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// frame.setVisible(false);
				genererTableau();
			}
		});

		itemRegenerate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// frame.setVisible(false);
				regenerer();
			}
		});

		itemEnregistrerPNG.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				savePNG();
			}
		});

		itemEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				save();
			}
		});

		itemOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});

		itemReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
			}
		});

		itemActiveKinect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				activeKinect();
			}
		});

		itemQuitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				destroy();
			}
		});

		itemReduire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reduire();
			}
		});

		itemHideCursor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				hideCursor();
			}
		});

		itemShowCursor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showCursor();
			}
		});

		this.itemRegenerate.setEnabled(false);
		this.itemEnregistrerPNG.setEnabled(false);
		this.itemEnregistrer.setEnabled(false);
		this.itemActiveKinect.setEnabled(false);
		this.itemReset.setEnabled(false);
		this.itemShowCursor.setVisible(false);
		this.itemReduire.setEnabled(false);

		this.menuEdition.add(this.itemGenerate);
		this.menuEdition.add(this.itemRegenerate);
		this.menuEdition.add(this.itemActiveKinect);
		this.menuEdition.add(this.itemReduire);
		this.menuEdition.add(this.itemHideCursor);
		this.menuEdition.add(this.itemShowCursor);

		this.menuFichier.add(this.itemEnregistrer);
		this.menuFichier.add(this.itemOuvrir);
		this.menuFichier.add(this.itemEnregistrerPNG);
		this.menuFichier.add(this.itemQuitter);

		this.menuBar.add(this.menuFichier);
		this.menuBar.add(this.menuEdition);

		this.frame.setJMenuBar(this.menuBar);
		this.frame.setVisible(true);
	}

	public void genererTableau() {
		Saisie saisieFrame = new Saisie(this);
	}

	// Instancie le tableau (logique et graphique) ee partir des parameetres
	// fournis (fonction utiliseee pour le herbinisation)
	public void addDraw(HProperties hp) {
		// On instancie les objets logiques (peindre) et graphique (jcanvas)
		this.hProp = hp;
		this.jCanvas = new JCanvas();
		if (this.hProp.isFullScreen()) {
			this.jCanvas.setPreferredSize(this.frame.getToolkit().getScreenSize());
		} else {
			this.jCanvas.setPreferredSize(new Dimension(this.hProp.getLargeur(), this.hProp.getLongueur()));
		}
		this.peinture = new Peindre(this.jCanvas, this.hProp.getMot());

		// On recharge le jCanvas
		this.jCanvas = this.peinture.reload();
		// this.mousE = new MouseEvents(this.peinture.getHisto(), this.jCanvas);
		this.mousE = new MouseEvents(this);
		this.jCanvas.addMouseListener(this.mousE);
		this.jCanvas.addMouseMotionListener(this.mousE);

		// Si c'est la premieere geeneeration. NE FONCTIONNE PAS. WHYYYY ??!!!
		if (this.firstJC == null && this.firstPeinture == null) {
			this.firstJC = this.jCanvas;
			this.firstPeinture = this.peinture;
			System.out.println("first JC/peindre");
		}

		this.itemEnregistrer.setEnabled(true);
		this.itemEnregistrerPNG.setEnabled(true);
		this.itemRegenerate.setEnabled(true);
		this.itemActiveKinect.setEnabled(true);
		this.itemReset.setEnabled(true);
		this.itemReduire.setEnabled(true);

		this.frame.getContentPane().removeAll();
		this.frame.getContentPane().add(this.jCanvas);
		if (this.hProp.isFullScreen()) {
			this.frame.dispose();
			this.menuBar.setVisible(false);
			this.frame.setUndecorated(true);
			this.frame.setSize(frame.getToolkit().getScreenSize());
			this.frame.setLocationRelativeTo(null);
			this.frame.validate();
		} else {
			this.frame.pack();
		}
		this.frame.setVisible(true);
	}

	// Redessine le tableau ee partir des objets deejee existants (utilisees ee
	// l'ouverture d'un .herbinator)
	public void addDraw(JCanvas jc, Peindre p, HProperties hp) {
		this.jCanvas = jc;
		this.hProp = hp;
		this.peinture = p;
		// this.mousE = new MouseEvents(this.peinture.getHisto(), this.jCanvas);
		this.mousE = new MouseEvents(this);
		this.jCanvas.addMouseListener(this.mousE);
		this.jCanvas.addMouseMotionListener(this.mousE);

		this.itemEnregistrer.setEnabled(true);
		this.itemEnregistrerPNG.setEnabled(true);
		this.itemRegenerate.setEnabled(true);
		this.itemActiveKinect.setEnabled(true);
		this.itemReset.setEnabled(true);
		this.itemReduire.setEnabled(true);

		this.frame.getContentPane().removeAll();
		this.frame.getContentPane().add(this.jCanvas);
		if (this.hProp.isFullScreen()) {
			this.frame.dispose();
			this.menuBar.setVisible(false);
			this.frame.setUndecorated(true);
			this.frame.setSize(frame.getToolkit().getScreenSize());
			this.frame.setLocationRelativeTo(null);
			this.frame.validate();
		} else {
			this.frame.pack();
		}
		this.frame.setVisible(true);
	}

	public void savePNG() {
		JFileChooser fenetreMenu = new JFileChooser();
		fenetreMenu.setCurrentDirectory(new File(
				"/Users/YPierru/Documents/HerbinImg"));
		// fenetreMenu.setCurrentDirectory(new File("/~"));
		fenetreMenu.setDialogTitle("Sauvegarder");
		fenetreMenu.setFileFilter(new FileNameExtensionFilter(".png",
				new String[] { ".png" }));
		int resultat = fenetreMenu.showDialog(fenetreMenu, "Save");
		if (resultat == JFileChooser.APPROVE_OPTION) {
			File file = new File(fenetreMenu.getSelectedFile()
					.getAbsolutePath() + ".png");
			BufferedImage tamponSauvegarde = new BufferedImage(
					jCanvas.getPreferredSize().width,
					jCanvas.getPreferredSize().height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = tamponSauvegarde.getGraphics();
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, jCanvas.getPreferredSize().width,
					jCanvas.getPreferredSize().height);
			jCanvas.paint(g);
			try {
				ImageIO.write(tamponSauvegarde, "PNG", file);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void save() {
		JFileChooser fenetreMenu = new JFileChooser();
		fenetreMenu.setCurrentDirectory(new File(
				"/Users/YPierru/Documents/HerbinImg"));
		// fenetreMenu.setCurrentDirectory(new File("/~"));
		fenetreMenu.setDialogTitle("Sauvegarder");
		fenetreMenu.setFileFilter(new FileNameExtensionFilter(".herbinator",
				new String[] { ".herbinator" }));
		int resultat = fenetreMenu.showDialog(fenetreMenu, "Save");
		if (resultat == JFileChooser.APPROVE_OPTION) {
			File file = new File(fenetreMenu.getSelectedFile()
					.getAbsolutePath() + ".herbinator");
			FileOutputStream fichier;
			try {
				fichier = new FileOutputStream(file);
				ObjectOutputStream oos = new ObjectOutputStream(fichier);
				// On save un objet HBlock qui contient le tableau logique
				// (peinture) et graphique (jCanvas)
				oos.writeObject(new HBlock(jCanvas, peinture, hProp));
				oos.flush();
				oos.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void open() {
		// Choix du fichier et parameetres
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(
				"/Users/YPierru/Documents/HerbinImg"));
		fileChooser.setDialogTitle("Ouvrir");
		fileChooser.setApproveButtonText("Ouvrir");
		int returnVal = fileChooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// On reecupeere le fichier

			File file = fileChooser.getSelectedFile();
			try {
				FileInputStream fichier = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fichier);
				HBlock hb = (HBlock) ois.readObject();
				destroy();
				// Une fois l'objet reecupeerer, on redessine ee partir des
				// tableaux logique et graphique
				addDraw(hb.getJc(), hb.getTableau(), hb.getHProp());
			} catch (IOException e2) {
				e2.printStackTrace();
			} catch (ClassNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
		}
	}

	public void activeKinect() {
		for(int i=0;i<this.peinture.getHisto().size();i++){
			System.out.println("Xforme="+this.peinture.getHisto().get(i).getRectangle().getX());
			System.out.println("Yforme="+this.peinture.getHisto().get(i).getRectangle().getY());
		}
		String[] arg = null;
		Hand.setJc(this.jCanvas);
		Hand.setListForme(this.peinture.getHisto());
		//HandsCapture.main(arg);
	}

	public void reduire() {
		this.frame.dispose();
		this.frame.setUndecorated(false);
		this.hProp.setLargeur(this.frame.getToolkit().getScreenSize().width-10);
		this.hProp.setLongueur(this.frame.getToolkit().getScreenSize().height-10);
		addDraw(this.jCanvas, this.peinture, this.hProp);
	}

	public void hideCursor() {
		this.currentCursor = this.frame.getCursor();
		this.frame.setCursor(this.frame.getToolkit().createCustomCursor(
				new BufferedImage(3, 3, BufferedImage.TYPE_INT_ARGB),
				new Point(0, 0), "null"));
		this.itemShowCursor.setVisible(true);
		this.itemHideCursor.setVisible(false);
	}

	public void showCursor() {
		this.itemShowCursor.setVisible(false);
		this.itemHideCursor.setVisible(true);
		this.frame.setCursor(this.currentCursor);
	}

	public void reset() {
		destroy();
		this.addDraw(this.firstJC, this.firstPeinture, this.hProp);
	}

	public void regenerer() {
		this.addDraw(this.hProp);
	}

	public void destroy() {
		this.frame.dispose();
	}

	public JCanvas getJCanvas() {
		return this.jCanvas;
	}

	public Peindre getPeindre() {
		return this.peinture;
	}

	public HProperties getHProperties() {
		return this.hProp;
	}

	public JMenuBar getMenuBar() {
		return this.menuBar;
	}

	public JFrame getFrame() {
		return this.frame;
	}
	
	public JMenuItem getItemHideCursor(){
		return this.itemHideCursor;
	}
	
	public JMenuItem getItemShowCursor(){
		return this.itemShowCursor;
	}

}

class KeyOnHFrame implements KeyListener{

	private HFrame hframe;
	public KeyOnHFrame(HFrame hf){
		this.hframe=hf;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==83){
			if(this.hframe.getItemHideCursor().isVisible()){
				this.hframe.getItemHideCursor().doClick();
			}
			else{
				this.hframe.getItemShowCursor().doClick();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
}