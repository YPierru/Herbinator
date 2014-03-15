package boites;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

import draw.JShapeDraw;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author hle
 */

/*
 * public class Boite implements java.io.Serializable{ private Dimension dim;
 * private Point point; private ArrayList<JShapeDraw> listFormes; private
 * SortedSet<Boite> listBoitesTriees;
 * 
 * public Boite (Dimension d, Point p){ this.dim = d; this.point = p;
 * this.listBoitesTriees = new TreeSet<Boite>(new OrdreTailleBoite());
 * this.listBoitesTriees.add(this); }
 * 
 * public void ajoutForme(JShapeDraw f){ this.listFormes.add(f); }
 * 
 * public void retirerForme(int i){ this.listFormes.remove(i); }
 * 
 * public void ajoutBoite(Boite b) throws BoiteTropGrande{ if
 * (this.getDim().width > b.getDim().width && this.getDim().height >
 * b.getDim().height){ this.listBoitesTriees.add(b); } else { throw new
 * BoiteTropGrande(); } }
 * 
 * public SortedSet<Boite> mosaique(int nbMaxBoite,TreeSet<Boite> b,int
 * largeurMin, int hauteurMin) { /* les entiers largeur/hauteurMin sont les
 * dimensions minimales de découpage l'ensemble b est triée de la plus grosse
 * boîte à la plus petite l'entier nbMaxBoite est le nombre maximal de découpage
 *//*
	 * 
	 * int compteur=0;
	 * 
	 * // on récupère le type de découpe pour b.last() // 0: impossible, 1:
	 * vertical, 2: horizontal, 3: aléatoire int decoupe =
	 * estDecoupable(b.last(),largeurMin,hauteurMin);
	 * 
	 * while (compteur < nbMaxBoite && decoupe!=0){ if (decoupe!=0){ String
	 * d=""; d = (decoupe==1) ? d+="verticalement" : ""; if
	 * (decoupe==1){d+="verticalement";} if (decoupe==2){d+="horizontalement";}
	 * if (decoupe==3){d+="aléatoirement";}
	 * //System.out.println("Découpage n°"+(compteur+1)+" "+d); // le tableau
	 * qui récupère les 2 boîtes issues du découpage de la plus grosse boîte //
	 * de l'ensemble b (b.last())
	 * 
	 * Boite[] BoxTab = decouper(b.last(),largeurMin,hauteurMin,decoupe); // on
	 * fait un tri de la liste de boîtes // en retirant la boîte découpée, et en
	 * ajoutant les 2 nouvelles boîtes
	 * 
	 * this.listBoitesTriees = trier(b,b.last(),BoxTab[0],BoxTab[1]); }
	 * compteur++; decoupe = estDecoupable(b.last(),largeurMin,hauteurMin); }
	 * 
	 * //if
	 * (compteur==nbMaxBoite){System.out.println("Nombre max de découpage atteint"
	 * );} //if (decoupe==0){System.out.println("Aucune boîte découpable");}
	 * return this.listBoitesTriees; }
	 * 
	 * public Boite[] decouper(Boite b, int wMin, int hMin, int typeDecoupe){ //
	 * taBoite est un tableau qui contiendra les 2 boîtes b1 et b2 issues du
	 * découpage Boite[] taBoite = new Boite[2]; Boite b1=null,b2=null;
	 * 
	 * int bool;
	 */
// bool permet de savoir si on va découper horizontalement ou verticalement

/*
 * System.out.println(
 * "Boîte à découper : Point("+b.getAbscisse()+", "+b.getOrdonnee()+
 * "), Dimension("+b.dim.width+", "+b.dim.height+")");
 * System.out.println("w="+b.
 * dim.width+", h="+b.dim.height+", type = "+typeDecoupe
 * +", wMin="+wMin+", hMin="+hMin);
 */
