package graphic.fxmlcontroller;

import graphic.animation.AbstractMoveAnimation;
import graphic.bean.RectangleTile;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;

public class MenuController extends AbstractController {

	public MenuController() {
	}
	
	@FXML
	private RectangleTile rectangle;
	
	private AbstractMoveAnimation moveAnimation;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		this.moveAnimation = rectangle.getAnimationMoveProperty().getAnimation();
	}
}
