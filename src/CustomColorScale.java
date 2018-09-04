import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class CustomColorScale {

    private int[] gradient;

    public CustomColorScale() {
	gradient = new int[256];
    }

    public CustomColorScale(int color1, int color2) {
	gradient = new int[256];
	for (int i = 0; i < 256; i++) {
	    int red = (((color1 >> 16) & 0xff) * (256 - i) +
		       ((color2 >> 16) & 0xff) * i) / 256;
	    int green = (((color1 >> 8) & 0xff) * (256 - i) +
			 ((color2 >> 8) & 0xff) * i) / 256;
	    int blue = ((color1 & 0xff) * (256 - i) +
			(color2 & 0xff) * i) / 256;
	    gradient[i] = ((255 << 24) | (red << 16) | (green << 8) | blue);    
	}
    }

    public Image process(Image img) {
	int width = (int)img.getWidth();
	int height = (int)img.getHeight();
	WritableImage newImg = new WritableImage(width, height);
	PixelReader reader = img.getPixelReader();
	PixelWriter writer = newImg.getPixelWriter();
	for (int i = 0; i < width; i++)
	    for (int j = 0; j < height; j++) {
		int index = (int)(reader.getColor(i, j).getBrightness() * 255);
		writer.setArgb(i, j, gradient[index]);
	    }
	return newImg;
    }
}
