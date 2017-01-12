import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Laser{

    int x, y; //change these to doubles?
    double theta;
    boolean on;

    public Laser(int x, int y) {
	this.x = x;
	this.y = y;
	theta = 0;
	on = false;
    }
    
    private double distance(int x1, int y1, int x2, int y2){ //distance formula
	return Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
    }
    
    public boolean intersect(Mirror m){ //abuses triangle inequality theorem
	double d1 = distance(x, y, m.x1, m.y1);
	double d2 = distance(x, y, m.x2, m.y2);
	return (d1 + d2 >= distance(m.x1,m.y1,m.x2,m.y2)*0.9999 && d1 + d2 <= distance(m.x1,m.y1,m.x2,m.y2)*1.0001);
    }

    public void reflect(Mirror m){ //angle calculations!
    	//if (m.theta == 0 || m.theta == Math.PI){
	//on=false;
	//}
    	//else {
	    theta = Math.toRadians(360-Math.toDegrees(theta)+2*Math.toDegrees(m.theta));
	    if (theta >= 2*Math.PI){
		theta -= 2*Math.PI;
	    }
	    if (theta <= 0){
		theta += 2*Math.PI;
	    }
	    //}
    }
    
    public void propagate(){
	if (x < 0 || x > 1300 || y < 0 || y > 700){ //ends laser at boundaries
	    on=false;
	}
    	x+=0.01;
    	//y-=x*Math.tan(theta);
    }
}
