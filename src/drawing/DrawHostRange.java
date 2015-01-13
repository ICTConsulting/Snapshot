package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * the rectangular structure representing a single host (i.e. the single dot of the black line)
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class DrawHostRange extends JPanel{

	private static final long serialVersionUID = 1L;
	private boolean analisi = false;
	private int ratio = 2;

	public DrawHostRange(){
		setAnalisi(false);
	}

	public void paintComponent(Graphics g){

		super.paintComponents(g);		
		if(isAnalisi() == false){
			g.setColor(Color.BLACK);
			g.draw3DRect(5, 1, 2, getRatio(), true);
		}else{
			g.setColor(Color.RED);
			g.fillRect(4, 1, 4, 4);
		}
	}

	public boolean isAnalisi() {
		return analisi;
	}

	public void setAnalisi(boolean analisi) {
		this.analisi = analisi;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

}