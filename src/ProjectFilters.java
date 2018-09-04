/*
 *
 * Author: Arteaga Hernandez Alejandro
 * Version: 1.0
 *
 * This software is released under the terms of the GNU LGPL license. 
 * See http://www.gnu.org/licenses/lgpl.html for more information.
 * 
 */
import java.io.*;
import java.util.*;


import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ChoiceDialog;


/**
 * Clase de interfaz grafica, para un manejador de filtros de imagenes
 */
public class ProjectFilters extends Application {

    /**
     * Metodo de inicio de aplicacion
     * @param stage Escenario principal de la aplicacion
     */
    @Override
    public void start (Stage stage) {
	BorderPane bpPrimary = new BorderPane();
	GridPane imgViewers = new GridPane();
	GridPane controls = new GridPane();
	ImageView viewer1 = new ImageView();
	ImageView viewer2 = new ImageView();
	Button btnBrowse = new Button("Buscar imagen:");
	Button btnSave = new Button("Salvar imagen:");
	Button btnGenerate = new Button("Generar: ");
	ComboBox<String> filterBox = new ComboBox<>();
	Slider sldBright = new Slider(-255, 255, 0);
	Slider sldRed = new Slider(0, 255, 255);
	Slider sldGreen = new Slider(0, 255, 255);
	Slider sldBlue = new Slider(0, 255, 255);
	Slider sldWidth = new Slider(1, 100, 1);
	Slider sldHeight = new Slider(1, 100, 1);
	Label lBright = new Label("Brightness:");
	Label lRed = new Label("Red:");
	Label lGreen = new Label("Green:");
	Label lBlue = new Label("Blue:");
	Label lWidth = new Label("Width");
	Label lHeight = new Label("Height");
	Label lBrval = new Label("0");
	Label lRval = new Label("255");
	Label lGval = new Label("255");
	Label lBval = new Label("255");
	Label lWval = new Label("1");
	Label lHval = new Label("1");
	ToggleGroup group = new ToggleGroup();
	RadioButton rb1 = new RadioButton("Upper Left");
	RadioButton rb2 = new RadioButton("Upper Right");
	RadioButton rb3 = new RadioButton("Lower Left");
	RadioButton rb4 = new RadioButton("Lower Right");
	FilterManager manager = new FilterManager();
	FileChooser fileChooser = new FileChooser();
	FileChooser.ExtensionFilter imgExt = 
	    new FileChooser.ExtensionFilter("Image Files", "*.bmp",
					    "*.jpg", "*.png");
	FileChooser.ExtensionFilter htmlExt = 
	    new FileChooser.ExtensionFilter("HTML File", "*.html");
	FileChooser.ExtensionFilter txtExt = 
	    new FileChooser.ExtensionFilter("Text File", "*.txt");
	
	rb1.setToggleGroup(group);
	rb2.setToggleGroup(group);
	rb3.setToggleGroup(group);
	rb4.setToggleGroup(group);
	rb1.setVisible(false);
	rb2.setVisible(false);
	rb3.setVisible(false);
	rb4.setVisible(false);
	sldBright.setShowTickMarks(true);
	sldBright.setVisible(false);
	sldRed.setShowTickMarks(true);
	sldRed.setVisible(false);
	sldGreen.setShowTickMarks(true);
	sldGreen.setVisible(false);
	sldBlue.setShowTickMarks(true);
	sldBlue.setVisible(false);
	sldWidth.setShowTickMarks(true);
	sldWidth.setVisible(false);
	sldHeight.setShowTickMarks(true);
	sldHeight.setVisible(false);
	lWidth.setVisible(false);
	lHeight.setVisible(false);
	lBright.setVisible(false);
	lRed.setVisible(false);
	lGreen.setVisible(false);
	lBlue.setVisible(false);
	lBrval.setVisible(false);
	lRval.setVisible(false);
	lGval.setVisible(false);
	lBval.setVisible(false);
	lWval.setVisible(false);
	lHval.setVisible(false);
	btnGenerate.setVisible(false);
	filterBox.getItems().addAll("GreyScaleD3", "GreyScaleHumanEye",
				    "GreyScaleWithRed", "GreyScaleWithGreen",
				    "GreyScaleWithBlue", "GreyScaleRG",
				    "GreyScaleRB", "GreyScaleGB", "Brightness",
				    "Negative", "High Contrast",
				    "PhotoMosaic");
	viewer1.setEffect(new DropShadow(40, Color.BLACK));
	viewer1.setPreserveRatio(true);
	viewer1.setFitWidth(500);
	viewer1.setFitHeight(400);
	viewer2.setEffect(new DropShadow(40, Color.BLACK));
	viewer2.setPreserveRatio(true);
	viewer2.setFitWidth(500);
	viewer2.setFitHeight(400);
	imgViewers.setHgap(10);
	imgViewers.setVgap(10);
	imgViewers.setPadding(new Insets(10));
	imgViewers.add(viewer1, 0 , 0);
	imgViewers.add(viewer2, 1 , 0);
	imgViewers.setAlignment(Pos.CENTER);
	controls.setHgap(10);
	controls.setVgap(10);
	controls.setPadding(new Insets(10));
	controls.add(btnBrowse, 0 , 0);
	controls.add(btnSave, 1 , 0);
	controls.add(filterBox, 2 , 0);
	controls.add(lBright, 3, 0);
	controls.add(sldBright, 4, 0);
	controls.add(lBrval, 5, 0);
	controls.add(lRed, 3, 0);
	controls.add(sldRed, 4, 0);
	controls.add(lRval, 5, 0);
	controls.add(lGreen, 3, 1);
	controls.add(sldGreen, 4, 1);
	controls.add(lGval, 5, 1);
	controls.add(lBlue, 3, 2);
	controls.add(sldBlue, 4, 2);
	controls.add(lBval, 5, 2);
	controls.add(lWidth, 3, 0);
	controls.add(sldWidth, 4, 0);
	controls.add(lWval, 5, 0);
	controls.add(lHeight, 3, 1);
	controls.add(sldHeight, 4, 1);
	controls.add(lHval, 5, 1);
	controls.add(btnGenerate, 3, 2);
	controls.add(rb1, 6, 0);
	controls.add(rb2, 6, 1);
	controls.add(rb3, 6, 2);
	controls.add(rb4, 6, 3);
	controls.setAlignment(Pos.CENTER);
	bpPrimary.setCenter(imgViewers);
	bpPrimary.setBottom(controls);

	btnBrowse.setOnAction((event) -> {
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().add(imgExt);
		File file = fileChooser.showOpenDialog(stage);
		if (file != null) {
		    viewer1.setImage(new Image(file.toURI().toString()));
		    viewer2.setImage(new Image(file.toURI().toString()));
		}
	    });

	btnSave.setOnAction((event) -> {
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().add(imgExt);
		File file = fileChooser.showSaveDialog(stage);
		if (file != null) {
		    try {
			ImageIO.write(SwingFXUtils.
				      fromFXImage(viewer2.getImage(),
						  null), "png", file);
		    }
		    catch (IOException ioe) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("IOException - Java");
			alert.setContentText("Try Again");
			alert.showAndWait();
		    }
		}
	    });

