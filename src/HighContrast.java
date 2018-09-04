import javafx.scene.paint.Color;

public class HighContrast implements Filter {

    public Color pixel(Color c) {
	double avg = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
	return (avg < 0.5) ? Color.BLACK : Color.WHITE;
    }
}
