import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class LaserShoot extends JPanel implements ActionListener, KeyListener{
	
    Timer tm = new Timer(0, this);
    Mirror margaret;
    Mirror winston;
    Laser L;
    ArrayList<Double> elainex;
    ArrayList<Double> elainey;
    
    public LaserShoot(){
	tm.start();
	addKeyListener(this);
	setFocusable(true);//enables keylistener
	setFocusTraversalKeysEnabled(false);
	margaret = new Mirror(100,300,250,300);
	winston = new Mirror(600,500,750,500);
	L = new Laser(0.0,300.0);
	elainex = new ArrayList<Double>();
	elainey = new ArrayList<Double>();
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.setColor(Color.BLUE);
	Graphics2D g2d=(Graphics2D)g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			     RenderingHints.VALUE_ANTIALIAS_ON);
	//mirrors
	g2d.setStroke(new BasicStroke(1));
	Line2D.Double margLine = new Line2D.Double(margaret.x1,margaret.y1,margaret.x2,margaret.y2);
	g2d.draw(margLine);
	Line2D.Double winLine = new Line2D.Double(winston.x1,winston.y1,winston.x2,winston.y2);
	g2d.draw(winLine);
		
	//midline
	g2d.setStroke(new BasicStroke(1));
	g.setColor(Color.BLACK);
	g2d.drawLine(0,350,1300,350);
		
	//target
	g.setColor(Color.GREEN);
	g2d.fillOval(1270, 343, 15, 15);
		
	//laser source
	g.setColor(Color.RED);
	g2d.fillOval(0, 343, 15, 15);

	//dots on end of mirrors for reference in angle calculation
	g.setColor(Color.ORANGE);
	Ellipse2D.Double margDot = new Ellipse2D.Double(margaret.x2,margaret.y2,10,10);
	g2d.draw(margDot);
	Ellipse2D.Double winDot = new Ellipse2D.Double(winston.x2,winston.y2,10,10);
	g2d.draw(winDot);

	//laser photon
	g.setColor(Color.RED);
	Ellipse2D.Double laser = new Ellipse2D.Double(L.x,L.y,5,5);
	g2d.draw(laser);
	if (!L.on){
	    for (int i=0; i<elainex.size();i++){
		g2d.fillOval(elainex.get(i).intValue(), elainey.get(i).intValue(), 2, 2);
	    }
	}
    }

     public void actionPerformed(ActionEvent e){
	margaret.rotate(margaret.thetaChange);
	winston.rotate(winston.thetaChange);
	margaret.translate(margaret.xa,margaret.ya);
	winston.translate(winston.xa,winston.ya);
	if (L.x< 0 || L.x>=1300 || L.y<=0 || L.y>=700){L.on=false;}
	if(L.on){
	    L.propagate();
	    if (L.intersect(margaret)){
	    	L.reflect(margaret);
	    }
	    if (L.intersect(winston)){
	    	L.reflect(winston);
	    }
	    elainex.add(L.x);
	    elainey.add(L.y);
	    System.out.println(Math.toDegrees(L.theta));
	}
	repaint(); //calls paintcomponent
     }
	
    public void keyPressed(KeyEvent e){
	int c = e.getKeyCode();
	if (!L.on){
	    if (c == KeyEvent.VK_LEFT){
		margaret.ya=0;
		margaret.xa=-0.1;
		margaret.thetaChange=0;
	    }
	    if (c == KeyEvent.VK_RIGHT){
		margaret.ya=0;
		margaret.xa=0.1;
	    }
	    if (c ==KeyEvent.VK_UP){
		margaret.ya=-0.1;
		margaret.xa=0;
	    }
	    if (c == KeyEvent.VK_DOWN){
		margaret.xa=0;
		margaret.ya=0.1;
	    }
	    if (c == KeyEvent.VK_S){
		winston.ya=0;
		winston.xa=-0.1;
		winston.thetaChange=0;
	    }
	    if (c == KeyEvent.VK_F){
		winston.ya=0;
		winston.xa=0.1;
		//margaret.thetaChange=0;
	    }
	    if (c ==KeyEvent.VK_E){
		winston.ya=-0.1;
		winston.xa=0;
		//margaret.thetaChange=0;
	    }
	    if (c == KeyEvent.VK_D){
		winston.xa=0;
		winston.ya=0.1;
		//Xmargaret.thetaChange=0;
	    }
	    if (c == KeyEvent.VK_M){
		margaret.thetaChange=0.0005;
		System.out.println(Math.toDegrees(margaret.theta));
	    }
	    if (c == KeyEvent.VK_W){
		winston.thetaChange=0.0005;
		winston.xa=0;
		winston.ya=0;
	    }
	    if (c == KeyEvent.VK_L){
		L.on = true;
	    } 
	}
    }
	
    public void keyTyped(KeyEvent e){
    }

    public void keyReleased(KeyEvent e){
	margaret.thetaChange=0;
	winston.thetaChange=0;
	margaret.xa=0;
	margaret.ya=0;
	winston.xa=0;
	winston.ya=0;
	//L.on=false;
    }
	
    public static void main (String[] args){
	LaserShoot LSS = new LaserShoot();
	JFrame frame = new JFrame("Laser Shoot");
	frame.setVisible(true);
	frame.setSize(1300,700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(LSS);
    }
}
