import javafx.scene.paint.Color;

public class GreyScaleWithRed implements Filter {
    
    public Color pixel(Color c) {
	return Color.color(c.getRed(), c.getRed(), c.getRed());
    }
}
