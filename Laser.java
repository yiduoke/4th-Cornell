import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Laser{

    int x1, y1;
    double theta;
    boolean on;

    public Laser(int x, int y, Color color) {
	this.x1 = x1;
	this.y1 = y1;
	theta = 0;
	on = false;//should not be on at first wINSTON
    }

    private double distance(int x1, int y1, int x2, int y2){
	return Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
    }

    public boolean intersect(Mirror m){ //abuses triangle inequality theorem
	double d1 = distance(x1, y1, m.x1, m.y1);
	double d2 = distance(x1, y1, m.x2, m.y2);
	return (d1 + d2 >= distance(m.x1,m.y1,m.x2,m.y2)*0.9999 || d1 + d2 <= distance(m.x1,m.y1,m.x2,m.y2)*1.0001);
    }

    public void reflect(Mirror m){
    	if (m.theta==0 || m.theta==Math.PI){on=false;}
    	else {theta = Math.toRadians(360-Math.toDegrees(theta)+2*Math.toDegrees(m.theta));}
	}
}