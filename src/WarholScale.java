import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class WarholScale implements Filter {
    
    public int pixel(int color, int value) {
	return color & value;
    }

    public Image getFourInOne(Image img) {
	int width = (int)img.getWidth();
	int height = (int)img.getHeight();
	WritableImage newImg = new WritableImage(width * 2, height * 2);
	PixelReader reader = img.getPixelReader();
	PixelWriter writer = newImg.getPixelWriter();
	for (int i = 0; i < width; i++)
	    for (int j = 0; j < height; j++) {
		writer.setColor(i, j, reader.getColor(i, j));
		writer.setColor(i + width, j, reader.getColor(i, j));
		writer.setColor(i, j + height, reader.getColor(i, j));
		writer.setColor(i + width, j + height, reader.getColor(i, j));
	    }
	return newImg;
    }

    public Image executeIn(Image img1, Image img2, int value, int x,
				  int y) {
	int width = (int)img2.getWidth();
	int height = (int)img2.getHeight();
	WritableImage newImg = new WritableImage(width, height);
	PixelReader reader1 = img1.getPixelReader();
	PixelReader reader2 = img2.getPixelReader();
	PixelWriter writer = newImg.getPixelWriter();
	for (int i = 0; i < width; i++)
	    for (int j = 0; j < height; j++)
		if (i >= x && i < x + width / 2 && j >= y && j < y + height / 2)
		    writer.setArgb(i, j, this.pixel(reader1.getArgb(i - x,
								    j - y),
						    value));
		else
		    writer.setArgb(i, j, reader2.getArgb(i, j));
	return newImg;
    } 
}
