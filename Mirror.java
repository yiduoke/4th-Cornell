import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Mirror {
int x;
int y;
int xa;
int ya;
double theta=0;
LaserShoot laserShoot;
Rectangle rect;

public Mirror(LaserShoot a, int x, int y, int w, int l){
	laserShoot=a;
	rect= new Rectangle(x,y,w,l);
	}

void move() {
	theta +=0.01;
//	x+=xa;
//	y+=ya;
}

public void paint(Graphics2D g) {
	g.rotate(theta, rect.x + rect.width/2, rect.y + rect.height/2);
	g.fill(rect);
}

}
