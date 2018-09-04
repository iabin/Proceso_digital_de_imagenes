import javafx.scene.paint.Color;

public class Negative implements Filter {

    public Color pixel(Color c) {
	return Color.color(1 - c.getRed(), 1 - c.getGreen(), 1 - c.getBlue());
    }
}
