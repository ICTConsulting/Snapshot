package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * the rectangular structure representing the bar of the histogram
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class DrawPlot extends JPanel{

	private static final long serialVersionUID = 1L;
	private double adjust = 0;
	private int altezza = 0;
	private int altezzaInfo = 0;
	private int altezzaLow = 0;
	private int altezzaMedium = 0;
	private int altezzaHigh = 0;
	private int altezzaCritical = 0;
	private boolean greyEdition;

	public void paintComponent(Graphics g){

		super.paintComponents(g);
		sommaAltezze();
		
		if(!isGreyEdition())
			g.setColor(Color.BLUE);
		else
			g.setColor(new Color(140, 140, 140));
		g.fillRect(50, 250-(int) (Math.round(this.getAltezzaInfo()/this.getAdjust())), 50, (int) (Math.round(this.getAltezzaInfo()/this.getAdjust())));

		if(!isGreyEdition())
			g.setColor(Color.YELLOW);
		else
			g.setColor(new Color(120, 120, 120));
		g.fillRect(50, 250-(int) (Math.round(this.getAltezzaLow()/this.getAdjust()))-(int) (Math.round(this.getAltezzaInfo()/this.getAdjust())), 50, (int) (Math.round(this.getAltezzaLow()/this.getAdjust())));
		
		if(!isGreyEdition())
			g.setColor(Color.ORANGE);
		else
			g.setColor(new Color(100, 100, 100));
		g.fillRect(50, 250-(int) (Math.round(this.getAltezzaMedium()/this.getAdjust()))-(int) (Math.round(this.getAltezzaLow()/this.getAdjust()))-(int) (Math.round(this.getAltezzaInfo()/this.getAdjust())), 50, (int) (Math.round(this.getAltezzaMedium()/this.getAdjust())));
		
		if(!isGreyEdition())
			g.setColor(Color.MAGENTA);
		else
			g.setColor(new Color(80, 80, 80));
		g.fillRect(50, 250-(int) (Math.round(this.getAltezzaHigh()/this.getAdjust()))-(int) (Math.round(this.getAltezzaMedium()/this.getAdjust()))-(int) (Math.round(this.getAltezzaLow()/this.getAdjust()))-(int) (Math.round(this.getAltezzaInfo()/this.getAdjust())), 50, (int) (Math.round(this.getAltezzaHigh()/this.getAdjust())));
		
		if(!isGreyEdition())
			g.setColor(Color.RED);
		else
			g.setColor(new Color(60, 60, 60));
		g.fillRect(50, 250-(int) (Math.round(this.getAltezzaCritical()/this.getAdjust()))-(int) (Math.round(this.getAltezzaHigh()/this.getAdjust()))-(int) (Math.round(this.getAltezzaMedium()/this.getAdjust()))-(int) (Math.round(this.getAltezzaLow()/this.getAdjust()))-(int) (Math.round(this.getAltezzaInfo()/this.getAdjust())), 50, (int) (Math.round(this.getAltezzaCritical()/this.getAdjust())));

	}

	public void sommaAltezze() {
		this.setAltezza(this.getAltezzaInfo()+this.getAltezzaLow()+this.getAltezzaMedium()+this.getAltezzaHigh()+this.getAltezzaCritical());
	}

	public int getAltezza() {
		return altezza;
	}

	public void setAltezza(int altezza) {
		this.altezza = altezza;
	}

	public int getAltezzaInfo() {
		return altezzaInfo;
	}

	public void setAltezzaInfo(int altezzaInfo) {
		this.altezzaInfo = altezzaInfo;
	}

	public int getAltezzaLow() {
		return altezzaLow;
	}

	public void setAltezzaLow(int altezzaLow) {
		this.altezzaLow = altezzaLow;
	}

	public int getAltezzaMedium() {
		return altezzaMedium;
	}

	public void setAltezzaMedium(int altezzaMedium) {
		this.altezzaMedium = altezzaMedium;
	}

	public int getAltezzaHigh() {
		return altezzaHigh;
	}

	public void setAltezzaHigh(int altezzaHigh) {
		this.altezzaHigh = altezzaHigh;
	}

	public int getAltezzaCritical() {
		return altezzaCritical;
	}

	public void setAltezzaCritical(int altezzaCritical) {
		this.altezzaCritical = altezzaCritical;
	}


	public double getAdjust() {
		return adjust;
	}


	public void setAdjust(double adjust) {
		this.adjust = adjust;
	}

	public boolean isGreyEdition() {
		return greyEdition;
	}

	public void setGreyEdition(boolean greyEdition) {
		this.greyEdition = greyEdition;
	}





}
