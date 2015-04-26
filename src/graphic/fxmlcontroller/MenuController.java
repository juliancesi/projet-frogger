package graphic.fxmlcontroller;

import graphic.bean.RectangleTile;

import java.io.IOException;

import javafx.fxml.FXML;
import application.GraphicsEngine;

public class MenuController extends AbstractController {

	public MenuController() {
	}
	
	@FXML
	private RectangleTile cube;
	
	@FXML
	protected void playGame() {
		try {
			GraphicsEngine.getInstance().loadGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
