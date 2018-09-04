import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;

public class PhotoMosaic {

    private class ImageData {

	private int red;
	private int green;
	private int blue;
	private String imageName;
	private boolean used;

	private ImageData(int red, int green, int blue, String imageName) {
	    this.red = red;
	    this.green = green;
	    this.blue = blue;
	    this.imageName = imageName;
	    used = false;
	}
    }

    private LinkedList<ImageData> imgData;
    
    public PhotoMosaic() {
	imgData = new LinkedList<>();
    }

    public void buildData(File dataFile) {
	imgData = new LinkedList<>();
	try{
	    BufferedReader br = new BufferedReader(new FileReader(dataFile));
	    String line = "";
	    while ((line = br.readLine()) != null) {
		String[] values = line.split("\\s+");
		imgData.add(new ImageData(Integer.parseInt(values[0]),
					  Integer.parseInt(values[1]),
					  Integer.parseInt(values[2]),
					  values[3]));
	    }
	    br.close();
	}
	catch (IOException ioe) {
	    System.err.println("IO Error, image datafile");
	}
    }

    private double riemersmaDiff(ImageData imd, int red, int green, int blue) {
	int rFactor = (imd.red + red) / 2;
	int rDiff = imd.red - red;
	int gDiff = imd.green - green;
	int bDiff = imd.blue - blue;
	rDiff = (2 + rFactor / 256) * rDiff * rDiff;
	gDiff = 4 * gDiff * gDiff;
	bDiff = (2 + (255 - rFactor) / 256) * bDiff * bDiff;
	return Math.sqrt(rDiff + gDiff + bDiff);
    }
    
    private String searchImage(int red, int green, int blue) {
	double minDiff = Double.POSITIVE_INFINITY;
	String imageFile = "";
	ImageData aux = null;
	for (ImageData imd : imgData) {
	    if (!imd.used) {
		double temp = riemersmaDiff(imd, red, green, blue);
		if (temp < minDiff) {
		    minDiff = temp;
		    imageFile = imd.imageName;
		    aux = imd;
		}
	    }
	}
	aux.used = true;
	return imageFile;
    }

    public String generate(Image img, int cellWidth, int cellHeight) {
	Image mosaic = new MosaicFilter(cellWidth, cellHeight).process(img);
	PixelReader readerMosaic = mosaic.getPixelReader();
	StringBuilder htmlCode =
	    new StringBuilder(String.format("<!DOCTYPE html>%n" +
					    "<html>%n" +
					    "<head>%n" +
					    "<style>%n" +
					    "img {%n" +
					    "    width: %dpx;%n" +
					    "    height: %dpx;%n" +
					    "    position:absolute;%n" +
					    "}%n" +
					    "</style>%n" +
					    "</head>%n" +
					    "<body>%n", cellWidth, cellHeight));
	for (int i = 0; i < mosaic.getWidth(); i += cellWidth)
	    for (int j = 0; j < mosaic.getHeight(); j += cellHeight) {
		int color = readerMosaic.getArgb(i, j);
		int red = (color >> 16) & 0xff;
		int green = (color >> 8) & 0xff;
		int blue = color & 0xff;
		htmlCode.append(String.format("<img style=\"top:%dpx;" +
					      "left:%dpx;\" src=\"%s\"/>%n",
					      j, i,
					      searchImage(red, green, blue)));
	    }
	htmlCode.append(String.format("</body>%n" +
				      "</html>"));
	return htmlCode.toString();
    }
}