// découpage vertical
// type=3 et bool=1 ou typ!=3 et bool=2
/*
 * if(typeDecoupe==3){ bool = (int)(Math.random()*2 + 1); } else { bool =
 * typeDecoupe; }
 * 
 * // découpage horizontal
 * 
 * // y est l'ordonnée de découpage //System.out.println("Test4"); switch(bool){
 * case 1: int x; // w1 et w2 sont les deux largeurs de part et d'autre de x int
 * w1,w2;
 * 
 * // on tire un x tant que x est trop proche d'un des des bords de la boîte do
 * { //System.out.println("Test5"); x = (int)(Math.random()*(b.dim.width) +
 * b.getAbscisse()); w1 = x - b.getAbscisse(); w2 = b.dim.width - w1; } while
 * ((w1 < wMin) || (w2 < wMin));
 * 
 * b1 = new Boite (new Dimension(w1,b.dim.height), new Point(b.getAbscisse(),
 * b.getOrdonnee())); b2 = new Boite (new Dimension(w2,b.dim.height), new
 * Point(x, b.getOrdonnee()));
 * 
 * //System.out.println("Découpe verticale en x = "+x); break; case 2: int y; //
 * h1 et h2 sont les hauteur de part et d'autre de y int h1, h2;
 * 
 * // on tire un y tant que y est trop proche d'un des des bords de la boîte do
 * { //System.out.println("Test6"); y = (int)(Math.random()*(b.dim.height) +
 * b.getOrdonnee()); h1 = y - b.getOrdonnee(); h2 = b.dim.height - h1; } while
 * ((h1 < hMin) || (h2<hMin));
 * 
 * b1 = new Boite (new Dimension(b.dim.width,h1), new
 * Point(b.getAbscisse(),b.getOrdonnee())); b2 = new Boite (new
 * Dimension(b.dim.width,h2), new Point(b.getAbscisse(),y));
 * 
 * //System.out.println("Découpe horizontale en y = "+y); break;
 * 
 * 
 * 
 * 
 * 
 * }
 */
/*
 * System.out.println("Dimension de la b1 : w="+b1.dim.width+", h="+b1.dim.height
 * + ", aire=" + (b1.dim.width*b1.dim.height));
 * System.out.println("Dimension de la b2 : w="
 * +b2.dim.width+", h="+b2.dim.height+ ", aire=" +
 * (b2.dim.width*b2.dim.height));
 */
/*
 * taBoite[0] = b1; taBoite[1] = b2; return taBoite; }
 * 
 * public TreeSet<Boite> trier(TreeSet<Boite> b, Boite old_b, Boite b1, Boite
 * b2){ TreeSet<Boite> listeBoites = b;
 * 
 * listeBoites.remove(old_b); listeBoites.add(b1); listeBoites.add(b2);
 */
// System.out.println("Triage de la liste de boîte");
/*
 * for (Boite box : b){ System.out.println(
 * "\t\tPoint("+box.getAbscisse()+", "+box.getOrdonnee()+
 * "), Dimension("+box.dim.width+", "+box.dim.height+
 * "), Aire="+box.dim.width*box.dim.height); }
 */
/*
 * System.out.println( "La plus grande boîte est : Point(" +
 * b.last().getAbscisse() + ", "+b.last().getOrdonnee()+"), Dimension(" +
 * b.last().dim.width+ ", "+b.last().dim.height+"), Aire=" +
 * (b.last().dim.width*b.last().dim.height)+ "\n");
 *//*
	 * return listeBoites; }
	 * 
	 * public int estDecoupable(Boite b,int largeur,int hauteur){
	 */
/*
 * 0 si pas découpable 1 découpable vertical seulement 2 si découpable
 * horizontal seulement 3 si découpable vertical ou horizontal possible
 */
/*
 * int type=-1; if (b.dim.width >= (2*largeur) && b.dim.height >= (2*hauteur))
 * type = 3;
 * 
 * if (b.dim.width >= (2*largeur) && b.dim.height < (2*hauteur)) type=1; if (
 * b.dim.width < (2*largeur) && b.dim.height < (2*hauteur)) type=0; if
 * (b.dim.width < (2*largeur) && b.dim.height >= (2*hauteur)) type=2;
 * 
 * return type; }
 * 
 * public Dimension getDim(){ return this.dim; }
 * 
 * public Point getPoint() { return this.point; }
 * 
 * public int getAbscisse() { return this.point.x; }
 * 
 * public int getOrdonnee() { return this.point.y; }
 * 
 * public TreeSet<Boite> getListBoites() { return
 * (TreeSet<Boite>)this.listBoitesTriees; }
 * 
 * public ArrayList<JShapeDraw> getListFormes() { return this.listFormes; } }
 */

public class Boite implements java.io.Serializable {
	private Dimension dim;
	private Point point;
	private ArrayList<JShapeDraw> listFormes;
	private SortedSet<Boite> listBoitesTriees;
	private Color color;
	private ArrayList<Point> listeCoordonneesSousBoites;
	private Dimension dimSB;

	public Boite(Dimension d, Point p) {
		this.dim = d;
		this.point = p;
		this.listBoitesTriees = new TreeSet<Boite>(new OrdreTailleBoite());
		this.listBoitesTriees.add(this);
		this.listeCoordonneesSousBoites = new ArrayList<Point>();
	}

