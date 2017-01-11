import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;


public class Mirror {
    double midX,midY;
    int x1,y1,x2,y2;
    int xa;
    int ya;
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

    public void rotate(double t) {
	theta+=t;
	x1=(int) (midX-Math.cos(theta)*75);
	x2=(int) (midX+Math.cos(theta)*75);
	y1=(int) (midY+Math.sin(theta)*75);
	y2=(int) (midY-Math.sin(theta)*75);
	}

    public void translate(int xVel, int yVel){
        x1+=xVel;
	x2+=xVel;
	y1+=yVel;
	y2+=yVel;
	midX+=xVel;
	midY+=yVel;
    }

    public void paint(Graphics2D g) {
	g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

}
