import javafx.scene.paint.Color;

public class GreyScaleRG implements Filter {
    
    public Color pixel(Color c) {
	double avg = (c.getRed() + c.getGreen()) / 2;
	return Color.color(avg, avg, avg);
    }
}
