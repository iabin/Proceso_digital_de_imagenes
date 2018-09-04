import javafx.scene.paint.Color;

public class GreyScaleWithGreen implements Filter {
    
    public Color pixel(Color c) {
	return Color.color(c.getGreen(), c.getGreen(), c.getGreen());
    }
}
