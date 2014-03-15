package HandCapture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.event.MouseInputAdapter;

import boites.Boite;

import draw.DrawCircle;
import draw.DrawRectBorder;
import draw.JCanvas;
import draw.JShapeDraw;


public class Hand {
	
	public static float xHand;
	public static float yHand;
	public static float zHand;
	public static float xHandOld;
	public static float yHandOld;
	public static float zHandOld;
	public static DrawCircle lastCircle;
	public static JCanvas jc;
	public static int count;
	public static DrawCircle circle;
	public static boolean onSelected,onForme,moveForme,onChangeColor;
	public static ArrayList<JShapeDraw> listeFormes;
	public static int preX,preY;
	public static JShapeDraw shape,saveForme;
	
	public static void setJc(JCanvas j){
		jc=j;
	}
	
	public static void setListForme(ArrayList<JShapeDraw> lf){
		listeFormes=lf;
	}
	
	public static void setCount(){
		count=0;
	}
	
	public static void setXYZ(float x, float y, float z){
        //xHand=(Float)null;
        //yHand=(Float)null;
        //zHand=(Float)null;
		xHandOld=xHand;
		yHandOld=yHand;
		zHandOld=zHand;
		xHand=x;
		yHand=y;
		zHand=z;
		//System.out.println("X = "+xHand+" Y = "+yHand+" Z = "+zHand);
	}
	
	public static void onMouvedHand(){
		if(isOnSelected()){
			System.out.println("on selected");
			if(isOnForme()){
				System.out.println("on forme");
			}
			/*if(isOnChangeColor()){
				System.out.println("veux changer forme");
			}*/
		}
		drawHand();
	}
	
	public static void drawHand(){
		//On supprime le précédent pointeur
		jc.removeShapeFromDraw(lastCircle);
		Color colorForme = new Color(0, 255, 0);
		//A modifier en fonction de Z
		//xHand*=3;
		//yHand*=3;
        Point position = new Point((int)xHand, (int)(yHand*-1));
        Dimension dimForme = new Dimension(30,30);
        circle = new DrawCircle(colorForme, position, dimForme);
        lastCircle=circle;
        jc.addShapeToDraw(circle);
        circle=null;
	}
	
	public static void eraseHand(){
		jc.removeShapeFromDraw(lastCircle);
		jc.removeShapeFromDraw(circle);
	}
	
	public static boolean isOnSelected(){
		if(zHand-1000>0){
			onSelected=true;
		}
		else{
			onSelected=false;
		}
		return onSelected;
	}
	
	public static boolean isOnChangeColor(){
		if(zHand<300){
			onChangeColor=true;
		}
		else{
			onChangeColor=false;
		}
		return onChangeColor;
	}
	
	public static boolean isOnForme(){
		onForme=false;
		//System.out.println("X = "+(int)xHand+" Y = "+(int)yHand*-1+" Z = "+(int)zHand);
		
		for (int i = 0; i < listeFormes.size(); i++) {
			if (listeFormes.get(i).getShape().contains((int)xHand, (int)(yHand*-1))) {
				onForme = true;
				/*preX = listeFormes.get(i).getShape().getBounds().x- (int)xHand;
				preY = listeFormes.get(i).getShape().getBounds().y- (int)(yHand*-1);
				shape = listeFormes.get(i);
				saveForme = shape;
				jc.removeShapeFromDraw(shape);
				jc.addShapeToDraw(shape);
				Point p = new Point((int)xHand, (int)(yHand*-1));
				deplacer(p, shape);
				//break;*/
				break;
			}
		}
		
		return onForme;
	}
	
	public static void deplacer(Point e, JShapeDraw shape) {

		ArrayList<JShapeDraw> listeFormesLocal = new ArrayList<JShapeDraw>(listeFormes);
		int rangForme;

		listeFormesLocal.remove(shape);

		shape.translate((int)e.getX() - shape.getRectangle().x + preX, (int)e.getY()- shape.getRectangle().y + preY);
		jc.repaint();
	}
	
	public String desc(){
		return "X = "+xHand+" Y = "+yHand+" Z = "+zHand;
	}
}



