	public void ajoutForme(JShapeDraw f) {
		this.listFormes.add(f);
	}

	public void remplacerForme(int i, JShapeDraw f) {
		this.listFormes.set(i, f);
	}

	public void retirerForme(int i) {
		this.listFormes.remove(i);
	}

	public void getForme(int i) {
		// return this.listFormes.get(i);
		System.out.println(this.listFormes.size());
	}

	public void ajoutBoite(Boite b) throws BoiteTropGrande {
		if (this.getDim().width > b.getDim().width
				&& this.getDim().height > b.getDim().height) {
			this.listBoitesTriees.add(b);
		} else {
			throw new BoiteTropGrande();
		}
	}

	public SortedSet<Boite> mosaique(int nbMaxBoite, TreeSet<Boite> b,
			int largeurMin, int hauteurMin) {
		/*
		 * les entiers largeur/hauteurMin sont les dimensions minimales de
		 * d�coupage l'ensemble b est tri�e de la plus grosse bo�te � la plus
		 * petite l'entier nbMaxBoite est le nombre maximal de d�coupage
		 */

		int compteur = 0;

		// on r�cup�re le type de d�coupe pour b.last()
		// 0: impossible, 1: vertical, 2: horizontal, 3: al�atoire
		int decoupe = estDecoupable(b.last(), largeurMin, hauteurMin);

		while (compteur < nbMaxBoite && decoupe != 0) {
			if (decoupe != 0) {
				String d = "";
				d = (decoupe == 1) ? d += "verticalement" : "";
				if (decoupe == 1) {
					d += "verticalement";
				}
				if (decoupe == 2) {
					d += "horizontalement";
				}
				if (decoupe == 3) {
					d += "al�atoirement";
				}
				// System.out.println("D�coupage n�"+(compteur+1)+" "+d);
				// le tableau qui r�cup�re les 2 bo�tes issues du d�coupage de
				// la plus grosse bo�te
				// de l'ensemble b (b.last())

				Boite[] BoxTab = decouper(b.last(), largeurMin, hauteurMin,
						decoupe);
				// on fait un tri de la liste de bo�tes
				// en retirant la bo�te d�coup�e, et en ajoutant les 2 nouvelles
				// bo�tes

				this.listBoitesTriees = trier(b, b.last(), BoxTab[0], BoxTab[1]);
			}
			compteur++;
			decoupe = estDecoupable(b.last(), largeurMin, hauteurMin);
		}

		// if
		// (compteur==nbMaxBoite){System.out.println("Nombre max de d�coupage atteint");}
		// if (decoupe==0){System.out.println("Aucune bo�te d�coupable");}
		return this.listBoitesTriees;
	}

	public Boite[] decouper(Boite b, int wMin, int hMin, int typeDecoupe) {
		// taBoite est un tableau qui contiendra les 2 bo�tes b1 et b2 issues du
		// d�coupage
		Boite[] taBoite = new Boite[2];
		Boite b1 = null, b2 = null;

		int bool;
		// bool permet de savoir si on va d�couper horizontalement ou
		// verticalement

		/*
		 * System.out.println(
		 * "Bo�te � d�couper : Point("+b.getAbscisse()+", "+b.getOrdonnee()+
		 * "), Dimension("+b.dim.width+", "+b.dim.height+")");
		 * System.out.println
		 * ("w="+b.dim.width+", h="+b.dim.height+", type = "+typeDecoupe
		 * +", wMin="+wMin+", hMin="+hMin);
		 */
		// d�coupage vertical
		// type=3 et bool=1 ou typ!=3 et bool=2
		if (typeDecoupe == 3) {
			bool = (int) (Math.random() * 2 + 1);
		} else {
			bool = typeDecoupe;
		}

		// d�coupage horizontal

		// y est l'ordonn�e de d�coupage
		// System.out.println("Test4");
		switch (bool) {
		case 1:
			int x;
			// w1 et w2 sont les deux largeurs de part et d'autre de x
			int w1,
			w2;

			// on tire un x tant que x est trop proche d'un des des bords de la
			// bo�te
			do {
				// System.out.println("Test5");
				x = (int) (Math.random() * (b.dim.width) + b.getAbscisse());
				w1 = x - b.getAbscisse();
				w2 = b.dim.width - w1;
			} while ((w1 < wMin) || (w2 < wMin));

			b1 = new Boite(new Dimension(w1, b.dim.height), new Point(
					b.getAbscisse(), b.getOrdonnee()));
			b2 = new Boite(new Dimension(w2, b.dim.height), new Point(x,
					b.getOrdonnee()));

			// System.out.println("D�coupe verticale en x = "+x);
			break;
		case 2:
			int y;
			// h1 et h2 sont les hauteur de part et d'autre de y
			int h1,
			h2;

			// on tire un y tant que y est trop proche d'un des des bords de la
			// bo�te
			do {
				// System.out.println("Test6");
				y = (int) (Math.random() * (b.dim.height) + b.getOrdonnee());
				h1 = y - b.getOrdonnee();
				h2 = b.dim.height - h1;
			} while ((h1 < hMin) || (h2 < hMin));

			b1 = new Boite(new Dimension(b.dim.width, h1), new Point(
					b.getAbscisse(), b.getOrdonnee()));
			b2 = new Boite(new Dimension(b.dim.width, h2), new Point(
					b.getAbscisse(), y));

			// System.out.println("D�coupe horizontale en y = "+y);
			break;

		}
		/*
		 * System.out.println("Dimension de la b1 : w="+b1.dim.width+", h="+b1.dim
		 * .height+ ", aire=" + (b1.dim.width*b1.dim.height));
		 * System.out.println
		 * ("Dimension de la b2 : w="+b2.dim.width+", h="+b2.dim.height+
		 * ", aire=" + (b2.dim.width*b2.dim.height));
		 */

		taBoite[0] = b1;
		taBoite[1] = b2;
		return taBoite;
	}

