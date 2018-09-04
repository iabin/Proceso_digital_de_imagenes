import javafx.scene.paint.Color;

public class GreyScaleGB implements Filter {
    
    public Color pixel(Color c) {
	double avg = (c.getGreen() + c.getBlue()) / 2;
	return Color.color(avg, avg, avg);
    }
}
