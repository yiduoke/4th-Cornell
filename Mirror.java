import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Mirror {
    double x1,y1,x2,y2,x3,y3,x4,y4;
    int xa;
    int ya;
    double theta=0;
    LaserShoot laserShoot;
    Rectangle rect;

    public Mirror(int a,int b,int c,int d, int e, int f, int g, int h){
	rect= new Rectangle(x2,y2,(x3-x2),(y3-y4));
    }

    public void move() {
	theta+=0.01;
	x1=s(x1,y1)*Math.sin(theta);
	x2=s(x2,y2)*Math.sin(theta);
	x3=s(x3,y3)*Math.sin(theta);
	x4=s(x4,y4)*Math.sin(theta);
	y1=s(x1,y1)*Math.cos(theta);
	y2=s(x2,y2)*Math.cos(theta);
	y3=s(x3,y3)*Math.cos(theta);
	y4=s(x4,y4)*Math.cos(theta);

	//	x+=xa;
	//	y+=ya;
	}
    public double s(double x, double y){///// a point's distance from origin
	return Math.sqrt(x*x+y*y);
    }


    public void myMove(){
	
    }

    public void paint(Graphics2D g) {
	g.rotate(theta, rect.x + rect.width/2, rect.y + rect.height/2);
	g.fill(rect);
    }

}
