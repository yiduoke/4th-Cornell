import java.awt.*;
import java.awt.geom.*;

public class Obstacle{
    double x, y;
    double w, h;
    Rectangle2D.Double R;

    public Obstacle(double x1, double y1, double width, double height){
	x = x1;
	y = y1;
	w = width;
	h = height;
	R = new Rectangle2D.Double(x, y, w, h);
    }

    public boolean contains(double x, double y){
	return R.contains(x, y);
    }
}
