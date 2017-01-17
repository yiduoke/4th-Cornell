import java.awt.*;

public class Mirror {
    private double midX,midY;
    private double x1, y1, x2, y2;
    private double xa, ya;
    private double theta;
    private double thetaChange;
    private boolean canReflect;
    private Color color;
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
    x1 = (int) (midX-Math.cos(theta)*100);
    x2 = (int) (midX+Math.cos(theta)*100);
    y1 = (int) (midY+Math.sin(theta)*100);
    y2 = (int) (midY-Math.sin(theta)*100);
    }

    public void translate(double xVel, double yVel){
    x1+=xVel;
    x2+=xVel;
    y1+=yVel;
    y2+=yVel;
    midX+=xVel;
    midY+=yVel;
    }

    public double midX(){
        return midX;
    }
    public double midY(){
        return midY;
    }
    public double x1(){
        return x1;
    }
    public double y1(){
        return y1;
    }
    public double x2(){
        return x2;
    }
    public double y2(){
        return y2;
    }    
    public double theta(){
        return theta;
    }
    public double thetaChange(){
        return thetaChange;
    }
    public double xa(){
        return xa;
    }
    public double ya(){
        return ya;
    }
    public boolean canReflect(){
        return canReflect;
    }
    
    public Color color(){
        return color;
    }
    
    
    //setters
    public void setmidX(double a){
        midX = a;
    }
    
    public void setmidY(double a){
        midY=a;
    }
    
    public void setx1(double a){
        x1 = a;
    }
    
    public void sety1(double a){
        y1 = a;
    }
    
    public void setx2(double a){
        x2=a;
    }
    
    public void sety2(double a){
        y2 = a;
    }
    
    public void setxa(double a){
        xa = a;
    }
    
    public void setya(double a){
        ya = a;
    }
    
    public void setTheta(double a){
        theta=a;
    }
    
    public void setThetaChange(double a){
        thetaChange = a;
    }
    
    public void setcanReflect(boolean a){
        canReflect = a;
    }
    
    public void setcolor(Color a){
        color = a;
    }
}