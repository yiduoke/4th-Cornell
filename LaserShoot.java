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
    Mirror a=new Mirror (0,0,0,1,1,1,1,0);
	public LaserShoot(){
		tm.start();
		addKeyListener(this);
		setFocusable(true);//enables keylistener
		setFocusTraversalKeysEnabled(false);
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.RED);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.rotate(theta,x+50,y+3);
		g2d.drawRect((int)x,(int)y,100,6);
		g2d.setColor(Color.BLACK);
		g2d.drawLine(0,400,1500,400);
	}
	
	public void actionPerformed(ActionEvent e){
		if (x<0){
			velX=0;
			x=0;
		}
		if (x>640){
			velX=0;
			x=640;
		}
		if (y<0){
			velY=0;
			y=0;
		}
		if (y>640){
			velY=0;
			y=640;
		}
		x+=velX;
		y+=velY;
		repaint();//calls paintcomponent
	}
	
	public void keyPressed(KeyEvent e){
		int c=e.getKeyCode();
		if (c== KeyEvent.VK_LEFT){
			velY=0;
			velX=-0.5;
		}
		if (c== KeyEvent.VK_RIGHT){
			velY=0;
			velX=0.5;
		}
		if (c==KeyEvent.VK_UP){
			velY=-0.5;
			velX=0;
		}
		if (c== KeyEvent.VK_DOWN){
			velX=0;
			velY=0.5;
		}
		if (c== KeyEvent.VK_R){
			theta+=0.05;
		}
	}
	
	public void keyTyped(KeyEvent e){
	}
	public void keyReleased(KeyEvent e){
		velX=0;
		velY=0;
	}
	
public static void main (String[] args){
	LaserShoot marg = new LaserShoot();
	JFrame frame= new JFrame("HIIII");
	frame.setVisible(true);
	frame.setSize(1500,800);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(marg);
}
}