	public TreeSet<Boite> trier(TreeSet<Boite> b, Boite old_b, Boite b1,
			Boite b2) {
		TreeSet<Boite> listeBoites = b;

		listeBoites.remove(old_b);
		listeBoites.add(b1);
		listeBoites.add(b2);

		// System.out.println("Triage de la liste de bo�te");
		/*
		 * for (Boite box : b){ System.out.println(
		 * "\t\tPoint("+box.getAbscisse()+", "+box.getOrdonnee()+
		 * "), Dimension("+box.dim.width+", "+box.dim.height+
		 * "), Aire="+box.dim.width*box.dim.height); }
		 */
		/*
		 * System.out.println( "La plus grande bo�te est : Point(" +
		 * b.last().getAbscisse() + ", "+b.last().getOrdonnee()+"), Dimension("
		 * + b.last().dim.width+ ", "+b.last().dim.height+"), Aire=" +
		 * (b.last().dim.width*b.last().dim.height)+ "\n");
		 */
		return listeBoites;
	}

	public int estDecoupable(Boite b, int largeur, int hauteur) {

		/*
		 * 0 si pas d�coupable 1 d�coupable vertical seulement 2 si d�coupable
		 * horizontal seulement 3 si d�coupable vertical ou horizontal possible
		 */

		int type = -1;
		if (b.dim.width >= (2 * largeur) && b.dim.height >= (2 * hauteur))
			type = 3;

		if (b.dim.width >= (2 * largeur) && b.dim.height < (2 * hauteur))
			type = 1;
		if (b.dim.width < (2 * largeur) && b.dim.height < (2 * hauteur))
			type = 0;
		if (b.dim.width < (2 * largeur) && b.dim.height >= (2 * hauteur))
			type = 2;

		return type;
	}

	public Dimension getDim() {
		return this.dim;
	}

	public Point getPoint() {
		return this.point;
	}

	public int getAbscisse() {
		return this.point.x;
	}

	public int getOrdonnee() {
		return this.point.y;
	}

	public TreeSet<Boite> getListBoites() {
		return (TreeSet<Boite>) this.listBoitesTriees;
	}

	public ArrayList<JShapeDraw> getListFormes() {
		return this.listFormes;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void ajouterCoordonneeSousBoite(Point coordSB) {
		this.listeCoordonneesSousBoites.add(coordSB);
	}

	public ArrayList<Point> getListeCoordonneesSousBoites() {
		return this.listeCoordonneesSousBoites;
	}

	public Dimension getDimSB() {
		return dimSB;
	}

	public void setDimSB(Dimension dimSB) {
		this.dimSB = dimSB;
	}

	public Color getColorBorder() {
		if (this.color.getBlue() == 200) // bleu => orange
			return Color.ORANGE;
		else if (this.color.getGreen() == 100) // vert => rouge
			return Color.RED;
		else if (this.color.getRed() == 100) // rouge => vert
			return Color.GREEN;
		else if (this.color == Color.BLACK) // noir => blanc
			return Color.WHITE;
		else
			// blanc => noir
			return Color.BLACK;
	}
}

class BoiteTropGrande extends Exception {
}
