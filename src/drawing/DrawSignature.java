package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * the rectangular structure placed in background representing a given vulnerability signature 
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class DrawSignature extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private Color color;
	private boolean borderS;
	private int ampiezza;
	private int numHostPerSign;
	
	public void paintComponent(Graphics g){
		super.paintComponents(g);	
		if(isBorderS()){
			g.setColor(getColor());
			g.fillRect(0, 0, getAmpiezza(), 200);
		}else{
			g.setColor(getColor());
			g.fillRect(0, 0, 10, 200);
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public boolean isBorderS() {
		return borderS;
	}

	public void setBorderS(boolean borderS) {
		this.borderS = borderS;
	}

	public int getAmpiezza() {
		return ampiezza;
	}

	public void setAmpiezza(int ampiezza) {
		this.ampiezza = ampiezza;
	}

	public int getNumHostPerSign() {
		return numHostPerSign;
	}

	public void setNumHostPerSign(int numHostPerSign) {
		this.numHostPerSign = numHostPerSign;
	}
	
}
