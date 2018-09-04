import javafx.scene.paint.Color;

public class GreyScaleWithBlue implements Filter {
    
    public Color pixel(Color c) {
	return Color.color(c.getBlue(), c.getBlue(), c.getBlue());
    }
}
