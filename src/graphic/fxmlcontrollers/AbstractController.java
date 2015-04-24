package graphic.fxmlcontrollers;

import java.net.URL;
import java.util.ResourceBundle;

import utils.Utils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
		Utils.parseNodeId(node.getId());
	}
}
