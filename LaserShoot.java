import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class LaserShoot extends JPanel implements ActionListener, KeyListener{
	
    Timer tm = new Timer(5, this);
    Mirror margaret;
    Mirror winston;
    Laser L;
    //Mirror[] wall;
    
    public LaserShoot(){
	tm.start();
	addKeyListener(this);
	setFocusable(true);//enables keylistener
	setFocusTraversalKeysEnabled(false);
	margaret = new Mirror(100,300,250,300);
	winston = new Mirror(600,500,750,500);
	L = new Laser(0.0,300.0);
	//Mirror[0]=margaret;
	//Mirror[1]=winston;
    }
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	g.setColor(Color.BLUE);
	Graphics2D g2d=(Graphics2D)g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			     RenderingHints.VALUE_ANTIALIAS_ON);
	//mirrors
	g2d.setStroke(new BasicStroke(5));
	g2d.drawLine(margaret.x1,margaret.y1,margaret.x2,margaret.y2);

	g2d.drawLine(winston.x1,winston.y1,winston.x2,winston.y2);
		
	//midline
	g2d.setStroke(new BasicStroke(1));
	g.setColor(Color.BLACK);
	g2d.drawLine(0,350,1300,350);
		
	//target
	g.setColor(Color.GREEN);
	g2d.fillOval(1280, 343, 15, 15);
		
	//laser source
	g.setColor(Color.RED);
	g2d.fillOval(0, 343, 15, 15);

	//dots on end of mirrors for reference in angle calculation
	g.setColor(Color.ORANGE);
	g2d.fillOval(margaret.x2,margaret.y2,10,10);
	g2d.fillOval(winston.x2,winston.y2,10,10);

	//laser photon
	g.setColor(Color.RED);
	Ellipse2D.Double laser = new Ellipse2D.Double(L.x,L.y,5,5);
	g2d.draw(laser);
	//System.out.println(L.x);
    }

    public void actionPerformed(ActionEvent e){
	margaret.rotate(margaret.thetaChange);
	winston.rotate(winston.thetaChange);
	margaret.translate(margaret.xa,margaret.ya);
	winston.translate(winston.xa,winston.ya);
	if(L.on){
	    L.propagate();
	    if(L.intersect(margaret)){
		L.reflect(margaret);
	    }
	}
	repaint();//calls paintcomponent
    }
	
    public void keyPressed(KeyEvent e){
	int c = e.getKeyCode();
	if (c == KeyEvent.VK_LEFT){
	    System.out.println("Left");
	    margaret.ya=0;
	    margaret.xa=-1;
	    margaret.thetaChange=0;
	    //System.out.println(margaret.ya);
	    //System.out.println(margaret.xa);
	}
	if (c == KeyEvent.VK_RIGHT){
	    margaret.ya=0;
	    margaret.xa=1;
	    //margaret.thetaChange=0;
	}
	if (c ==KeyEvent.VK_UP){
	    margaret.ya=-1;
	    margaret.xa=0;
	    //margaret.thetaChange=0;
	}
	if (c == KeyEvent.VK_DOWN){
	    margaret.xa=0;
	    margaret.ya=1;
	    //Xmargaret.thetaChange=0;
	}
	if (c == KeyEvent.VK_M){
	    margaret.thetaChange=0.005;
	    //margaret.xa=0;
	    //margaret.ya=0;
	}
	if (c == KeyEvent.VK_W){
	    winston.thetaChange=0.005;
	    winston.xa=0;
	    winston.ya=0;
	}
	if (c == KeyEvent.VK_L){
	    L.on = true;
	    System.out.println(L.x);
	} 
    }
	
    public void keyTyped(KeyEvent e){
    }

    public void keyReleased(KeyEvent e){
	margaret.thetaChange=0;
	winston.thetaChange=0;
	margaret.xa=0;
	margaret.ya=0;
    }
	
    public static void main (String[] args){
	LaserShoot LSS = new LaserShoot();
	JFrame frame= new JFrame("Laser Shoot");
	frame.setVisible(true);
	frame.setSize(1300,700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(LSS);
    }
}