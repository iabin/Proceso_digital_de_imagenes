import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class MosaicFilter {

    private int height;
    private int width;

    public MosaicFilter() {
	height = 0;
	width = 0;
    }

    public MosaicFilter(int height, int width) {
	this.height = height;
	this.width = width;
    }

    public Image process(Image img) {
	int width2 = (int)img.getWidth();
	int height2 = (int)img.getHeight();
	WritableImage newImg = new WritableImage(width2, height2);
	PixelReader reader = img.getPixelReader();
	PixelWriter writer = newImg.getPixelWriter();
	for (int i = 0; i < width2; i += width)
	    for (int j = 0; j < height2; j += height) {
		int red = 0;
		int green = 0;
		int blue = 0;
		int color = 0;
		int factor = 0;
		for (int k = i; k < i + width && k < width2; k++) 
		    for (int l = j; l < j + height && l < height2; l++) { 
			color = reader.getArgb(k, l);
			red += (color >> 16) & 0xff;
			green += (color >> 8) & 0xff;
			blue += color & 0xff;
			factor++;
		    }
		red /= factor;
		green /= factor;
		blue /= factor;
		color = ((255 << 24) | (red << 16) | (green << 8) | blue);
		for (int m = i; m < i + width && m < width2; m++) 
		    for (int n = j; n < j + height && n < height2; n++)
			writer.setArgb(m, n, color);
	    }
	return newImg;
    }

}
