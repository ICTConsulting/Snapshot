package drawing;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * the y axes structure
 * 
 * Copyright (c) 2014 ICT Consulting
 */
public class AsseY extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g){
		
		super.paintComponents(g);
			
			//asse y (x1, y1, x2, y2)
			g.setColor(Color.BLACK);
			g.drawLine(5, 200, 200, -200000);
	}
}
