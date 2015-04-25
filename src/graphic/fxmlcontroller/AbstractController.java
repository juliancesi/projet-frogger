package graphic.fxmlcontroller;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.MoveAnimation;
import graphic.animation.SimpleTranslation;
import graphic.bean.RectangleTile;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class AbstractController implements Initializable {

	@FXML
	private Pane root;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		readAllNodes(root);
	}

	private void readAllNodes(Node parent) {
		assert parent instanceof Pane;
		if(parent instanceof Pane) {
			for(Node n : ((Pane) parent).getChildren()) {
				readAllNodes(n);
			}
		}
		else {
			bindProperty(parent);
		}
	}

	@FXML
	protected void bindProperty(Node node) {
//		if(node instanceof RectangleTile) {
//			RectangleTile tile = (RectangleTile) node;
//			System.out.println(tile.getCollisionsProperty());
//			
//			System.out.println(tile.getAnimationMoveProperty());
//			
//			MoveAnimation animation = tile.getAnimationMoveProperty();
//			AbstractMoveAnimation animation2 = animation.getAnimation();
//			
//			if(animation2 instanceof SimpleTranslation) {
//				SimpleTranslation animation3 = (SimpleTranslation) animation2;
//				animation3.setTile(tile);
//			
//				animation3.play(KeyCode.UP);
//			}
//		
//		}
	}
	

}
