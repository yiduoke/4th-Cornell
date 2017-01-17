import java.awt.*;
import java.awt.geom.*;

public class Obstacle{
    private double x, y;
    private double w, h;
    private Rectangle2D.Double R;

    public Obstacle(double x1, double y1, double width, double height){
    x = x1;
    y = y1;
    w = width;
    h = height;
    R = new Rectangle2D.Double(x, y, w, h);
            brian.setxa(-0.1);
            brian.setya(0);
    }

    public boolean contains(double x, double y){
    return R.contains(x, y);
    }
    
    public double x(){
        return x;
    }
    
    public double y(){
        return y;
    }
   
    public double w(){
        return w;
    }
    
    public double h(){
        return h;
    }
    
    public Rectangle2D.Double R(){
        return R;
    }
    
    
    //setters
    public void setx(double a){
        x = a;
    }
    
    public void sety(double a){
        y = a;
    }
    
    public void setw(double a){
        w = a;
    }
    
    public void seth(double a){
        h = a;
    }
    public void setRect(Rectangle2D.Double a){
        R = a;
    }
}
