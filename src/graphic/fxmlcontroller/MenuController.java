package graphic.fxmlcontroller;

import graphic.animation.SimpleTranslation;
import graphic.bean.RectangleTile;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

public class MenuController extends AbstractController {

	public MenuController() {
	}
	
	@FXML
	private RectangleTile rectangle;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		AbstractMoveAnimation animation = rectangle.getAnimationMoveProperty().getAnimation();
//		animation.setInterpolator(Interpolator.LINEAR);
//		animation.setCycleCount(Timeline.INDEFINITE);
//		animation.setJump(200);
//		animation.setAutoReverse(true);
//		animation.setTile(rectangle);
//		animation.play();
		
//		SimpleTranslation n = (SimpleTranslation) rectangle.getAnimationMoveProperty().getAnimation();
//		n.setTile(rectangle);
//		n.play(KeyCode.RIGHT);
		
		SimpleTranslation tt = new SimpleTranslation(50, Duration.millis(1000));
		tt.setTile(rectangle);
		tt.play(KeyCode.UP);
		
	}
	
}
