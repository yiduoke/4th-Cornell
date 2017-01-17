import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;

public class Laser{

    double x, y;
    double theta;
    boolean on;

    public Laser(double x, double y) {
    this.x = x;
    this.y = y;
    theta = 0;
    on = false; //should not be on at first wINSTON
    }

    private double distance(double x1, double y1, double x2, double y2){
    return Math.sqrt((y2-y1)*(y2-y1)+(x2-x1)*(x2-x1));
    }

    public boolean collideM(Mirror m){ //abuses triangle inequality theorem
    double d1 = distance(x, y, m.x1(), m.y1());
    double d2 = distance(x, y, m.x2(), m.y2());
    double d3 = distance(m.x1(),m.y1(),m.x2(),m.y2()); //length of mirror
    //return (d1 + d2 >= distance(m.x1,m.y1,m.x2,m.y2)*0.9999 || d1 + d2 <= distance(m.x1,m.y1,m.x2,m.y2)*1.0001);
    return Math.abs(d1 + d2 - d3) < .005; //allows for an error margin because even doubles can't be that precise, adjust based on tests
    }

    public boolean collideO(Obstacle o){
    return o.contains(o.x(), o.y());
    }

    public void reflect(Mirror m){
    if (m.canReflect()){
        theta = 2 * m.theta() - theta;
    }
    theta %= 2 * Math.PI;
    m.setcanReflect(false);
    }
    
    public void propagate(){
    x += 0.1 * Math.cos(theta);
    y -= 0.1 * Math.sin(theta);
    }
    
    public double x(){
        return x;
    }
    
    public double y(){
        return y;
    }
    
    public double theta(){
        return theta;
    }
    
    public boolean on(){
        return on;
    }

    public void setx(double a){x=a;}
    public void sety(double a){y=a;}
    public void setTheta(double a){theta=a;}
    public void seton(boolean a){on=a;}

}