	btnGenerate.setOnAction((event) -> {
		File file = null;
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().add(htmlExt);
		file = fileChooser.showSaveDialog(stage);
		fileChooser.getExtensionFilters().clear();
		fileChooser.getExtensionFilters().add(txtExt);
		File file2 = fileChooser.showOpenDialog(stage);
		if (file != null && file2 != null) {
		    try {
			BufferedWriter writer =
			    new BufferedWriter(new FileWriter(file));
			PhotoMosaic pm = new PhotoMosaic();
			pm.buildData(file2);
			int w = (int)sldWidth.getValue();
			int h = (int)sldHeight.getValue();
			writer.write(pm.generate(viewer1.getImage(),
						 w, h));
			writer.close();
			Alert alertC = new Alert(AlertType.INFORMATION);
			alertC.setTitle("PhotoMosaic");
			alertC.setHeaderText("Process Complete");
			alertC.setContentText("Check your file: " +
					     file.getAbsolutePath());
			alertC.showAndWait();
		    }
		    catch (IOException ioe) {
			Alert alert3 = new Alert(AlertType.ERROR);
			alert3.setTitle("Error");
			alert3.setHeaderText("IOException - Java");
			alert3.setContentText("Try Again");
			alert3.showAndWait();
		    }
		}
	    });

