package application;
	
import static graphic.FxmlOverview.*;
import graphic.GameLoop;

import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class GraphicsEngine {
	private Stage primaryStage;
	private GameLoop gameLoop;
	
	public GraphicsEngine(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Mon Application");
		primaryStage.setWidth(800);
		primaryStage.setHeight(800);
		
		loadMenu(); // load the menu fxml
	}
	
	public void setScene(Pane newLayout) {
		Scene scene = new Scene(newLayout);
		primaryStage.setScene(scene);
	}
	
	public void loadMenu() throws IOException {
		setScene(loadFxmlView(IHM));
	}

	public void loadGame() throws IOException {
		setScene(loadFxmlView(GAME));
		
		gameLoop = new GameLoop(getFxmlController());
		gameLoop.startGame();
	}
	
	public void launch() {
		primaryStage.show();
	}
}
