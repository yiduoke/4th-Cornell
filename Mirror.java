import java.awt.*;

public class Mirror {
    double midX,midY;
    double x1, y1, x2, y2;
    double xa, ya;
    double theta;
    double thetaChange;
    boolean canReflect;
    Color color;
    //LaserShoot laserShoot;

    public Mirror(int a,int b,int c,int d){
    	x1=a;
    	y1=b;
    	x2=c;
    	y2=d;
    	midX=(x1+x2)/2;
    	midY=(y1+y2)/2;
	xa = 0;
	ya = 0;
	theta = 0;
	thetaChange = 0;
    	canReflect = true;
    	color=Color.BLUE;
    }

    public void rotate(double t) {
	theta += t;
	theta %= 2 * Math.PI;
	x1 = (int) (midX-Math.cos(theta)*75);
	x2 = (int) (midX+Math.cos(theta)*75);
	y1 = (int) (midY+Math.sin(theta)*75);
	y2 = (int) (midY-Math.sin(theta)*75);
	}

    public void translate(double xVel, double yVel){
    x1+=xVel;
	x2+=xVel;
	y1+=yVel;
	y2+=yVel;
	midX+=xVel;
	midY+=yVel;
    }

}