	filterBox.setOnAction((event) -> {
		if (viewer1.getImage() == null)
		    return;
		File file = null;
		Image img = viewer1.getImage();
		sldBright.setVisible(false);
		sldRed.setVisible(false);
		sldGreen.setVisible(false);
		sldBlue.setVisible(false);
		sldWidth.setVisible(false);
		sldHeight.setVisible(false);
		lBright.setVisible(false);
		lRed.setVisible(false);
		lGreen.setVisible(false);
		lBlue.setVisible(false);
		lWidth.setVisible(false);
		lHeight.setVisible(false);
		lBrval.setVisible(false);
		lRval.setVisible(false);
		lGval.setVisible(false);
		lBval.setVisible(false);
		lWval.setVisible(false);
		lHval.setVisible(false);
		rb1.setVisible(false);
		rb2.setVisible(false);
		rb3.setVisible(false);
		rb4.setVisible(false);
		btnGenerate.setVisible(false);
		String selection =
		    filterBox.getSelectionModel().getSelectedItem();
		switch (selection) {
		case "GreyScaleD3":
		    viewer2.setImage(manager.execute(img, new GreyScaleD3()));
		    break;
		case "GreyScaleHumanEye":
		    viewer2.setImage(manager.execute(img,
						     new GreyScaleHumanEye()));
		    break;
		case "GreyScaleWithRed":
		    viewer2.setImage(manager.execute(img,
						     new GreyScaleWithRed()));
		    break;
		case "GreyScaleWithGreen":
		    viewer2.setImage(manager.execute(img,
						     new GreyScaleWithGreen()));
		    break;
		case "GreyScaleWithBlue":
		    viewer2.setImage(manager.execute(img,
						     new GreyScaleWithBlue()));
		    break;
		case "GreyScaleRG":
		    viewer2.setImage(manager.execute(img, new GreyScaleRG()));
		    break;
		case "GreyScaleRB":
		    viewer2.setImage(manager.execute(img, new GreyScaleRB()));
		    break;
		case "GreyScaleGB":
		    viewer2.setImage(manager.execute(img, new GreyScaleGB()));
		    break;

		case "Brightness":
		    lBright.setVisible(true);
		    lBrval.setVisible(true);
		    sldBright.setVisible(true);
		    sldBright.setValue(0);
		    viewer2.setImage(manager.execute(viewer1.getImage(), 0,
						     new Brightness()));
		    break;

		case "Negative":
		    viewer2.setImage(manager.execute(img, new Negative()));
		    break;
		case "High Contrast":
		    viewer2.setImage(manager.execute(img, new HighContrast()));
		    break;

		case "PhotoMosaic":
		    viewer2.setImage(viewer1.getImage());
		    sldWidth.setValue(1);
		    sldHeight.setValue(1);
		    sldWidth.setVisible(true);
		    sldHeight.setVisible(true);
		    lWidth.setVisible(true);
		    lHeight.setVisible(true);
		    lWval.setVisible(true);
		    lHval.setVisible(true);
		    btnGenerate.setVisible(true);
		    break;
		default:
		    System.out.println("?????");
		}
	    });

	sldBright.valueProperty().addListener(new ChangeListener<Number>() {
		
		public void changed(ObservableValue<? extends Number> ov,
				    Number oVal, Number nVal) {
		    lBrval.setText(String.format("%d", nVal.intValue()));
		    viewer2.setImage(manager.execute(viewer1.getImage(),
						     nVal.intValue(),
						     new Brightness()));
		}
	    });

	sldRed.valueProperty().addListener(new ChangeListener<Number>() {
		
		public void changed(ObservableValue<? extends Number> ov,
				    Number oVal, Number nVal) {
		    lRval.setText(String.format("%d", nVal.intValue()));
		    int r = nVal.intValue();
		    int g = (int)sldGreen.getValue();
		    int b = (int)sldBlue.getValue();
		    int x = (255 << 24) | (r << 16) | (g << 8) | b;
		    switch (filterBox.getSelectionModel().getSelectedItem()) {
		    case "WarholScale":
			viewer2.setImage(manager.execute(viewer1.getImage(), x,
							 new WarholScale()));
			break;
		    case "CustomColorScale":
			viewer2.setImage(new CustomColorScale(0x000000,
							      x).
					 process(viewer1.getImage()));
			break;
		    case "Warhol X4":
			int posX = 0;
			int posY = 0;
			if (rb2.isSelected() || rb4.isSelected())
			    posX = (int)(viewer2.getImage().getWidth() / 2);
			if (rb3.isSelected() || rb4.isSelected())
			    posY = (int)(viewer2.getImage().getHeight() / 2);
			viewer2.setImage(new WarholScale().
					 executeIn(viewer1.getImage(),
						   viewer2.getImage(),
						   x, posX, posY));
			break;
		    default:
			System.out.println("!!!!!");
		    }
		}
	    });

