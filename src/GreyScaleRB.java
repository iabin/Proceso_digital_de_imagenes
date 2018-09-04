import javafx.scene.paint.Color;

public class GreyScaleRB implements Filter {
    
    public Color pixel(Color c) {
	double avg = (c.getRed() + c.getBlue()) / 2;
	return Color.color(avg, avg, avg);
    }
}
