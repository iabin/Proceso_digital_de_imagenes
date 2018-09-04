import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class ImageRGB {

    public void values(Image img) {
	int width2 = (int)img.getWidth();
	int height2 = (int)img.getHeight();
	PixelReader reader = img.getPixelReader();
	int red = 0;
	int green = 0;
	int blue = 0;
	int color = 0;
	int factor = 0;
	for (int i = 0; i < width2; i++)
	    for (int j = 0; j < height2; j++) {
		color = reader.getArgb(i, j);
		red += (color >> 16) & 0xff;
		green += (color >> 8) & 0xff;
		blue += color & 0xff;
		factor++;
	    }
	System.out.printf("R: %d, G: %d, B: %d%n", red / factor, green / factor,
			  blue / factor);
    }
}