	sldGreen.valueProperty().addListener(new ChangeListener<Number>() {
		
		public void changed(ObservableValue<? extends Number> ov,
				    Number oVal, Number nVal) {
		    lGval.setText(String.format("%d", nVal.intValue()));
		    int r = (int)sldRed.getValue();
		    int g = nVal.intValue();
		    int b = (int)sldBlue.getValue();
		    int x = (255 << 24) | (r << 16) | (g << 8) | b;
		    switch (filterBox.getSelectionModel().getSelectedItem()) {
		    case "WarholScale":
			viewer2.setImage(manager.execute(viewer1.getImage(), x,
							 new WarholScale()));
			break;
		    case "CustomColorScale":
			viewer2.setImage(new CustomColorScale(0x000000,
							      x).
					 process(viewer1.getImage()));
			break;
		    case "Warhol X4":
			int posX = 0;
			int posY = 0;
			if (rb2.isSelected() || rb4.isSelected())
			    posX = (int)(viewer2.getImage().getWidth() / 2);
			if (rb3.isSelected() || rb4.isSelected())
			    posY = (int)(viewer2.getImage().getHeight() / 2);
			viewer2.setImage(new WarholScale().
					 executeIn(viewer1.getImage(),
						   viewer2.getImage(),
						   x, posX, posY));
			break;
		    default:
			System.out.println("!!!!!");
		    }
		}
	    });

	sldBlue.valueProperty().addListener(new ChangeListener<Number>() {
		
		public void changed(ObservableValue<? extends Number> ov,
				    Number oVal, Number nVal) {
		    lBval.setText(String.format("%d", nVal.intValue()));
		    int r = (int)sldRed.getValue();
		    int g = (int)sldGreen.getValue();
		    int b = nVal.intValue();
		    int x = (255 << 24) | (r << 16) | (g << 8) | b;
		    switch (filterBox.getSelectionModel().getSelectedItem()) {
		    case "WarholScale":
			viewer2.setImage(manager.execute(viewer1.getImage(), x,
							 new WarholScale()));
			break;
		    case "CustomColorScale":
			viewer2.setImage(new CustomColorScale(0x000000,
							      x).
					 process(viewer1.getImage()));
			break;
		    case "Warhol X4":
			int posX = 0;
			int posY = 0;
			if (rb2.isSelected() || rb4.isSelected())
			    posX = (int)(viewer2.getImage().getWidth() / 2);
			if (rb3.isSelected() || rb4.isSelected())
			    posY = (int)(viewer2.getImage().getHeight() / 2);
			viewer2.setImage(new WarholScale().
					 executeIn(viewer1.getImage(),
						   viewer2.getImage(),
						   x, posX, posY));
			break;
		    default:
			System.out.println("!!!!!");
		    }
		}
	    });

	sldWidth.valueProperty().addListener(new ChangeListener<Number>() {
		
		public void changed(ObservableValue<? extends Number> ov,
				    Number oVal, Number nVal) {
		    lWval.setText(String.format("%d", nVal.intValue()));
		    int w = (int)sldWidth.getValue();
		    int h = (int)sldHeight.getValue();
		    viewer2.setImage(new MosaicFilter(w, h).
				     process(viewer1.getImage()));
		}
	    });

	sldHeight.valueProperty().addListener(new ChangeListener<Number>() {
		
		public void changed(ObservableValue<? extends Number> ov,
				    Number oVal, Number nVal) {
		    lHval.setText(String.format("%d", nVal.intValue()));
		    int w = (int)sldWidth.getValue();
		    int h = (int)sldHeight.getValue();
		    viewer2.setImage(new MosaicFilter(w, h).
				     process(viewer1.getImage()));
		}
	    });
	
	stage.setScene(new Scene(bpPrimary));
	stage.setResizable(false);
	stage.setTitle("Proceso Digital de imágenes");
	stage.show();
    } 
}
