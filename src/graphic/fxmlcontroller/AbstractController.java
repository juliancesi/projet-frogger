package graphic.fxmlcontroller;

import graphic.animation.AbstractMoveAnimation;
import graphic.animation.SimpleTranslation;
import graphic.bean.IAnimationMoveProperty;

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
			bindAnimationProperty(parent);
		}
	}

	@FXML
	protected void bindAnimationProperty(Node node) {
//		if(node instanceof IAnimationMoveProperty) {
//			System.out.println(((IAnimationMoveProperty) node).getAnimationMoveProperty());
//			System.out.println(((IAnimationMoveProperty) node).getMoveAnimation());
//			
//			AbstractMoveAnimation n = ((IAnimationMoveProperty) node).getAnimationMoveProperty().getAnimation();
//			n.setTile(node);
//			((SimpleTranslation) n).play(KeyCode.UP);
//			
//			System.out.println("fin");
//		}
	}
	
}
