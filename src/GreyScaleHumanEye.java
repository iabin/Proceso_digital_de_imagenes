import javafx.scene.paint.Color;

public class GreyScaleHumanEye implements Filter {
    
    public Color pixel(Color c) {
	double avg = (c.getRed() * 0.3 + c.getGreen() * 0.59 +
		      c.getBlue() * 0.11);
	return Color.color(avg, avg, avg);
    }
}
