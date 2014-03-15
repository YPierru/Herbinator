package moteur;

public class HProperties implements java.io.Serializable{
	
	private int largeur;
	private int longueur;
	private String mot;
	private boolean modeLibre;
	private boolean fullScreen;
	
	public HProperties(int la, int lo, String m, boolean ml, boolean fs){
		this.largeur=la;
		this.longueur=lo;
		this.mot=m;
		this.modeLibre=ml;
		this.fullScreen=fs;
	}
	public HProperties(String m, boolean ml, boolean fs){
		this.mot=m;
		this.modeLibre=ml;
		this.fullScreen=fs;
	}


	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}

	public String getMot() {
		return mot;
	}

	public void setMot(String mot) {
		this.mot = mot;
	}

	public boolean isModeLibre() {
		return modeLibre;
	}

	public void setModeLibre(boolean modeLibre) {
		this.modeLibre = modeLibre;
	}
	
	public boolean isFullScreen() {
		return fullScreen;
	}

	public void setFullScreen(boolean fs) {
		this.fullScreen = fs;
	}
}
