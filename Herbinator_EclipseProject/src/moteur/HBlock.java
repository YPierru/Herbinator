package moteur;

import draw.JCanvas;

public class HBlock implements java.io.Serializable{
	private JCanvas jc;
	private Peindre tableau;
	private HProperties hprop;
	
	public HBlock(JCanvas j, Peindre p, HProperties hp){
		this.jc=j;
		this.tableau=p;
		this.hprop=hp;
	}

	public JCanvas getJc() {
		return this.jc;
	}

	public Peindre getTableau() {
		return this.tableau;
	}
	
	public HProperties getHProp(){
		return this.hprop;
	}
	
}
