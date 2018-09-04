import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class FilterManager {
    
    public Image execute(Image img, Filter filter) {
	int width = (int)img.getWidth();
	int height = (int)img.getHeight();
	WritableImage newImg = new WritableImage(width, height);
	PixelReader reader = img.getPixelReader();
	PixelWriter writer = newImg.getPixelWriter();
	for (int i = 0; i < width; i++)
	    for (int j = 0; j < height; j++) 
		writer.setColor(i, j, filter.pixel(reader.getColor(i, j)));
	return newImg;
    }

    public Image execute(Image img, int value, Filter filter) {
	int width = (int)img.getWidth();
	int height = (int)img.getHeight();
	WritableImage newImg = new WritableImage(width, height);
	PixelReader reader = img.getPixelReader();
	PixelWriter writer = newImg.getPixelWriter();
	for (int i = 0; i < width; i++)
	    for (int j = 0; j < height; j++) 
		writer.setArgb(i, j, filter.pixel(reader.getArgb(i, j), value));
	return newImg;
    }

}
