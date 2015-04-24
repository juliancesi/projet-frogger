package graphic.beans;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.shape.Rectangle;


public class RectangleTile extends Rectangle {

	@FXML
	private Rectangle rectangle;
	
	public RectangleTile() {
		super();
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("RectangleTile.fxml"));
		fxmlloader.setRoot(this);
		fxmlloader.setController(this);
		try {
			fxmlloader.load();
		} catch(IOException ioE) {
			throw new RuntimeException(ioE);
		}
	}
	
}
