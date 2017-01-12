import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Line{
    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private double theta;
    private Color color;
    
    public Line(double x1, double y1, double x2, double y2, Color color) {
	this.x1 = x1;
	this.y1 = y1;
	this.x2 = x2;
	this.y2 = y2;
	if (x1==x2){
	    if (y1>=y2){
		theta = Math.PI/2;
	    }
	    else{
		theta = Math.PI*3/2;
	    }
	}
	else{
	    this.theta = Math.atan((y2-y1)/(x2-x1));
	}
	this.color = color;
    }
	
    public double getx1(){
	return x1;
    }
	
    public double gety1(){
	return y1;
    }
	
    public double getx2(){
	return x2;
    }
	
    public double gety2(){
	return y2;
    }
	
    public double gettheta(){
	return theta;
    }
	
    public void setx1(double n){
	x1 = n;
    }

    public void sety1(double n){
	y1 = n;
    }
	
    public void setx2(double n){
	x2 = n;
    }
	
    public void sety2(double n){
	y2 = n;
    }
	
    public void rotate(double t){
	theta += Math.toRadians(t);
	double d = Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
	double cx = (x1+x2)/2; //midpt x
	double cy = (y1+y2)/2; //midpt y
	x1 = cx + d/2*Math.cos(theta);
	y1 = cy + d/2*Math.sin(theta);
	x2 = cx - d/2*Math.cos(theta);
	y2 = cy - d/2*Math.sin(theta);
    }

}
