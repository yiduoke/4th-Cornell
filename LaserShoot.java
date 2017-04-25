import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class LaserShoot extends JPanel implements ActionListener, KeyListener{
	
    Timer tm = new Timer(0, this);
    
    Laser L;
    
    Mirror margaret; //aka mirror 1
    Mirror winston; //2
    Mirror despoina; //3
    Mirror penn; //4
    Mirror brian; //5
    
    Obstacle jonathan;
    Obstacle kerwin;
    Obstacle darren;
    
    JButton margaretButton;
    JButton winstonButton;
    JButton despoinaButton;
    JButton pennButton;
    JButton brianButton;
    JButton resetButton;
    
    ArrayList<Double> elainex;
    ArrayList<Double> elainey;
    
    String stateOfSelection;
  
    //constructor
    public LaserShoot(){
	tm.start();
	addKeyListener(this);
	setFocusable(true); //enables keylistener
	setFocusTraversalKeysEnabled(false);
	
	//mirrors
	margaret = new Mirror(50,50,250,50);
	winston = new Mirror(50,100,250,100);
	despoina = new Mirror(50,150,250,150);
	penn = new Mirror(50,200,250,200);
	brian = new Mirror(50,250,250,250);

	//obstacles
	jonathan = new Obstacle (900.0, 125.0, 100.0, 100.0);
	kerwin = new Obstacle (900.0, 475.0, 100.0, 100.0);
	darren = new Obstacle(375.0, 275.0, 150.0, 150.0);
	
	//laser
	L = new Laser(0.0,350.0);
	
	//for the laser path
	elainex = new ArrayList<Double>();
	elainey = new ArrayList<Double>();
	
	//buttons for mirror selection
	margaretButton = new JButton("M1");
	margaretButton.setBounds(50,100,30,30);
	margaretButton.addActionListener(new margaretListener());
	add(margaretButton);
	
	winstonButton = new JButton("M2");
	winstonButton.setBounds(100,100,30,30);
	winstonButton.addActionListener(new winstonListener());
	add(winstonButton);
	
	despoinaButton = new JButton("M3");
	despoinaButton.setBounds(150,100,30,30);
	despoinaButton.addActionListener(new despoinaListener());
	add(despoinaButton);
	
	pennButton = new JButton("M4");
	despoinaButton.setBounds(200,100,30,30);
	pennButton.addActionListener(new pennListener());
	add(pennButton);
	
	brianButton = new JButton("M5");
	brianButton.setBounds(250,100,30,30);
	brianButton.addActionListener(new brianListener());
	add(brianButton);

	//reset button
	resetButton = new JButton("Clear Laser");
	resetButton.setBounds(150,670,30,30);
	resetButton.addActionListener(new resetListener());
	add(resetButton);

	margaret.color = Color.GREEN;
	stateOfSelection = "margaret";
    }
    
    
    public void paintComponent(Graphics g){
	super.paintComponent(g);
	Graphics2D g2d=(Graphics2D)g;
	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			     RenderingHints.VALUE_ANTIALIAS_ON);
	
	//mirrors
	//margaret
	g2d.setStroke(new BasicStroke(1));
	g2d.setColor(margaret.color);
	Line2D.Double margLine = new Line2D.Double(margaret.x1,margaret.y1,margaret.x2,margaret.y2);
	g2d.draw(margLine);
	
	//winston
	g2d.setStroke(new BasicStroke(1));
	g2d.setColor(winston.color);
	Line2D.Double winLine = new Line2D.Double(winston.x1,winston.y1,winston.x2,winston.y2);
	g2d.draw(winLine);
	
	//despoina
	g2d.setStroke(new BasicStroke(1));
	g2d.setColor(despoina.color);
	Line2D.Double desLine = new Line2D.Double(despoina.x1,despoina.y1,despoina.x2,despoina.y2);
	g2d.draw(desLine);
	
	//penn
	g2d.setStroke(new BasicStroke(1));
	g2d.setColor(penn.color);
	Line2D.Double pennLine = new Line2D.Double(penn.x1, penn.y1,penn.x2, penn.y2);
	g2d.draw(pennLine);
	
	//brian
	g2d.setStroke(new BasicStroke(1));
	g2d.setColor(brian.color);
	Line2D.Double briLine = new Line2D.Double(brian.x1, brian.y1, brian.x2, brian.y2);
	g2d.draw(briLine);
		
	
	//midline
	g2d.setStroke(new BasicStroke(1));
	g.setColor(Color.BLACK);
	g2d.drawLine(0,350,1300,350);
	
	//Obstacles
	//jonathan
	g2d.setColor(Color.GRAY);
	Rectangle2D.Double jonOb = new Rectangle2D.Double(jonathan.x,jonathan.y,jonathan.w,jonathan.h);
	g2d.fill(jonOb);
	
	//kerwin
	Rectangle2D.Double kerwinOb = new Rectangle2D.Double(kerwin.x,kerwin.y,kerwin.w,kerwin.h);
	g2d.fill(kerwinOb);
	
	//darren
	Rectangle2D.Double darrenOb = new Rectangle2D.Double(darren.x,darren.y,darren.w,darren.h);
	g2d.fill(darrenOb);
		
	//target
	g.setColor(Color.GREEN);
	g2d.fillOval(1292, 342, 16, 16);
		
	//laser source
	g.setColor(Color.RED);
	g2d.fillOval(-8, 342, 16, 16);

	//laser photon
	g.setColor(Color.RED);
	Ellipse2D.Double laser = new Ellipse2D.Double(L.x-3,L.y-3,6,6);
	g2d.draw(laser);
	if (!L.on){
	    for (int i = 0; i < elainex.size(); i++){
		g2d.fillOval(elainex.get(i).intValue(), elainey.get(i).intValue(), 2, 2);
	    }
	}
    }

    public void actionPerformed(ActionEvent e){
	//rotations
	margaret.rotate(margaret.thetaChange);
	winston.rotate(winston.thetaChange);
	despoina.rotate(despoina.thetaChange);
	penn.rotate(penn.thetaChange);
	brian.rotate(brian.thetaChange);
	
	//translations
	margaret.translate(margaret.xa,margaret.ya);
	winston.translate(winston.xa,winston.ya);
	despoina.translate(despoina.xa, despoina.ya);
	penn.translate(penn.xa, penn.ya);
	brian.translate(brian.xa, brian.ya);
	
	if (L.x < 0 || L.x >= 1300 || L.y <= 0 || L.y >= 700){
	    L.on = false;
	}
	if (kerwin.contains(L.x, L.y) || jonathan.contains(L.x, L.y) || darren.contains(L.x, L.y)){
	    L.on = false;
	}
	if(L.on){
	    L.propagate();
	    if (L.collideM(margaret)){
	    	L.reflect(margaret);
	        winston.canReflect = true;
		despoina.canReflect = true;
		penn.canReflect = true;
		brian.canReflect = true;
	    }
	    if (L.collideM(winston)){
	    	L.reflect(winston);
		margaret.canReflect = true;
		despoina.canReflect = true;
		penn.canReflect = true;
		brian.canReflect = true;
	    }
	    if (L.collideM(despoina)){
	    	L.reflect(despoina);
		margaret.canReflect = true;
		winston.canReflect = true;
		penn.canReflect = true;
		brian.canReflect = true;

	    }
	    if (L.collideM(penn)){
	    	L.reflect(penn);
		margaret.canReflect = true;
		winston.canReflect = true;
		despoina.canReflect = true;
		brian.canReflect = true;
	    }
	    if (L.collideM(brian)){
	    	L.reflect(brian);
		margaret.canReflect = true;
		winston.canReflect = true;
		despoina.canReflect = true;
		penn.canReflect = true;
	    }
	    elainex.add(L.x);
	    elainey.add(L.y);
	}
	repaint(); //calls paintComponent
    }
    
    public void keyPressed(KeyEvent e){
	int c = e.getKeyCode();
	//System.out.println(stateOfSelection);
	if (!L.on){
	    if (stateOfSelection.equals("margaret")){
		if (c == KeyEvent.VK_LEFT){
		    margaret.xa = -0.1;
		    margaret.ya = 0;
		    //margaret.thetaChange = 0;
		}
		if (c == KeyEvent.VK_RIGHT){
		    margaret.xa = 0.1;
		    margaret.ya = 0;
		}
		if (c == KeyEvent.VK_UP){
		    margaret.xa = 0;
		    margaret.ya = -0.1;
		}
		if (c == KeyEvent.VK_DOWN){
		    margaret.xa = 0;
		    margaret.ya = 0.1;
		}
	    }
	    
	    else if (stateOfSelection.equals("winston")){
		if (c == KeyEvent.VK_LEFT){
		    winston.xa = -0.1;
		    winston.ya = 0;
		    //winston.thetaChange = 0;
		}
		if (c == KeyEvent.VK_RIGHT){
		    winston.xa = 0.1;
		    winston.ya = 0;
		}
		if (c == KeyEvent.VK_UP){
		    winston.xa = 0;
		    winston.ya = -0.1;
		}
		if (c == KeyEvent.VK_DOWN){
		    winston.xa = 0;
		    winston.ya = 0.1;
		}
	    }
	    else if (stateOfSelection.equals("despoina")){
		if (c == KeyEvent.VK_LEFT){
		    despoina.xa = -0.1;
		    despoina.ya = 0;
		    //despoina.thetaChange = 0;
		}
		if (c == KeyEvent.VK_RIGHT){
		    despoina.xa = 0.1;
		    despoina.ya = 0;
		}
		if (c == KeyEvent.VK_UP){
		    despoina.xa = 0;
		    despoina.ya = -0.1;
		}
		if (c == KeyEvent.VK_DOWN){
		    despoina.xa = 0;
		    despoina.ya = 0.1;
		}
	    }
	    else if (stateOfSelection.equals("penn")){
		if (c == KeyEvent.VK_LEFT){
		    penn.xa = -0.1;
		    penn.ya = 0;
		    //penn.thetaChange = 0;
		}
		if (c == KeyEvent.VK_RIGHT){
		    penn.xa = 0.1;
		    penn.ya = 0;
		}
		if (c == KeyEvent.VK_UP){
		    penn.xa = 0;
		    penn.ya = -0.1;
		}
		if (c == KeyEvent.VK_DOWN){
		    penn.xa = 0;
		    penn.ya = 0.1;
		}
	    }
	    else if (stateOfSelection.equals("brian")){
		if (c == KeyEvent.VK_LEFT){
		    brian.xa = -0.1;
		    brian.ya = 0;
		    //brian.thetaChange = 0;
		}
		if (c == KeyEvent.VK_RIGHT){
		    brian.xa = 0.1;
		    brian.ya = 0;
		}
		if (c == KeyEvent.VK_UP){
		    brian.xa = 0;
		    brian.ya = -0.1;
		}
		if (c == KeyEvent.VK_DOWN){
		    brian.xa = 0;
		    brian.ya = 0.1;
		}
	    }
	    //rotation keys
	    if (c == KeyEvent.VK_M || c == KeyEvent.VK_1){
		margaret.thetaChange = 0.0005;
		//System.out.println(Math.toDegrees(margaret.theta));
	    }
	    if (c == KeyEvent.VK_W || c == KeyEvent.VK_2){
		winston.thetaChange = 0.0005;
		//winston.xa=0;
		//winston.ya=0;
	    }
	    if (c == KeyEvent.VK_D || c == KeyEvent.VK_3){
		despoina.thetaChange = 0.0005;
		//despoina.xa=0;
		//despoina.ya=0;
	    }
	    if (c == KeyEvent.VK_P || c == KeyEvent.VK_4){
		penn.thetaChange = 0.0005;
		//penn.xa=0;
		//penn.ya=0;
	    }
	    if (c == KeyEvent.VK_B || c == KeyEvent.VK_5){
		brian.thetaChange = 0.0005;
		//brian.xa=0;
		//brian.ya=0;
	    }
	    //turning on the laser
	    if (c == KeyEvent.VK_L){
		L.on = true;
	    }
	}
    }
    
    //dummy function to satisfy the interface
    public void keyTyped(KeyEvent e){
    }

    //when no key is held, nothing should move
    public void keyReleased(KeyEvent e){
	//no rotation
	margaret.thetaChange=0;
	winston.thetaChange=0;
	despoina.thetaChange=0;
	penn.thetaChange=0;
	brian.thetaChange=0;
	
	//no translation
	margaret.xa=0;
	margaret.ya=0;
	winston.xa=0;
	winston.ya=0;
	despoina.xa=0;
	despoina.ya=0;
	penn.xa=0;
	penn.ya=0;
	brian.xa=0;
	brian.ya=0;
    }

    public class margaretListener implements ActionListener{   	
    	public void actionPerformed(ActionEvent e){
	    margaret.color=Color.GREEN;
	    winston.color=Color.BLUE;
	    despoina.color=Color.BLUE;
	    penn.color=Color.BLUE;
	    brian.color=Color.BLUE;
    		
	    stateOfSelection="margaret";
    	}
    }
    
    public class winstonListener implements ActionListener{   	
    	public void actionPerformed(ActionEvent e){
	    winston.color=Color.GREEN;
	    margaret.color=Color.BLUE;
	    despoina.color=Color.BLUE;
	    penn.color=Color.BLUE;
	    brian.color=Color.BLUE;
    		
	    stateOfSelection="winston";    		
    	}
    }
    
    public class despoinaListener implements ActionListener{   	
    	public void actionPerformed(ActionEvent e){
	    despoina.color=Color.GREEN;
	    margaret.color=Color.BLUE;
	    winston.color=Color.BLUE;
	    penn.color=Color.BLUE;
	    brian.color=Color.BLUE;
    		
	    stateOfSelection="despoina";   		
    	}
    }
    
    public class pennListener implements ActionListener{   	
    	public void actionPerformed(ActionEvent e){
	    penn.color=Color.GREEN;
	    margaret.color=Color.BLUE;
	    winston.color=Color.BLUE;
	    despoina.color=Color.BLUE;
	    brian.color=Color.BLUE;
    		
	    stateOfSelection="penn";   		
    	}
    }
    
    public class brianListener implements ActionListener{   	
    	public void actionPerformed(ActionEvent e){
	    brian.color=Color.GREEN;
	    margaret.color=Color.BLUE;
	    winston.color=Color.BLUE;
	    despoina.color=Color.BLUE;
	    penn.color=Color.BLUE;
    		
	    stateOfSelection="brian";   		
    	}
    }

    public class resetListener implements ActionListener{   	
    	public void actionPerformed(ActionEvent e){
	    L.x = 0.0;
	    L.y = 350.0;
	    elainex.clear();
	    elainey.clear();
	    L.theta=0;
    	}
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