package moteur;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

import boites.Boite;


import draw.DrawRectBorder;
import draw.JCanvas;
import draw.JShapeDraw;

public class MouseEvents implements MouseListener, MouseMotionListener {

	int preX, preY;
	ArrayList<JShapeDraw> listeFormes;
	JCanvas jc;
	JShapeDraw shape;
	boolean touche; // Bool�en, vrai quand la souris est toujours sur la forme
	TreeSet<Boite> listeBoites;
	boolean modeLibre;
	JShapeDraw contourSB;
	JShapeDraw saveForme;
	JShapeDraw formeASwaper;
	Boite boiteSurvolee;
	int rangFormeDansBoite;
	HFrame hFrame;
	boolean trouve;

	public MouseEvents(ArrayList<JShapeDraw> listeFormes, JCanvas jc,
			TreeSet<Boite> lb, boolean ml) {

		this.listeFormes = listeFormes;
		this.jc = jc;
		this.listeBoites = lb;
		this.modeLibre = ml;
		this.contourSB = null;
		this.saveForme = null;
		this.formeASwaper = null;
		this.boiteSurvolee = null;
		this.rangFormeDansBoite = 0;
		this.trouve = false;
	}

	public MouseEvents(HFrame hFrame) {

		this.listeFormes = hFrame.getPeindre().getHisto();
		this.jc = hFrame.getJCanvas();
		this.listeBoites = hFrame.getPeindre().getListeBoites();
		this.modeLibre = hFrame.getHProperties().isModeLibre();
		this.contourSB = null;
		this.saveForme = null;
		this.formeASwaper = null;
		this.boiteSurvolee = null;
		this.rangFormeDansBoite = 0;
		this.hFrame = hFrame;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			for (int i = 0; i < this.listeFormes.size(); i++) {
				// Si on est sur une forme
				if (this.listeFormes.get(i).getShape()
						.contains(e.getX(), e.getY())) {
					preX = this.listeFormes.get(i).getShape().getBounds().x
							- e.getX();
					preY = this.listeFormes.get(i).getShape().getBounds().y
							- e.getY();
					this.shape = this.listeFormes.get(i);
					this.saveForme = this.shape;
					this.jc.removeShapeFromDraw(this.shape);
					this.jc.addShapeToDraw(this.shape);
					this.deplacer(e, this.shape);
					touche = true;
					break;
				}
			}
		}
		/*
		 * boolean trouve = false; if(e.getButton() == MouseEvent.BUTTON1) { for
		 * (int i=0; i<this.listeFormes.size(); i++) { if
		 * (this.listeFormes.get(i).getShape().contains(e.getX(), e.getY())) {
		 * this.shape = this.listeFormes.get(i); trouve = true; touche = true; }
		 * } if (trouve) { preX = this.shape.getShape().getBounds().x -
		 * e.getX(); preY = this.shape.getShape().getBounds().y - e.getY();
		 * this.jc.removeShapeFromDraw(this.shape);
		 * this.jc.addShapeToDraw(this.shape);
		 * this.listeFormes.remove(this.shape);
		 * this.listeFormes.add(this.shape); this.deplacer(e, this.shape);
		 * trouve = false; } }
		 */
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (touche) {
			this.deplacer(e, shape);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		JShapeDraw formeSave = null;
		if (touche) {
			this.deplacer(e, this.shape);
			if (!this.modeLibre) {
				formeSave = this.shape;
				/*this.shape = this.hFrame
						.getPeindre()
						.getGenerationFormes()
						.swap(this.shape,
								this.formeASwaper,
								this.boiteSurvolee.getDimSB(),
								this.boiteSurvolee
										.getListeCoordonneesSousBoites().get(
												this.rangFormeDansBoite));
				this.formeASwaper = this.hFrame
						.getPeindre()
						.getGenerationFormes()
						.swap(this.formeASwaper,
								this.shape,
								this.boiteSurvolee.getDimSB(),
								this.boiteSurvolee
										.getListeCoordonneesSousBoites().get(
												this.rangFormeDansBoite));*/
			}
			this.shape = null;
			this.formeASwaper = null;
			touche = false;
		}

		if (!this.modeLibre) {
			if (this.contourSB != null)
				this.jc.removeShapeFromDraw(this.contourSB);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if(this.hFrame.getHProperties().isFullScreen()){
			JMenuBar menubar = this.hFrame.getMenuBar();
			JFrame frame = this.hFrame.getFrame();
			if(e.getY()<30){
				//System.out.println("VISIBLE si kinect OK");
				menubar.setVisible(true);
			}
			else{
				//System.out.println("CACHE");
				menubar.setVisible(false);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		ArrayList<JShapeDraw> boites = this.jc.getListe();
		int i = 0;
		JShapeDraw shape = null;
		if (e.getButton() == MouseEvent.BUTTON3) 	{
			System.out.println("Detected Mouse Right Click!");

			/*
			 * Sans la palette de couleurs
			 */

			/*
			 * for (int i=0; i<this.listeFormes.size(); i++) { //Si on est sur
			 * une forme if
			 * (this.listeFormes.get(i).getShape().contains(e.getX(), e.getY()))
			 * { System.out.println("X du Shape : " +
			 * this.listeFormes.get(i).getShape().getBounds().x );
			 * System.out.println("X de la souris : " + e.getX());
			 * this.listeFormes.get(i).setColor(new
			 * Color((int)(Math.random()*255), (int)(Math.random()*255),
			 * (int)(Math.random()*255))); trouve = true; } }
			 */

			/*
			 * Avec la palette de couleurs
			 */

			/* Formes */
			for (i = 0; i < this.listeFormes.size(); i++) {
				if (this.listeFormes.get(i).getShape()
						.contains(e.getX(), e.getY())) {
					shape = this.listeFormes.get(i);
					trouve = true;
				}
			}
			if (trouve) {
				Color background = JColorChooser.showDialog(null,
						"COLORIZATOR", null);
				System.out.println("Couleur" + i + " : " + background);
				if (background != null) {
					shape.setColor(background);
					this.jc.repaint();
				}
				trouve = false;
			} else {
				for (i = 0; i < boites.size(); i++) {
					if (boites.get(i).getShape().contains(e.getX(), e.getY())) {
						Color background = JColorChooser.showDialog(null,
								"Name", null);
						if (background != null) {
							boites.get(i).setColor(background);
							jc.repaint();
						}
					}
				}
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stubtableau

	}

	public void deplacer(MouseEvent e, JShapeDraw shape) {
		ArrayList<JShapeDraw> listeFormesLocal = new ArrayList<JShapeDraw>(
				this.listeFormes);
		int rangForme;

		listeFormesLocal.remove(shape);

		shape.translate(e.getX() - shape.getRectangle().x + preX, e.getY()- shape.getRectangle().y + preY);

		this.jc.repaint();

		if (!this.modeLibre) {


			if (this.contourSB != null){
				this.jc.removeShapeFromDraw(this.contourSB);
			}

			for (Boite boite : this.listeBoites) {
				
				if (boite.getAbscisse() <= e.getX()
						&& (boite.getAbscisse() + boite.getDim().getWidth() - 1) >= e
								.getX()
						&& boite.getOrdonnee() <= e.getY()
						&& (boite.getOrdonnee() + boite.getDim().getHeight() - 1) >= e
								.getY()) {

					for (int i = 0; i < boite.getListeCoordonneesSousBoites()
							.size(); i++) {

						if (boite.getListeCoordonneesSousBoites().get(i).x <= e
								.getX()
								&& (boite.getListeCoordonneesSousBoites()
										.get(i).x + boite.getDimSB().getWidth() - 1) >= e
											.getX()
								&& boite.getListeCoordonneesSousBoites().get(i).y <= e
										.getY()
								&& (boite.getListeCoordonneesSousBoites()
										.get(i).y
										+ boite.getDimSB().getHeight() - 1) >= e
											.getY()) {
							//System.out.println("couscous merguez");
							this.contourSB = new DrawRectBorder(
									boite.getColorBorder(), boite
											.getListeCoordonneesSousBoites()
											.get(i), boite.getDimSB());
							this.jc.addShapeToDraw(this.contourSB);
							this.boiteSurvolee = boite;
							this.rangFormeDansBoite = i;
							break;
						}
					}

					for (JShapeDraw forme : listeFormesLocal) {
						if (forme.getRectangle().x <= e.getX()
								&& (forme.getRectangle().x
										+ forme.getRectangle().width - 1) >= e
											.getX()
								&& forme.getRectangle().y <= e.getY()
								&& (forme.getRectangle().y
										+ forme.getRectangle().height - 1) >= e
											.getY()) {
							// this.hFrame.getPeindre().
							rangForme = this.listeFormes.indexOf(forme);

							this.formeASwaper = forme;
							break;
						}
					}
					break;
				}
			}
		}
	}

	public ArrayList<JShapeDraw> getListeFormes() {
		return this.listeFormes;
	}
}