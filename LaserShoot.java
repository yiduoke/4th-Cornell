import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LaserShoot extends JPanel{
	
	Mirror one=new Mirror(this, 400,300,8,60);
	Mirror two=new Mirror(this,600,400,8,60);

	public void move(){
		one.move();
		two.move();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		one.paint(g2d);
		two.paint(g2d);
	}
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Laser Shoot");
		LaserShoot laserShoot= new LaserShoot();
		frame.add(laserShoot);
		frame.setSize(1000, 600);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		while (true) {
			laserShoot.move();
			laserShoot.repaint();
			Thread.sleep(10);
		}
	}
}
