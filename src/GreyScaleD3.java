import javafx.scene.paint.Color;

public class GreyScaleD3 implements Filter {

    public Color pixel(Color c) {
	double avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
	return Color.color(avg, avg, avg);
    }
}
