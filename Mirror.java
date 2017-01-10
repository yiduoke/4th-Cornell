import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Mirror {
	double midX,midY;
    int x1,y1,x2,y2;
    double xa;
    double ya;
    double theta=0;
    double thetaChange;
    LaserShoot laserShoot;

    public Mirror(int a,int b,int c,int d){
    	x1=a;
    	y2=b;
    	x2=c;
    	y2=d;
    	midX=(x1+x2)/2;
    	midY=(y1+y2)/2;
    }

    public void move(double t, double xa, double ya) {
	theta+=t;
	x1=(int) (midX-Math.cos(theta)*50);
	x2=(int) (midX+Math.cos(theta)*50);
	y1=(int) (midY+Math.sin(theta)*50);
	y2=(int) (midY-Math.sin(theta)*50);
	x1+=xa;
	x2+=xa;
	y1+=ya;
	y2+=ya;
	}


    public void paint(Graphics2D g) {
	g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

}
