import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class LaserShoot extends JPanel implements ActionListener, KeyListener{
	
	Timer tm = new Timer(5, this);
	Mirror margaret;
    
	public LaserShoot(){
		tm.start();
		addKeyListener(this);
		setFocusable(true);//enables keylistener
		setFocusTraversalKeysEnabled(false);
		margaret=new Mirror(100,100,200,100);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(3));
		g2d.drawLine(margaret.x1,margaret.y1,margaret.x2,margaret.y2);
		margaret.paint(g2d);
		}
	
	public void actionPerformed(ActionEvent e){
		margaret.x1+=margaret.xa;
		margaret.x2+=margaret.xa;
		margaret.y2+=margaret.ya;
		margaret.y1+=margaret.ya;
		margaret.move(margaret.thetaChange,margaret.xa,margaret.ya);
		repaint();//calls paintcomponent
	}
	
	public void keyPressed(KeyEvent e){
		int c=e.getKeyCode();
		if (c== KeyEvent.VK_LEFT){
			margaret.ya=0;
			margaret.xa=-0.5;
			margaret.thetaChange=0;
		}
		if (c== KeyEvent.VK_RIGHT){
			margaret.ya=0;
			margaret.xa=0.5;
			margaret.thetaChange=0;
		}
		if (c==KeyEvent.VK_UP){
			margaret.ya=-0.5;
			margaret.xa=0;
			margaret.thetaChange=0;
		}
		if (c== KeyEvent.VK_DOWN){
			margaret.xa=0;
			margaret.ya=0.5;
			margaret.thetaChange=0;
		}
		if (c== KeyEvent.VK_R){
			margaret.thetaChange=0.005;
		}
	}
	
	public void keyTyped(KeyEvent e){
	}
	public void keyReleased(KeyEvent e){
		margaret.thetaChange=0;
		margaret.xa=0;
		margaret.ya=0;
	}
	
public static void main (String[] args){
	LaserShoot marg = new LaserShoot();
	JFrame frame= new JFrame("HIIII");
	frame.setVisible(true);
	frame.setSize(1300,700);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(marg);
}
}
