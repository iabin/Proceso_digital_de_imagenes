import javafx.scene.paint.Color;

public interface Filter {

    default public Color pixel(Color c) {
	throw new IllegalArgumentException();
    }

    default public int pixel(int color, int value) {
	throw new IllegalArgumentException();
    }
    
}
