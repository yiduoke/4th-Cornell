import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.math.*;

import javax.swing.*;

public class Laser extends JComponent{

    private static class Line{
	final int x1; 
	final int y1;
	final int x2;
	final int y2;   
	final double theta;
	final Color color;

	public Line(int x1, int y1, int x2, int y2, Color color) {
	    this.x1 = x1;
	    this.y1 = y1;
	    this.x2 = x2;
	    this.y2 = y2;
	    this.theta = atan((y2-y1)/(x2-x1));
	    this.color = color;
	}               
    }

    private final LinkedList<Line> lines = new LinkedList<Line>();

    public void addLine(int x1, int x2, int x3, int x4) {
	addLine(x1, x2, x3, x4, Color.red);
    }

    public void addLine(int x1, int x2, int x3, int x4, Color color) {
	lines.add(new Line(x1, x2, x3, x4, color));        
	repaint();
    }

    public void clearLines() {
	lines.clear();
	repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	for (Line line : lines) {
	    g.setColor(line.color);
	    g.drawLine(line.x1, line.y1, line.x2, line.y2);
	}
    }

    public static void main (String[] args){
	LaserShoot marg = new LaserShoot();
	JFrame frame= new JFrame("HIIII");
	frame.setVisible(true);
	frame.setSize(700,700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(marg);
	final Laser comp = new Laser();
	comp.setPreferredSize(new Dimension(700, 700));
	frame.getContentPane().add(comp, BorderLayout.CENTER);
	JPanel buttonsPanel = new JPanel();
	JButton laserButton = new JButton("Fire Laser");
	JButton clearButton = new JButton("Clear");
	buttonsPanel.add(laserButton);
	buttonsPanel.add(clearButton);
	frame.getContentPane().add(buttonsPanel, BorderLayout.SOUTH);
	laserButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    int x1 = 0;
		    int x2 = 700;
		    int y1 = 350;
		    int y2 = 350;
		    comp.addLine(x1, y1, x2, y2, Color.red);
		}
	    });
	clearButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
		    comp.clearLines();
		}
	    });
	frame.pack();
	frame.setVisible(true);
    }
}
