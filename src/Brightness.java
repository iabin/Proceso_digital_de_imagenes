import javafx.scene.paint.Color;

public class Brightness implements Filter {
    
    public int pixel(int color, int value) {
	int alpha = (color >> 24) & 0xff;
	int red = ((color >> 16) & 0xff) + value;
	int green = ((color >> 8) & 0xff) + value;
	int blue = (color & 0xff) + value;
	red = (red > 255) ? 255 : red;
	red = (red < 0) ? 0 : red;
	green = (green > 255) ? 255 : green;
	green = (green < 0) ? 0 : green;
	blue = (blue > 255) ? 255 : blue;
	blue = (blue < 0) ? 0 : blue;
	return ((alpha << 24) | (red << 16) | (green << 8) | blue);
    }
}
